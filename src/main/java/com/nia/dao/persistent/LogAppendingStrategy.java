package com.nia.dao.persistent;

import com.nia.dao.loader.ConfigLoader;
import com.nia.pojo.Data;
import com.nia.pojo.arraylist.MArrayList;
import com.nia.command.CommandInvoker;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

/**
 * 追加日志持久化策略类
 */
public class LogAppendingStrategy implements PersistenceStrategy {

    private static MArrayList<String> commandsList = new MArrayList<>();//存放追加指令的集合
    private static final Path path = Path.of(ConfigLoader.getString("AOF"));//根据文件路径创建Path对象
    private static boolean isDataLoaded = false;


    /**
     * 将新的指令存放到集合中
     *
     * @param cmd 追加的指令
     */
    @Override
    public void appendCmd(String cmd) {
        if (!isDataLoaded) {
            return;
        }
        // ISO-8601 规范定义的日期格式"yyyy-MM-dd'T'HH:mm:ss"
        String time = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        cmd = "[" + time + "] " + cmd;
        //处理后的指令存放到集合中
        commandsList.add(cmd);
    }

    /**
     * 将集合中的字符串整合成一个字符串
     *
     * @return 返回处理后的字符串
     */
    private String commandsListToStr() {
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
        if (commandsList.isEmpty()) {
            return;
        }
        //获取转化后的追加字符串
        String commands = commandsListToStr();
        try {
            //获取通道
            FileChannel fileChannel = FileChannel.open(path,
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
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
        //创建Data对象
        Data data = new Data();
        //存放日志文件内容的字符串
        //判断文件是否存在
        if (!Files.exists(path)) {
            //若不存在则直接返回
            return data;
        }
        //获取FileChannel
        try (FileChannel channel = FileChannel.open(path, StandardOpenOption.READ, StandardOpenOption.CREATE)) {
            //读取日志文件中的指令
            String commands = getCommands(channel);
            //判断读取的字符串是否只含有空格或为空
            if (!commands.trim().isEmpty()) {
                String[] split = commands.split("\n");
                //使用Stream流对数据中的数据进行处理
                String[] commandArray = processCommands(split);
                data = executeCmd(commandArray);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return data;
    }

    /**
     * 获取文件中的指令
     *
     * @param channel FileChannel
     * @return 返回读取到的指令
     * @throws IOException 抛出异常
     */
    private String getCommands(FileChannel channel) throws IOException {
        String commands = "";
        //读取数据
        ByteBuffer buf = ByteBuffer.allocate((int) channel.size());
        int bytesRead = channel.read(buf);
        if (bytesRead > 0) {
            //将缓冲区切换到读模式
            buf.flip();
            byte[] bytes = new byte[buf.remaining()];
            buf.get(bytes);
            commands = new String(bytes);
            buf.clear();
        }
        return commands;
    }

    /**
     * 处理命令数组
     *
     * @param split 由换行符分隔的命令数组
     * @return 去除多余字符处理后的结果
     */
    private String[] processCommands(String[] split) {
        return Arrays.stream(split).
                map(s -> s.substring(s.indexOf("]") + 2)).
                toArray(String[]::new);
    }

    /**
     * 将指令加载到缓存
     *
     * @param commandArray 存储指令的数组
     * @return 返回执行完指令的数据
     */
    private Data executeCmd(String[] commandArray) {
        //创建Data对象作为执行指令的数据
        Data data = new Data();
        //创建指令
        CommandInvoker invoker = new CommandInvoker();
        for (String cmd : commandArray) {
            invoker.executeCommand(cmd, data, false);
        }
        isDataLoaded = true;
        return data;
    }


}
