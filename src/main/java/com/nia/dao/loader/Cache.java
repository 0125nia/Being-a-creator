package com.nia.dao.loader;

import com.nia.pojo.MMap;
import com.nia.pojo.hashmap.MHashMap;
import com.nia.pojo.linkedlist.MLinkedList;

/**
 * 缓存池
 */
public class Cache<V> {
    private static final int DEFAULTEXPIRATIONTIME = ConfigLoader.getInt("time");   //默认过期时间
    private static final int CACHECAPACITY = ConfigLoader.getInt("capacity");  //缓存容量
    private MMap<String, CacheObject<V>> cacheMap;  //缓存数据存储
    private MLinkedList<String> keyList;    //存放key的链表

    public Cache() {
        cacheMap = new MHashMap<>();
        keyList = new MLinkedList<>();
    }

    //写入缓存
    public void put(String key, V value) {
        //默认过期时间
        put(key, value, DEFAULTEXPIRATIONTIME);
    }

    //写入缓存,设置过期时间
    public synchronized void put(String key, V value, long expirationTime) {
        long expiration = System.currentTimeMillis() + expirationTime;//设置过期时间
        cacheMap.put(key, new CacheObject<>(value, expiration));//存放到map
        addToKeyList(key);
    }

    // 从缓存中获取指定键的值
    public synchronized V get(String key) {
        CacheObject<V> cacheObject = cacheMap.get(key);
        //判断是否存在该对象且是否过期
        if (cacheObject != null && !cacheObject.isExpired()) {
            return cacheObject.value;
        }
        return null;
    }

    //设置过期时间
    public synchronized void setExpirationTime(String key,long expirationTime){
        CacheObject<V> cacheObject = cacheMap.get(key);
        V value = cacheObject.getValue();//获取对应的数据
        put(key,value,expirationTime);  //重新添加到map中
    }

    //查看过期时间
    public synchronized long deadline(String key){
        CacheObject<V> cacheObject = cacheMap.get(key);
        return cacheObject.remainedTime();
    }

    //添加可以到存储key的列表
    private void addToKeyList(String key) {
        //若已存在此key
        if (keyList.contains(key)){
            keyList.remove(key);//将key从列表删除
            keyList.addFirst(key);//将key放到列表的最前面
            return;
        }
        keyList.addFirst(key);//将key放到列表的最前面
        //判断key的数量是否超过缓存容量
        for (int i = 0; i < keyList.size(); i++) {
            String k = keyList.get(i);
            //判断是否超出容量
            if (keyList.size() > CACHECAPACITY){
                String lastKey = keyList.removeLast();//将最后的key从key列表移除
                remove(lastKey);//获取到最后的key,从缓存map中移除
            }
            //判断是否过期
            if (cacheMap.get(k).isExpired()) {
                remove(k);
            }
        }
    }

    //移除key
    private void remove(String key){
        cacheMap.remove(key);
    }

    //清除缓存
    public void clear(){
        keyList = new MLinkedList<>();
        cacheMap = new MHashMap<>();
    }

    /**
     * 缓存的对象
     */
    private class CacheObject<T> {
        private final T value;
        private final long CustomExpirationTime;
        private final long createTime = System.currentTimeMillis();

        public CacheObject(T value, long customExpirationTime) {
            this.value = value;
            CustomExpirationTime = customExpirationTime;
        }

        public T getValue() {
            return value;
        }

        //存在的时间
        private long exitsTime(){
            return System.currentTimeMillis() - createTime;
        }

        //是否过期
        public boolean isExpired() {
            return exitsTime() > CustomExpirationTime;
        }

        //剩余的时间
        public long remainedTime(){
            return CustomExpirationTime - exitsTime();//过期时间减去存在时间
        }
    }



}
