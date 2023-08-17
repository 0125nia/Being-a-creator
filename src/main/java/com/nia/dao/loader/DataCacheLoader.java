package com.nia.dao.loader;

import com.nia.dao.persistent.BinaryStrategy;
import com.nia.dao.persistent.LogAppendingStrategy;
import com.nia.dao.persistent.PersistenceContext;
import com.nia.dao.persistent.PersistenceStrategy;
import com.nia.pojo.Data;

/**
 * 数据缓存加载器
 */
public class DataCacheLoader {
    //定义静态策略类常量,便于后续其他类调用
    public static final PersistenceStrategy PERSISTENCESTRATEGY;
    //缓存数据变量
    private static Data data = new Data();
    //缓存标志
    private static boolean isCached = false;
    //私有化构造器
    private DataCacheLoader(){}

    //静态代码块,判断持久化类型
    static {
        //读取配置文件中持久化类型
        String pattern = ConfigLoader.getString("pattern");
        //给常量赋值
        if ("1".equals(pattern)) {
            PERSISTENCESTRATEGY = new LogAppendingStrategy();
        } else if ("2".equals(pattern)) {
            PERSISTENCESTRATEGY = new BinaryStrategy();
        } else {
            throw new RuntimeException("持久化类型参数错误!");
        }
    }

    /**
     * 加载数据到缓存数据成员变量
     */
    private static void load() {
        if (isCached) {
            return;
        }
        data = PersistenceContext.loadData();//加载数据
        isCached = true;//设置为已缓存
    }

    public static Data getData() {
        load();
        return data;
    }
}
