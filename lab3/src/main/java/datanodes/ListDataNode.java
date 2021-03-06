package datanodes;
/**
 * The class {@code ListDataNode} extends {@code ADataNode} .
 * It uses {@code IndexedHashContainer} to work with objects.
 */

import abstracts.ADataNode;
import abstracts.AHashContainer;
import abstracts.AState;
import utils.*;

import java.util.function.Predicate;

public class ListDataNode<T> extends ADataNode<T> {
    private int writeSize=0;
    private AState<T> currentState=new InitialState();

    public ListDataNode(String folder){
        super(folder);
        utils=new FileUtils(folder);
    }

    @Override
    public int indexOf(Object o) {
        return indexOf(o, t->t>0);
    }

    public int indexOf(Object o, Predicate<Integer> p){
        int h=o.hashCode();
        int[] currentIndex=null;
        int index=-1;
        for(int i=0;i<=writtenFilesIndex;i++){
            currentIndex=hash.get(i).getIndices(h);
            if (currentIndex!=null) {
                for (int j: currentIndex) {
                    if (index != -1 && p.test(hash.get(i).getRealIndex(j) - index)) {
                        continue;
                    }
                    T t=readObject(i,j);
                    if (t.equals(o)) {
                        index = hash.get(i).getRealIndex(j);
                    }
                }
            }
        }
        return index;
    }
    public T readObject(int i, int j){
        T t=(T) utils.readObject(i, j + 1);
        return t;
    }

    @Override
    public T get(int index) {
        int j=-1;
        for (int i=0;i<=writtenFilesIndex;i++){
            j=hash.get(i).getIndex(index);
            if (j!=-1) {
                return readObject(i,j);
            }
        }
        return null;
    }

    @Override
    public boolean add(T t) {
        return currentState.add(t);
    }

    @Override
    public boolean remove(Object o) {
        int index=indexOf(o);
        if (index==-1) return false;
        remove(index);
        return true;
    }

    @Override
    public void remove(int index) {
        justRemove(index);
        if (size==0){
            emptyCondition();
        }
        for (int i=0;i<=writtenFilesIndex;i++){
            hash.get(i).decrement(index);
        }
    }
    public void justRemove(int index){
        for (int i=0;i<=writtenFilesIndex;i++){
            int j=hash.get(i).getIndex(index);
            if (j==-1){
                continue;
            }
            else {
                size--;
                if (simplyRemove(j, i)) {
                    if (writtenFilesIndex>0) {
                        writtenFilesIndex--;
                    }
                }
                break;
            }
        }
    }

    public void emptyCondition(){
        hash.add(new IndexedHashContainer(writeSize));
        writtenFilesIndex=0;
    }

    public T set(int index, T element){
        T t=get(index);
        if (t.equals(element)) return t;
        justRemove(index);
        currentState.add(element);
        hash.get(writtenFilesIndex).setIndexOfLastElement(index);
        return t;
    }
    public void insert(int index, T element){
        for (int i=0;i<=writtenFilesIndex;i++){
            hash.get(i).increment(index);
        }
        currentState.add(element);
        hash.get(writtenFilesIndex).setIndexOfLastElement(index);
    }


    @Override
    public AHashContainer copyDataFrom(AHashContainer h1, AHashContainer h2) {
        IndexedHashContainer h=new IndexedHashContainer(writeSize);
        IndexedHashContainer lhc=(IndexedHashContainer) h1;
        int[] hashVal=h1.getHashValues();
        int[] ind=lhc.getRealIndices();
        for (int i=0;i<ind.length;i++){
            h.add(hashVal[i],ind[i]);
        }
        lhc=(IndexedHashContainer) h2;
        hashVal=h2.getHashValues();
        ind=lhc.getRealIndices();
        for (int i=0;i<ind.length;i++){
            h.add(hashVal[i],ind[i]);
        }
        return h;
    }


    @Override
    public void addElement(T t, int ind) {
        hash.get(ind).add(t.hashCode(),size++);
    }

    @Override
    public void newHashContainer() {
        hash.add(new IndexedHashContainer(writeSize));
    }

    public void writeObject(int writtenFilesIndex, T... data){
        if (data.length==0) return;
        utils.write(data[0],writtenFilesIndex);
    }


    private class InitialState<T1> extends AState<T1> {
        @Override
        public boolean add(T1 data) {
            if (checkSerialization((T) data)) {
                t = (T) data;
                int fileSize = utils.getFileSize(data);
                writeSize = getWriteSize(fileSize);
                hash.add(new IndexedHashContainer(writeSize));
                currentState = new ListState<>();
            } else {
                System.err.println("Objects cannot be serialized for storage");
                return false;
            }
            return currentState.add((T) data);
        }
    }

    private class ListState<T2> extends AState<T2> {
        @Override
        public boolean add(T2 t) {
            ListDataNode.this.t=(T)t;
            if (hash.get(writtenFilesIndex).isFull()){
                writeObject(writtenFilesIndex);
                newHashContainer();
                writtenFilesIndex++;
            }
            addElement((T)t, writtenFilesIndex);
            writeObject(writtenFilesIndex, ListDataNode.this.t);
            return true;
        }
    }

}
