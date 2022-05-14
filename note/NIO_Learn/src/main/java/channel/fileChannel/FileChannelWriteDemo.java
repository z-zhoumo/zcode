package channel.fileChannel;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelWriteDemo {
    public static void main(String[] args) throws Exception {

        //创建 FileChannel
        RandomAccessFile raFile =
                new RandomAccessFile("F:\\testWrite.txt", "rw");
        FileChannel channel = raFile.getChannel();

        String newData = "New String to write to file..." +
                System.currentTimeMillis();
        //创建 buffer
        ByteBuffer buffer = ByteBuffer.allocate(48);
        buffer.clear();
        //写入内容
        buffer.put(newData.getBytes());
        buffer.flip();
        //FileChannel 实现
        while (buffer.hasRemaining()) {
            channel.write(buffer);
        }
        //必须关闭 channel
        channel.close();
        System.out.println("end");
    }
}

