package selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Scanner;

public class ClientDemo {
    public static void main(String[] args) throws IOException {
        //1、获取通道，绑定主机和端口号
        SocketChannel socketChannel = SocketChannel
                .open(new InetSocketAddress("127.0.0.1", 8000));
        //2、切换非阻塞模式
        socketChannel.configureBlocking(false);
        //3、创建 buffer
        int capacity = 1024;
        ByteBuffer buffer = ByteBuffer.allocate(capacity);

        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("please send message");
            String message = scanner.next();
            if("exit".equals(message)){
                buffer.clear();
                break;
            }
            //4、写入 buffer
            buffer.put((new Date().toString()+"\n消息："+message).getBytes());

            //5、 buffer 读写模式切换
            buffer.flip();
            //6、 写入通道
            socketChannel.write(buffer);
            buffer.clear();
            System.out.println("---end");

        }
        //7、关闭
        socketChannel.close();


    }
}
