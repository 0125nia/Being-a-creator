package com.nia.dao.loader;


import java.io.IOException;
import java.util.Properties;

/**
 * 配置文件加载器
 */
public class ConfigLoader {
    private static Properties properties;

    //私有化对象,拒绝外界创建
    private ConfigLoader() {}

    static {
        try {
            //获取类加载器
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            // 读取properties文件内容并初始化Properties对象
            properties = new Properties();
            properties.load(classLoader.getResourceAsStream("config.properties"));
        } catch (NullPointerException e) {
            System.out.println("文件获取失败");
        } catch (IOException e) {
            System.out.println("配置文件加载失败: " + e.getMessage());
        }

    }

    /**
     * 获取参数值
     * @param key 参数名
     * @return 返回参数名对应的字符串参数值
     */
    public static String getString(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new NullPointerException("获取的参数为空!");
        }
        return value;
    }

    /**
     * 将配置文件中获取到的字符串参数值转化为Int类型并返回
     * @param key 参数名
     * @return 返回Int类型参数值
     */
    public static int getInt(String key) {
        return Integer.parseInt(getString(key));
    }

    /**
     * 获取配置文件的键值对对象
     * @return 返回键值对对象
     */
    public static Properties getProperties() {
        if (!properties.isEmpty()) {
            return properties;
        }
        throw new NullPointerException("加载配置文件失败!");
    }


}
