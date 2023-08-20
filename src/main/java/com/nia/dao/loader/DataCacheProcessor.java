package com.nia.dao.loader;

import com.nia.dao.persistent.PersistenceContext;

/**
 * 数据缓存处理器
 */
public class DataCacheProcessor {

    private static Cache cache = Cache.getInstance();//获取缓存池实例

    //私有化构造器
    private DataCacheProcessor() {
    }


    /**
     * 数据写入缓存
     *
     * @param key   操作的键
     * @param value 写入的缓存数据
     * @param <V>   缓存数据的泛型
     */
    public static <V> void put(String key, V value) {
        cache.put(key, value);
    }


    /**
     * 获取对应的缓存数据,若缓存池中不存在,则读取持久化文件获取数据
     *
     * @param key 操作的键
     * @param <V> 缓存数据的泛型
     * @return 返回缓存数据
     * @throws NullPointerException 抛出异常交给具体命令类处理
     */
    public static <V> V get(String key) throws NullPointerException {
        if (cache.containKey(key)) {
            return cache.get(key);
        }
        return PersistenceContext.loadData(key);
    }


    /**
     * 过期时间
     *
     * @param key   操作的键
     * @param delay 多少毫秒后过期
     * @throws NullPointerException 向上抛出异常交给具体命令类处理
     */
    public static void expire(String key, long delay) throws NullPointerException {
        cache.setExpirationTime(key, delay);
    }


    //清空缓存
    public static void flush() {
        cache.clear();
    }


    //移除缓存池中key对应的数据
    public static <V> V remove(String key) {
        return cache.remove(key);
    }


    /**
     * 查看过期时间
     *
     * @param key 操作的键
     * @return 获取key对应的缓存对象过期时间(毫秒)并返回
     * @throws NullPointerException 抛出异常交给具体命令类处理
     */
    public static long ddl(String key) throws NullPointerException {
        return cache.deadline(key);
    }


}
