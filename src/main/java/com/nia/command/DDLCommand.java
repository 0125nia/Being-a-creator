package com.nia.command;

import com.nia.dao.loader.DataCacheProcessor;
import com.nia.pojo.ResponseMsg;

/**
 * 显示还有多少秒过期
 * ddl [key]
 */
public class DDLCommand implements Command{
    private static final int DDL_CMD_NUM = 3;

    @Override
    public String handleCommand(String[] cmd, String cmdStr) {
        String key = cmd[1];
        long ddl;
        try {
            ddl = DataCacheProcessor.ddl(key);
        } catch (NullPointerException e) {
            return ResponseMsg.NULL_DATA;
        }
        return String.valueOf(ddl/1000);
    }

    @Override
    public int getCmdNum() {
        return DDL_CMD_NUM;
    }
}
