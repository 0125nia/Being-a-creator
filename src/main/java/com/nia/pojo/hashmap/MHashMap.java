package com.nia.pojo.hashmap;

import com.nia.pojo.MMap;
import com.nia.pojo.arraylist.MArrayList;

import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class MHashMap<K, V> implements MMap<K, V> , Serializable {

    private MArrayList<MEntry<K, V>> entries;
    private int size;

    public MHashMap() {
        entries = new MArrayList<>();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(K key) {
        if (isEmpty()||size==0){
            return false;
        }
        for (MEntry<K, V> entry : entries) {
            K k = entry.getKey();
            if (key.equals(k)){
                return true;
            }
        }
        return false;
    }

    @Override
    public V get(K key) {
        MEntry<K, V> entry = getEntry(key);
        return entry != null ? entry.getValue() : null;
    }


    public void put(K key, V value) {
        MEntry<K, V> entry = getEntry(key);
        if (entry != null) {
            entry.setValue(value);
        } else {
            entries.add(new MEntry<>(key, value));
            size++;
        }
    }

    private MEntry<K, V> getEntry(K key) {
        boolean isNull = key == null;
        for (MEntry<K, V> entry : entries) {
            if (isNull||entry.getKey() == null){
                return entry;
            }
            if (key.equals(entry.getKey())) {
                return entry;
            }
        }
        return null;
    }

    @Override
    public V remove(K key) {
        MEntry<K, V> entry = getEntry(key);
        if (entry != null) {
            entries.remove(entry);
            return entry.getValue();
        }
        return null;
    }


    private static class MEntry<K, V> implements MMap.MEntry<K,V>{
        private K key;
        private V value;

        public MEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }


    public Iterator<MMap.MEntry<K, V>> iterator() {
        return new MHashMapIterator();
    }

    private class MHashMapIterator implements Iterator<MMap.MEntry<K, V>> {

        private Iterator<MEntry<K, V>> iterator;

        public MHashMapIterator() {
            iterator = entries.iterator();
        }

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public MEntry<K, V> next() {
            if (hasNext()) {
                return iterator.next();
            }
            throw new NoSuchElementException();
        }


    }
}
