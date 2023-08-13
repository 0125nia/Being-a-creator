package com.nia.dao.persistent;

import com.nia.dao.conf.ConfigLoader;
import com.nia.pojo.Data;
import com.nia.pojo.MList;
import com.nia.pojo.arraylist.MArrayList;
import org.junit.Test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;

/**
 * 追加日志持久化策略类
 */
public class LogAppendingStrategy implements PersistenceStrategy {

    private static MArrayList<String> commandsList = new MArrayList<>();//存放追加指令的集合
    private static String filePath = ConfigLoader.getString("AOF");//文件路径

    /**
     * 将新的指令存放到集合中
     * @param cmd 追加的指令
     */
    public static void appendCmd(String cmd){
        // ISO-8601 规范定义的日期格式"yyyy-MM-dd'T'HH:mm:ss"
        String time = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        cmd = "[" + time + "] " + cmd;
        //处理后的指令存放到集合中
        commandsList.add(cmd);
    }

    /**
     * 将集合中的字符串整合成一个字符串
     * @return 返回处理后的字符串
     */
    private String commandsListToStr(){
        StringBuilder sb = new StringBuilder();
        //遍历,插入\n换行符
        for (String cmd : commandsList) {
            sb.append(cmd).append("\n");
        }
        return sb.toString();
    }

    @Override
    public void save() {
        //判断是否有追加的数据
        if (commandsList.isEmpty()){
            return;
        }
        //创建Path对象
        Path path = Path.of(filePath);
        //获取转化后的追加字符串
        String commands = commandsListToStr();
        try {
            //获取通道
            FileChannel fileChannel = FileChannel.open(path,
                    StandardOpenOption.CREATE,StandardOpenOption.APPEND);
            //放入缓冲区
            ByteBuffer buf = ByteBuffer.wrap(commands.getBytes(StandardCharsets.UTF_8));
            //写入文件
            fileChannel.write(buf);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Data load() {
        return null;
    }




}
