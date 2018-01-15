package utils;

import dbSet.SetHashContainer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ConfigClass<T> implements Serializable{
    private List<SetHashContainer> hash;
    private int size;
    private int writtenFiles;
    private T[] cache;

    public List<SetHashContainer> getHash() {
        return new ArrayList<>(hash);
    }

    public void setHash(List<SetHashContainer> hash) {
        this.hash = new ArrayList<>(hash);
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getWrittenFiles() {
        return writtenFiles;
    }

    public void setWrittenFiles(int writtenFiles) {
        this.writtenFiles = writtenFiles;
    }

    public T[] getCache() {
        return cache;
    }

    public void setCache(T[] cache) {
        this.cache = cache;
    }
}
