package com.nia;

import com.nia.reactor.Reactor;

/**
 * 服务端入口
 */
public class DataBase {
    public static void main(String[] args) {
        //创建Reactor
        Reactor reactor = Reactor.getInstance();
        //注册钩子函数
        Runtime.getRuntime().addShutdownHook(new Thread(reactor::stop));
        //开启Reactor
        reactor.start();
    }
}
