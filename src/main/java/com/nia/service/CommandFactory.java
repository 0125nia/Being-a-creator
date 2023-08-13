package com.nia.service;

import com.nia.dao.conf.CommandConfigLoader;
import com.nia.pojo.MMap;
import com.nia.service.concrete_command.ErrorCommand;

/**
 * 享元工厂对象
 * 将已创建的命令对象进行缓存,返回已缓存的对象
 */
public class CommandFactory {

    private MMap<String, Command> commandMap;

    public CommandFactory() {

    }

    public Command getCommand(String cmd){
        if (commandMap == null){
            initializeCommandMap();
        }
        Command command = commandMap.get(cmd);
        if (command == null){
            return new ErrorCommand();
        }
        return command;
    }




    private synchronized void initializeCommandMap() {
        if (commandMap == null) {
            commandMap = CommandConfigLoader.getCommandsMap();
        }
    }

}
