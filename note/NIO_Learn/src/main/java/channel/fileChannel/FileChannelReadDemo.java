package channel.fileChannel;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelReadDemo {
    public static void main(String[] args) throws Exception {

        //创建 FileChannel
        RandomAccessFile raFile =
                new RandomAccessFile("F:\\test.txt", "rw");
        FileChannel channel = raFile.getChannel();

        //创建 buffer 大小为 5 byte
        ByteBuffer buffer = ByteBuffer.allocate(5);

        //读取数据到 buffer 中
        int byteRead = channel.read(buffer);
        while (byteRead != -1){
            System.out.println("读取："+byteRead);
            buffer.flip();//反转读写
            while (buffer.hasRemaining()){
                System.out.print((char) buffer.get());
            }
            System.out.println();
            buffer.clear();
            byteRead = channel.read(buffer);
        }

        /*
        关闭这个随机访问文件流，并释放与该流相关的任何系统资源。
        已关闭的随机访问文件不能执行输入或输出操作，也不能重新打开。
        如果该文件有关联的通道，那么该通道也将关闭。
         */
        raFile.close();
        System.out.println("end");

    }
}
