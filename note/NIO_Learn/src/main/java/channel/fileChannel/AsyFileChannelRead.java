package channel.fileChannel;


import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class AsyFileChannelRead {
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
        Future<Integer> operation = fileChannel.read(buffer, 0);

        // 4、判断是是否完成
        while (!operation.isDone()) {
            TimeUnit.SECONDS.sleep(1);
            buffer.flip();
            byte[] data = new byte[buffer.limit()];
            buffer.get(data );
            System.out.println(new String(data));
           buffer.clear();
        }
        fileChannel.close();
    }
}
