package com.nia.handler;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

public class ClientConnector implements Handler {
    @Override
    public void handle(SelectionKey sk) throws IOException {
        // 建立连接
        SocketChannel channel = (SocketChannel) sk.channel();
        if (channel.isConnectionPending()) {
            channel.finishConnect(); // 完成连接
        }

        channel.register(sk.selector(), SelectionKey.OP_READ);

    }
}
