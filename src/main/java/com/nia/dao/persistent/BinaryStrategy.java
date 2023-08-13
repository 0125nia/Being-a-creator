package com.nia.dao.persistent;

import com.nia.pojo.Data;

public class BinaryStrategy implements PersistenceStrategy {

    private static Data data;

    public static void setData(Data data) {
        BinaryStrategy.data = data;
    }

    @Override
    public void save() {

    }

    @Override
    public Data load() {
        return null;
    }
}
