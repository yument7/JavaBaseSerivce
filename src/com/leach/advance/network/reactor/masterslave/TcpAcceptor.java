package com.leach.advance.network.reactor.masterslave;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author Administrator
 * @name 概述：
 * @date 2019/8/25 14:48
 */
public class TcpAcceptor implements Runnable{

    private ServerSocketChannel serverSocketChannel;
    private final int cores = Runtime.getRuntime().availableProcessors();
    private Selector[] selectors = new Selector[cores];
    private int selIndex = 0;
    private TcpSubReactor[] tcpSubReactors = new TcpSubReactor[cores];
    private Thread[] t = new Thread[cores];

    public TcpAcceptor(ServerSocketChannel serverSocketChannel) throws IOException {
        this.serverSocketChannel = serverSocketChannel;
        // 创建多个selector 与多个subReactor, 并开启多个线程运行 从Reactor线程
        for(int i=0; i< cores; i++){
            selectors[i] = Selector.open();
            tcpSubReactors[i] = new TcpSubReactor(serverSocketChannel, selectors[i], i);
            t[i] = new Thread(tcpSubReactors[i]);
            t[i].start();
        }
    }

    @Override
    public void run() {
        try {
            SocketChannel socketChannel = serverSocketChannel.accept();
            System.out.println(socketChannel.socket().getRemoteSocketAddress().toString() + " is connected.");
            socketChannel.configureBlocking(false);
            tcpSubReactors[selIndex].setRestart(true); // 暂停线程
            selectors[selIndex].wakeup();
            SelectionKey sk = socketChannel.register(selectors[selIndex], SelectionKey.OP_READ);
            selectors[selIndex].wakeup();
            tcpSubReactors[selIndex].setRestart(false); // 重新启动线程
            sk.attach(new TcpHandler(sk,socketChannel));
            if(++selIndex == selectors.length){
                selIndex = 0;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
