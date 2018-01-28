package dbList;
/**
 * The class {@code DBList}, implementing methods of interface {@code List}, is a structure
 * to store data on HDD and basic operations of data processing as add(T t), remove(Object o), etc.
 * It also implements method to save the current collection on disc
 * or start working with stored data.
 * In addition, it implements method to recover data from files written on disk.
 */

import datanodes.ListDataNode;
import jdk.nashorn.internal.ir.debug.ObjectSizeCalculator;
import java.util.*;


public class DBList<T> implements List<T> {
    private T t=null;
    private ListDataNode<T> listDataNode;

    public DBList(String adr, boolean open){
        listDataNode=new ListDataNode<>(adr);
        if (open){
            listDataNode.readConfiguration("dbList");
        }
        else {
            listDataNode.clearDirectory();
        }
    }

    public void recover(String from, int p){
        listDataNode.recover(from, p);
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
        return listDataNode.toArray(a);
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
        for (Object ob:c){
            if (!contains(ob)) return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean flag=false;
        for (T t1:c){
            if (listDataNode.add(t1)) flag=true;
        }
        return flag;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        if (index>size()) throw new IllegalArgumentException();
        int s=size();
        for (T t1:c) {
            add(index++, t1);
        }
        return s!=size();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean flag=false;
        for (Object ob:c){
            if (remove(ob)) flag=true;
        }
        return flag;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean flag=false;
        Iterator<T> iterator=iterator();
        while (iterator.hasNext()){
            if (!c.contains(iterator.next())) {
                iterator.remove();
                flag = true;
            }
        }
        return flag;
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
        if (index>size()) throw new IllegalArgumentException();
        return listDataNode.set(index, element);
    }

    @Override
    public void add(int index, T element) {
        if (index>size()) throw new IllegalArgumentException();
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
        return listIterator(0);
    }

    @Override
    public ListIterator<T> listIterator(int ind) {
        if (ind<0||ind>=size()) {
            throw new IllegalArgumentException();
        }
        return new ListIterator<T>() {
            private int count = size();
            private int index = ind;
            private boolean next=false;

            private int getIndex(){
                if (ind+index<size()) {
                    return ind+index;
                }
                return ind+index-size();
            }

            @Override
            public boolean hasNext() {
                return index < count;
            }

            @Override
            public T next() {
                if (index < count) {
                    next=true;
                    return t=get(index++);
                } else {
                    throw new NoSuchElementException("No such element.");
                }
            }

            @Override
            public boolean hasPrevious() {
                return index>0;
            }

            @Override
            public T previous() {
                if (index >0) {
                    next=false;
                    return t=get(--index);
                } else {
                    throw new NoSuchElementException("No such element.");
                }
            }

            @Override
            public int nextIndex() {
                return index;
            }

            @Override
            public int previousIndex() {
                return index-1;
            }

            @Override
            public void remove() {
                if (next) {
                    index--;
                }
                DBList.this.remove(index);
                count=size();
            }

            @Override
            public void set(T t) {
                if (next) {
                    index--;
                }
                DBList.this.set(index++,t);
            }

            @Override
            public void add(T t) {
                DBList.this.add(index,t);
                count=size();
            }
        };
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        if (fromIndex>=size()) throw new IllegalArgumentException();
        int to=Math.min(size(),toIndex);
        List<T> list=new ArrayList<>();
        for (int i=fromIndex;i<to;i++){
           list.add(get(i));
        }
        return list;
    }

    public int getMaxSize(){
        long freeMemory = Runtime.getRuntime().maxMemory() - Runtime.getRuntime().totalMemory() + Runtime.getRuntime().freeMemory();
        if (t==null) throw new IllegalArgumentException("List is empty");
        return (int) (freeMemory / ObjectSizeCalculator.getObjectSize(t));
    }


    public void save(){
        listDataNode.save("dbList");
    }

    public String toString() {
        return Arrays.toString(toArray());
    }
}
