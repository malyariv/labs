package dbQueue;

import dbSet.SetDataNode;
import utils.States;

import java.util.*;

public class DBSafeQueue<T> implements Queue<T> {
    private T t=null;
    private SetDataNode<T> setDataNode;

    public DBSafeQueue(String folder, boolean open) {
        setDataNode =new SetDataNode<>(folder);
        setDataNode.setState(States.LIST);
        if (open){
            setDataNode.readConfiguration("dbQueue");
        }
        else {
            setDataNode.clearDirectory();
        }
    }

    @Override
    public int size() {
        return setDataNode.size();
    }

    @Override
    public boolean isEmpty() {
        return size()==0;
    }

    @Override
    public boolean contains(Object o) {
        if (setDataNode.indexOf(o)==-1) return false;
        return true;
    }

    @Override
    public Iterator<T> iterator() {
        return setDataNode.iterator();
    }

    @Override
    public Object[] toArray() {
        return setDataNode.toArray();
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public boolean add(T t) {
        this.t=t;
        return setDataNode.add(t);
    }

    @Override
    public boolean remove(Object o) {
        return setDataNode.remove(o);
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
            if (setDataNode.add(t1)) flag=true;
        }
        return flag;
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
        setDataNode.clear();
    }

    @Override
    public boolean offer(T t) {
        return add(t);
    }

    @Override
    public T remove() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        t=setDataNode.get(0);
        setDataNode.remove(0);
        return t;
    }

    @Override
    public T poll() {
        if (isEmpty()) {
            return null;
        }
        t=setDataNode.get(0);
        setDataNode.remove(0);
        return t;
    }

    @Override
    public T element() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return setDataNode.get(0);
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return setDataNode.get(0);
    }

    public void save(){
        setDataNode.save("dbQueue");
    }

    @Override
    public String toString() {
        return Arrays.toString(toArray());
    }
}
