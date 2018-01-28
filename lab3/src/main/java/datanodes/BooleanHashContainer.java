package datanodes;
/**
 * The class {@code BooleanHashContainer} extends {@code AHashContainer}
 * adding a boolean array coupled with the hashcode array. A boolean value indicates
 * the status of object: presented or to be removed.
 * The class implements abstract methods of the parent class as add(int h), remove(int index), etc.
 */

import abstracts.AHashContainer;

import java.io.Serializable;
import java.util.Arrays;

public class BooleanHashContainer extends AHashContainer implements Serializable  {

    private boolean[] booleans;

    public BooleanHashContainer(int size){
        booleans=new boolean[size];
        hash =new int[size];
    }

    public void add(int i){
        hash[maxSize]=i;
        booleans[maxSize++]=true;
        currentSize++;
    }

    public int[] toArray(){
        int[] out=new int[currentSize];
        int indx=0;
        for (int i = 0; i< hash.length; i++){
            if (booleans[i]) out[indx++]= hash[i];
        }
        return out;
    }

    public int[] getIndices(){
        int[] out=new int[currentSize];
        int indx=0;
        for (int i = 0; i< hash.length; i++){
            if (booleans[i]) out[indx++]=i;
        }

        return out;
    }

    @Override
    public int[] getHashValues() {
        int[] out=new int[currentSize];
        int indx=0;
        for (int i = 0; i< hash.length; i++){
            if (booleans[i]) out[indx++]= hash[i];
        }

        return out;
    }

    @Override
    public int[] getIndices(int v) {
        return new int[0];
    }

    public void remove(int index){
        if (booleans[index]) {
            booleans[index] = false;
            currentSize--;
            return;
        }
        throw new IllegalArgumentException();
    }
    public int getIndex(int value){
        int ind=-1;
        for (int i=0;i<maxSize;i++){
            if (hash[i]==value&&booleans[i]) return i;
        }
        return -1;
    }

    public int getRealIndex(int ind){
        int index=-1;
        for (int i=0;i<maxSize;i++){
            if (booleans[i]) index++;
            if (booleans[i]&&ind==index) return i;
        }
        throw new IllegalArgumentException("getRealIndex");
    }

    @Override
    public String toString() {
        return Arrays.toString(toArray());
    }

}
