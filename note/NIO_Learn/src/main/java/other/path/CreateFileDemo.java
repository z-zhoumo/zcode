package other.path;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CreateFileDemo {
    public static void main(String[] args) {

        Path DirPath = Paths.get("f:\\test2");
        Path FilePath = Paths.get("f:\\test2\\file1.txt");

        try {
            //创建目录
            Files.createDirectories(DirPath);
            System.out.println("目录创建成功");
            //创建文件
            Files.createFile(FilePath);
            System.out.println("文件创建成功");
        } catch (FileAlreadyExistsException e) {
            System.out.println("文件已存在");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
