package com.nia.command;

import com.nia.pojo.ResponseMsg;

public class ErrorCommand implements Command {

    @Override
    public String execute(String cmdStr) {
        return ResponseMsg.CMD_ERROR;
    }


    //添加注解标识不需使用的方法
    @Deprecated
    @Override
    public String handleCommand(String[] cmd, String cmdStr) {
        return null;
    }

    @Deprecated
    @Override
    public int getCmdNum() {
        return 0;
    }
}
