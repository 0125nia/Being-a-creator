package com.nia.command;

import com.nia.dao.loader.DataCacheProcessor;
import com.nia.pojo.linkedlist.MLinkedList;

public interface AbstractLinkedListCommand extends Command {

    /**
     * 根据key读取缓存或文件中的链表数据
     *
     * @param key 获取链表的key
     * @return 返回key对应的链表
     */
    default MLinkedList<String> getLinkedList(String key) {
        MLinkedList<String> list = DataCacheProcessor.get(key);
        if (list == null){
            DataCacheProcessor.<MLinkedList<String>>put(key,new MLinkedList<>());
        }
        return DataCacheProcessor.get(key);
    }
}
