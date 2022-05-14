package channel.datagranmChannel;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class DatagramChannelReceive {

    public static void main(String[] args) throws Exception {
        //打开通道
        // 例：打开 10086 端口接收 UDP 数据包
        DatagramChannel datagramChannel = DatagramChannel.open();
        datagramChannel.socket().bind(new InetSocketAddress(10086));

        ByteBuffer buffer = ByteBuffer.allocate(64);
        buffer.clear();

        while (true){
            //接收数据
            SocketAddress receiveAddr = datagramChannel.receive(buffer);
            System.out.println(receiveAddr.toString()+"--");///127.0.0.1:54267--
        }

    }


}
