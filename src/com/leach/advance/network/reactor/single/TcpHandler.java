package com.leach.advance.network.reactor.single;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

/**
 * @author Administrator
 * @name 概述：
 * @date 2019/8/24 15:35
 */
public class TcpHandler implements Runnable {

    private SocketChannel socketChannel;
    private SelectionKey selectionKey;

    private int state;

    public TcpHandler(SocketChannel socketChannel, SelectionKey selectionKey){
        this.socketChannel = socketChannel;
        this.selectionKey = selectionKey;
        state = 0;
    }

    @Override
    public void run() {
        try {
            if (state == 0)
                read(); // 解析网络数据
            else
                send(); // 发送网络数据

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            System.out.println("[Warning!] A client has been closed.");
            selectionKey.cancel();
            try {
                socketChannel.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    private synchronized void read() throws IOException {
        byte[] data = new byte[1024];
        ByteBuffer byteBuffer = ByteBuffer.wrap(data);
        int readNum = socketChannel.read(byteBuffer);

        if(readNum == -1){
            System.out.println("a client has been closed.");
            selectionKey.cancel();
            socketChannel.close();
        }

        String dataStr = new String(data);
        if(dataStr.equals("")){
            // 处理业务逻辑
            dealBussiness();
            System.out.println(socketChannel.getRemoteAddress().toString() + ":" + dataStr);
            state = 1;
            selectionKey.interestOps(SelectionKey.OP_WRITE);
            selectionKey.selector().wakeup();
        }
    }


    public void send() throws IOException {
        String respMsg = " response the client " +socketChannel.getRemoteAddress().toString();
        ByteBuffer byteBuffer = ByteBuffer.wrap(respMsg.getBytes());

        while(byteBuffer.hasRemaining()){
            socketChannel.write(byteBuffer);
        }
        state = 0;
        selectionKey.interestOps(SelectionKey.OP_READ);
        selectionKey.selector().wakeup();
    }


    public void dealBussiness(){
        System.out.println(" do bussiness ...");
    }
}
