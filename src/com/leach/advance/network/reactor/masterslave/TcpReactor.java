package com.leach.advance.network.reactor.masterslave;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author Administrator
 * @name 概述：
 * @date 2019/8/25 14:48
 */
public class TcpReactor implements Runnable{

    private ServerSocketChannel serverSocketChannel;

    private Selector selector;

    public TcpReactor(int port) throws IOException {
        serverSocketChannel = ServerSocketChannel.open();
        selector = Selector.open();

        serverSocketChannel.bind(new InetSocketAddress(port));
        serverSocketChannel.configureBlocking(false);

        SelectionKey sk = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        sk.attach(new TcpAcceptor(serverSocketChannel));
    }


    @Override
    public void run() {
        while (!Thread.interrupted()) { // 在线程被中断钱持续运行
            System.out.println("mainReactor waiting for new event on port: " + serverSocketChannel.socket().getLocalPort() + "...");
            try {
                if (selector.select() == 0) // 若沒有事件就绪则不往下执行
                    continue;
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("have connect things ...");
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> it = selectedKeys.iterator();
            while (it.hasNext()) {
                dispatch(it.next());
                it.remove();
            }
        }
    }

    private void dispatch(SelectionKey key) {
        Runnable r = (Runnable) (key.attachment());
        if (r != null)
            r.run();
    }
}
