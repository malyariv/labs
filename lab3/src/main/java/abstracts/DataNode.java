package abstracts;

import utils.ConfigClass;
import utils.FileUtils;

import java.io.Serializable;
import java.util.*;

public abstract class DataNode<T> implements Iterable<T>{

    private int writeSize=50_000, fileSizeMb=250;

    protected T t;
    protected FileUtils utils;
    protected int size=0, writtenFilesIndex=0;
    protected List<HashContainer> hash=new ArrayList<>();


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
                DataNode.this.remove(--index);
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
                    utils.fileShift(i + 1, writtenFilesIndex);
                    if (i != 0) i--;
                    flag = true;
                }
            }
            else {
                if (i > 0) {
                    if (containerMerge(hash, i - 1)) {
//                utils.fileMerge(i-1,hash.get(i-1).getIndices(),hash.get(i).getIndices());
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


    public boolean containerMerge(List<HashContainer> hash, int i){
        if (mergeCondition(i)) {
            fileMerge(i);
            HashContainer h1= copyDataFrom(hash.get(i), hash.get(i+1));
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

    public abstract HashContainer copyDataFrom(HashContainer h1, HashContainer h2);

    public void fileMerge(int i){
        utils.fileMerge(i,hash.get(i).getIndices(),hash.get(i+1).getIndices());
    }


    public int getWriteSize(int fileSize) {
//        return 9;
        int max=fileSizeMb*1024*1024/fileSize;
        int limit=writeSize;
        while (limit>1){
            if (max>limit) return limit;
            limit/=10;
        }
        return 1;
    }

    public boolean checkSerialization(T t) {
        for (Class cls: t.getClass().getInterfaces()){
            if (cls== Serializable.class) return true;
        }
        return false;
    }


    public abstract int indexOf(Object o);
    public abstract T get(int index);
    public abstract boolean add(T t);
    public abstract boolean remove(Object o);
    public abstract void remove(int index);
    public abstract void addElement(T t, int ind);
    public abstract void newHashContainer();

}
