package com.nia.service.concrete_command;

import com.nia.dao.persistent.PersistenceContext;
import com.nia.pojo.Data;
import com.nia.pojo.ResponseMsg;
import com.nia.pojo.hashmap.MHashMap;
import com.nia.pojo.linkedlist.MLinkedList;

/**
 * 链表数据push命令操作的抽象父类
 * rpush [key] [value]
 * lpush [key] [value]
 */
public abstract class AbstractPushCommand implements AbstractLinkedListCommand {
    private static final int PUSH_CMD_NUM = 3;//链表push指令的指令数

    @Override
    public String execute(String[] cmd, String cmdStr, Data data) {
        String key = cmd[1];
        String value = cmd[2];
        //判断是否符合指令数
        if (!isCorrectCmd(cmd, PUSH_CMD_NUM)) {
            return new ErrorCommand().execute(cmd, cmdStr, data);
        }
        //获取链表数据
        MHashMap<String, MLinkedList<String>> linkedListData = data.getLinkedListData();
        MLinkedList<String> linkedList = getLinkedList(linkedListData, key);
        pushToList(linkedList, value);
        //追加指令到日志文件中
        PersistenceContext.appendCmd(cmdStr);
        //返回信息
        return ResponseMsg.SUCCESS;
    }

    /**
     * 抽象方法 添加元素到链表
     *
     * @param linkedList 对应链表
     * @param value      需要添加的元素
     */
    protected abstract void pushToList(MLinkedList<String> linkedList, String value);


}
