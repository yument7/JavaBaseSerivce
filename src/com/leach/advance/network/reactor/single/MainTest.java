package com.leach.advance.network.reactor.single;

import java.io.IOException;

/**
 * @author Administrator
 * @name 概述：
 * @date 2019/8/24 15:34
 */
public class MainTest {

    public static void main(String[] args) throws IOException {
        TcpReactor tcpReactor = new TcpReactor();
        tcpReactor.run();
    }
}
