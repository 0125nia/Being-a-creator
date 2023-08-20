package com.nia.command;

/**
 * get [key]
 */
public class GetCommand implements AbstractStringCommand {
    private static final int GET_CMD_NUM = 2;

    @Override
    public String handleCommand(String[] cmd, String cmdStr) {
        String key = cmd[1];
        return getList(key);
    }

    @Override
    public int getCmdNum() {
        return GET_CMD_NUM;
    }
}
