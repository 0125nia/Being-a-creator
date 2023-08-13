package com.nia.dao.conf;

import com.nia.pojo.hashmap.MHashMap;
import com.nia.service.Command;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

public class CommandConfigLoader {
    private static MHashMap<String,Command> commandsMap;    //存储命令以及其对应的具体命令对象的map
    private static boolean isInitialized = false;           //初始化标志

    /**
     * 加载配置信息
     */
    private static void loadCommandConfig(){

        if (isInitialized){
            return;
        }
        commandsMap = new MHashMap<>();
        Properties properties = new Properties();
        //获取类加载器
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        // 读取properties文件内容并初始化Properties对象
        try (InputStream is = classLoader.getResourceAsStream("command.properties")) {
            properties.load(is);

            //遍历将命令与类放入map
            for (String commandName : properties.stringPropertyNames()) {
                String className = properties.getProperty(commandName);
                //加载具体命令对象
                Class<?> commandClass = Class.forName(className);

                //创建具体命令对象实例
                Command command = (Command) commandClass.getDeclaredConstructor().newInstance();

                //将字符串指令与命令对象放入map
                commandsMap.put(commandName, command);
            }

            //将初始化标志设置为已初始化
            isInitialized = true;
        } catch (IOException e) {
            System.out.println("读取文件失败" + e.getMessage());
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("配置文件类的读取或创建错误" + e.getMessage());
        }
    }

    public static MHashMap<String,Command> getCommandsMap(){
        loadCommandConfig();//初始化
        return commandsMap;//返回map
    }




}
