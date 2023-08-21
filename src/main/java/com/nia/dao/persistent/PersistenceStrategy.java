package com.nia.dao.persistent;

/**
 * 抽象策略类
 */
public interface PersistenceStrategy {

    void save();

    <V> boolean load(String key);

    void appendQueue(String cmd, byte sign);
}
