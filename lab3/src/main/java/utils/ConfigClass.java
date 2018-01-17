package utils;

import abstracts.HashContainer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ConfigClass<T> implements Serializable{
    private List<HashContainer> hash;
    private int size;
    private int writtenFiles;
    private int blocksize;
    private T[] cache;

    public List<HashContainer> getHash() {
        return new ArrayList<>(hash);
    }

    public void setHash(List<HashContainer> hash) {
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

    public int getBlocksize() {
        return blocksize;
    }

    public void setBlocksize(int blocksize) {
        this.blocksize = blocksize;
    }
}
