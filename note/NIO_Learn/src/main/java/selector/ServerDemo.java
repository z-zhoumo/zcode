package selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class ServerDemo {
    public static void main(String[] args) throws IOException {
        // 1、获取通道
        ServerSocketChannel channel = ServerSocketChannel.open();
        //2、绑定端口
        channel.bind(new InetSocketAddress(8000));
        //3、切换非阻塞
        channel.configureBlocking(false);
        //4、创建 buffer
        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
        //5、获取 selector
        Selector selector = Selector.open();
        //6、通道注册到选择器，监听
        channel.register(selector, SelectionKey.OP_ACCEPT);
        //7、选择器轮询查询
        while (selector.select() > 0) {
            System.out.println(".");
            Set<SelectionKey> keySet = selector.selectedKeys();
            Iterator<SelectionKey> iterator = keySet.iterator();
            while (iterator.hasNext()) {
                SelectionKey option = iterator.next();
                //判断操作类型
                if (option.isAcceptable()) {
                    System.out.println("接收channel状态");
                    //获取连接
                    SocketChannel accept = channel.accept();
                    //切换非阻塞模式
                    accept.configureBlocking(false);
                    //注册
                    accept.register(selector, SelectionKey.OP_READ);
                    System.out.println("注册成功");
                } else if (option.isReadable()) {
                    //当 client 通道关闭时，一直处于该状态
                    System.out.println("准备读状态");
                    SocketChannel readChannel = (SocketChannel) option.channel();
                    //读取数据
                    int length = 0;
                    while ((length = readChannel.read(readBuffer)) > 0) {
                        readBuffer.flip();
                        System.out.println(new String(readBuffer.array(), 0, length));
                        readBuffer.clear();
                    }
                }
                iterator.remove();

            }

        }

    }

}

