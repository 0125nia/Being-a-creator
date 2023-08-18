package com.nia.handler;

import com.nia.dao.loader.ConfigLoader;
import com.nia.reactor.Reactor;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

public class ServerReader implements Handler {


    @Override
    public void handle(SelectionKey sk) {
        SocketChannel socketChannel = null;
        try {
            // 获取SocketChannel
            socketChannel = (SocketChannel) sk.channel();
            // 加载ByteBuffer的容量值
            final int SIZE = ConfigLoader.getInt("size");
            ByteBuffer buf = ByteBuffer.allocate(SIZE);

            // 从客户端读取数据
            int count = socketChannel.read(buf);

            if (count > 0) {
                buf.flip();
                String msg = new String(buf.array(), 0, buf.remaining());
                System.out.println("接收到信息 " + socketChannel.getRemoteAddress() + " : " + msg);
                socketChannel.register(sk.selector(), SelectionKey.OP_WRITE, msg);
            }

        } catch (NumberFormatException | NullPointerException e) {
            System.out.println("配置参数错误!请检查!");
        } catch (IOException e) {
            try {
                // 客户端离线，关闭连接
                Reactor.LOGGER.info(socketChannel.getRemoteAddress() + "离线了");
                sk.cancel();
                socketChannel.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}
