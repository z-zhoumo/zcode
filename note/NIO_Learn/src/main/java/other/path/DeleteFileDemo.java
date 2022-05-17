package other.path;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DeleteFileDemo {
    public static void main(String[] args) {
        Path path = Paths.get("f:\\test2\\file1.txt");
        try {
            Files.delete(path);
        } catch (IOException e) {
            // 删除文件失败
            e.printStackTrace();
        }
    }
}
