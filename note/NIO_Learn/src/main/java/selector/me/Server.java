package selector.me;



import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel channel = ServerSocketChannel.open();
        channel.bind(new InetSocketAddress(9999));
        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
        channel.configureBlocking(false);
        Selector selector = Selector.open();
        channel.register(selector, SelectionKey.OP_ACCEPT);
        while (selector.select() > 0){
            //有连接
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()){
                System.out.println("有连接");
                SelectionKey next = iterator.next();
                if(next.isAcceptable()){
                    SocketChannel accept = (SocketChannel) channel.accept();
                    accept.configureBlocking(false);
                    accept.register(selector,SelectionKey.OP_READ);
                }else if(next.isReadable()){
                    System.out.println("message：--》");
                    SocketChannel socketChannel = (SocketChannel) next.channel();
//                    socketChannel.configureBlocking(false);
                    int len = 0;
                    while ((len = socketChannel.read(readBuffer)) > 0){
                        readBuffer.flip();
                        System.out.println(new String(readBuffer.array(),0,len));
                        readBuffer.clear();
                    }
                    System.out.println();
                }
                iterator.remove();
            }

        }
    }
}
