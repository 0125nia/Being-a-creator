package com.nia.command;

import com.nia.dao.persistent.PersistenceContext;
import com.nia.pojo.PersistentDataIdentifier;
import com.nia.pojo.ResponseMsg;
import com.nia.pojo.hashmap.MHashMap;
import com.nia.pojo.linkedlist.MLinkedList;

/**
 * 链表数据pop命令操作的抽象父类
 * lpop [key]
 * rpop [key]
 */
public abstract class AbstractPopCommand implements AbstractLinkedListCommand {
    private static final int POP_CMD_NUM = 2;//链表push指令的指令数

    @Override
    public String handleCommand(String[] cmd, String cmdStr) {
        String key = cmd[1];
        MHashMap<String, MLinkedList<String>> linkedListData = new MHashMap<>();
        MLinkedList<String> linkedList = linkedListData.get(key);
        if (linkedList == null || linkedList.isEmpty()) {
            return ResponseMsg.FAIL;
        }
        String s = popFromList(linkedList);
        //修改数据到持久化策略类中
        PersistenceContext.appendToStrategy(cmdStr, PersistentDataIdentifier.LINKEDLIST_DATA);
        return s;//返回删除的元素
    }
    //pop抽象方法
    protected abstract String popFromList(MLinkedList<String> linkedList);

    @Override
    public int getCmdNum() {
        return POP_CMD_NUM;
    }
}
