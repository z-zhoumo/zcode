package channel.fileChannel;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.TimeUnit;

public class AsyncFileChannelCompletionRead {
    public static void main(String[] args) throws Exception {
        Path path = Paths.get("F:\\test\\001.txt");

        //1、 创建AsynchronousFileChannel
        // StandardOpenOption.READ 表示该文件将被打开阅读
        AsynchronousFileChannel fileChannel =
                AsynchronousFileChannel
                        .open(path, StandardOpenOption.READ);
        //2、 创建 buffer
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        //3、得到 Future
        fileChannel.read(buffer, 0, buffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                //读取完成后
                System.out.println("result:::"+result);
                attachment.flip();
                byte[] data = new byte[attachment.limit()];
                attachment.get(data );
                System.out.println(new String(data));
                attachment.clear();
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                //读取失败
            }
        });
        TimeUnit.SECONDS.sleep(1);
        // 4、判断是是否完成
        fileChannel.close();
    }
}
