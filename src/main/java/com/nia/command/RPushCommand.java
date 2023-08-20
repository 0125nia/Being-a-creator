package com.nia.command;

import com.nia.pojo.linkedlist.MLinkedList;

public class RPushCommand extends AbstractPushCommand {

    @Override
    protected void pushToList(MLinkedList<String> linkedList, String value) {
        //在链表的右端添加元素
        linkedList.addLast(value);
    }

}
