package com.nia.dao.persistent;

import com.nia.dao.loader.ConfigLoader;
import com.nia.dao.loader.DataCacheLoader;
import com.nia.pojo.Data;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class BinaryStrategy implements PersistenceStrategy {

    private static final String path = ConfigLoader.getString("RDB");//根据文件路径创建Path对象
    private static boolean isDataLoaded = false;


    @Override
    public void save() {
        //获取缓存中的Data数据
        Data data = DataCacheLoader.getData();
        //若数据为空则直接返回
        if (data == null) {
            return;
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
            //将Data对象写入ByteArrayOutputStream
            oos.writeObject(data);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Data load() {
        Data data = new Data();
        if (!Files.exists(Path.of(path))) {
            //若不存在则直接返回
            return data;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))){
            data = (Data) ois.readObject();
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return data;
    }


    @Override
    public void appendCmd(String cmd) {
        //不实现代码逻辑
    }
}
