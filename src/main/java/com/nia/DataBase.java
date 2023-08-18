package com.nia;

import com.nia.reactor.Reactor;

/**
 * 服务端入口
 */
public class DataBase {
    public static void main(String[] args) {
        //创建Reactor
        Reactor reactor = Reactor.getInstance();
        //开启Reactor
        reactor.start();

        // 在关闭钩子中停止服务器
        Runtime.getRuntime().addShutdownHook(new Thread(reactor::stop));

    }
}
