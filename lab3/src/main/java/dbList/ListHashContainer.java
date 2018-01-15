package dbList;

import abstracts.HashContainer;

import java.io.Serializable;
import java.util.Arrays;

public class ListHashContainer extends HashContainer implements Serializable {

    private int[] hash;
    private int[] realIndex;
    private int maxSize=0, currentSize=0, currentIndex=0;

    public ListHashContainer(int size){
        realIndex=new int[size];
        hash=new int[size];
        Arrays.fill(realIndex,-1);
    }
    public ListHashContainer(int size, int start){
        this(size);
        currentIndex=start;
    }


    @Override
    public int size() {
        return currentSize;
    }

    public int getMaxSize(){ return maxSize;}

    @Override
    public boolean isFull() {
        return maxSize==hash.length;
    }

    @Override
    public void add(int i) {
        hash[maxSize]=i;
        realIndex[maxSize++]=currentIndex++;
        currentSize++;
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

    @Override
    public double getLoadFactor() {
        double d1=(currentSize+0.0)/maxSize;
        double d2=(0.0+currentSize)/hash.length;
        return Math.min(d1, d2);
    }

//    public int getPosition(int ind){
//        for (int i=0;i<maxSize;i++){
//            if (realIndex[i]==ind) return i;
//        }
//        return -1;
//    }

    public void setIndexOfLastElement(int index){
        currentIndex--;
        realIndex[maxSize-1]=index;
    }
}
