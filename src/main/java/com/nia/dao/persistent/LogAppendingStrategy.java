package com.nia.dao.persistent;

import com.nia.command.CommandInvoker;
import com.nia.dao.loader.ConfigLoader;
import com.nia.pojo.MMap;
import com.nia.pojo.arraylist.MArrayList;
import com.nia.pojo.hashmap.MHashMap;
import com.nia.reactor.Reactor;

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

    public static MHashMap<String, MArrayList<String>> commandsList = new MHashMap<>();//存放追加指令的集合
    private static final Path path = Path.of(ConfigLoader.getString("AOF"));//根据文件路径创建Path对象


    /**
     * 将新的指令存放到集合中
     *
     * @param cmd  追加的指令
     * @param sign 数据类型标识
     */
    @Override
    public void appendQueue(String cmd, byte sign) {
        String key = cmd.split("\\s+")[1];

        // ISO-8601 规范定义的日期格式"yyyy-MM-dd'T'HH:mm:ss"
        String time = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        cmd = sign + "$" + key + "[" + time + "] " + cmd;
        //处理后的指令存放到集合中
        MArrayList<String> list = commandsList.get(key);
        if (list == null) {
            list = new MArrayList<>();
            list.add(cmd);
            commandsList.put(key, list);
        } else {
            list.add(cmd);
        }
    }


    /**
     * 将集合中的字符串整合成一个字符串
     *
     * @return 返回处理后的字符串
     */
    private String commandsListToStr() {
        StringBuilder sb = new StringBuilder();
        //遍历,插入\n换行符
        for (MMap.MEntry<String, MArrayList<String>> entry : commandsList) {
            MArrayList<String> list = entry.getValue();
            for (String cmd : list) {
                sb.append(cmd).append("\n");
            }
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
            //清空map
            commandsList = new MHashMap<>();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean load(String key) {
        //存放日志文件内容的字符串
        //判断文件是否存在
        if (!Files.exists(path)) {
            //若不存在则新建并直接返回
            try {
                Files.createFile(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }
        //获取FileChannel
        try (FileChannel channel = FileChannel.open(path, StandardOpenOption.READ, StandardOpenOption.CREATE)) {
            //读取日志文件中的指令
            String commands = getCommands(channel);
            //判断读取的字符串是否只含有空格或为空
            if (!commands.trim().isEmpty()) {
                String[] keyCommands = getKeyCommands(commands, key);
                if (keyCommands.length == 0){
                    return false;
                }
                //使用Stream流对数据中的数据进行处理
                String[] commandArray = processCommands(keyCommands);
                //写入缓存
                executeCmd(commandArray);
                return true;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private String[] getKeyCommands(String commands, String key) {
        MArrayList<String> keyCmds = new MArrayList<>();
        String[] split = commands.split("\n");
        for (String cmd : split) {
            String[] split1 = cmd.split("\\[")[1].split("&");
            String key1 = split1[1];
            if (key.equals(key1)) {
                keyCmds.add(cmd);
            }
        }
        String[] keyCommands = new String[keyCmds.size()];
        for (int i = 0; i < keyCmds.size(); i++) {
            keyCommands[i] = keyCmds.get(i);
        }
        return keyCommands;
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
     * @param keyCommands 需操作的命令数组
     * @return 去除多余字符处理后的结果
     */
    private String[] processCommands(String[] keyCommands) {
        return Arrays.stream(keyCommands).
                map(s -> s.substring(s.indexOf("]") + 2)).
                toArray(String[]::new);
    }

    /**
     * 将指令加载到缓存
     *
     * @param commandArray 存储指令的数组
     */
    private void executeCmd(String[] commandArray) {
        //创建指令
        CommandInvoker invoker = CommandInvoker.getInstance();
        for (String cmd : commandArray) {
            invoker.executeCommand(cmd, false);
        }
    }


}
