package concurrent;

import dbList.DBList;

import java.util.*;

public class DBSynchronizedList<T> implements List<T> {

    private final DBList<T> list;

    public DBSynchronizedList(DBList<T> l){
        list=l;
    }
    @Override
    synchronized public int size() {
        return list.size();
    }

    @Override
    synchronized public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    synchronized public boolean contains(Object o) {
        return list.contains(o);
    }

    @Override
    synchronized public Iterator<T> iterator() {
        return list.iterator();
    }

    @Override
    synchronized public Object[] toArray() {
        return list.toArray();
    }

    @Override
    synchronized public <T1> T1[] toArray(T1[] a) {
        return list.toArray(a);
    }

    @Override
    synchronized public boolean add(T t) {
        return list.add(t);
    }

    @Override
    synchronized public boolean remove(Object o) {
        return list.remove(o);
    }

    @Override
    synchronized public boolean containsAll(Collection<?> c) {
        return list.containsAll(c);
    }

    @Override
    synchronized public boolean addAll(Collection<? extends T> c) {
        return list.addAll(c);
    }

    @Override
    synchronized public boolean addAll(int index, Collection<? extends T> c) {
        return list.addAll(index,c);
    }

    @Override
    synchronized public boolean removeAll(Collection<?> c) {
        return list.removeAll(c);
    }

    @Override
    synchronized public boolean retainAll(Collection<?> c) {
        return list.retainAll(c);
    }

    @Override
    synchronized public void clear() {
        list.clear();
    }

    @Override
    synchronized public T get(int index) {
        return list.get(index);
    }

    @Override
    synchronized public T set(int index, T element) {
        return list.set(index,element);
    }

    @Override
    synchronized public void add(int index, T element) {
        list.add(index, element);
    }

    @Override
    synchronized public T remove(int index) {
        return list.remove(index);
    }

    @Override
    synchronized public int indexOf(Object o) {
        return list.indexOf(o);
    }

    @Override
    synchronized public int lastIndexOf(Object o) {
        return list.lastIndexOf(o);
    }

    @Override
    synchronized public ListIterator<T> listIterator() {
        return list.listIterator();
    }

    @Override
    synchronized public ListIterator<T> listIterator(int index) {
        return list.listIterator(index);
    }

    @Override
    synchronized public List<T> subList(int fromIndex, int toIndex) {
        return list.subList(fromIndex,toIndex);
    }

    synchronized public void save(){
        list.save();
    }

    synchronized public void recover(String from,int p){
            list.recover(from, p);
    }

    @Override
    public String toString() {
        return Arrays.toString(toArray());
    }
}
