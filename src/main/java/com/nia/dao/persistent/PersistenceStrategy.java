package com.nia.dao.persistent;

import com.nia.pojo.Data;

/**
 * 抽象策略类
 */
public interface PersistenceStrategy {

    void save();

    void load();

    void appendCmd(String cmd);
}
