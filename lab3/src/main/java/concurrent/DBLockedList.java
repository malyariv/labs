package concurrent;

import dbList.DBList;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class DBLockedList<T> implements List<T> {

    private final DBList<T> list;
    private final ReadWriteLock rwl=new ReentrantReadWriteLock(true);
    private final Lock readLock=rwl.readLock();
    private final Lock writeLock=rwl.writeLock();

    public DBLockedList(DBList<T> list) {
        this.list = list;
    }


    @Override
    public int size() {
        readLock.lock();
        try {
            return list.size();
        }
        finally {
            readLock.unlock();
        }
    }

    @Override
    public boolean isEmpty() {
        readLock.lock();
        try {
            return list.isEmpty();
        }
        finally {
            readLock.unlock();
        }
    }

    @Override
    public boolean contains(Object o) {
        readLock.lock();
        try {
            return list.contains(o);
        }
        finally {
            readLock.unlock();
        }
    }

    @Override
    public Iterator<T> iterator() {
        writeLock.lock();
        try {
            return list.iterator();
        }
        finally {
            writeLock.unlock();
        }
    }

    @Override
    public Object[] toArray() {
        readLock.lock();
        try {
            return list.toArray();
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
            return list.add(t);
        }
        finally {
            writeLock.unlock();
        }
    }

    @Override
    public boolean remove(Object o) {
        writeLock.lock();
        try {
            return list.remove(o);
        }
        finally {
            writeLock.unlock();
        }
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        readLock.lock();
        try {
            return list.containsAll(c);
        }
        finally {
            readLock.unlock();
        }
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        writeLock.lock();
        try {
            return list.addAll(c);
        }
        finally {
            writeLock.unlock();
        }
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        writeLock.lock();
        try {
            return list.addAll(index,c);
        }
        finally {
            writeLock.unlock();
        }
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        writeLock.lock();
        try {
            return list.removeAll(c);
        }
        finally {
            writeLock.unlock();
        }
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        writeLock.lock();
        try {
            return list.retainAll(c);
        }
        finally {
            writeLock.unlock();
        }
    }

    @Override
    public void clear() {
        writeLock.lock();
        try {
            list.clear();
        }
        finally {
            writeLock.unlock();
        }
    }

    @Override
    public T get(int index) {
        readLock.lock();
        try {
            return list.get(index);
        }
        finally {
            readLock.unlock();
        }
    }

    @Override
    public T set(int index, T element) {
        writeLock.lock();
        try {
            return list.set(index, element);
        }
        finally {
            writeLock.unlock();
        }
    }

    @Override
    public void add(int index, T element) {
        writeLock.lock();
        try {
            list.add(index, element);
        }
        finally {
            writeLock.unlock();
        }
    }

    @Override
    public T remove(int index) {
        writeLock.lock();
        try {
            return list.remove(index);
        }
        finally {
            writeLock.unlock();
        }
    }

    @Override
    public int indexOf(Object o) {
        readLock.lock();
        try {
            return list.indexOf(o);
        }
        finally {
            readLock.unlock();
        }
    }

    @Override
    public int lastIndexOf(Object o) {
        readLock.lock();
        try {
            return list.lastIndexOf(o);
        }
        finally {
            readLock.unlock();
        }
    }

    @Override
    public ListIterator<T> listIterator() {
        writeLock.lock();
        try {
            return list.listIterator();
        }
        finally {
            writeLock.unlock();
        }
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        writeLock.lock();
        try {
            return list.listIterator(index);
        }
        finally {
            writeLock.unlock();
        }
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        readLock.lock();
        try {
            return list.subList(fromIndex, toIndex);
        }
        finally {
            readLock.unlock();
        }
    }

    public int getMaxSize(){
        return list.getMaxSize();
    }

    public void recover(String from,int p){
        writeLock.lock();
        try {
            list.recover(from, p);
        }
        finally {
            writeLock.unlock();
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(toArray());
    }
}
