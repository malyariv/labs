package abstracts;

/**
 * The class {@code ADataNode} is an abstract class providing a structure to store data
 * on HDD and basic operations of data processing as add(T t), remove(Object o), etc.
 * Every object will be written on disk but its hashcode will be stored
 * in a {@code HashContainer} object.
 * This class also implements some methods as iterator(), toArray(), recover(), etc.
 */

import utils.ConfigClass;
import utils.FileUtils;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.*;
import java.util.logging.*;

public abstract class ADataNode<T> implements Iterable<T>{

    /** the number of written objects in file*/
    private int writeSize=50_000;
    /** the expected size in Mb of one file with written objects*/
    private int fileSizeMb=250;
    /** the current object*/
    protected T t;
    /** the object that read/write objects on disk*/
    protected FileUtils utils;
    /** the current number of objects in the data store structure*/
    protected int size=0;
    /** the current number of file for writing next object*/
    protected int writtenFilesIndex=0;
    /** the list of {@code HashContainer} objects */
    protected List<AHashContainer> hash=new ArrayList<>();
    /** the logger to write logs*/
    protected final Logger logger= Logger.getLogger(this.toString());

    protected ADataNode(String folder) {
        try {
            logger.setUseParentHandlers(false);
            File file=new File(folder+"Log/");
            file.mkdir();
            Handler handler = new FileHandler(folder+"Log/log.txt");
            handler.setFormatter(new SimpleFormatter());
            logger.addHandler(handler);
        }catch (IOException e){e.printStackTrace();}
    }

    /**
     * @return number of objects in the structure
     */
    public int size(){
        return size;
    }

