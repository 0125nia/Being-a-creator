package com.nia.command;

import com.nia.dao.persistent.PersistenceContext;
import com.nia.pojo.ResponseMsg;

/**
 * save
 */
public class SaveCommand implements Command {

    @Override
    public String handleCommand(String[] cmd, String cmdStr) {
        PersistenceContext.saveData();
        return ResponseMsg.SUCCESS;
    }

    @Override
    public int getCmdNum() {
        return 1;
    }
}
