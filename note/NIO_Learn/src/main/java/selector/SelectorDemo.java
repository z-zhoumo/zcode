package selector;


import java.io.IOException;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

public class SelectorDemo {

    public static void main(String[] args) throws IOException {
        //1、创建 selector
        Selector selector = Selector.open();
        //2、创建通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //3、设置非阻塞
        serverSocketChannel.configureBlocking(false);

    }

}
