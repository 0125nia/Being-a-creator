package com.nia.command;

import com.nia.dao.persistent.PersistenceContext;
import com.nia.pojo.PersistentDataIdentifier;
import com.nia.pojo.ResponseMsg;
import com.nia.pojo.linkedlist.MLinkedList;

/**
 * 链表数据push命令操作的抽象父类
 * rpush [key] [value]
 * lpush [key] [value]
 */
public abstract class AbstractPushCommand implements AbstractLinkedListCommand {
    private static final int PUSH_CMD_NUM = 3;//链表push指令的指令数

    @Override
    public String handleCommand(String[] cmd, String cmdStr) {
        String key = cmd[1];
        String value = cmd[2];
        //获取链表数据
        MLinkedList<String> linkedList = getLinkedList(key);
        pushToList(linkedList, value);
        //修改数据到持久化策略类中
        PersistenceContext.appendToStrategy(cmdStr, PersistentDataIdentifier.LINKEDLIST_DATA);
        System.out.println("111");
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

    @Override
    public int getCmdNum() {
        return PUSH_CMD_NUM;
    }
}
