package chatroom;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

public class ClientThread implements Runnable {

    private Selector selector;

    public ClientThread(Selector selector) {
        this.selector = selector;
    }

    @Override
    public void run() {
        try {
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
                    if (key.isReadable()) {
                        readOperator(selector, key);
                    }

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
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
            System.out.println(msg);
        }
    }

}
