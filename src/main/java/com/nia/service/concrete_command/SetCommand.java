package com.nia.service.concrete_command;

import com.nia.dao.persistent.PersistenceContext;
import com.nia.pojo.Data;
import com.nia.pojo.ResponseMsg;
import com.nia.pojo.hashmap.MHashMap;
import com.nia.service.Command;

/**
 * set [key] [value]
 */
public class SetCommand implements Command {
    private static final int SET_CMD_NUM = 3;

    @Override
    public String execute(String[] cmd, String cmdStr, Data data) {
        //判断
        if(!isCorrectCmd(cmd, SET_CMD_NUM)){
            return new ErrorCommand().execute(cmd, cmdStr, data);
        }
        //对字符串数据进行操作
        MHashMap<String, String> stringData = data.getStringData();
        stringData.put(cmd[1],cmd[2]);
        //追加指令到日志文件中
        PersistenceContext.appendCmd(cmdStr);
        //返回成功数据
        return ResponseMsg.SUCCESS;
    }
}
