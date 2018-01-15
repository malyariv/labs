package abstracts;

import utils.FileUtils;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public abstract class DataNode<T> implements Iterable<T>{
    private T t;
    private int writeSize=50_000, fileSizeMb=250;
    private FileUtils utils;

    public abstract int size();
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
                DataNode.this.remove(t);
                index--;
                count=size();
            }
        };
    }
    public abstract void readConfiguration();
    public abstract void clearDirectory();
    public abstract int indexOf(Object o);
    public abstract T get(int index);
    public boolean add(T t){
        this.t=t;
        return true;
    }
    public abstract boolean remove(Object o);
    public abstract void remove(int index);

    public boolean simplyRemove(int index, int i, List<HashContainer> hash, int writtenFilesIndex, FileUtils utils){
        boolean flag=false;
        hash.get(i).remove(index);
        if (hash.get(i).size() == 0) {
            utils.fileShift(i, writtenFilesIndex--);
            hash.remove(i);
            if (i!=0) i--;
            flag=true;
        }

        if (i < writtenFilesIndex) {
            if (containerMerge(hash,i)) {

                utils.fileShift(i + 1, writtenFilesIndex--);
                if (i!=0) i--;
                flag=true;
            }
        }
        if (i > 0) {
            if (containerMerge(hash,i-1)) {
//                utils.fileMerge(i-1,hash.get(i-1).getIndices(),hash.get(i).getIndices());
                utils.fileShift(i, writtenFilesIndex--);
                if (i!=0) i--;
                flag=true;
            }
        }
        return flag;
    }

    public abstract void clear();
    public abstract void save();
    public Object[] toArray() {
        Object[] array=new Object[size()];
        int i=0;
        for (T t1:this){
            array[i++]=t1;
        }
        return array;
    }


    public boolean containerMerge(List<HashContainer> hash, int i){
        double load1=hash.get(i).getLoadFactor();
        double load2=hash.get(i+1).getLoadFactor();
        if(load1+load2<1) {
            fileMerge(i);
            HashContainer h1= copyDataFrom(hash.get(i), hash.get(i+1));
            hash.set(i, h1);
            hash.remove(i+1);
            return true;
        }
        return false;
    }
    public abstract HashContainer copyDataFrom(HashContainer h1, HashContainer h2);
    public abstract void fileMerge(int i);


    public int getWriteSize(int fileSize) {
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

    public abstract void addElement(T t, int ind);
    public abstract void newHashContainer();
}
