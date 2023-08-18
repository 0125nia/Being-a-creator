package com.nia.command;

import com.nia.dao.persistent.PersistenceContext;
import com.nia.pojo.Data;
import com.nia.pojo.ResponseMsg;
import com.nia.pojo.hashmap.MHashMap;

/**
 * hset [field] [key] [value]
 */
public class HSetCommand implements AbstractHashMapCommand {
    private static final int HSET_CMD_NUM = 4;

    @Override
    public String execute(String[] cmd, String cmdStr, Data data) {
        System.out.println("Hset");
        if (!isCorrectCmd(cmd, HSET_CMD_NUM)) {
            return new ErrorCommand().execute(cmd, cmdStr, data);
        }
        String key = cmd[1];
        String field = cmd[2];
        String value = cmd[3];
        MHashMap<String, MHashMap<String, String>> mapData = data.getMapData();
        MHashMap<String, String> map = getHashMap(mapData, key);
        map.put(field, value);
        //追加指令到日志文件中
        PersistenceContext.appendCmd(cmdStr);
        //返回成功数据
        return ResponseMsg.SUCCESS;
    }


}
