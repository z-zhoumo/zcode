package channel.socketChannel;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class SocketChannelDemo {
    public static void main(String[] args) throws Exception {

        InetSocketAddress baiduAddr =
                new InetSocketAddress("www.baidu.com", 80);

        //创建 SocketChannel
        //方式一
        SocketChannel socketChannel = SocketChannel.open(baiduAddr);
        //方式二
        SocketChannel sc2 = SocketChannel.open();
        sc2.connect  (baiduAddr);

        //设置非阻塞
        socketChannel.configureBlocking(false);

        //读
        ByteBuffer buffer = ByteBuffer.allocate(16);
        int byteRead = socketChannel.read(buffer);
        socketChannel.close();
        System.out.println("read over");

        /*
        // 测试 SocketChannel 是否为 open 状态
        socketChannel.isOpen();
        //测试 SocketChannel 是否已经被连接
        socketChannel.isConnected();
        //测试 SocketChannel 是否正在进行连接
        socketChannel.isConnectionPending();
        //校验正在进行套接字连接的 SocketChannel 是否已经完成连接
        socketChannel.finishConnect();

        //设置参数，也可以 get 获取参数
        socketChannel.setOption(StandardSocketOptions.SO_KEEPALIVE,
Boolean.TRUE)
        */
    }
}
