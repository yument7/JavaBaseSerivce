package com.leach.advance.network.reactor.masterslave;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ThreadPoolExecutor;

public class WriteState implements HandlerState {

    public WriteState() {
    }

    @Override
    public void changeState(TcpHandler h) {
        h.setState(new ReadState());
    }

    @Override
    public void handle(TcpHandler h, SelectionKey sk, SocketChannel sc,
                       ThreadPoolExecutor pool) throws IOException {

        String str = "Your message has sent to "
                + sc.socket().getLocalSocketAddress().toString() + "\r\n";
        ByteBuffer buf = ByteBuffer.wrap(str.getBytes()); // wrap自动将bufde position设置为0， 所以不需要再flip()

        while (buf.hasRemaining()) {
            sc.write(buf);
        }

        h.setState(new ReadState()); // 改变状态(SENDING->READING)
        sk.interestOps(SelectionKey.OP_READ); // 通过key改变通道注册事件
        sk.selector().wakeup(); // 使一个阻塞住的selector操作立即返回
    }
}