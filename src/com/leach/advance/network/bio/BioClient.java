package com.leach.advance.network.bio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author Administrator
 * @name 概述：
 * @date 2019/8/19 18:44
 */
public class BioClient {

    private Socket socket;

    private BioClient() throws IOException {
        socket = new Socket();
        socket.connect(new InetSocketAddress(1333));
    }

    private BioClient(int port) throws IOException {
        socket = new Socket("127.0.0.1",port);
    }

    public void doRequest(){
        new Thread(new BioRequest(socket)).start();
    }

    public static void main(String[] args) throws IOException {
        BioClient bioClient = new BioClient();
        bioClient.doRequest();
    }
}
