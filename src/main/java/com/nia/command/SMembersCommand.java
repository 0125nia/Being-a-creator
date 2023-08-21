package com.nia.command;

import com.nia.pojo.arraylist.MArrayList;

/**
 * smembers [key]
 */
public class SMembersCommand implements AbstractArrayListCommand{
    private static final int S_MEMBERS_CMD_NUM = 2;

    @Override
    public String handleCommand(String[] cmd, String cmdStr) {
        String key = cmd[1];
        MArrayList<String> arrayList = getArrayList(key);
        StringBuilder sb = new StringBuilder();
        for (String s : arrayList) {
            sb.append(s).append(" ");
        }
        return sb.toString();
    }

    @Override
    public int getCmdNum() {
        return S_MEMBERS_CMD_NUM;
    }
}
