package com.nia.dao.conf;


import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {
    private static Properties properties;

    static {
        try {

            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

            // 读取properties文件内容并初始化Properties对象
            properties = new Properties();
            properties.load(classLoader.getResourceAsStream("config.properties"));
        }catch (NullPointerException e){
            System.out.println("文件获取失败");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static String getString(String key) {
        String value = properties.getProperty(key);
        if (value == null){
            throw new NullPointerException("获取的参数为空!");
        }
        return value;
    }

    public static int getInt(String key){
        return Integer.parseInt(getString(key));
    }

    public static Properties getProperties(){
        if (!properties.isEmpty()){
            return properties;
        }
        throw new NullPointerException("加载配置文件失败!");
    }



}
