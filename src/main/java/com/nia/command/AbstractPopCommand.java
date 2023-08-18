package com.nia.command;

import com.nia.dao.persistent.PersistenceContext;
import com.nia.pojo.Data;
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
        String s = popFromList(linkedList);
        //追加指令到日志文件中
        PersistenceContext.appendCmd(cmdStr);
        return s;//返回删除的元素
    }
    //pop抽象方法
    protected abstract String popFromList(MLinkedList<String> linkedList);
}
