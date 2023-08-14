package com.nia.pojo;

import com.nia.pojo.hashmap.MHashMap;
import com.nia.pojo.linkedlist.MLinkedList;

/**
 * 数据缓存类
 */
public class Data {
    //静态成员变量
    private MHashMap<String, String> stringData = new MHashMap<>();
    private MHashMap<String, MLinkedList<String>> linkedListData = new MHashMap<>();
    private MHashMap<String, MHashMap<String, String>> mapData = new MHashMap<>();

    public Data() {}

    public MHashMap<String, String> getStringData() {
        return stringData;
    }

    public void setStringData(MHashMap<String, String> stringData) {
        this.stringData = stringData;
    }

    public MHashMap<String, MLinkedList<String>> getLinkedListData() {
        return linkedListData;
    }

    public void setLinkedListData(MHashMap<String, MLinkedList<String>> linkedListData) {
        this.linkedListData = linkedListData;
    }

    public MHashMap<String, MHashMap<String, String>> getMapData() {
        return mapData;
    }

    public void setMapData(MHashMap<String, MHashMap<String, String>> mapData) {
        this.mapData = mapData;
    }
}
