package com.nia.handler;

import com.nia.pojo.Data;
import com.nia.command.CommandInvoker;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class ServerWriter implements Handler {
    private static CommandInvoker invoker;
//    private static Data data = DataCacheLoader.getData();
    private static Data data = new Data();

    public ServerWriter() {
        invoker = new CommandInvoker();
    }

    String resp = "无数据";

    @Override
    public void handle(SelectionKey sk) throws IOException {
        String msg = (String) sk.attachment();
        if (msg == null) {
            return;
        }


        SocketChannel socketChannel = (SocketChannel) sk.channel();

        resp = invoker.executeCommand(msg, data, true);
        if (resp == null){
            resp = "null";
        }
        socketChannel.write(ByteBuffer.wrap(resp.getBytes(StandardCharsets.UTF_8)));

        socketChannel.register(sk.selector(), SelectionKey.OP_READ);
    }
}
