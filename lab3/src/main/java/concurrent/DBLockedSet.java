package concurrent;

import dbSet.DBSet;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class DBLockedSet<T> implements Set<T> {

    private final DBSet<T> set;
    private final ReadWriteLock rwl=new ReentrantReadWriteLock(true);
    private final Lock readLock=rwl.readLock();
    private final Lock writeLock=rwl.writeLock();

    public DBLockedSet(DBSet<T> s){
        set=s;
    }

    @Override
    public int size() {
        readLock.lock();
        try {
            return set.size();
        }
        finally {
            readLock.unlock();
        }
    }

    @Override
    public boolean isEmpty() {
        readLock.lock();
        try {
            return set.isEmpty();
        }
        finally {
            readLock.unlock();
        }
    }

    @Override
    public boolean contains(Object o) {
        readLock.lock();
        try {
            return set.contains(o);
        }
        finally {
            readLock.unlock();
        }
    }

    @Override
    public Iterator<T> iterator() {
        writeLock.lock();
        try {
            return set.iterator();
        }
        finally {
            writeLock.unlock();
        }
    }

    @Override
    public Object[] toArray() {
        readLock.lock();
        try {
            return set.toArray();
        }
        finally {
            readLock.unlock();
        }
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public boolean add(T t) {
        writeLock.lock();
        try {
            return set.add(t);
        }
        finally {
            writeLock.unlock();
        }
    }

    @Override
    public boolean remove(Object o) {
        writeLock.lock();
        try {
            return set.remove(o);
        }
        finally {
            writeLock.unlock();
        }
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        readLock.lock();
        try {
            return set.containsAll(c);
        }
        finally {
            readLock.unlock();
        }
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        writeLock.lock();
        try {
            return set.addAll(c);
        }
        finally {
            writeLock.unlock();
        }
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        writeLock.lock();
        try {
            return set.retainAll(c);
        }
        finally {
            writeLock.unlock();
        }
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        writeLock.lock();
        try {
            return set.removeAll(c);
        }
        finally {
            writeLock.unlock();
        }
    }

    @Override
    public void clear() {
        writeLock.lock();
        try {
            set.clear();
        }
        finally {
            writeLock.unlock();
        }
    }

    synchronized public void save(){
        readLock.lock();
        try {
            set.save();
        }
        finally {
            readLock.unlock();
        }
    }

    public int getMaxSize(){
        return set.getMaxSize();
    }

    @Override
    public String toString() {
        return Arrays.toString(toArray());
    }
}
