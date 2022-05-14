package buffer;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class BufferMethodDemo {

    @Test
    public void demo01() {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put(((byte) i));
        }
        //创建子缓冲区
        buffer.position(3);
        buffer.limit(7);
        ByteBuffer slice = buffer.slice();

        //改变子缓冲区的内容
        for (int i = 0; i < slice.capacity(); i++) {
            byte b = slice.get(i);
            b *= 10;
            slice.put(i, b);
        }
        //改变缓冲区 position 和 limit 的位置
        //position == start    limit == end
        buffer.position(0);
        buffer.limit(buffer.capacity());
        while (buffer.remaining() > 0) {
            System.out.print(buffer.get() + "\t");
        }
    }


    @Test
    public void demo02() {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        for (int i = 0; i < 10; i++) {
            buffer.put(((byte) i));
        }
        //重新生成只读缓冲区,并便利
        System.out.println("只读缓冲区：");
        ByteBuffer onlyReadBuffer = buffer.asReadOnlyBuffer();
        for (int i = 0; i < onlyReadBuffer.capacity(); i++) {
            System.out.print(onlyReadBuffer.get(i) + "\t");
        }
        System.out.println("\n改变原缓冲区的值~~~~");
        buffer.flip();
        for (int i = 0; i < buffer.capacity(); i++) {
            byte b = buffer.get(i);
            b *= 10;
            buffer.put(i, b);
        }
        onlyReadBuffer.flip();
        //==
//        onlyReadBuffer.position(0);
//        onlyReadBuffer.limit(buffer.capacity());
        System.out.println("只读缓冲区：");
        while (onlyReadBuffer.remaining() > 0) {
            System.out.print(onlyReadBuffer.get() + "\t");
        }
    }


    @Test
    public void demo3() throws IOException {
        String infile = "f:\\test.txt";
        FileInputStream fin = new FileInputStream(infile);
        FileChannel fcin = fin.getChannel();

        String outfile = String.format("f:\\0002.txt");
        FileOutputStream fout = new FileOutputStream(outfile);
        FileChannel fcout = fout.getChannel();

        // 使用 allocateDirect，而不是 allocate
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
        while (true) {
            buffer.clear();
            int r = fcin.read(buffer);
            if (r == -1) {
                break;
            }
            buffer.flip();
            fcout.write(buffer);
        }
        System.out.println("--- over -----");
    }



    @Test
    public void demo4() throws IOException {

        RandomAccessFile raf =
                new RandomAccessFile("f:\\2222.txt",
                "rw");
        FileChannel fc = raf.getChannel();
        MappedByteBuffer mbb = fc.map(FileChannel.MapMode.READ_WRITE,
                0, 1024);
        mbb.put(0, (byte) 97);
        mbb.put(1023, (byte) 122);
        raf.close();
        System.out.println("--- over -----");
    }


}