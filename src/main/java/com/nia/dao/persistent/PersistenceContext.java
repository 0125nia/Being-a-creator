package com.nia.dao.persistent;

import com.nia.dao.loader.ConfigLoader;
import com.nia.reactor.Reactor;

/**
 * 上下文类，在运行时选择和执行具体的持久化策略
 */
public class PersistenceContext {
    //定义静态策略类常量
    private static final PersistenceStrategy PERSISTENCESTRATEGY;


    //静态代码块,判断持久化类型
    static {
        //读取配置文件中持久化类型
        String pattern = ConfigLoader.getString("pattern");
        //给常量赋值
        if ("1".equals(pattern)) {
            Reactor.LOGGER.info("使用追加日志持久化方式");
            PERSISTENCESTRATEGY = new LogAppendingStrategy();
        } else {
            Reactor.LOGGER.info("使用二进制文件持久化方式");
            //默认为二进制文件持久化
            PERSISTENCESTRATEGY = new BinaryStrategy();
        }
    }


    public static void saveData() {
        PERSISTENCESTRATEGY.save();
    }

    //读取数据写入缓存
    public static<V> V loadData(String key) {
        Reactor.LOGGER.info("load data");//加载数据
//        return PERSISTENCESTRATEGY.load();
        return null;
    }

    public static void appendToStrategy(String cmd, byte sign) {
        PERSISTENCESTRATEGY.appendQueue(cmd, sign);
    }

    public static void bgSaveData() {
        new Thread(PERSISTENCESTRATEGY::save).start();
        Reactor.LOGGER.info("bgsave data");//将bgsave操作写入日志
    }
}
