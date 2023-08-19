package com.nia.dao.loader;

import com.nia.pojo.MMap;
import com.nia.pojo.hashmap.MHashMap;
import com.nia.pojo.linkedlist.MLinkedList;

/**
 * 缓存池
 */
public class Cache {
    private static final int DEFAULTEXPIRATIONTIME = ConfigLoader.getInt("time");   //默认过期时间
    private static final int CACHECAPACITY = ConfigLoader.getInt("capacity");  //缓存容量
    public static MLinkedList<String> keyList;   //存放key的链表
    private MMap<String, CacheObject<?>> cacheMap;  //缓存数据存储

    /**
     * 为了防止在多线程的情况下由于JVM在实例化对象时会优化和指令重排序操作
     * 可能导致出现空指针异常的情况
     * 所以使用volatile关键字,保证可见性与有序性
     */
    private static volatile Cache instance;

    private Cache() {
        keyList = new MLinkedList<>();
        cacheMap = new MHashMap<>();
    }

    /**
     * 使用双重检查锁单例模式获取实例对象,保证正确性与线程安全
     *
     * @return 实例对象
     */
    public static Cache getInstance() {
        //第一次判断,若instance的值不为null,不需要抢占锁,直接返回对象
        if (instance == null) {
            synchronized (Cache.class) {
                //第二次判断
                if (instance == null) {
                    instance = new Cache();
                }
            }
        }
        return instance;
    }


    //写入缓存
    public <V> void put(String key, V value) {
        //默认过期时间
        put(key, value, DEFAULTEXPIRATIONTIME);
    }

    //写入缓存,设置过期时间
    private synchronized <V> void put(String key, V value, long expirationTime) {
        long expiration = System.currentTimeMillis() + expirationTime;//设置过期时间
        cacheMap.put(key, new CacheObject<>(value, expiration));//存放到map
        addToKeyList(key);
    }

    // 从缓存中获取指定键的值
    public synchronized <V> V get(String key) throws NullPointerException {
        CacheObject<?> cacheObject = cacheMap.get(key);
        //判断该对象是否存在且是否过期
        if (cacheObject != null && !cacheObject.isExpired()) {
            return (V) cacheObject.getValue();
        }
        //若均不符合则抛出异常
        throw new NullPointerException();
    }

    //设置过期时间
    public synchronized <V> void setExpirationTime(String key, long expirationTime) throws NullPointerException {
        CacheObject<?> cacheObject = cacheMap.get(key);
        if (cacheObject == null) {
            throw new NullPointerException();
        }
        V value = (V) cacheObject.getValue();//获取对应的数据
        put(key, value, expirationTime);  //重新添加到map中
    }

    //查看过期时间
    public synchronized long deadline(String key) throws NullPointerException {
        CacheObject<?> cacheObject = cacheMap.get(key);
        //判断是否存在
        if (cacheObject == null) {
            throw new NullPointerException();
        }
        //返回剩余时间
        return cacheObject.remainedTime();
    }

    //添加可以到存储key的列表
    private void addToKeyList(String key) {
        //若已存在此key
        if (keyList.contains(key) && cacheMap.containsKey(key)) {
            keyList.remove(key);//将key从列表删除
            keyList.addFirst(key);//将key放到列表的最前面
            return;
        }
        keyList.addFirst(key);//将key放到列表的最前面
        //判断key的数量是否超过缓存容量
        for (int i = 0; i < keyList.size(); i++) {
            String k = keyList.get(i);
            //判断是否超出容量
            if (keyList.size() > CACHECAPACITY) {
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
    private void remove(String key) {
        cacheMap.remove(key);
    }

    //清除缓存
    public void clear() {
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
        private long exitsTime() {
            return System.currentTimeMillis() - createTime;
        }

        //是否过期
        public boolean isExpired() {
            return exitsTime() > CustomExpirationTime;
        }

        //剩余的时间
        public long remainedTime() {
            return CustomExpirationTime - exitsTime();//过期时间减去存在时间
        }
    }


}
