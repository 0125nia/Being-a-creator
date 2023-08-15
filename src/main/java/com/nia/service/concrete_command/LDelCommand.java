package com.nia.service.concrete_command;

import com.nia.pojo.Data;
import com.nia.pojo.ResponseMsg;
import com.nia.pojo.hashmap.MHashMap;
import com.nia.pojo.linkedlist.MLinkedList;

/**
 * 直接删除key以及对应的链表
 * ldel [key]
 */
public class LDelCommand implements AbstractLinkedListCommand {
    private static final int LDEL_CMD_NUM = 2;//链表push指令的指令数

    @Override
    public String execute(String[] cmd, String cmdStr, Data data) {
        if (!isCorrectCmd(cmd, LDEL_CMD_NUM)) {
            return new ErrorCommand().execute(cmd, cmdStr, data);
        }
        String key = cmd[1];
        MHashMap<String, MLinkedList<String>> linkedListData = data.getLinkedListData();
        //移除key对应的链表
        MLinkedList<String> remove = linkedListData.remove(key);
        //返回响应
        return remove == null ? ResponseMsg.FAIL : ResponseMsg.SUCCESS;
    }
}
