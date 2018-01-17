package dbList;

import dbList.CacheListDataNode;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class DBSmartList<T> implements List<T> {

    private T t=null;
    private CacheListDataNode<T> cacheDataNode;

    public DBSmartList(String adr, boolean open){
        cacheDataNode=new CacheListDataNode<>(adr);
        if (open){
            cacheDataNode.readConfiguration("dbCacheList");
        }
        else {
            cacheDataNode.clearDirectory();
        }
    }

//    public DBSmartList(String folder, Collection<T> c){
//        setDataNode =new SetDataNode<>(folder);
//        setDataNode.setState(States.LIST);
//        setDataNode.clearDirectory();
//        copyFromCollection(c);
//    }
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
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
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

    private void copyFromCollection(Collection<T> c){
        for (T t1:c){
            add(t1);
        }
    }

    public void save(){
        cacheDataNode.save("dbCacheList");
    }
}
