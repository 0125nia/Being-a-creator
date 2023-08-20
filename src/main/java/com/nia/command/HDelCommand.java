package com.nia.command;

import com.nia.dao.loader.DataCacheProcessor;
import com.nia.dao.persistent.PersistenceContext;
import com.nia.pojo.PersistentDataIdentifier;
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
    public String execute(String cmdStr) {
        String[] cmd = cmdStr.split("\\s+");

        if (cmd.length != HDEL_BY_KEY && cmd.length != HDEL_BY_FIELD) {
            Command error = new CommandFactory().getCommand("ERROR");
            return error.execute(cmdStr);
        }
        Object o;
        String key = cmd[1];
        if (cmd.length == HDEL_BY_KEY) {
            o = DataCacheProcessor.remove(key);
        } else {
            String field = cmd[2];
            MHashMap<String, String> hashMap = getHashMap(key);
            o = hashMap.remove(field);
        }
        //修改数据到持久化策略类中
        PersistenceContext.appendToStrategy(cmdStr, PersistentDataIdentifier.LINKEDLIST_DATA);
        //返回响应信息
        return o == null ? ResponseMsg.FAIL : ResponseMsg.SUCCESS;



    }

    //设置为不可用
    @Deprecated
    @Override
    public int getCmdNum() {
        return 0;
    }

    @Deprecated
    @Override
    public String handleCommand(String[] cmd, String cmdStr) {
        return null;
    }

}
