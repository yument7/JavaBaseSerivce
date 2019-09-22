package com.leach.advance.network.reactor.single;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author Administrator
 * @name 概述：单线程下reactor模型
 * @date 2019/8/24 15:34
 */
public class TcpReactor implements Runnable{

    private ServerSocketChannel serverSocketChannel;

    private Selector selector;

    public TcpReactor() throws IOException {
        serverSocketChannel = ServerSocketChannel.open();
        selector = Selector.open();

        serverSocketChannel.bind(new InetSocketAddress(9098));
        serverSocketChannel.configureBlocking(false);

        SelectionKey sk = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        sk.attach(new TcpAcceptor(serverSocketChannel, selector));
    }

    @Override
    public void run() {
        while(!Thread.interrupted()){
            System.out.println("Waiting for new event on port:"+ serverSocketChannel.socket().getLocalPort() + "...");
            try {
                if(selector.select() == 0){
                    continue; // 没有事件发生，继续执行
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            Set<SelectionKey> selectedKeys = selector.selectedKeys(); // 取得所有已就緒事件的key集合
            Iterator<SelectionKey> it = selectedKeys.iterator();
            while (it.hasNext()) {
                dispatch((it.next())); // 根據事件的key進行調度
                it.remove();
            }
        }
    }

    private void dispatch(SelectionKey key){
        Runnable r = (Runnable) (key.attachment());
        if (r != null)
            r.run();
    }
}
