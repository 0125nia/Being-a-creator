package com.nia.command;

import com.nia.dao.persistent.PersistenceContext;
import com.nia.pojo.Data;
import com.nia.pojo.ResponseMsg;
import com.nia.pojo.hashmap.MHashMap;

/**
 * hdel [key] [field]
 * hdel [key]
 */
public class HDelCommand implements AbstractHashMapCommand {

    private static final int HDEL_BY_FIELD = 3;
    private static final int HDEL_BY_KEY = 2;

    @Override
    public String execute(String[] cmd, String cmdStr, Data data) {
        if (!isCorrectCmd(cmd, HDEL_BY_FIELD) && !isCorrectCmd(cmd, HDEL_BY_KEY)) {
            return new ErrorCommand().execute(cmd, cmdStr, data);
        }
        String key = cmd[1];
        MHashMap<String, MHashMap<String, String>> mapData = data.getMapData();
        if (cmd.length == HDEL_BY_FIELD){
            String field = cmd[2];
            MHashMap<String, String> hashMap = getHashMap(mapData, key);
            String remove = hashMap.remove(field);
            //追加指令到日志文件中
            PersistenceContext.appendCmd(cmdStr);
            return remove;
        }else {
            mapData.remove(key);
            //追加指令到日志文件中
            PersistenceContext.appendCmd(cmdStr);
            return ResponseMsg.SUCCESS;
        }


    }


}