    /**
     * @return iterator
     */

    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int count = size();
            private int index = 0;
            @Override
            public boolean hasNext() {
                return index < count;
            }
            @Override
            public T next() {
                if (index < count) {
                    return t=get(index++);
                } else {
                    throw new NoSuchElementException("No such element.");
                }
            }
            @Override
            public void remove() {
                ADataNode.this.remove(--index);
                count=size();
            }
        };
    }

    /**
     * Reads a {@code ConfigClass} object from file and initiates parameters
     * @param filename is file name to read a {@code ConfigClass} object
     * @return a read {@code ConfigClass} object
     */

    public ConfigClass readConfiguration(String filename){
        ConfigClass conf=utils.readConfiguration(filename);
        hash=new ArrayList<>(conf.getHash());
        size=conf.getSize();
        writtenFilesIndex=conf.getWrittenFiles();
        return conf;
    }

    /**
     * Removes all file from working directory
     */
    public void clearDirectory(){
        utils.clearDirectory();
    }

    /**
     * Removes object from a {@code AHashContainer} object.
     * Additionally, it removes file if there is no objects presented in the data structure.
     * It also provides file merge if the total number of objects in them is less than {@code writesize}.
     * @param index - index of element to be removed
     * @param i - index of file and {@code AHashContainer} object which containing the element
     * @return - true if operation results in file remove
     */

    public boolean simplyRemove(int index, int i){
        boolean flag=false;
        hash.get(i).remove(index);
        if (hash.get(i).size() == 0) {
            logger.log(Level.INFO,"file {0}.ser does not contain any elements and was removed. " +
                    "The size is {1}",new Object[]{i,size});
            utils.fileShift(i, writtenFilesIndex);
            hash.remove(i);
            if (i!=0) i--;
            flag=true;
            if (size()==0) {
                newHashContainer();
            }
        }
        else {
            if (i < writtenFilesIndex) {
                if (containerMerge(i)) {
                    logger.log(Level.INFO,"files {0}.ser and {1}.ser were merged in a one containing {2} elements." +
                            "The size is {2}",new Object[]{i,i+1,hash.get(i).size(), size});
                    utils.fileShift(i + 1, writtenFilesIndex);
                    if (i != 0) i--;
                    flag = true;
                }
            }
            else {
                if (i > 0) {
                    if (containerMerge(i - 1)) {
                        logger.log(Level.INFO,"files {0}.ser and {1}.ser were merged in a one." +
                                "The size is {2}",new Object[]{i-1,i,hash.get(i-1).size(),size});
                        utils.fileShift(i, writtenFilesIndex);
                        if (i != 0) i--;
                        flag = true;
                    }
                }
            }
        }
        return flag;
    }


    /**
     * Removes all elements from the data structure.
     */
    public void clear(){
        hash=new ArrayList<>();
        newHashContainer();
        utils.clearDirectory();
        size=0;
        writtenFilesIndex=0;
    }

    /**
     * Writes a {@code ConfigClass} object on disk.
     * @param filename is file name to write a {@code ConfigClass} object
     */
    public void save(String filename){
        ConfigClass conf=saveConfig();
        utils.saveConfiguration(conf, filename);
    }

    /**
     * Saves all runtime parameters into a {@code ConfigClass} object.
     * @return a {@code ConfigClass} object containing runtime parameters
     */
    public ConfigClass saveConfig(){
        ConfigClass conf=new ConfigClass();
        conf.setHash(hash);
        conf.setSize(size);
        conf.setWrittenFiles(writtenFilesIndex);
        return conf;
    }

    /**
     * Returns all elements from the data structure as an array of {@code Object}
     * @return  array of {@code Object}
     */
    public Object[] toArray() {
        Object[] array=new Object[size()];
        int i=0;
        for (T t1:this){
            array[i++]=t1;
        }
        return array;
    }

    public <T1> T1[] toArray(T1[] a) {
        Object[] list=toArray();
        if (a.length<size()) {
            T1[] cache=(T1[]) Array.newInstance(a.getClass().getComponentType(),size());
            int ind=0;
            for (Object el:list) {
                cache[ind++]=(T1)el;
            }
            return cache;
        }
        if (a.length==size()) {
            System.arraycopy(list,0,a,0,size());
            return a;
        }
        System.arraycopy(a,0,a,size()+1,a.length-size()-1);
        a[size()]=null;
        System.arraycopy(list,0,a,0,size());
        return a;
    }

    /**
     * Implements a merge of 2 {@code AHashContainer} objects
     * and files containing corresponding objects
     * @param i index of first {@code HashContainer} object
     * @return true if merge occurs
     */
    public boolean containerMerge(int i){
        if (mergeCondition(i)) {
            fileMerge(i);
            AHashContainer h1= copyDataFrom(hash.get(i), hash.get(i+1));
            hash.set(i, h1);
            hash.remove(i+1);
            return true;
        }
        return false;
    }

    /**
     * Checks the merge condition
     * @param i index of first {@code HashContainer} object
     * @return depending on condition
     */
    public boolean mergeCondition(int i) {
        if (i==writtenFilesIndex) return false;
        double load1=hash.get(i).getLoadFactor();
        double load2=hash.get(i+1).getLoadFactor();
        return load1+load2<1;
    }

    public void fileMerge(int i){
        utils.fileMerge(i,hash.get(i).getIndices(),hash.get(i+1).getIndices());
    }

    /**
     * Calculates the number of elements in file
     * @param fileSize is desirable file length
     * @return the number of elements in file
     */
    public int getWriteSize(int fileSize) {
        int max=fileSizeMb*1024*1024/fileSize;
        int limit=writeSize;
        while (limit>1){
            if (max>limit) {
                logger.log(Level.INFO, "Estimated file size is less {0} Mb, so element number in file is {1}", new Object[]{fileSizeMb,limit});
                return limit;
            }
            limit/=10;
        }
        logger.log(Level.INFO, "Estimated file size is less {0} Mb, so element number in file is {1}", new Object[]{fileSizeMb,1});
        return 1;
    }

    public boolean checkSerialization(T t) {
        for (Class cls: t.getClass().getInterfaces()){
            if (cls== Serializable.class) return true;
        }
        return false;
    }
    public void recover(String from, int p){
        for (int i=0;i<=p;i++){
            int j=1;
            while (true){
                FileUtils u=new FileUtils(from);
                u.setLoger(logger);
                Object ob=u.readObject(i,j++);
                if (ob==null){
                    break;
                }
                add((T)ob);
            }
        }
    }


    public abstract int indexOf(Object o);
    public abstract T get(int index);
    public abstract boolean add(T t);
    public abstract boolean remove(Object o);
    public abstract void remove(int index);
    public abstract void addElement(T t, int ind);
    public abstract void newHashContainer();
    public abstract AHashContainer copyDataFrom(AHashContainer h1, AHashContainer h2);
}
