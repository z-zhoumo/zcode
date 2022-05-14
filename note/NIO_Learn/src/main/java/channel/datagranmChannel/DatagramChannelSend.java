package channel.datagranmChannel;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.charset.StandardCharsets;

public class DatagramChannelSend {
    public static void main(String[] args) throws Exception {
        //打开通道
        // 例：打开 10086 端口接收 UDP 数据包
        DatagramChannel datagramChannel = DatagramChannel.open();

        //发送数据
        ByteBuffer sendBuffer =
                ByteBuffer.wrap("client send".getBytes(StandardCharsets.UTF_8));
        datagramChannel.send(sendBuffer,
                new InetSocketAddress("127.0.0.1", 10086));


    }

}
