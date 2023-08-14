package com.nia.service.concrete_command;

import com.nia.dao.persistent.PersistenceContext;
import com.nia.pojo.Data;
import com.nia.pojo.ResponseMsg;
import com.nia.pojo.hashmap.MHashMap;
import com.nia.pojo.linkedlist.MLinkedList;
import com.nia.service.Command;

public abstract class AbstractPopCommand implements Command {
    private static final int POP_CMD_NUM = 2;//链表push指令的指令数

    @Override
    public String execute(String[] cmd, String cmdStr, Data data) {
        String key = cmd[1];
        //判断是否符合指令数
        if (!isCorrectCmd(cmd, POP_CMD_NUM)) {
            return new ErrorCommand().execute(cmd, cmdStr, data);
        }
        MHashMap<String, MLinkedList<String>> linkedListData = data.getLinkedListData();
        MLinkedList<String> linkedList = linkedListData.get(key);
        if (linkedList == null || linkedList.isEmpty()) {
            return ResponseMsg.FAIL;
        }
        popFromList(linkedList);
        //追加指令到日志文件中
        PersistenceContext.appendCmd(cmdStr);
        return ResponseMsg.SUCCESS;
    }

    protected abstract void popFromList(MLinkedList<String> linkedList);


}
