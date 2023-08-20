package com.nia.command;

import com.nia.dao.persistent.PersistenceContext;
import com.nia.pojo.PersistentDataIdentifier;
import com.nia.pojo.ResponseMsg;
import com.nia.pojo.arraylist.MArrayList;

/**
 * sadd [key] [member1] [member2] ...
 */
public class SAddCommand implements AbstractArrayListCommand{
    private static final int MIN_S_ADD_CMD_NUM = 3;
    @Override
    public String execute(String cmdStr) {
        String[] cmd = cmdStr.split("\\s+");
        if (cmd.length < MIN_S_ADD_CMD_NUM){
            Command error = new CommandFactory().getCommand("ERROR");
            return error.execute(cmdStr);
        }
        String key = cmd[1];
        MArrayList<String> arrayList = getArrayList(key);
        for (int i = 2; i < cmd.length; i++) {
            arrayList.add(cmd[i]);
        }
        PersistenceContext.appendToStrategy(cmdStr, PersistentDataIdentifier.ARRAYLIST_DATA);
        return ResponseMsg.SUCCESS;
    }


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
