package concurrent;

import dbSet.DBSet;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class DBSynchronizedSet<T> implements Set<T> {

    private final DBSet<T> set;

    public DBSynchronizedSet(DBSet<T> s){
        set=s;
    }

    @Override
    synchronized public int size() {
        return set.size();
    }

    @Override
    synchronized public boolean isEmpty() {
        return set.isEmpty();
    }

    @Override
    synchronized public boolean contains(Object o) {
        return set.contains(o);
    }

    @Override
    synchronized public Iterator<T> iterator() {
        return set.iterator();
    }

    @Override
    synchronized public Object[] toArray() {
        return set.toArray();
    }

    @Override
    synchronized public <T1> T1[] toArray(T1[] a) {
        return set.toArray(a);
    }

    @Override
    synchronized public boolean add(T t) {
        return set.add(t);
    }

    @Override
    synchronized public boolean remove(Object o) {
        return set.remove(o);
    }

    @Override
    synchronized public boolean containsAll(Collection<?> c) {
        return set.containsAll(c);
    }

    @Override
    synchronized public boolean addAll(Collection<? extends T> c) {
        return set.addAll(c);
    }

    @Override
    synchronized public boolean retainAll(Collection<?> c) {
        return set.retainAll(c);
    }

    @Override
    synchronized public boolean removeAll(Collection<?> c) {
        return set.removeAll(c);
    }

    @Override
    synchronized public void clear() {
        set.clear();
    }

    synchronized public void save(){
        set.save();
    }

    public int getMaxSize(){
        return set.getMaxSize();
    }

    @Override
    public String toString() {
        return Arrays.toString(toArray());
    }

}
