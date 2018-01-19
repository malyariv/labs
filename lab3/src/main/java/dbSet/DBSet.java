package dbSet;

import jdk.nashorn.internal.ir.debug.ObjectSizeCalculator;
import utils.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class DBSet<T> implements Set<T> {

    private T t=null;
    private SetDataNode<T> setDataNode;

    public DBSet(String folder, boolean open) {
        setDataNode =new SetDataNode<>(folder);
        setDataNode.setState(States.SET);
        if (open){
            setDataNode.readConfiguration("dbSet");
        }
        else {
            setDataNode.clearDirectory();
        }
    }

    public DBSet(String folder, Set<T> set){
        setDataNode =new SetDataNode<>(folder);
        setDataNode.clearDirectory();
        copySet(set);
    }


    @Override
    public int size() {
        return setDataNode.size();
    }

    @Override
    public boolean isEmpty() {
        return setDataNode.size()==0;
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

        return a;
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
    public boolean removeAll(Collection<?> c) {
        boolean flag=false;
        for (Object ob:c){
            if (remove(ob)) flag=true;
        }
        return flag;
    }

    @Override
    public void clear() {
        setDataNode.clear();
    }

    public void save(){
        setDataNode.save("dbSet");
    }

    private void copySet(Set<T> set){
        setDataNode.setState(States.LIST);
        for (T t1:set) {
            setDataNode.add(t1);
        }
        setDataNode.setCurrentState(States.SET);
    }

    public int getMaxSize(){
        long freeMemory = Runtime.getRuntime().maxMemory() - Runtime.getRuntime().totalMemory() + Runtime.getRuntime().freeMemory();
        if (t==null) throw new IllegalArgumentException("Set is empty");
        return (int) (freeMemory / ObjectSizeCalculator.getObjectSize(t));
    }
    @Override
    public String toString() {
        return Arrays.toString(toArray());
    }
}
