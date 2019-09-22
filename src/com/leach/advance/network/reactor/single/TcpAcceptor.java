package com.leach.advance.network.reactor.single;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author Administrator
 * @name 概述：
 * @date 2019/8/24 15:35
 */
public class TcpAcceptor implements Runnable{

    private ServerSocketChannel serverSocketChannel;
    private Selector selector;

    public TcpAcceptor(ServerSocketChannel serverSocketChannel, Selector selector){
        this.serverSocketChannel = serverSocketChannel;
        this.selector = selector;
    }

    @Override
    public void run() {
        try {
            SocketChannel sc = serverSocketChannel.accept();
            System.out.println(sc.getRemoteAddress().toString() + "is connected.");

            sc.configureBlocking(false);
            SelectionKey sk = sc.register(selector, SelectionKey.OP_READ);
            selector.wakeup(); // 使一个阻塞的selector操作立即返回
            sk.attach(new TcpHandler(sc, sk));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
