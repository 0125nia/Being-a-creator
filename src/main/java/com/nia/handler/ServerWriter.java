package com.nia.handler;

import com.nia.command.CommandInvoker;
import com.nia.pojo.ResponseMsg;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class ServerWriter implements Handler {
    private static CommandInvoker invoker;


    public ServerWriter() {
        invoker = CommandInvoker.getInstance();
    }

    String resp = "无数据";

    @Override
    public void handle(SelectionKey sk) throws IOException {
        String msg = (String) sk.attachment();
        if (msg == null) {
            return;
        }


        SocketChannel socketChannel = (SocketChannel) sk.channel();

        resp = invoker.executeCommand(msg,true);
        if (resp == null){
            resp = ResponseMsg.NULL_DATA;
        }
        socketChannel.write(ByteBuffer.wrap(resp.getBytes(StandardCharsets.UTF_8)));

        socketChannel.register(sk.selector(), SelectionKey.OP_READ);
    }
}
