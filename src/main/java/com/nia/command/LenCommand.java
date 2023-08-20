package com.nia.command;

import com.nia.pojo.linkedlist.MLinkedList;

/**
 * len [key]
 */
public class LenCommand implements AbstractLinkedListCommand {
    private static final int LEN_CMD_NUM = 2;//链表len的指令数

    @Override
    public String handleCommand(String[] cmd, String cmdStr) {
        String key = cmd[1];
        //获取链表
        MLinkedList<String> linkedList = getLinkedList(key);
        int size = linkedList.size();
        //将链表长度转化为字符串后返回
        return String.valueOf(size);
    }

    @Override
    public int getCmdNum() {
        return LEN_CMD_NUM;
    }


}
