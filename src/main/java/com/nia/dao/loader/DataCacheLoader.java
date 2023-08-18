package com.nia.dao.loader;

import com.nia.dao.persistent.PersistenceContext;
import com.nia.pojo.Data;

/**
 * 数据缓存加载器
 */
public class DataCacheLoader {
    //缓存数据变量
    private static Data data = new Data();
    //缓存标志
    private static boolean isCached = false;

    //私有化构造器
    private DataCacheLoader() {}


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
