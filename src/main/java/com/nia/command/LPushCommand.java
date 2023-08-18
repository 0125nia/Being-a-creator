package com.nia.command;

import com.nia.pojo.linkedlist.MLinkedList;

public class LPushCommand extends AbstractPushCommand {
    @Override
    protected void pushToList(MLinkedList<String> linkedList, String value) {
        //在链表的左端添加元素
        linkedList.addFirst(value);
    }
}
