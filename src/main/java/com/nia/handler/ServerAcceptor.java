package com.nia.handler;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class ServerAcceptor implements Handler{

    @Override
    public void handle(SelectionKey sk) throws IOException {
        //获取ServerSocketChannel
        ServerSocketChannel serverChannel = (ServerSocketChannel) sk.channel();
        //获取连接的SocketChannel
        SocketChannel accept = serverChannel.accept();
        //打印接入客户端的信息
        System.out.println("接收到连接:" + accept.socket().getRemoteSocketAddress());
        //设置为非阻塞模式
        accept.configureBlocking(false);

        // 注册SelectionKey和Handler的关联
        accept.register(sk.selector(), SelectionKey.OP_READ,this);
        //提示信息
        String msg = "welcome to database";
        accept.write(ByteBuffer.wrap(msg.getBytes(StandardCharsets.UTF_8)));

    }

}
