package com.nia.dao.persistent;

/**
 * 抽象策略类
 */
public interface PersistenceStrategy {

    void save();

    <V> void load(String key);

    void appendQueue(String cmd, byte sign);
}
