package com.nia.service.concrete_command;

import com.nia.service.Command;

public class SetCommand implements Command {


    @Override
    public String execute(String cmd) {
        System.out.println(cmd);
        return "set";
    }
}
