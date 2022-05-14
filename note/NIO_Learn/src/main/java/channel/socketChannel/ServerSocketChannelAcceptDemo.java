package channel.socketChannel;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

public class ServerSocketChannelAcceptDemo {

    public static void main(String[] args) throws Exception {
        // 端口号
        int port = 8888;

        //buffer
        ByteBuffer buffer = ByteBuffer
                .wrap("hello,world!".getBytes(StandardCharsets.UTF_8));

        // ServerSocketChannel 创建
        ServerSocketChannel ssc = ServerSocketChannel.open();
        // 绑定
        ssc.socket().bind(new InetSocketAddress(port));

        //设置非阻塞模式
        ssc.configureBlocking(false);

        // 监听是否有新的连接传入
        while (true){
            System.out.println("Waiting for connections/等待链接");
            /*
            接受到此通道的套接字的连接。
            如果此通道处于非阻塞模式，那么如果没有传入的连接，此方法将立即返回null。
            如果处于阻塞模式， 它将无限期地阻塞，直到有新的连接可用或发生I/O错误。
             */
            SocketChannel sc = ssc.accept();
            if(sc == null){//没有链接传入
                System.out.println("无连接");
                TimeUnit.SECONDS.sleep(2);
            }else {
                //有链接传入
                System.out.println("Connection from/链接:"
                + sc.socket().getRemoteSocketAddress());
                buffer.rewind();//指针 0
                sc.write(buffer );
                sc.close();

            }
        }
    }
}