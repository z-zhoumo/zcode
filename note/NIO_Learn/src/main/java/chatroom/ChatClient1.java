package chatroom;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Scanner;

public class ChatClient1 {
    public static void main(String[] args) {
        try {
            new ChatClient1().startClient("李四");
//            //使用多线程创建 3 个客户端
//            for (int i = 0; i < 3; i++) {
////                new Thread(() -> {
////                    try {
////                        new ChatClient().startClient("用户"+Thread.currentThread().getName());
////                    } catch (IOException e) {
////                        e.printStackTrace();
////                    }
////                }, "Thread" + i).start();
//            }
        } catch (Exception e) {
            System.out.println("客户端启动失败");
        }
    }


    //启动客户端方法
    public void startClient(String name) throws IOException {
        //1、连接服务器
        SocketChannel channel = SocketChannel
                .open(new InetSocketAddress("127.0.0.1", 8000));
        //2、接收服务端响应数据
        Selector selector = Selector.open();
        channel.configureBlocking(false);
        channel.register(selector, SelectionKey.OP_READ);

        //创建一个线程实现接收消息
        new Thread(new ClientThread(selector)).start();

        //3、向服务器发送消息
        System.out.println("please send message");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String msg = scanner.nextLine();
            if (msg.length() > 0) {
                channel.write(Charset.forName("utf-8")
                        .encode(name + ":::::" + msg));
            }

        }

        //3、接收服务器响应数据
    }
}
