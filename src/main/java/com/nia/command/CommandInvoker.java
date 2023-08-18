package com.nia.command;

import com.nia.pojo.Data;

/**
 * 使用命令的入口
 * 命令模式中的调用者
 */
public class CommandInvoker {
    //持有命令享元工厂对象
    private CommandFactory factory;


    public CommandInvoker() {
        factory = new CommandFactory();
    }


    /**
     * 执行命令对象方法
     *
     * @param cmd  指令
     * @param data 指令执行的数据对象
     * @param flag 是否对客户端进行响应
     */
    public String executeCommand(String cmd, Data data, boolean flag) {
        //去掉空白字符
        String[] cmdArr = cmd.split("\\s+");

        String cmdType = cmdArr[0].toUpperCase();

        Command command = factory.getCommand(cmdType);

        String result = command.execute(cmdArr, cmd, data);
        //向服务端回写响应数据
        if (flag) {
            return result;
        }
        return null;
    }

}
