package com.nia.command;

import com.nia.dao.loader.DataCacheProcessor;

public interface AbstractStringCommand extends Command {

    default String getList(String key) {
        return DataCacheProcessor.get(key);
    }
}
