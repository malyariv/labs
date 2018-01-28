package concurrent;
/**
 * The class {@code DBLockedQueue} is a wrapper for {@code DBQueue}.
 * This class provides thread-safety by means of a {@code ReadWriteLock} object.
 */
import dbQueue.DBQueue;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class DBLockedQueue<T> implements Queue<T> {

    private final DBQueue<T> q;
    private final ReadWriteLock rwl=new ReentrantReadWriteLock(true);
    private final Lock readLock=rwl.readLock();
    private final Lock writeLock=rwl.writeLock();

    public DBLockedQueue(DBQueue<T> q) {
        this.q = q;
    }

    @Override
    public int size() {
        readLock.lock();
        try {
            return q.size();
        }
        finally {
            readLock.unlock();
        }
    }

    @Override
    public boolean isEmpty() {
        readLock.lock();
        try {
            return q.isEmpty();
        }
        finally {
            readLock.unlock();
        }
    }

    @Override
    public boolean contains(Object o) {
        readLock.lock();
        try {
            return q.contains(o);
        }
        finally {
            readLock.unlock();
        }
    }

    @Override
    public Iterator<T> iterator() {
        writeLock.lock();
        try {
            return q.iterator();
        }
        finally {
            writeLock.unlock();
        }
    }

    @Override
    public Object[] toArray() {
        readLock.lock();
        try {
            return q.toArray();
        }
        finally {
            readLock.unlock();
        }
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        readLock.lock();
        try {
            return q.toArray(a);
        }
        finally {
            readLock.unlock();
        }
    }

    @Override
    public boolean add(T t) {
        writeLock.lock();
        try {
            return q.add(t);
        }
        finally {
            writeLock.unlock();
        }
    }

    @Override
    public boolean remove(Object o) {
        writeLock.lock();
        try {
            return q.remove(o);
        }
        finally {
            writeLock.unlock();
        }
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        readLock.lock();
        try {
            return q.containsAll(c);
        }
        finally {
            readLock.unlock();
        }
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        writeLock.lock();
        try {
            return q.removeAll(c);
        }
        finally {
            writeLock.unlock();
        }
    }


    @Override
    public boolean retainAll(Collection<?> c) {
        writeLock.lock();
        try {
            return q.retainAll(c);
        }
        finally {
            writeLock.unlock();
        }
    }

    @Override
    public void clear() {
        writeLock.lock();
        try {
            q.clear();
        }
        finally {
            writeLock.unlock();
        }
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        writeLock.lock();
        try {
            return q.addAll(c);
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public boolean offer(T t) {
        writeLock.lock();
        try {
            return q.offer(t);
        }
        finally {
            writeLock.unlock();
        }
    }

    @Override
    public T remove() {
        writeLock.lock();
        try {
            return q.remove();
        }
        finally {
            writeLock.unlock();
        }
    }

    @Override
    public T poll() {
        writeLock.lock();
        try {
            return q.poll();
        }
        finally {
            writeLock.unlock();
        }
    }

    @Override
    public T element() {
        readLock.lock();
        try {
            return q.element();
        }
        finally {
            readLock.unlock();
        }
    }

    @Override
    public T peek() {
        readLock.lock();
        try {
            return q.peek();
        }
        finally {
            readLock.unlock();
        }
    }

    public void recover(String from,int p){
        writeLock.lock();
        try {
            q.recover(from, p);
        }
        finally {
            writeLock.unlock();
        }
    }
}
