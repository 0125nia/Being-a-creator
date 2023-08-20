package com.nia.command;

import com.nia.pojo.hashmap.MHashMap;

/**
 * hget [key] [field]
 */
public class HGetCommand implements AbstractHashMapCommand {

    private static final int HGET_CMD_NUM = 3;

    @Override
    public String handleCommand(String[] cmd, String cmdStr) {
        String key = cmd[1];
        String field = cmd[2];
        MHashMap<String, String> hashMap = getHashMap(key);
        return hashMap.get(field);
    }

    @Override
    public int getCmdNum() {
        return HGET_CMD_NUM;
    }
}
