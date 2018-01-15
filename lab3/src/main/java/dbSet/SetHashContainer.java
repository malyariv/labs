package dbSet;

import abstracts.HashContainer;

import java.io.Serializable;
import java.util.Arrays;

public class SetHashContainer extends HashContainer implements Serializable  {

    private int[] array;
    private boolean[] booleans;
    private int maxSize=0, currentSize;

    public SetHashContainer(int size){
        booleans=new boolean[size];
        array=new int[size];
    }

    public int size(){
        return currentSize;
    }

    public boolean isFull(){
        return maxSize==array.length;
    }

    public void add(int i){
        array[maxSize]=i;
        booleans[maxSize++]=true;
        currentSize++;
    }



    public int[] toArray(){
        int[] out=new int[currentSize];
        int indx=0;
        for (int i=0;i<array.length;i++){
            if (booleans[i]) out[indx++]=array[i];
        }
        return out;
    }
    public int[] getIndices(){
        int[] out=new int[currentSize];
        int indx=0;
        for (int i=0;i<array.length;i++){
            if (booleans[i]) out[indx++]=i;
        }

        return out;
    }

    @Override
    public int[] getHashValues() {
        int[] out=new int[currentSize];
        int indx=0;
        for (int i=0;i<array.length;i++){
            if (booleans[i]) out[indx++]=array[i];
        }

        return out;
    }

    @Override
    public int[] getIndices(int v) {
        return new int[0];
    }

    //    public void remove(int element){
//        for (int i=0;i<maxSize;i++){
//            if (array[i]==element&&booleans[i]){
//                booleans[i]=false;
//                currentSize--;
//            }
//        }
//    }
    public void remove(int index){
        if (booleans[index]) {
            booleans[index] = false;
            currentSize--;
            return;
        }
        throw new IllegalArgumentException();
    }
    public int getIndex(int value){
        for (int i=0;i<maxSize;i++){
            if (array[i]==value&&booleans[i]) return i;
        }
        return -1;
    }

    public int getRealIndex(int ind){
        int index=0;
        for (int i=0;i<maxSize;i++){
            if (booleans[i]&&ind==index) return i;
            if (booleans[i]) index++;
        }
        throw new IllegalArgumentException();
    }

    public double getLoadFactor(){
        double d1=(currentSize+0.0)/maxSize;
        double d2=(0.0+currentSize)/array.length;
        return Math.min(d1, d2);
    }



    @Override
    public String toString() {
        return Arrays.toString(toArray());
    }



}
