package com.nia.handler;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class ServerWriter implements Handler{
    String resp = "无数据";

    @Override
    public void handle(SelectionKey sk) throws IOException {
        String msg = (String) sk.attachment();
        if (msg == null){
            return;
        }

        SocketChannel socketChannel = (SocketChannel) sk.channel();

        socketChannel.write(ByteBuffer.wrap(resp.getBytes(StandardCharsets.UTF_8)));
        socketChannel.register(sk.selector(), SelectionKey.OP_READ);
    }
}
