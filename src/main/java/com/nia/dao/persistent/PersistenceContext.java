package com.nia.dao.persistent;

import com.nia.dao.loader.DataCacheLoader;
import com.nia.pojo.Data;

/**
 * 上下文类，在运行时选择和执行具体的持久化策略
 */
public class PersistenceContext {
    private static PersistenceStrategy strategy = DataCacheLoader.PERSISTENCESTRATEGY;


    public static void saveData() {
        strategy.save();
    }

    public static Data loadData(){
        return strategy.load();
    }

    public static void appendCmd(String cmd){
        strategy.appendCmd(cmd);
    }
}
