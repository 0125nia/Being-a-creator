package com.nia.dao.persistent;

import com.nia.dao.loader.DataCacheLoader;
import com.nia.pojo.Data;

public class BinaryStrategy implements PersistenceStrategy {

    private static Data data = DataCacheLoader.getData();

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


    @Override
    public void appendCmd(String cmd) {

    }
}
