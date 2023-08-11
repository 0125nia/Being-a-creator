package com.nia.reactor;

import com.nia.dao.conf.ConfigLoader;
import com.nia.handler.ServerAcceptor;
import com.nia.handler.ServerReader;
import com.nia.handler.ServerWriter;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;

/**
 * Reactor模型
 */
public class Reactor {

    //定义通道和选择器
    private Selector selector;
    private ServerSocketChannel serverSocketChannel;

    /**
     * 构造器
     */
    public Reactor() {
        final int PORT;
        try {
            //加载端口号
            PORT = ConfigLoader.getInt("port");
            //获取通道和选择器
            serverSocketChannel = ServerSocketChannel.open();
            selector = Selector.open();

            //设置为非阻塞
            serverSocketChannel.configureBlocking(false);
            //绑定端口
            serverSocketChannel.bind(new InetSocketAddress(PORT));
            //将通道都注册到选择器上去，并且开始指定监听接收事件
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        } catch (NumberFormatException | NullPointerException e) {
            System.out.println("配置参数错误!请检查!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("服务端初始化完毕");
    }


    /**
     * 启动Reactor
     */
    public void start() {
        System.out.println("服务端启动,等待连接...");
        try {
            //循环处理事件
            while (selector.select() > 0) {
                Iterator<SelectionKey> it = selector.selectedKeys().iterator();
                while (it.hasNext()) {
                    SelectionKey sk = it.next();
                    if (sk.isAcceptable()) {      //处理连接事件
                        new ServerAcceptor().handle(sk);
                    } else if (sk.isReadable()) { // 处理读事件
                        new ServerReader().handle(sk);
                    }else if (sk.isWritable()){
                        new ServerWriter().handle(sk);
                    }
                    //处理完毕移除该事件
                    it.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    /**
     * 关闭Reactor
     */
    public void stop() {
        try {
            //关闭服务端
            serverSocketChannel.close();
            selector.close();
            //打印信息
            System.out.println("服务端已经成功关闭!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
