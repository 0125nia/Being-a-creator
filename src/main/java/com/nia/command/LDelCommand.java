package com.nia.command;

import com.nia.dao.loader.DataCacheProcessor;
import com.nia.dao.persistent.PersistenceContext;
import com.nia.pojo.PersistentDataIdentifier;
import com.nia.pojo.ResponseMsg;
import com.nia.pojo.linkedlist.MLinkedList;

/**
 * 直接删除key以及对应的链表
 * ldel [key]
 */
public class LDelCommand implements AbstractLinkedListCommand {
    private static final int LDEL_CMD_NUM = 2;//链表push指令的指令数

    @Override
    public String handleCommand(String[] cmd, String cmdStr) {
        String key = cmd[1];
        //移除key对应的链表
        MLinkedList<String> remove = DataCacheProcessor.remove(key);
        //修改数据到持久化策略类中
        PersistenceContext.appendToStrategy(cmdStr, PersistentDataIdentifier.LINKEDLIST_DATA);
        //返回响应信息
        return remove == null ? ResponseMsg.FAIL : ResponseMsg.SUCCESS;
    }

    @Override
    public int getCmdNum() {
        return LDEL_CMD_NUM;
    }
}
