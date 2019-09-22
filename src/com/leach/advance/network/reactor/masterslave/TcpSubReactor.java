package com.leach.advance.network.reactor.masterslave;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author Administrator
 * @name 概述：
 * @date 2019/8/25 14:57
 */
public class TcpSubReactor implements Runnable {

    private ServerSocketChannel serverSocketChannel;
    private Selector selector;
    private boolean restart = false;
    private int id;

    public TcpSubReactor(ServerSocketChannel serverSocketChannel, Selector selector, int id){
        this.serverSocketChannel = serverSocketChannel;
        this.selector = selector;
        this.id = id;
    }

    @Override
    public void run() {
        while(!Thread.interrupted()){
            System.out.println("Waiting for new event on port:"+ serverSocketChannel.socket().getLocalPort() + "...");
            System.out.println("interrupted:"+ Thread.interrupted() + ", restart:" + restart);
            while(!Thread.interrupted() && !restart){
                try {
                    if(selector.select() == 0){
                    continue; // 没有事件发生，继续执行
                }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                System.out.println("message is sending ...");
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> it = selectedKeys.iterator();
                while (it.hasNext()) {
                    dispatch((it.next()));
                    it.remove();
                }
            }
        }
    }

    private void dispatch(SelectionKey key){
        Runnable r = (Runnable) (key.attachment());
        if (r != null)
            r.run();
    }

    public void setRestart(boolean restart) {
        this.restart = restart;
    }
}
