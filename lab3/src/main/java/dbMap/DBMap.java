package dbMap;

import datanodes.SetDataNode;
import utils.States;

import java.util.*;

public class DBMap<K, V> implements Map<K, V> {

    private SetDataNode<K> keySetDataNode;
    private SetDataNode<V> valueSetDataNode;

    public DBMap(String folder, boolean open) {
        keySetDataNode =new SetDataNode<>(folder+"keys/");
        keySetDataNode.setState(States.SET);
        valueSetDataNode =new SetDataNode<>(folder+"values/");
        valueSetDataNode.setState(States.SET);
        if (open){
            keySetDataNode.readConfiguration("keys");
            valueSetDataNode.readConfiguration("values");
        }
        else {
            keySetDataNode.clearDirectory();
            valueSetDataNode.clearDirectory();
        }
    }

    public void recover(String from, int p){
        keySetDataNode.recover(from+"keys/",p);
        valueSetDataNode.recover(from+"values/",p);
    }

    @Override
    public int size() {
        return keySetDataNode.size();
    }

    @Override
    public boolean isEmpty() {
        return size()==0;
    }

    @Override
    public boolean containsKey(Object key) {
        if (keySetDataNode.indexOf(key)==-1) return false;
        return true;
    }

    @Override
    public boolean containsValue(Object value) {
        if (valueSetDataNode.indexOf(value)==-1) return false;
        return true;
    }

    @Override
    public V get(Object key) {
        int[] index=keySetDataNode.getIndices(key);
        if (index[0]==-1) return null;
        return valueSetDataNode.get(index);
    }

    @Override
    public V put(K key, V value) {
        V v=null;
        if (!isEmpty()&&containsKey(key)) {
            v = remove(key);
        }
        keySetDataNode.add(key);
        valueSetDataNode.add(value);
        return v;
    }

    @Override
    public V remove(Object key) {
        int[] index=keySetDataNode.getIndices(key);
        if (index[0]==-1) return null;
        V v=valueSetDataNode.get(index);
        keySetDataNode.remove(index);
        valueSetDataNode.remove(index);
        return v;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Map.Entry<? extends K,? extends  V> entry: m.entrySet()){
            put(entry.getKey(),entry.getValue());
        }

    }

    @Override
    public void clear() {
        keySetDataNode.clear();
        valueSetDataNode.clear();
    }

    public void save(){
        keySetDataNode.save("keys");
        valueSetDataNode.save("values");
    }

    @Override
    public Set<K> keySet() {
        Set<K> set=new HashSet<>();
        Object[] arr=keySetDataNode.toArray();
        for (Object t:keySetDataNode.toArray()){
            set.add((K)t);
        }
        return set;
    }

    @Override
    public Collection<V> values() {
        Collection<V> c=new ArrayList<>();
        Object[] arr=valueSetDataNode.toArray();
        for (Object t:arr){
            c.add((V)t);
        }
        return c;
    }


    private Entry<K,V> get(int ind){
        K k=keySetDataNode.get(ind);
        V v=valueSetDataNode.get(ind);
        return new Pair<>(k,v);
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return new EntrySet();
    }
    class EntrySet extends AbstractSet<Map.Entry<K,V>>{

        @Override
        public Iterator<Entry<K, V>> iterator() {
            return new Iterator<Entry<K, V>>() {
                private int count = size();
                private int index = 0;
                @Override
                public boolean hasNext() {
                    return index < count;
                }

                @Override
                public Entry<K, V> next() {
                    if (index < count) {
                        return get(index++);
                    } else {
                        throw new NoSuchElementException("No such element.");
                    }
                }
            };
        }

        @Override
        public int size() {
            return keySetDataNode.size();
        }
    }
    private class Pair<K,V> implements Entry<K,V>{
        private K key;
        private V value;

        Pair(K k, V v){
            key=k;
            value=v;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            V prev=this.value;
            this.value=value;
            return prev;
        }

        @Override
        public String toString() {
            return key +"="+ value;
        }
    }
}
