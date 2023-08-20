package com.nia.command;

import com.nia.dao.persistent.PersistenceContext;
import com.nia.pojo.PersistentDataIdentifier;
import com.nia.pojo.ResponseMsg;
import com.nia.pojo.hashmap.MHashMap;

/**
 * hset [field] [key] [value]
 */
public class HSetCommand implements AbstractHashMapCommand {
    private static final int HSET_CMD_NUM = 4;

    @Override
    public String handleCommand(String[] cmd, String cmdStr) {
        String key = cmd[1];
        String field = cmd[2];
        String value = cmd[3];
        MHashMap<String, String> hashMap = getHashMap(key);
        hashMap.put(field, value);
        //修改数据到持久化策略类中
        PersistenceContext.appendToStrategy(cmdStr, PersistentDataIdentifier.HASHMAP_DATA);
        //返回成功数据
        return ResponseMsg.SUCCESS;
    }

    @Override
    public int getCmdNum() {
        return HSET_CMD_NUM;
    }
}
