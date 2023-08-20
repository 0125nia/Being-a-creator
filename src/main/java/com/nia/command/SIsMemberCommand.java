package com.nia.command;

import com.nia.pojo.ResponseMsg;
import com.nia.pojo.arraylist.MArrayList;

/**
 * sismember [key] [member]
 */
public class SIsMemberCommand implements AbstractArrayListCommand {
    private static final int S_IS_MEMBER_CMD_NUM = 3;

    @Override
    public String handleCommand(String[] cmd, String cmdStr) {
        String key = cmd[1];
        String member = cmd[2];
        MArrayList<String> arrayList = getArrayList(key);
        boolean contains = arrayList.contains(member);
        if (contains) {
            return ResponseMsg.IS_MEMBER;
        }
        return ResponseMsg.IS_NOT_MEMBER;
    }

    @Override
    public int getCmdNum() {
        return S_IS_MEMBER_CMD_NUM;
    }
}
