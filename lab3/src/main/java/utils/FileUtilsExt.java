package utils;

import java.io.File;
import java.util.Arrays;

public class FileUtilsExt<T> extends FileUtils<T> {

    private final String adr;
    private Object[] cache;
    private int indI=-1,indJ=-1;

    public FileUtilsExt(String folder) {
        super(folder);
        adr=folder;
    }

    public Object readFromBlock(int i, int j, int elementIndex) {
        if (indI!=i||indJ!=j) {
            cache = (Object[]) readObject(i, j);
            indI=i;
            indJ=j;
        }
        return cache[elementIndex];
    }
    public void fileMerge(int fileIndex, int[] indices1, int[] indices2, int blocksize){
        Object[] localCache=new Object[blocksize];
        String buffer=adr+"bufferMerge"+".ser";
        String buffertxt=adr+"bufferMerge"+".txt";
        int blockIndex;
        int elementIndex;
        int currentIndex=0;
        for (int ind:indices1){
            blockIndex=ind/blocksize;
            elementIndex=ind%blocksize;
            T t= (T) readFromBlock(fileIndex,blockIndex+1,elementIndex);
            if (currentIndex==500) {
                super.write(localCache, buffer, buffertxt);
                currentIndex-=500;
            }
            localCache[currentIndex++]=t;
        }
        for (int ind:indices2){
            blockIndex=ind/blocksize;
            elementIndex=ind%blocksize;
            T t= (T) readFromBlock(fileIndex,blockIndex+1,elementIndex);
            if (currentIndex==500) {
                super.write(localCache, buffer, buffertxt);
                currentIndex-=500;
            }
            localCache[currentIndex++]=t;
        }
        super.write(Arrays.copyOf(localCache,currentIndex), buffer, buffertxt);
        new File(adr+fileIndex+".ser").delete();
        new File(adr+fileIndex+".txt").delete();
        new File(buffer).renameTo(new File(adr+fileIndex+".ser"));
        new File(buffertxt).renameTo(new File(adr+fileIndex+".txt"));
    }
}
