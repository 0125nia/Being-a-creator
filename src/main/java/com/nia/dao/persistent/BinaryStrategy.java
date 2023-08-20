package com.nia.dao.persistent;

import com.nia.dao.loader.ConfigLoader;
import com.nia.dao.loader.DataCacheProcessor;
import com.nia.pojo.MMap;
import com.nia.pojo.hashmap.MHashMap;
import com.nia.reactor.Reactor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class BinaryStrategy implements PersistenceStrategy {

    private static final Path path = Path.of(ConfigLoader.getString("RDB"));//根据文件路径创建Path对象
    private static MHashMap<String, byte[]> binaryDataMap = new MHashMap<>();//存储二进制数据的map


    @Override
    public void save() {
        if (binaryDataMap.isEmpty()) {
            return;
        }
        byte[] bytes = new byte[0];
        for (MMap.MEntry<String, byte[]> entry : binaryDataMap) {
            String key = entry.getKey();
            byte[] value = entry.getValue();
        }

        //写入文件
        try (FileChannel fileChannel = FileChannel.open(path,
                StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            //放入缓冲区
            ByteBuffer buf = ByteBuffer.wrap(bytes);
            //写入文件
            fileChannel.write(buf);
            //清空map
            binaryDataMap = new MHashMap<>();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Reactor.LOGGER.info("save data");//将save操作写入日志
    }


    @Override
    public <V> void load(String key) {


    }

    @Override
    public void appendQueue(String cmd, byte sign) {
        String key = cmd.split("\\s+")[1];
        Object value = DataCacheProcessor.get(key);
        byte[] bytes = convertToBinary(value);
        binaryDataMap.put(key, bytes);
    }


    private byte[] convertToBinary(Object value) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);

            // 序列化链表对象
            objectOutputStream.writeObject(value);
            // 获取序列化后的字节数组
            byte[] binaryData = byteArrayOutputStream.toByteArray();

            objectOutputStream.close();
            byteArrayOutputStream.close();

            return binaryData;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
