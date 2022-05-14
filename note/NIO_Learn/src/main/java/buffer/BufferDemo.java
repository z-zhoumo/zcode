package buffer;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;

public class BufferDemo {


    @Test
    public void buffer01() throws Exception {
        //打开 channel
        RandomAccessFile raFile =
                new RandomAccessFile("f:\\test.txt", "rw");

        FileChannel channel = raFile.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.clear();
        int byteRead = channel.read(buffer);
        while (byteRead != -1){
            System.out.println("-- start ---");
            // read 模式
            buffer.flip();
            while (buffer.hasRemaining()) {
                System.out.print((char) buffer.get() );
            }
            buffer.clear();
            byteRead = channel.read(buffer);
            System.out.println();
        }
        raFile.close();
        System.out.println("---over ----");
    }



    @Test
    public void buffer02() throws FileNotFoundException {
        //创建 buffer
        IntBuffer buffer = IntBuffer.allocate(10);
        for (int i = 0; i < buffer.capacity(); i++) {
            int j = 2*(i+1);
            buffer.put(j);
        }
        //重置缓冲区
        buffer.flip();
        //获取
        while (buffer.hasRemaining()){
            int value = buffer.get();
            System.out.print(value+" ");
        }
    }
}
