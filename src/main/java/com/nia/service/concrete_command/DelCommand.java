package com.nia.service.concrete_command;

import com.nia.dao.persistent.PersistenceContext;
import com.nia.pojo.Data;
import com.nia.pojo.hashmap.MHashMap;
import com.nia.service.Command;

/**
 * del [key]
 */
public class DelCommand implements Command {

    private static final int DEL_CMD_NUM = 2;

    @Override
    public String execute(String[] cmd, String cmdStr, Data data) {
        if (!isCorrectCmd(cmd, DEL_CMD_NUM)) {
            return new ErrorCommand().execute(cmd, cmdStr, data);
        }
        String key = cmd[1];
        MHashMap<String, String> stringData = data.getStringData();
        String remove = stringData.remove(key);
        //追加指令到日志文件中
        PersistenceContext.appendCmd(cmdStr);
        //返回成功数据
        return remove;
    }

}
