package com.nia.command;

import com.nia.dao.loader.DataCacheProcessor;
import com.nia.pojo.hashmap.MHashMap;

public interface AbstractHashMapCommand extends Command {

    /**
     * 根据key读取缓存或文件中的映射数据
     *
     * @param key 获取映射的key
     * @return 对应的映射数据
     */
    default MHashMap<String, String> getHashMap(String key) {
        return DataCacheProcessor.get(key);
    }
}
