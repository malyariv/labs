package dbList;

import datanodes.CacheListDataNode;
import jdk.nashorn.internal.ir.debug.ObjectSizeCalculator;

import java.util.*;

public class DBCacheList<T> implements List<T> {

    private T t=null;
    private CacheListDataNode<T> cacheDataNode;

    public DBCacheList(String adr, boolean open){
        cacheDataNode=new CacheListDataNode<>(adr);
        if (open){
            cacheDataNode.readConfiguration("dbCacheList");
        }
        else {
            cacheDataNode.clearDirectory();
        }
    }

    public void recover(String from, int p){
        cacheDataNode.recover(from, p);
    }

    @Override
    public int size() {
        return cacheDataNode.size();
    }

    @Override
    public boolean isEmpty() {
        return cacheDataNode.size()==0;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return cacheDataNode.iterator();
    }

    @Override
    public Object[] toArray() {
        return cacheDataNode.toArray();
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return a;
    }

    @Override
    public boolean add(T t) {
        this.t=t;
        return cacheDataNode.add(t);
    }

    @Override
    public boolean remove(Object o) {
        return cacheDataNode.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        cacheDataNode.clear();
    }

    @Override
    public T get(int index) {
        return cacheDataNode.get(index);
    }

    @Override
    public T set(int index, T element) {
        return null;
    }

    @Override
    public void add(int index, T element) {
        cacheDataNode.insert(index,element);
    }

    @Override
    public T remove(int index) {
        T t= cacheDataNode.get(index);
        cacheDataNode.remove(index);
        return t;
    }

    @Override
    public int indexOf(Object o) {
        return cacheDataNode.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return cacheDataNode.indexOf(o, t->t<0);
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }

    public String toString() {
        return Arrays.toString(toArray());
    }

    public void save(){
        cacheDataNode.save("dbCacheList");
    }

    public int getMaxSize(){
        long freeMemory = Runtime.getRuntime().maxMemory() - Runtime.getRuntime().totalMemory() + Runtime.getRuntime().freeMemory();
        if (t==null) throw new IllegalArgumentException("List is empty");
        return (int) (freeMemory / ObjectSizeCalculator.getObjectSize(t));
    }
}
