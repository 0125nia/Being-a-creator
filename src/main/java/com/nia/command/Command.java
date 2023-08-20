package com.nia.command;

/**
 * 执行命令的接口
 * 命令模式中的抽象命令类
 */
public interface Command {

    /**
     * 处理命令默认方法
     *
     * @param cmdStr 一整条命令字符串
     * @return 返回命令处理的结果
     */
    default String execute(String cmdStr) {
        String[] cmd = cmdStr.split("\\s+");
        //判断是否符合指令数
        if (!isCorrectCmd(cmd, getCmdNum())) {
            Command error = new CommandFactory().getCommand("ERROR");
            return error.execute(cmdStr);
        }
        //返回具体命令类处理结果
        try {
            return handleCommand(cmd, cmdStr);
        } catch (NullPointerException e) {
            return null;
        }
    }

    //具体实现的命令处理方法
    String handleCommand(String[] cmd, String cmdStr);

    //获取具体命令类的指令数
    int getCmdNum();

    /**
     * 判断命令数是否合法
     *
     * @param cmd    命令数组
     * @param cmdNum 命令数
     * @return 返回判断结果
     */
    default boolean isCorrectCmd(String[] cmd, int cmdNum) {
        return cmd.length == cmdNum;
    }
}
