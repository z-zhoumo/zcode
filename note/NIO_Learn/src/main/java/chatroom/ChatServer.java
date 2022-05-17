package chatroom;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

//聊天服务端
public class ChatServer {
    public static void main(String[] args) {
        try {
            new ChatServer().startServer();
            System.out.println("");
        } catch (IOException e) {
            System.out.println("启动失败");
            e.printStackTrace();
        }
    }


    //服务器启动方法
    public void startServer() throws IOException {
        //1、创建 Selector 选择器
        Selector selector = Selector.open();

        //2、创建 ServerSocketChannel 通道
        ServerSocketChannel serverChannel = ServerSocketChannel.open();

        //3、为channel通道绑定监听端口
        serverChannel.configureBlocking(false);
        serverChannel.bind(new InetSocketAddress(8000));


        //4、循环，等待连接接入
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("server start success");

        //5、根据就绪状态，调用对应方法实习具体业务操作
        for (; ; ) {
            //获取 channel 数量
            int channelNums = selector.select();
            if (channelNums == 0) {
                continue;
            }
            //获取可用的 channel
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = keys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                //移除 iterator
                iterator.remove();
                //根据就绪状态，执行不同方法
                if (key.isAcceptable()) {
                    acceptOperator(key, serverChannel, selector);
                } else if (key.isReadable()) {
                    readOperator(selector, key);
                }

            }
        }

    }

    /**
     * 处理可读状态操作
     *
     * @param selector
     * @param key
     */
    private void readOperator(Selector selector, SelectionKey key) throws IOException {
        //1、从 selectionKey 获取到已经就绪的通道
        SocketChannel channel = (SocketChannel) key.channel();
        //2、创建 buffer
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        //3、循环读取客户端消息
        int readLen = channel.read(buffer);
        String msg = "";
        if (readLen > 0) {
            //切换读模式
            buffer.flip();
            //读取内容
            msg += Charset.forName("utf-8").decode(buffer);
        }
        //4、将 channel 再次注册到选择器上，监听可读状态
        channel.register(selector, SelectionKey.OP_READ);
        //5、将客户端消息广播
        if (msg.length() > 0) {
            System.out.println("\n" + msg);
            castOtherClient(msg, selector, channel);
        }
    }

    private void castOtherClient(String msg, Selector selector, SocketChannel channel) throws IOException {
        //1、获取所有已经接入的客户端
        Set<SelectionKey> keys = selector.keys();
        //2、循环向所有的 channel 发送消息
        for (SelectionKey key : keys) {
            //获取每个 channel
            Channel tarChannel = (Channel) key.channel();
            //不需要给自己发送
            if (tarChannel instanceof SocketChannel && tarChannel != channel) {
                ((SocketChannel) tarChannel).write(Charset.forName("utf-8").encode(msg));
            }

        }
    }

    private void acceptOperator(SelectionKey key, ServerSocketChannel serverChannel, Selector selector) throws IOException {

        //1、创建 socketChannel
        SocketChannel accept = serverChannel.accept();
        //2、设置非阻塞
        accept.configureBlocking(false);
        //3、将channel 注册到 selector 上，监听
        accept.register(selector, SelectionKey.OP_READ);
        //4、客户端回复

        //接收客户端姓名
//        SocketChannel channel = (SocketChannel) key.channel();
//        ByteBuffer buffer = ByteBuffer.allocate(1024);
//        accept.read(buffer);
//        String name = "";
//        //切换读模式
//        buffer.flip();
//        //读取姓名
//        name = Charset.forName("utf-8").decode(buffer).toString();
//        System.out.println(name+"加入聊天室");

        accept.write(Charset.forName("utf-8").encode( "welcome to chat room!!!"));
    }


}
