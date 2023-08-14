package com.nia.service.concrete_command;

import com.nia.pojo.linkedlist.MLinkedList;

public class LPopCommand extends AbstractPopCommand {

    @Override
    protected void popFromList(MLinkedList<String> linkedList) {
        linkedList.removeFirst();
    }
}
