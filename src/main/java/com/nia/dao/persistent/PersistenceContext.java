package com.nia.dao.persistent;

import com.nia.pojo.Data;

/**
 * 上下文类，在运行时选择和执行具体的持久化策略
 */
public class PersistenceContext {
    private PersistenceStrategy strategy;

    public void setStrategy(PersistenceStrategy strategy) {
        this.strategy = strategy;
    }

    public void saveData() {
        strategy.save();
    }

    public Data loadData(){
        return strategy.load();
    }
}
