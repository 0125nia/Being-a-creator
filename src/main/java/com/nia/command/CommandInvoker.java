package com.nia.command;

/**
 * 使用命令的入口
 * 命令模式中的调用者
 */
public class CommandInvoker {
    //持有命令享元工厂对象
    private static CommandFactory factory = new CommandFactory();


    private CommandInvoker() {
    }

    //定义一个静态内部类
    private static class CommandInvokerHolder {
        //在内部类中声明并初始化外部类的对象
        private static final CommandInvoker INSTANCE = new CommandInvoker();
    }

    //提供公共的访问方式
    public static CommandInvoker getInstance() {
        return CommandInvokerHolder.INSTANCE;
    }


    /**
     * 执行命令对象方法
     *
     * @param cmd  指令
     * @param flag 是否对客户端进行响应
     */
    public String executeCommand(String cmd, boolean flag) {
        //去掉空白字符
        String[] cmdArr = cmd.split("\\s+");

        String cmdType = cmdArr[0].toUpperCase();

        Command command = factory.getCommand(cmdType);

        String result = command.execute(cmd);
        //向服务端回写响应数据
        if (flag) {
            return result;
        }
        return null;
    }

}
