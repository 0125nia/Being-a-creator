package com.nia.command;

import com.nia.dao.loader.DataCacheProcessor;
import com.nia.pojo.arraylist.MArrayList;

public interface AbstractArrayListCommand extends Command{

    default MArrayList<String> getArrayList(String key){
        return DataCacheProcessor.get(key);
    }

}
