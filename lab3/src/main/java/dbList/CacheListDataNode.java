package dbList;

import utils.ConfigClass;
import utils.FileUtilsExt;
import abstracts.State;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CacheListDataNode<T> extends ListDataNode<T> {
    private final double blockSizeMb=2;
    private int blocksize=1, blockCounter=0;
    private State<T> currentState=new InitialState<>();
    private T[] cache=null;
    private int cacheIndex=0;

    public CacheListDataNode(String folder) {
        super(folder);
        utils=new FileUtilsExt(folder);
    }



    @Override
    public ConfigClass readConfiguration(String filename) {
        ConfigClass conf =super.readConfiguration(filename);
        cache=(T[])conf.getCache();
        blocksize=conf.getBlocksize();
        return conf;
    }
    @Override
    public boolean add(T t) {
        return currentState.add(t);
    }

    private boolean superAdd(T t){
        return super.add(t);
    }

    public void emptyCondition(){
        super.emptyCondition();
        blockCounter=0;
    }

    public T readObject(int i, int j){
        int blockIndex=j/blocksize;
        int elementIndex=j%blocksize;
        T t;
        if (i==writtenFilesIndex &&blockCounter==blockIndex) {
            t=cache[elementIndex];
        }
        else {
            FileUtilsExt<T> u=(FileUtilsExt<T>) utils;
            t=(T)u.readFromBlock(i, blockIndex+1, elementIndex);
        }
        return t;
    }
    @Override
    public void clear() {
        super.clear();
        blockCounter=0;
    }
    @Override
    public void addElement(T t2, int ind) {
        int siz=hash.get(ind).realSize();
        if (siz!=0 && siz%blocksize==0){
            writeObject(ind, cache);
//            Arrays.fill(cache, null);
            blockCounter++;
        }
        cacheIndex=siz%blocksize;
        cache[cacheIndex]= t2;
        super.addElement(t2, ind);
    }

    @Override
    public void newHashContainer() {
        super.newHashContainer();
//        Arrays.fill(cache, null);
        blockCounter=0;
    }

    public void writeObject(int writtenFilesIndex, T... data){
        if (data.length==1) {
            return;
        }
        utils.writeBlock(cache, writtenFilesIndex);
    }

    public void fileMerge(int i){
        FileUtilsExt<T> u=(FileUtilsExt<T>) utils;
        int res=u.fileMerge(i,hash.get(i).getIndices(),hash.get(i+1).getIndices(), blocksize);
        if (res!=0) {
            blockCounter = res;
        }
    }

    @Override
    public ConfigClass saveConfig() {
        ConfigClass conf=super.saveConfig();
        conf.setCache(cache);
        conf.setBlocksize(blocksize);
        return conf;
    }

    @Override
    public boolean mergeCondition(int i) {
        if (super.mergeCondition(i)) {
            int sum = hash.get(i).size() + hash.get(i + 1).size();
            if (i+1==writtenFilesIndex) {
                sum-=cacheIndex+1;
                if (sum==hash.get(i).size()) {
                    return false;
                }
                return sum % blocksize == 0;
            }
            return sum%blocksize==0;
        }
        return false;
    }


    private class InitialState<T1> extends State<T1>{
        @Override
        public boolean add(T1 data) {
            int fileSize=utils.getFileSize(data);
            blocksize=getBlockSize(fileSize);

//            blocksize=3;

            System.out.println("blockSize="+blocksize);
            t=(T) data;
            currentState= new ListState<>();
            cache=(T[]) Array.newInstance(t.getClass(), blocksize);
            return currentState.add((T)data);
        }

        private int getBlockSize(int fileSize) {
            int max=(int)(blockSizeMb*1024*1024/fileSize);
            int limit=getWriteSize(fileSize);
            while (limit>1){
                if (max>limit) return limit;
                limit/=10;
            }
            return 1;
        }
    }

    private class ListState<T2> extends State<T2>{
        public boolean add(T2 t2) {
            return superAdd((T)t2);
        }

    }
}
