package com.leach.advance.network.bio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 * @name 概述：
 * @date 2019/8/19 18:43
 */
public class BioServer {

    // 这个socket是用于管理服务端的连接，负责监听与创建处理客户端请求响应的socket
    private ServerSocket serverSocket;

    private static ThreadPoolExecutor POOL = new ThreadPoolExecutor(20,50, 200, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(5));

    public BioServer() throws IOException {
        serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(9091));
    }

    public BioServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    public void doResponse() throws IOException {
        while(true){
            System.out.println("服务端准备就绪, 启用端口："+serverSocket.getLocalPort());
            Socket socket = serverSocket.accept(); // 阻塞方法，每有一个客户端发请请求，就会新创建一个socket去处理客户端连接
            POOL.execute(new BioResponse(socket));
        }
    }

    public static void main(String[] args) throws IOException {
        BioServer bioServer = new BioServer();
        bioServer.doResponse();
    }
}
