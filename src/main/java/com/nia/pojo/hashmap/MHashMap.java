package com.nia.pojo.hashmap;

import com.nia.pojo.MMap;
import com.nia.pojo.arraylist.MArrayList;

import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class MHashMap<K, V> implements MMap<K, V>, Serializable {

    private MArrayList<MEntry<K, V>> entries;   //结点集合
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

    /**
     * 判断是否存在key
     * @param key 键
     * @return 判断结果
     */
    @Override
    public boolean containsKey(K key) {
        if (isEmpty() || size == 0) {
            return false;
        }
        for (MEntry<K, V> entry : entries) {
            K k = entry.getKey();
            if (key.equals(k)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取值
     * @param key 想获取元素对应的键
     * @return key对应的value
     */
    @Override
    public V get(K key) {
        MEntry<K, V> entry = getEntry(key);
        return entry != null ? entry.getValue() : null;
    }

    /**
     * 添加键值对元素
     * @param key 键
     * @param value 值
     */
    public void put(K key, V value) {
        MEntry<K, V> entry = getEntry(key);
        if (entry != null) {
            entry.setValue(value);
        } else {
            entries.add(new MEntry<>(key, value));
            size++;
        }
    }

    /**
     * 获取结点
     * @param key 根据key
     * @return 返回对应的结点
     */
    private MEntry<K, V> getEntry(K key) {
        //如果key为空则将该变量赋值为false
        boolean isNull = key == null;
        //遍历
        for (MEntry<K, V> entry : entries) {
            //判断是否为空
            if (isNull || entry.getKey() == null) {
                return entry;
            }
            if (key.equals(entry.getKey())) {
                return entry;
            }
        }
        return null;
    }

    /**
     * 根据key移除元素
     * @param key 要移除元素的键
     * @return 移除元素的值
     */
    @Override
    public V remove(K key) {
        MEntry<K, V> entry = getEntry(key);
        if (entry != null) {
            entries.remove(entry);
            return entry.getValue();
        }
        return null;
    }


    /**
     * 结点
     * @param <K>
     * @param <V>
     */
    private static class MEntry<K, V> implements MMap.MEntry<K, V>, Serializable {
        private K key;
        private V value;

        //构造器,给key与value赋值
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


    //获取map的迭代器指针实例
    public Iterator<MMap.MEntry<K, V>> iterator() {
        return new MHashMapIterator();
    }

    //迭代器实例的代码实现
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
