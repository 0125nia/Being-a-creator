package com.nia.command;

import com.nia.dao.persistent.PersistenceContext;
import com.nia.pojo.PersistentDataIdentifier;
import com.nia.pojo.hashmap.MHashMap;

/**
 * del [key]
 */
public class DelCommand implements AbstractStringCommand {

    private static final int DEL_CMD_NUM = 2;

    @Override
    public String handleCommand(String[] cmd, String cmdStr) {
        String key = cmd[1];
        MHashMap<String, String> stringData = new MHashMap<>();
        String remove = stringData.remove(key);
        //修改数据到持久化策略类中
        PersistenceContext.appendToStrategy(cmdStr, PersistentDataIdentifier.STRING_DATA);
        //返回成功数据
        return remove;
    }

    @Override
    public int getCmdNum() {
        return DEL_CMD_NUM;
    }
}
