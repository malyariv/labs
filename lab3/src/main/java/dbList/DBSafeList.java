package dbList;

import jdk.nashorn.internal.ir.debug.ObjectSizeCalculator;
import dbList.ListDataNode;

import java.util.*;

public class DBSafeList<T> implements List<T> {

    private T t=null;
    private ListDataNode<T> listDataNode;

    public DBSafeList(String adr, boolean open){
        listDataNode=new ListDataNode<>(adr);
        if (open){
            listDataNode.readConfiguration();
        }
        else {
            listDataNode.clearDirectory();
        }
    }

    public DBSafeList(String folder, Collection<T> c){
        listDataNode =new ListDataNode<>(folder);
        listDataNode.clearDirectory();
        copyFromCollection(c);
    }

    @Override
    public int size() {
        return listDataNode.size();
    }

    @Override
    public boolean isEmpty() {
        return listDataNode.size()==0;
    }

    @Override
    public boolean contains(Object o) {
        if (listDataNode.indexOf(o)==-1) return false;
        return true;
    }

    @Override
    public Iterator<T> iterator() {
        return listDataNode.iterator();
    }

    @Override
    public Object[] toArray() {
        return listDataNode.toArray();
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public boolean add(T t) {
        this.t=t;
        return listDataNode.add(t);
    }

    @Override
    public boolean remove(Object o) {
        return listDataNode.remove(o);
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
        listDataNode.clear();
    }

    @Override
    public T get(int index) {
        return listDataNode.get(index);
    }

    @Override
    public T set(int index, T element) {
        return listDataNode.set(index, element);
    }

    @Override
    public void add(int index, T element) {
        listDataNode.insert(index, element);
    }

    @Override
    public T remove(int index) {
        T t= listDataNode.get(index);
        listDataNode.remove(index);
        return t;
    }

    @Override
    public int indexOf(Object o) {
        return listDataNode.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return listDataNode.indexOf(o, t->t<0);
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

    public int getMaxSize(){
        long freeMemory = Runtime.getRuntime().maxMemory() - Runtime.getRuntime().totalMemory() + Runtime.getRuntime().freeMemory();
        if (t==null) throw new IllegalArgumentException("List is empty");
        return (int) (freeMemory / ObjectSizeCalculator.getObjectSize(t));
    }

    private void copyFromCollection(Collection<T> c){
        for (T t1:c){
            add(t1);
        }
    }
}
