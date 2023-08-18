package com.nia.pojo;

import com.nia.pojo.arraylist.MArrayList;
import com.nia.pojo.hashmap.MHashMap;
import com.nia.pojo.linkedlist.MLinkedList;

import java.io.Serializable;

/**
 * 数据类
 */
public class Data implements Serializable {
    private MHashMap<String, String> stringData = new MHashMap<>();
    private MHashMap<String, MArrayList<String>> listData = new MHashMap<>();
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

    public MHashMap<String, MArrayList<String>> getListData() {
        return listData;
    }

    public void setListData(MHashMap<String, MArrayList<String>> listData) {
        this.listData = listData;
    }
}
