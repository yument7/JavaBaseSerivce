package com.leach.advance.network.reactor.masterslave;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ThreadPoolExecutor;

public class ReadState implements HandlerState {

    private SelectionKey sk;

    public ReadState() {
    }

    @Override
    public void changeState(TcpHandler h) {
        h.setState(new WorkState());
    }

    @Override
    public void handle(TcpHandler h, SelectionKey sk, SocketChannel sc,
                       ThreadPoolExecutor pool) throws IOException { // read()
        this.sk = sk;
        // non-blocking下不可用Readers，因为Readers不支持non-blocking
        byte[] arr = new byte[1024];
        ByteBuffer buf = ByteBuffer.wrap(arr);

        int numBytes = sc.read(buf);
        if (numBytes == -1) {
            System.out.println("[Warning!] A client has been closed.");
            h.closeChannel();
            return;
        }
        String str = new String(arr);
        if (!str.equals(" ")) {
            h.setState(new WorkState()); // 改变状态(READING->WORKING)
            pool.execute(new WorkerThread(h, str)); // do process in worker thread
            System.out.println(sc.socket().getRemoteSocketAddress().toString() + " > " + str);
        }

    }

    /*
     * 执行逻辑处理函数
     */
    synchronized void process(TcpHandler h, String str) {
        // do process(decode, logically process, encode)..
        // ..
        h.setState(new WriteState());
        this.sk.interestOps(SelectionKey.OP_WRITE);
        this.sk.selector().wakeup();
    }


    /*
     * 工作者线程池
     */
    class WorkerThread implements Runnable {

        TcpHandler h;
        String str;

        public WorkerThread(TcpHandler h, String str) {
            this.h = h;
            this.str = str;
        }

        @Override
        public void run() {
            process(h, str);
        }

    }
}