package com.nia.handler;

import java.io.IOException;
import java.nio.channels.SelectionKey;

public interface Handler {
    void handle(SelectionKey sk) throws IOException;
}
