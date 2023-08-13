package com.nia.service.concrete_command;

import com.nia.service.Command;

public class ErrorCommand implements Command {
    @Override
    public String execute(String cmd) {
return "error";
    }
}
