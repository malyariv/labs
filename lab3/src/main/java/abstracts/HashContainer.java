package abstracts;

import java.util.function.Function;

public abstract class HashContainer {
    public abstract int size();
    public abstract int realSize();
    public abstract boolean isFull();
    public abstract void add(int i);
    public void add(int val, int ind){}
    public abstract int[] toArray();
    public abstract void remove(int index);
    public abstract int getIndex(int value);
    public abstract int getRealIndex(int ind);
    public abstract int[] getIndices(int v);
    public abstract int[] getIndices();
    public abstract int[] getHashValues();
    public abstract double getLoadFactor();
    public void increment(int from){}
    public void decrement(int from){}
    public void setIndexOfLastElement(int index){}
}
