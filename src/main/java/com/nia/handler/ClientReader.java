package com.nia.handler;

import com.nia.dao.loader.ConfigLoader;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class ClientReader implements Handler {
    private static final int BUFFER_SIZE = ConfigLoader.getInt("size");
    private ByteBuffer readBuffer;
    private ByteBuffer writeBuffer;

    public ClientReader() {
        readBuffer = ByteBuffer.allocate(BUFFER_SIZE);
        writeBuffer = ByteBuffer.allocate(BUFFER_SIZE);
    }

    @Override
    public void handle(SelectionKey sk) throws IOException {
        SocketChannel channel = (SocketChannel) sk.channel();
        read(channel);
        boolean b = write2Server(channel);
        if (b){
            // 继续发送更多请求
            channel.register(sk.selector(), SelectionKey.OP_READ);
        }else {
            close(channel,sk.selector());
        }

    }

    private void read(SocketChannel socketChannel) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
        int bytesRead = socketChannel.read(buffer);
        if (bytesRead > 0) {
            buffer.flip();
            byte[] bytes = new byte[buffer.remaining()];
            buffer.get(bytes);
            String response = new String(bytes);
            System.out.println(response);
        }
    }

    private boolean write2Server(SocketChannel channel) throws IOException{
        Scanner sc = new Scanner(System.in);
        System.out.print(">");
        String msg = sc.nextLine();
        if ("exit".equals(msg)){
            return false;
        }
        ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
        channel.write(buffer);
        buffer.clear();
        return true;
    }

    private void close(SocketChannel socketChannel, Selector selector) {
        try {
            if (socketChannel != null && socketChannel.isOpen()) {
                socketChannel.close();
            }

            if (selector != null && selector.isOpen()) {
                selector.close();
            }

            System.out.println("连接已关闭");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
