package com.nia.pojo;


public interface MMap<K, V> extends Iterable<MMap.MEntry<K, V>> {

    int size();

    boolean isEmpty();

    boolean containsKey(K key);

    V get(K key);

    void put(K key, V value);

    V remove(K key);

    // MHashMap 中的节点
    interface MEntry<K, V> {
        K getKey();

        V getValue();
    }
}
