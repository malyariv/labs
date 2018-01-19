package dbSet;

import abstracts.DataNode;
import abstracts.HashContainer;
import abstracts.State;
import utils.*;

import java.util.ArrayList;
import java.util.List;

public class SetDataNode<T> extends DataNode<T> {
    private int writeSize;
    private List<HashContainer> hash=new ArrayList<>();
    private int size=0, writtenFilesIndex=0;
    private T t=null;
    private FileUtils utils;
    private State<T> currentState=new InitialState();
    private State<T> after=null;
    private int[] indcs=new int[2];

    public SetDataNode(String folder){
        utils=new FileUtils(folder);
    }

    public int size(){
        return size;
    }

    public void readConfiguration(String filename){
        ConfigClass conf=utils.readConfiguration(filename);
        hash=new ArrayList<>(conf.getHash());
        size=conf.getSize();
        writtenFilesIndex=conf.getWrittenFiles();
    }

    public void clearDirectory() {
        utils.clearDirectory();
    }

    public int indexOf(Object o){
        indcs[0]=-1;
        int h=o.hashCode();
        int indx=-1;
        for(int i=0;i<=writtenFilesIndex;i++){
            indx=hash.get(i).getIndex(h);
            if (indx==-1) continue;
            T t = (T) utils.readObject(i, indx + 1);
            if (t.equals(o)) {
                indcs[0]=i;
                indcs[1]=indx+1;
                return indx;
            }
        }
        return indx;
    }

    public int[] getIndices(Object o){
        indexOf(o);
        return indcs.clone();
    }

    public T get(int index){
        return (T)utils.readObject(calculateFileIndex(index), calculateElementIndex(index)+1);
    }
    public T get(int[] index){
        return (T)utils.readObject(index[0],index[1]);
    }

    public boolean add(T t){
        return currentState.add(t);
    }

    public boolean remove(Object o){
        int h=o.hashCode();
        int indx;
        for(int i=0;i<=writtenFilesIndex;i++){
            indx=hash.get(i).getIndex(h);
            if (indx==-1) continue;
            T t = (T) utils.readObject(i, indx + 1);
            if (t.equals(o)) {
                size--;
                if (simplyRemove(indx, i, hash, writtenFilesIndex, utils)) {
                    if (writtenFilesIndex>0) {
                        writtenFilesIndex--;
                    }
                }

                return true;
            }
        }
        return false;
    }
    public void remove(int index){
        int i=calculateFileIndex(index);
        int j=calculateElementIndex(index);
        size--;
        if (simplyRemove(j, i, hash, writtenFilesIndex, utils)) {
            writtenFilesIndex--;
        }
    }
    public void remove(int[] ind){
        size--;
        if (simplyRemove(ind[1]-1, ind[0], hash, writtenFilesIndex, utils)) {
            writtenFilesIndex--;
        }
    }

    public void clear(){
        hash=new ArrayList<>();
        newHashContainer();
        utils.clearDirectory();
        size=0;
        writtenFilesIndex=0;
    }

    @Override
    public HashContainer copyDataFrom(HashContainer h1, HashContainer h2) {
        SetHashContainer h=new SetHashContainer(writeSize);
        for (int val:h1.getHashValues()){
            h.add(val);
        }
        for (int val:h2.getHashValues()){
            h.add(val);
        }
        return h;
    }
    public void fileMerge(int i){
        utils.fileMerge(i,hash.get(i).getIndices(),hash.get(i+1).getIndices());
    }

    @Override
    public ConfigClass saveConfig() {
        ConfigClass conf=new ConfigClass();
        conf.setHash(hash);
        conf.setSize(size);
        conf.setWrittenFiles(writtenFilesIndex);
        return conf;
    }

    public void save(String filename){
        ConfigClass conf=saveConfig();
        utils.saveConfiguration(conf, filename);
    }
    @Override
    public void addElement(T t, int ind) {
        utils.write(t,ind);
        hash.get(ind).add(t.hashCode());
        size++;
    }

    @Override
    public void newHashContainer() {
        hash.add(new SetHashContainer(writeSize));

    }

    public void setState(States s) {
        switch (s){
            case INITIAL: currentState=new InitialState<>(); break;
            case SET: after=new SetState<>(); break;
            case LIST: after=new ListState<>(); break;
        }
    }
    public void setCurrentState(States s){
        switch (s){
            case SET: currentState=new SetState<>();break;
            case LIST: currentState=new ListState<>();break;
        }
    }

    private class InitialState<T1> extends State<T1>{
        @Override
        public boolean add(T1 data) {
            if (checkSerialization((T)data)) {
                t = (T) data;
                int fileSize=utils.getFileSize(data);
                writeSize=getWriteSize(fileSize);
                newHashContainer();
                currentState=after;
            }
            else {
                System.err.println("Objects cannot be serialized for storage");
                return false;
            }
            return currentState.add((T)data);
        }
    }

    private class SetState<T2> extends State<T2>{
        State<T2> list=new ListState<>();
        @Override
        public boolean add(T2 t) {
            if(indexOf(t)==-1) {
                return list.add(t);
            }
            return false;
        }
    }

    private class ListState<T3> extends State<T3>{
        @Override
        public boolean add(T3 t) {
            if (hash.get(writtenFilesIndex).isFull()){
                newHashContainer();
                writtenFilesIndex++;
            }
            addElement((T)t, writtenFilesIndex);
            return true;
        }
    }


    private int calculateFileIndex(int i) {
        for (int j=0;j<=writtenFilesIndex;j++){
            if (i-hash.get(j).size()<0) return j;
            i-=hash.get(j).size();
        }
        return writtenFilesIndex;
    }

    private int calculateElementIndex(int i) {
        for (int j=0;j<=writtenFilesIndex;j++){
            if (i-hash.get(j).size()<0) {
                return hash.get(j).getRealIndex(i);
            }
            i-=hash.get(j).size();
        }
        return hash.get(writtenFilesIndex).getRealIndex(i);
    }




}
