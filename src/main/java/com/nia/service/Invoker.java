package com.nia.service;

import com.nia.pojo.hashmap.MHashMap;
import org.junit.Test;

/**
 * 使用命令的入口
 * 命令模式中的调用者
 */
public class Invoker {
    //持有命令享元工厂对象
    private CommandFactory factory;
    private MHashMap<String,Command> map;

    public Invoker() {
        factory = new CommandFactory();
    }


    //执行命令对象方法
    public void executeCommand(String cmd){
        String[] tokens = cmd.split(" ");
        String commandType = tokens[0];
        Command command = factory.getCommand(commandType);
        String result = command.execute(cmd);
        System.out.println(result);

    }

}
