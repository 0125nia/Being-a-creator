package com.nia.command;

import com.nia.pojo.Data;

/**
 * 执行命令的接口
 * 命令模式中的抽象命令类
 */
public interface Command {

    String execute(String[] cmd, String cmdStr, Data data);

    default boolean isCorrectCmd(String[] s,int cmdNum){
        //判断指令数是否正确
        return s.length == cmdNum;
    }
}
