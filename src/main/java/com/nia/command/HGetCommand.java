package com.nia.command;

import com.nia.pojo.Data;
import com.nia.pojo.hashmap.MHashMap;

/**
 * hget [key] [field]
 */
public class HGetCommand implements AbstractHashMapCommand {

    private static final int HGET_CMD_NUM = 3;

    @Override
    public String execute(String[] cmd, String cmdStr, Data data) {
        if (!isCorrectCmd(cmd, HGET_CMD_NUM)) {
            return new ErrorCommand().execute(cmd, cmdStr, data);
        }
        String key = cmd[1];
        String field = cmd[2];
        MHashMap<String, MHashMap<String, String>> mapData = data.getMapData();
        MHashMap<String, String> hashMap = getHashMap(mapData, key);
        return hashMap.get(field);
    }
}
