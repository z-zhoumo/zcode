package other.fileLock;

import channel.fileChannel.FileChannelReadDemo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileDemo {
    public static void main(String[] args) throws IOException {

        //
        String input = "input--message";
        System.out.println(input);

        ByteBuffer buffer = ByteBuffer.wrap(input.getBytes(StandardCharsets.UTF_8));

        String filePath = "f:\\02.txt";
        Path path = Paths.get(filePath);
        FileChannel channel = FileChannel.open(path,
                StandardOpenOption.WRITE,
                StandardOpenOption.APPEND);
        channel.position(channel.size() - 1 );

        //独占锁
        FileLock lock = channel.lock();
        //共享锁
//        FileLock lock = channel.lock(0L, Long.MAX_VALUE, true);
        if(lock.isShared()){
            System.out.println("共享锁");
        }
        channel.write(buffer);
        channel.close();

        //读
        readFile(filePath);

    }

    private static void readFile(String filePath) {
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String str = bufferedReader.readLine();
            System.out.println("内容："+str+"\n");
            while (str != null){
                System.out.println("\t"+ str);
                str = bufferedReader.readLine();
            }
            fileReader.close();
            bufferedReader.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
