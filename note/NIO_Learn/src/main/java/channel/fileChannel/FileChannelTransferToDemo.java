package channel.fileChannel;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

public class FileChannelTransferToDemo {

    public static void main(String args[]) throws Exception {
        // 创建两个 channel
        RandomAccessFile aFile = new
                RandomAccessFile("f:\\test.txt", "rw");
        FileChannel fromChannel = aFile.getChannel();
        RandomAccessFile bFile = new
                RandomAccessFile("f:\\03.txt", "rw");
        FileChannel toChannel = bFile.getChannel();

        long position = 0;
        long count = fromChannel.size();
        //  fromChannel 传输到 toChannel
        fromChannel.transferTo(0,count,toChannel);
        aFile.close();
        bFile.close();
        System.out.println("over!");
    }
}
