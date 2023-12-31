package com.nia.command;

import com.nia.dao.persistent.PersistenceContext;
import com.nia.pojo.ResponseMsg;

public class BgSaveCommand implements Command {


    @Override
    public String handleCommand(String[] cmd, String cmdStr) {
        PersistenceContext.bgSaveData();
        return ResponseMsg.SUCCESS;
    }

    @Override
    public int getCmdNum() {
        return 1;
    }
}
