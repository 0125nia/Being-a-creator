package com.nia.command;

import com.nia.dao.loader.DataCacheProcessor;
import com.nia.pojo.arraylist.MArrayList;

public interface AbstractArrayListCommand extends Command {

    default MArrayList<String> getArrayList(String key) {
        MArrayList<String> list = DataCacheProcessor.get(key);
        if (list == null) {
            DataCacheProcessor.<MArrayList<String>>put(key, new MArrayList<>());
        }
        return DataCacheProcessor.get(key);
    }

}
