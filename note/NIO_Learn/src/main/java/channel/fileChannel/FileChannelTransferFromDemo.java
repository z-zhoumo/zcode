package channel.fileChannel;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

public class FileChannelTransferFromDemo {
    public static void main(String args[]) throws Exception {

        // 创建两个 channel
        RandomAccessFile aFile = new
                RandomAccessFile("f:\\test.txt", "rw");
        FileChannel fromChannel = aFile.getChannel();
        RandomAccessFile bFile = new
                RandomAccessFile("f:\\02.txt", "rw");
        FileChannel toChannel = bFile.getChannel();

        long position = 0;
        long count = fromChannel.size();
        //  fromChannel 传输到 toChannel
        toChannel.transferFrom(fromChannel, position, count);
        aFile.close();
        bFile.close();
        System.out.println("over!");
    }
}
