package com.nia.command;

import com.nia.dao.loader.DataCacheProcessor;
import com.nia.pojo.ResponseMsg;

/**
 * 设置过期时间
 * expire [key] [delay]
 */
public class ExpireCommand implements Command{
    private static final int EXPIRE_CMD_NUM = 3;
    @Override
    public String handleCommand(String[] cmd, String cmdStr) {
        String key = cmd[1];
        int delay = Integer.parseInt(cmd[2]);//单位:秒
        try {
            DataCacheProcessor.expire(key,delay* 1000L);//形参单位:毫秒
            return ResponseMsg.SUCCESS;
        } catch (NullPointerException e) {
            return ResponseMsg.FAIL;
        }

    }

    @Override
    public int getCmdNum() {
        return EXPIRE_CMD_NUM;
    }
}
