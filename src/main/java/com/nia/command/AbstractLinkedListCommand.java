package com.nia.command;

import com.nia.pojo.hashmap.MHashMap;
import com.nia.pojo.linkedlist.MLinkedList;

public interface AbstractLinkedListCommand extends Command {

    /**
     * 对存储链表数据的map中key对应的链表进行非空处理
     *
     * @param linkedListData 存储链表数据的map
     * @param key            获取链表的key
     * @return 返回key对应处理后的链表
     */
    default MLinkedList<String> getLinkedList(MHashMap<String, MLinkedList<String>> linkedListData, String key) {
        MLinkedList<String> linkedList = linkedListData.get(key);
        //若为空则创建链表对象
        if (linkedList == null) {
            linkedList = new MLinkedList<>();
            linkedListData.put(key, linkedList);
        }
        return linkedList;
    }
}
