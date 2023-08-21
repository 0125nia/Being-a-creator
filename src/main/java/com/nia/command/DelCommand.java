package com.nia.command;

import com.nia.dao.loader.DataCacheProcessor;
import com.nia.dao.persistent.PersistenceContext;
import com.nia.pojo.PersistentDataIdentifier;

/**
 * del [key]
 */
public class DelCommand implements AbstractStringCommand {

    private static final int DEL_CMD_NUM = 2;

    @Override
    public String handleCommand(String[] cmd, String cmdStr) {
        String key = cmd[1];
        String remove = DataCacheProcessor.remove(key);
        System.out.println(remove);
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
