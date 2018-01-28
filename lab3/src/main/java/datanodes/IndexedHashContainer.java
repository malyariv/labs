package datanodes;
/**
 * The class {@code IndexedHashContainer} extends {@code AHashContainer}
 * adding an int array coupled with the hashcode array. An int value indicates
 * the index of object in collection. Value of -1 is a default value or indicates
 * that object is removed. The class implements abstract methods of the parent class
 * as add(int h), remove(int index), etc.
 */
import abstracts.AHashContainer;
import java.io.Serializable;
import java.util.Arrays;

public class IndexedHashContainer extends AHashContainer implements Serializable {

    private int[] realIndex;

    public IndexedHashContainer(int size){
        realIndex=new int[size];
        hash=new int[size];
        Arrays.fill(realIndex,-1);
    }

    @Override
    public void add(int i) {
    }

    @Override
    public void add(int val, int ind){
        hash[maxSize]=val;
        realIndex[maxSize++]=ind;
        currentSize++;
    }

    @Override
    public int[] toArray() {
        int[] out=new int[currentSize];
        int indx=0;
        for (int i=0;i<hash.length;i++){
            if (realIndex[i]!=-1) out[indx++]=hash[i];
        }
        return out;
    }

    @Override
    public int[] getIndices(int value) {
        int[] indices=new int[hash.length];
        int counter=0;
        for (int i=0;i<maxSize;i++){
            if (hash[i]==value&&realIndex[i]!=-1) {
                indices[counter]=i;
                counter++;
            }
        }
        if (counter==0) return null;
        int[] out=Arrays.copyOf(indices,counter);
        return out;
    }

    @Override
    public int[] getIndices() {
        int[] out=new int[currentSize];
        int indx=0;
        for (int i=0;i<hash.length;i++){
            if (realIndex[i]!=-1) out[indx++]=i;
        }
        return out;
    }

    public int[] getRealIndices() {
        int[] out=new int[currentSize];
        int indx=0;
        for (int i=0;i<hash.length;i++){
            if (realIndex[i]!=-1) out[indx++]=realIndex[i];
        }
        return out;
    }

    @Override
    public int[] getHashValues() {
        int[] out=new int[currentSize];
        int indx=0;
        for (int i=0;i<hash.length;i++){
            if (realIndex[i]!=-1) out[indx++]=hash[i];
        }
        return out;
    }

    @Override
    public void remove(int index) {
        if (realIndex[index]!=-1) {
            realIndex[index] = -1;
            currentSize--;
            return;
        }
        throw new IllegalArgumentException();
    }
    @Override
    public void decrement(int from){
        for (int i=0;i<maxSize;i++){
            if (realIndex[i]>from) {
                realIndex[i]--;
            }
        }
    }
    @Override
    public void increment(int from){
        for (int i=0;i<maxSize;i++){
            if (realIndex[i]>=from) {
                realIndex[i]++;
            }
        }
    }

    @Override
    public int getIndex(int ind) {
        for (int i=0;i<maxSize;i++){
            if (realIndex[i]==ind) return i;
        }
        return -1;
    }

    @Override
    public int getRealIndex(int ind) {
        return realIndex[ind];
    }

    public void setIndexOfLastElement(int index){
        realIndex[maxSize-1]=index;
    }
}
