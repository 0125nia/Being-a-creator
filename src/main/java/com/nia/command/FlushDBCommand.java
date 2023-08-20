package com.nia.command;

import com.nia.dao.loader.DataCacheProcessor;
import com.nia.pojo.ResponseMsg;

/**
 * flushdb
 */
public class FlushDBCommand implements Command{
    @Override
    public String handleCommand(String[] cmd, String cmdStr) {
        DataCacheProcessor.flush();
        return ResponseMsg.SUCCESS;
    }

    @Override
    public int getCmdNum() {
        return 1;
    }
}
