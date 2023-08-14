package com.nia.service.concrete_command;

import com.nia.dao.persistent.PersistenceContext;
import com.nia.pojo.Data;
import com.nia.pojo.ResponseMsg;
import com.nia.service.Command;

public class SaveCommand implements Command {
    @Override
    public String execute(String[] cmd, String cmdStr, Data data) {
        PersistenceContext.saveData();
        return ResponseMsg.SUCCESS;
    }
}
