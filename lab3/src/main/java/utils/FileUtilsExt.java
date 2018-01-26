package utils;

import java.io.File;

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

    public int fileMerge(int fileIndex, int[] indices1, int[] indices2, int blocksize){
        int shift=0;
        boolean flag=false;
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
            localCache[currentIndex++]=t;
            if (currentIndex==blocksize) {
                super.write(localCache, buffer, buffertxt);
                shift++;
                currentIndex-=blocksize;
            }
        }
        File file=new File(adr+(fileIndex+1)+".ser");
        if (file.exists()) {
            for (int ind : indices2) {
                blockIndex = ind / blocksize;
                elementIndex = ind % blocksize;
                T t=null;
                try {
                    t = (T) readFromBlock(fileIndex + 1, blockIndex + 1, elementIndex);
                }catch (Exception e){
                    flag=true;
                    break;
                }
                localCache[currentIndex++] = t;
                if (currentIndex == blocksize) {
                    super.write(localCache, buffer, buffertxt);
                    shift++;
                    currentIndex -= blocksize;
                }
            }
        }
        else {
            flag=true;
        }
        new File(adr+fileIndex+".ser").delete();
        new File(adr+fileIndex+".txt").delete();
        new File(buffer).renameTo(new File(adr+fileIndex+".ser"));
        new File(buffertxt).renameTo(new File(adr+fileIndex+".txt"));
        if (flag) return shift;
        return 0;
    }
}
