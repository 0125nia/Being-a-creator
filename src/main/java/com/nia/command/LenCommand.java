package com.nia.command;

import com.nia.pojo.Data;
import com.nia.pojo.hashmap.MHashMap;
import com.nia.pojo.linkedlist.MLinkedList;

/**
 * len [key]
 */
public class LenCommand implements AbstractLinkedListCommand {
    private static final int LEN_CMD_NUM = 2;//链表len的指令数

    @Override
    public String execute(String[] cmd, String cmdStr, Data data) {
        if (!isCorrectCmd(cmd,LEN_CMD_NUM)){
            return new ErrorCommand().execute(cmd,cmdStr,data);
        }
        String key = cmd[1];
        //获取链表
        MHashMap<String, MLinkedList<String>> linkedListData = data.getLinkedListData();
        MLinkedList<String> linkedList = getLinkedList(linkedListData, key);
        int size = linkedList.size();
        //将链表长度转化为字符串后返回
        return String.valueOf(size);
    }


}
