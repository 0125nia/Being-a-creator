package com.nia.service.concrete_command;

import com.nia.pojo.linkedlist.MLinkedList;

public class RPopCommand extends AbstractPopCommand {

    @Override
    protected String popFromList(MLinkedList<String> linkedList) {
        return linkedList.removeLast();
    }
}
