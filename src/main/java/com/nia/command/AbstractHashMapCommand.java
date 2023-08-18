package com.nia.command;

import com.nia.pojo.hashmap.MHashMap;

public interface AbstractHashMapCommand extends Command {

    /**
     * 对存储映射数据的map中key对应的映射进行非空处理
     * @param mapData 存储映射数据的map
     * @param key 获取映射的key
     * @return 对应的映射数据
     */
    default MHashMap<String, String> getHashMap(MHashMap<String, MHashMap<String, String>> mapData, String key) {
        MHashMap<String, String> map = mapData.get(key);
        //若为空则创建映射对象
        if (map == null){
            map = new MHashMap<>();
            mapData.put(key,map);
        }
        return map;
    }
}
