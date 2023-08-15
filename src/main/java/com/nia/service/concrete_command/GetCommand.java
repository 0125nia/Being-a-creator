package com.nia.service.concrete_command;

import com.nia.pojo.Data;
import com.nia.pojo.hashmap.MHashMap;
import com.nia.service.Command;

/**
 * get [key]
 */
public class GetCommand implements Command {
    private static final int GET_CMD_NUM = 2;

    @Override
    public String execute(String[] cmd, String cmdStr, Data data) {
        if (!isCorrectCmd(cmd, GET_CMD_NUM)) {
            return new ErrorCommand().execute(cmd, cmdStr, data);
        }
        String key = cmd[1];
        MHashMap<String, String> stringData = data.getStringData();
        return stringData.get(key);
    }
}
