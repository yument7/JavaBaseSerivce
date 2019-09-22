package com.leach.advance.network.reactor.masterslave;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ThreadPoolExecutor;

public interface HandlerState {

    void changeState(TcpHandler h);

    void handle(TcpHandler h, SelectionKey sk, SocketChannel sc,
                ThreadPoolExecutor pool) throws IOException;
}