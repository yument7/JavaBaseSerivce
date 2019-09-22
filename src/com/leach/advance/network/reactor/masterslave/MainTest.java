package com.leach.advance.network.reactor.masterslave;

import java.io.IOException;

/**
 * @author Administrator
 * @name 概述：
 * @date 2019/8/25 14:49
 */
public class MainTest {

    public static void main(String[] args) {
        try {
            TcpReactor reactor = new TcpReactor(1333);
//                reactor.run();
            Thread thread = new Thread(reactor);
            thread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
