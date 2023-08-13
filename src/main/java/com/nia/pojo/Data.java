package com.nia.pojo;

import com.nia.pojo.hashmap.MHashMap;
import com.nia.pojo.linkedlist.MLinkedList;

/**
 * 数据缓存类
 */
public class Data {
    //静态成员变量
    private static MHashMap<String, String> StringData;
    private static MHashMap<String, MLinkedList<String>> linkedListData;
    private static MHashMap<String, MHashMap<String, String>> mapData;

    public static MHashMap<String, String> getStringData() {
        return StringData;
    }

    public static void setStringData(MHashMap<String, String> stringData) {
        StringData = stringData;
    }

    public static MHashMap<String, MLinkedList<String>> getLinkedListData() {
        return linkedListData;
    }

    public static void setLinkedListData(MHashMap<String, MLinkedList<String>> linkedListData) {
        Data.linkedListData = linkedListData;
    }

    public static MHashMap<String, MHashMap<String, String>> getMapData() {
        return mapData;
    }

    public static void setMapData(MHashMap<String, MHashMap<String, String>> mapData) {
        Data.mapData = mapData;
    }
}
