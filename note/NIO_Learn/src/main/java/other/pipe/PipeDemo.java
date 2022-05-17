package other.pipe;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;
import java.nio.charset.StandardCharsets;

//数据会被写到 sink 通道，从 source 通道读取
public class PipeDemo {
    public static void main(String[] args) throws IOException {

        //1、获取管道
        Pipe pipe = Pipe.open();
        //2、获取 sink 通道,写数据
        Pipe.SinkChannel sink = pipe.sink();

        //3、创建缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        //4、写入数据
        buffer.put("Hello,World!".getBytes(StandardCharsets.UTF_8));
        buffer.flip();
        sink.write(buffer);
        //5、获取 source 通道，读数据
        Pipe.SourceChannel source = pipe.source();
        //6、读数据
        buffer.flip();
        int len = source.read(buffer);
        System.out.println(new String(buffer.array(),0,len));

        //7、关闭管道
       sink.close();;
       source.close();
    }
}
