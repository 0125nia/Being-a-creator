package com.nia.command;

import com.nia.pojo.Data;
import com.nia.pojo.ResponseMsg;

public class ErrorCommand implements Command {
    @Override
    public String execute(String[] cmd, String cmdStr, Data data) {
        return ResponseMsg.ERROR;
    }
}
