package com.leach.advance.network.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

/**
 * @author Administrator
 * @name 概述：
 * 1. 开启服务端socket,  -> ServerSocketChannel.open();
 *    开启selector   -> Selector.open();
 *    绑定端口，设置非阻塞  -> bind()  ,   configureBlocking(false);
 *
 * 2. 注册socket 到 selector上并设置该socket的感兴趣事件-接收客户端连接事件， sk.registor(selector, SelectionKey.OP_ACCEPT)
 *
 * 3. 轮询selector中的socket;
 *    通过selector的select()方法进行监听，每次执行select()方法都去查看发生了哪些事件（eg. 客户端新连接， 客户端发送消息等）；
 *    通过selector的selectedkeys()去找出所有新发生的事件，一个sokect会对应一个selectionKey；
 *    遍历key对应的set集合，判断当前key是什么事件 做出对应的响应动作  -> isAcceptable() , isReadable()；
 *    当前selectedKey处理之后，需要从selectedSet中删除，表示已经对他做了处理 -> it.remove(), 下次如果还是该sokect发出时间，会通过select()再挑选出来放入集合中。
 *
 * 4. 当selectionkey.isAcceptable()时的事件处理
 *    此时，说明有新连接来连接客户端，对该事件感兴趣的socket为ServerSocketChannel，所有需要通过severSocketChannel去创建进行处理读写的socket
 *    即 sskc.accept() 创建一个SocketChannel()
 *    设置socketChannel 在读数据是为非阻塞，configureBlocking(false);
 *    然后将该socket 注册到selector上 并标记感兴趣事件为读客户端数据， sk.registor(selector, SelectionKey,OP_READ)
 *    这样一个客户端就与服务端建立起连接;
 *
 * 5. 当selectionkey.isReadable()时的事件处理
 *    此时，说明已建立好的连接客户端发来了消息；通过selectionKey.channel()取出socketchannel;
 *    建立消息中转站 byteBuffer = ByteBuffer.allocte() / allocateDirect();
 *    将socketchannel中的数据读入byteBuffer -> read(byteBuffer);
 *    然后对byteBuffer 的数据进行具体的业务处理；
 *    处理之后需要响应客户端：所有通过socket.write()方法，将处理后的数据返回；
 *    如果需要实现广播转发：通过遍历selector().keys() 取出所有已连接的客户端进行（排除ServerSocketChannel 通过 key.isValid()）。
 *
 * @date 2019/8/20 21:28
 */
public class NioServer {

    private ServerSocketChannel serverSocketChannel;

    private Selector selector;

    private static int TIMEOUT = 2000;

    public NioServer() throws IOException {
        serverSocketChannel = ServerSocketChannel.open();
        selector = Selector.open();

        serverSocketChannel.bind(new InetSocketAddress(9091));

        // 设置非阻塞，才能注册到selector上
        serverSocketChannel.configureBlocking(false);

        // 注册到registor上
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

    }

    public void doResponse() throws IOException {
        System.out.println("服务已启动，等待客户端连接...");
        while (true) {
            // 最重要的一步，通过select() 函数去查找被selected的事件， 不设置时间，会阻塞在这里直到有客户端连接或消息； 设置时间不会阻塞
            selector.select(TIMEOUT);

            Set<SelectionKey>  keys = selector.keys();

            Set<SelectionKey> selectionKeySet = selector.selectedKeys();

            Iterator<SelectionKey> it = selectionKeySet.iterator();

            while (it.hasNext()) {
                SelectionKey selectionKey = it.next();

                if (selectionKey.isAcceptable()) {
                    SocketChannel socketChannel = serverSocketChannel.accept(); // 获取客端连接，每一个客户端发来一个连接，就会产生一个对应的socket
                    socketChannel.configureBlocking(false); // 设置为非阻塞， 即在读的时候不让其阻塞
                    socketChannel.register(selector, SelectionKey.OP_READ); // 将当前socket注册到selector上去
                    System.out.println(socketChannel.getRemoteAddress().toString() + "上线了....");
                }

                if (selectionKey.isReadable()) {
                    readClientData(selectionKey);
                }

                it.remove();
            }
        }
    }

    private void readClientData(SelectionKey selectionKey) throws IOException {
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        int read = socketChannel.read(byteBuffer);
        byteBuffer.flip();
        if (read > 0) {
            byte[] bytes = new byte[read];
            byteBuffer.get(bytes, 0, read);
            //读取了数据  广播
            String s = new String(bytes, StandardCharsets.UTF_8);
            writeClientData(socketChannel, s);
        }
    }

    //广播  将读取的数据群发
    private void writeClientData(SocketChannel socketChannel, String s) throws IOException {
        Set<SelectionKey> keys = selector.keys();
        for (SelectionKey key : keys) {
            if (key.isValid()) {
                SelectableChannel channel = key.channel();
                if (channel instanceof SocketChannel) {
                    SocketChannel socketChannel1 = (SocketChannel) channel;
                    if (channel != socketChannel) {
                        ByteBuffer wrap = ByteBuffer.wrap(s.getBytes());
                        socketChannel1.write(wrap);
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        NioServer nioServer = new NioServer();
        nioServer.doResponse();
    }

}
