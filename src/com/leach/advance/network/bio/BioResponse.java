package com.leach.advance.network.bio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author Administrator
 * @name 概述：
 * @date 2019/8/19 18:45
 */
public class BioResponse implements Runnable {

    private Socket socket;

    public BioResponse(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        while(true){
            String message = recvMessage();
            sendMessage(message);
        }
    }

    public String recvMessage() {
        String result = null;
        try {
            System.out.println("来自客户端消息。");
            InputStream is = socket.getInputStream();
            byte[] line = new byte[1024];
            StringBuilder sb = new StringBuilder();
            is.read(line);
            result = new String(line);
            result = result.trim();
            //int len;
            // Socket中InputStream的read方法是一个阻塞方法，会一直阻塞这里，无法返回-1，所以不能用 !=-1 的方式
            /*while ((len = is.read(line)) != -1) {
                System.out.println(len);
                sb.append(new String(line));
            }*/
            //result = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void sendMessage(String message){
        System.out.println("服务端做出响应：");
        try {
            OutputStream os = socket.getOutputStream();
            String resMessage;
            if(message.equals("java")){
                resMessage = "我最擅长做web服务了，当然其他方面我也很6。";
            }else if(message.equals("python")){
                resMessage = "我最擅长脚本处理，网络爬虫，数据清洗，数据科学都是我的拿手好戏。";
            }else if(message.length() > 1024){
                resMessage = "发送消息超长，无法处理 ...";
            }else{
                resMessage = "不知道能为你干点什么";
            }
            os.write(resMessage.getBytes());
            os.flush();
            System.out.println("响应完毕！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
