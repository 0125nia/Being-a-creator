package com.nia.service;

/**
 * 使用命令的入口
 * 命令模式中的调用者
 */
public class Invoker {
    //持有命令对象
    private Command command;

    public Invoker(Command command) {
        this.command = command;
    }

    //执行命令对象方法
    public void executeCommand(){
        if (command != null){
            command.execute(null);
        }
    }
}
