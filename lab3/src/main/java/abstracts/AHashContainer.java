package abstracts;

/**
 * The class {@code AHashContainer} is an abstract class
 * containing an array of object hashcode values.
 * This array is a reflection of the file containing real serialized objects.
 */
public abstract class AHashContainer{
    /** the array of hashcode values*/
    protected int[] hash;
    /** the number of hashcode values in array including ones for objects which were removed */
    protected int maxSize=0;
    /** the number of hashcode values in array without ones for objects which were removed */
    protected int currentSize;

    public int size() {
        return currentSize;
    }

    public int realSize(){ return maxSize;}

    public boolean isFull() {
        return maxSize==hash.length;
    }

    public double getLoadFactor() {
        double d1=(currentSize+0.0)/maxSize;
        double d2=(0.0+currentSize)/hash.length;
        return Math.min(d1, d2);
    }

    public void add(int val, int ind){}
    public void increment(int from){}
    public void decrement(int from){}
    public void setIndexOfLastElement(int index){}

    public abstract void add(int i);
    public abstract int[] toArray();
    public abstract void remove(int index);
    public abstract int getIndex(int value);
    public abstract int getRealIndex(int ind);
    public abstract int[] getIndices(int v);
    public abstract int[] getIndices();
    public abstract int[] getHashValues();
}
