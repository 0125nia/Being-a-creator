package com.nia.command;

import com.nia.pojo.ResponseMsg;
import com.nia.pojo.arraylist.MArrayList;

/**
 * srem [key] [member]
 */
public class SRemCommand implements AbstractArrayListCommand{
    private static final int S_REMOVE_CMD_NUM = 3;

    @Override
    public String handleCommand(String[] cmd, String cmdStr) {
        String key = cmd[1];
        String member = cmd[2];
        MArrayList<String> arrayList = getArrayList(key);
        boolean remove = arrayList.remove(member);
        if (remove){
            return ResponseMsg.SUCCESS;
        }
        return ResponseMsg.FAIL;
    }

    @Override
    public int getCmdNum() {
        return S_REMOVE_CMD_NUM;
    }
}
