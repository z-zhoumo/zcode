package channel.fileChannel;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class AsyncFileChannelCompletionWrite {
    public static void main(String[] args) throws Exception {
        Path path = Paths.get("F:\\test\\001.txt");

        //1、 创建AsynchronousFileChannel
        // StandardOpenOption.WRITE 表示该文件将允许写入
        AsynchronousFileChannel fileChannel =
                AsynchronousFileChannel
                        .open(path, StandardOpenOption.WRITE);
        //2、 创建 buffer
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put("write message \n".getBytes(StandardCharsets.UTF_8));
        buffer.flip();
        fileChannel.write(buffer, 0, buffer, new CompletionHandler<Integer, ByteBuffer>() {

            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                System.out.println("writing----");
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {

            }
        });
        System.out.println("write over----");
    }
}
