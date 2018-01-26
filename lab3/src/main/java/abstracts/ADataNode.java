package abstracts;

import utils.ConfigClass;
import utils.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;
import java.util.logging.*;

public abstract class ADataNode<T> implements Iterable<T>{

    private int writeSize=50_000, fileSizeMb=250;

    protected T t;
    protected FileUtils utils;
    protected int size=0, writtenFilesIndex=0;
    protected List<AHashContainer> hash=new ArrayList<>();

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


    public int size(){
        return size;
    }

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

    public ConfigClass readConfiguration(String filename){
        ConfigClass conf=utils.readConfiguration(filename);
        hash=new ArrayList<>(conf.getHash());
        size=conf.getSize();
        writtenFilesIndex=conf.getWrittenFiles();
        return conf;
    }

    public void clearDirectory(){
        utils.clearDirectory();
    }

    public boolean simplyRemove(int index, int i){
        boolean flag=false;
        hash.get(i).remove(index);
        if (hash.get(i).size() == 0) {
            logger.log(Level.INFO,"file {0}.ser does not contain any elements and was removed",i);
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
                if (containerMerge(hash, i)) {
                    logger.log(Level.INFO,"files {0}.ser and {1}.ser were merged in a one containing {2} elements",new Object[]{i,i+1,hash.get(i).size()});
                    utils.fileShift(i + 1, writtenFilesIndex);
                    if (i != 0) i--;
                    flag = true;
                }
            }
            else {
                if (i > 0) {
                    if (containerMerge(hash, i - 1)) {
                        logger.log(Level.INFO,"files {0}.ser and {1}.ser were merged in a one",new Object[]{i-1,i,hash.get(i-1).size()});
                        utils.fileShift(i, writtenFilesIndex);
                        if (i != 0) i--;
                        flag = true;
                    }
                }
            }
        }
        return flag;
    }



    public void clear(){
        hash=new ArrayList<>();
        newHashContainer();
        utils.clearDirectory();
        size=0;
        writtenFilesIndex=0;
    }

    public void save(String filename){
        ConfigClass conf=saveConfig();
        utils.saveConfiguration(conf, filename);
    }


    public ConfigClass saveConfig(){
        ConfigClass conf=new ConfigClass();
        conf.setHash(hash);
        conf.setSize(size);
        conf.setWrittenFiles(writtenFilesIndex);
        return conf;
    }
    public Object[] toArray() {
        Object[] array=new Object[size()];
        int i=0;
        for (T t1:this){
            array[i++]=t1;
        }
        return array;
    }


    public boolean containerMerge(List<AHashContainer> hash, int i){
        if (mergeCondition(i)) {
            fileMerge(i);
            AHashContainer h1= copyDataFrom(hash.get(i), hash.get(i+1));
            hash.set(i, h1);
            hash.remove(i+1);
            return true;
        }
        return false;
    }

    public boolean mergeCondition(int i) {
        if (i==writtenFilesIndex) return false;
        double load1=hash.get(i).getLoadFactor();
        double load2=hash.get(i+1).getLoadFactor();
        return load1+load2<1;
    }

    public abstract AHashContainer copyDataFrom(AHashContainer h1, AHashContainer h2);

    public void fileMerge(int i){
        utils.fileMerge(i,hash.get(i).getIndices(),hash.get(i+1).getIndices());
    }


    public int getWriteSize(int fileSize) {
//        return 9;
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


}
