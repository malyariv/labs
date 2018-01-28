package concurrent;
/**
 * The class {@code DBLockedMap} is a wrapper for {@code DBMap}.
 * This class provides thread-safety by means of a {@code ReadWriteLock} object.
 */
import dbMap.DBMap;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class DBLockedMap<K,V> implements Map<K,V> {

    private final DBMap<K,V> map;
    private final ReadWriteLock rwl=new ReentrantReadWriteLock(true);
    private final Lock readLock=rwl.readLock();
    private final Lock writeLock=rwl.writeLock();

    public DBLockedMap(DBMap<K, V> map) {
        this.map = map;
    }

    @Override
    public int size() {
        readLock.lock();
        try {
            return map.size();
        }
        finally {
            readLock.unlock();
        }
    }

    @Override
    public boolean isEmpty() {
        readLock.lock();
        try {
            return map.isEmpty();
        }
        finally {
            readLock.unlock();
        }
    }

    @Override
    public boolean containsKey(Object key) {
        readLock.lock();
        try {
            return map.containsKey(key);
        }
        finally {
            readLock.unlock();
        }
    }

    @Override
    public boolean containsValue(Object value) {
        readLock.lock();
        try {
            return map.containsValue(value);
        }
        finally {
            readLock.unlock();
        }
    }

    @Override
    public V get(Object key) {
        readLock.lock();
        try {
            return map.get(key);
        }
        finally {
            readLock.unlock();
        }
    }

    @Override
    public V put(K key, V value) {
        writeLock.lock();
        try {
            return map.put(key, value);
        }
        finally {
            writeLock.unlock();
        }
    }

    @Override
    public V remove(Object key) {
        writeLock.lock();
        try {
            return map.remove(key);
        }
        finally {
            writeLock.unlock();
        }
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        writeLock.lock();
        try {
            map.putAll(m);
        }
        finally {
            writeLock.unlock();
        }
    }

    @Override
    public void clear() {
        writeLock.lock();
        try {
            map.clear();
        }
        finally {
            writeLock.unlock();
        }
    }

    @Override
    public Set<K> keySet() {
        readLock.lock();
        try {
            return map.keySet();
        }
        finally {
            readLock.unlock();
        }
    }

    @Override
    public Collection<V> values() {
        readLock.lock();
        try {
            return map.values();
        }
        finally {
            readLock.unlock();
        }
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        writeLock.lock();
        try {
            return map.entrySet();
        }
        finally {
            writeLock.unlock();
        }
    }

    public void recover(String from,int p){
        writeLock.lock();
        try {
            map.recover(from, p);
        }
        finally {
            writeLock.unlock();
        }
    }
}
