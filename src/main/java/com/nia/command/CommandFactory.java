package com.nia.command;

import com.nia.dao.loader.CommandConfigLoader;
import com.nia.pojo.MMap;

/**
 * 享元工厂对象
 * 将已创建的命令对象进行缓存,返回已缓存的对象
 */
public class CommandFactory {

    private static MMap<String, Command> commandMap;

    /**
     * 返回指令对应的具体命令对象
     *
     * @param commandType 指令的类型
     * @return 对应的具体命令对象
     */
    public Command getCommand(String commandType) {
        //判断是否初始化
        if (commandMap == null) {
            initializeCommandMap();
        }
        //获取该指令类型对应在map中存储的具体命令对象
        Command command = commandMap.get(commandType);
        //判断是否为空,为空则返回新的错误指令结果
        if (command == null) {
            return new ErrorCommand();
        }
        return command;
    }


    //懒加载数据,从命令配置文件加载器中获取map
    //双重检查锁保证线程安全且只加载一次数据,提高性能
    private synchronized void initializeCommandMap() {
        //判断是否加载成功
        if (commandMap == null) {
            commandMap = CommandConfigLoader.getCommandsMap();
        }
    }

}
