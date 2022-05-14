package channel.datagranmChannel;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class DatagramChannelDemo {
    //发送的实现
    @Test
    public void sendDatagram() throws Exception {
        //打开 DatagramChannel
        DatagramChannel sendChannel = DatagramChannel.open();
        InetSocketAddress sendAddr =
                new InetSocketAddress("127.0.0.1", 9999);
        //发送
        while (true){
            ByteBuffer buffer = ByteBuffer.wrap("Message" .getBytes(StandardCharsets.UTF_8));
            //将消息写入缓冲区后，加上地址发送
            sendChannel.send(buffer,sendAddr);
            System.out.println("----send over -----");
            TimeUnit.SECONDS.sleep(2);
        }
    }


    //接收的实现
    @Test
    public void receiveDatagram() throws Exception {
        //打开 DatagramChannel
        DatagramChannel receiveChannel = DatagramChannel.open();
        InetSocketAddress receiveAddr =
                new InetSocketAddress( 9999);
        //绑定
        receiveChannel.bind(receiveAddr);
        ByteBuffer receiveBuffer = ByteBuffer.allocate(1024);
        //接收
        while (true){
            receiveBuffer.clear();
            //通道绑定地址后，接收消息
            SocketAddress socketAddress = receiveChannel.receive(receiveBuffer);
            receiveBuffer.flip();
            //发送方地址
            System.out.println(socketAddress.toString());
            //发送方发送信息
            System.out.println(Charset.forName("utf-8").decode(receiveBuffer));
            System.out.println("receive over");
        }
    }
}
