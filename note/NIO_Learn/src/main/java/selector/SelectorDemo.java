package selector;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

public class SelectorDemo {

    public static void main(String[] args) throws IOException {
        //1、创建 selector
        Selector selector = Selector.open();
        //2、创建通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //3、设置非阻塞
        serverSocketChannel.configureBlocking(false);
        //4、绑定连接
        serverSocketChannel.bind(new InetSocketAddress(9999));
        //5、将通道注册到选择器上
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        //6、查询已经就绪的通道操作
        Set<SelectionKey> selectionKeys = selector.selectedKeys();
        //7、遍历
        Iterator<SelectionKey> iterator = selectionKeys.iterator();
        while (iterator.hasNext()){
            SelectionKey key = iterator.next();
            //判断 key 就绪状态操作
            if(key.isAcceptable()) {
                // a connection was accepted by a ServerSocketChannel.
                // 准备接收 ServerSocketChannel

            } else if (key.isConnectable()) {
                // a connection was established with a remote server.
                // 准备与远程服务器建立连接

            } else if (key.isReadable()) {
                // a channel is ready for reading 准备写

            } else if (key.isWritable()) {
                // a channel is ready for writing 准备读

            }
            iterator.remove();
        }



    }

}
