package com.nia.service;

/**
 * 执行命令的接口
 * 命令模式中的抽象命令类
 */
public interface Command {
    String execute(String cmd);
}
