package com.nia.dao.data;

import com.nia.dao.conf.ConfigLoader;
import com.nia.dao.persistent.BinaryStrategy;
import com.nia.dao.persistent.LogAppendingStrategy;
import com.nia.dao.persistent.PersistenceContext;
import com.nia.dao.persistent.PersistenceStrategy;
import com.nia.pojo.Data;

/**
 * 缓存数据加载器
 */
public class DataLoader {
    //定义静态策略类常量,便于后续其他类调用
    private static final PersistenceStrategy persistenceStrategy;
    //缓存数据变量
    private static Data data;
    //缓存标志
    private static boolean isCached = false;

    //静态代码块,判断持久化类型
    static {
        //读取配置文件中持久化类型
        String pattern = ConfigLoader.getString("pattern");
        //给常量赋值
        if ("1".equals(pattern)) {
            persistenceStrategy = new LogAppendingStrategy();
        } else if ("2".equals(pattern)) {
            persistenceStrategy = new BinaryStrategy();
        } else {
            throw new RuntimeException("持久化类型参数错误!");
        }
    }

    /**
     * 加载数据到缓存数据成员变量
     */
    public static void load() {
        if (isCached){
            return;
        }
        PersistenceContext pc = new PersistenceContext();//创建策略上下文类
        pc.setStrategy(persistenceStrategy);//设置具体的策略对象
        data = pc.loadData();//
        isCached = true;//设置为已缓存
    }

}
