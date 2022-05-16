package selector.me;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        SocketChannel channel = SocketChannel.open(new InetSocketAddress("127.0.0.1",9999));
        channel.configureBlocking(false);
//        channel.connect(new InetSocketAddress("127.0.0.1",9999));
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("please send message");
            String msg = scanner.next();
            buffer.put(msg.getBytes(StandardCharsets.UTF_8));
            buffer.flip();
            channel.write(buffer);
            buffer.clear();
            System.out.println("send success");

        }
    }
}
