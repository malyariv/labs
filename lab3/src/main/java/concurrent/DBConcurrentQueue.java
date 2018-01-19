package concurrent;

import dbQueue.DBSafeQueue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

public class DBConcurrentQueue<T> implements Queue<T> {
    private DBSafeQueue<T> q;

    public DBConcurrentQueue(DBSafeQueue<T> q) {
        this.q = q;
    }

    @Override
    synchronized public int size() {
        return q.size();
    }

    @Override
    synchronized public boolean isEmpty() {
        return q.isEmpty();
    }

    @Override
    synchronized public boolean contains(Object o) {
        return q.contains(o);
    }

    @Override
    synchronized public Iterator<T> iterator() {
        return q.iterator();
    }

    @Override
    synchronized public Object[] toArray() {
        return q.toArray();
    }

    @Override
    synchronized public <T1> T1[] toArray(T1[] a) {
        return q.toArray(a);
    }

    @Override
    synchronized public boolean add(T t) {
        return q.add(t);
    }

    @Override
    synchronized public boolean remove(Object o) {
        return q.remove(o);
    }

    @Override
    synchronized public boolean containsAll(Collection<?> c) {
        return q.containsAll(c);
    }

    @Override
    synchronized public boolean addAll(Collection<? extends T> c) {
        return q.addAll(c);
    }

    @Override
    synchronized public boolean removeAll(Collection<?> c) {
        return q.removeAll(c);
    }

    @Override
    synchronized public boolean retainAll(Collection<?> c) {
        return q.retainAll(c);
    }

    @Override
    synchronized public void clear() {
        q.clear();
    }

    @Override
    synchronized public boolean offer(T t) {
        return q.offer(t);
    }

    @Override
    synchronized public T remove() {
        return q.remove();
    }

    @Override
    synchronized public T poll() {
        return q.poll();
    }

    @Override
    synchronized public T element() {
        return q.element();
    }

    @Override
    synchronized public T peek() {
        return q.peek();
    }

    synchronized public void save(){
        q.save();
    }

    @Override
    public String toString() {
        return Arrays.toString(toArray());
    }
}
