package channel.datagranmChannel;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class DatagramChannelConnectDemo {

    // read 和 write
    @Test
    public void testConnect() throws IOException {
        //打开 DatagramChannel
        DatagramChannel connChannel = DatagramChannel.open();
        //绑定
        connChannel.bind(new InetSocketAddress(9999));
        //连接
        connChannel.connect(new InetSocketAddress("localhost",9999));
        // write()
        connChannel.write
                (ByteBuffer.wrap("Message".getBytes(StandardCharsets.UTF_8)));

        ByteBuffer readBuffer = ByteBuffer.allocate(1024);

        while (true) {
            readBuffer.clear();
            // read()
            connChannel.read(readBuffer);
            readBuffer.flip();
            System.out.println(Charset.forName("utf-8").decode(readBuffer));
        }

    }
}




