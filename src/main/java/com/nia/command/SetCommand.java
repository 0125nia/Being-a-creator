package com.nia.command;

import com.nia.dao.loader.DataCacheProcessor;
import com.nia.dao.persistent.PersistenceContext;
import com.nia.pojo.PersistentDataIdentifier;
import com.nia.pojo.ResponseMsg;

/**
 * set [key] [value]
 */
public class SetCommand implements AbstractStringCommand {
    private static final int SET_CMD_NUM = 3;

    @Override
    public String handleCommand(String[] cmd, String cmdStr) {
        String key = cmd[1];
        String value = cmd[2];
        //对字符串数据进行操作
        DataCacheProcessor.put(key, value);
        //修改数据到持久化策略类中
        PersistenceContext.appendToStrategy(cmdStr, PersistentDataIdentifier.STRING_DATA);
        //返回成功数据
        return ResponseMsg.SUCCESS;
    }

    @Override
    public int getCmdNum() {
        return SET_CMD_NUM;
    }
}
