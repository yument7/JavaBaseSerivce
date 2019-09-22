package com.leach.advance.network.bio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Administrator
 * @name 概述：
 * @date 2019/8/19 18:44
 */
public class BioRequest implements Runnable{

    private Socket socket;

    public BioRequest(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        while (true){
            sendMessage();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            recvMessage();
        }
    }

    public void sendMessage(){
        try {
            OutputStream os = socket.getOutputStream();
            System.out.println("输入要发送的消息：");
            Scanner sc = new Scanner(System.in);
            String message = sc.next();
            os.write(message.getBytes());
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void recvMessage(){
        try {
            System.out.println("接收服务端发来的消息：");
            InputStream is = socket.getInputStream();
            byte[] line = new byte[1024];
            is.read(line);
            String msg = new String(line);
            /*while(is.read(line) != -1){
                sb.append(new String(line));
            }*/
            System.out.println(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
