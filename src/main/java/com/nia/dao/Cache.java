package com.nia.dao;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 缓存池
 * @param <K>
 * @param <V>
 */
public class Cache<K, V> {

    //缓存大小
    private final int cacheSize;

    private Map<K, V> cache = new LinkedHashMap<>() {
        @Override
        protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
            //当超过缓存池限制容量时,删除最前的元素
            return size() > cacheSize;
        }
    };


    public Cache(int cacheSize) {
        this.cacheSize = cacheSize;
    }

    public Map<K, V> getCache() {
        return cache;
    }

    public V get(K key){
        synchronized (cache){
            return cache.get(key);
        }
    }

    public void put(K key,V value){
        synchronized (cache){
            cache.put(key,value);
        }
    }
}
