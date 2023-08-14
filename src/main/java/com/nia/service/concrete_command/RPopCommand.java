package com.nia.service.concrete_command;

import com.nia.pojo.linkedlist.MLinkedList;

public class RPopCommand extends AbstractPopCommand {

    @Override
    protected void popFromList(MLinkedList<String> linkedList) {
        linkedList.removeLast();
    }
}
