package com.nia.handler;

import com.nia.dao.loader.ConfigLoader;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
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
        write2Server(channel);
        // 继续发送更多请求
        channel.register(sk.selector(), SelectionKey.OP_READ);
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

    private void write2Server(SocketChannel channel) throws IOException{
        Scanner sc = new Scanner(System.in);
        String msg = sc.nextLine();
        ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
        channel.write(buffer);
        buffer.clear();
    }

}
