package com.nia.dao;

import com.nia.pojo.hashmap.MHashMap;
import com.nia.pojo.linkedlist.MLinkedList;

public class DataPersistent {
    MHashMap<String, String> kvData;
    MHashMap<String, MLinkedList<String>> linkedListData;
    MHashMap<String, MHashMap<String, String>> mapData;


}
