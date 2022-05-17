package other.path;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class MoveFileDemo {
    public static void main(String[] args) {
        Path sourcePath = Paths.get("f:\\test2\\file1.txt");
        Path destinationPath = Paths.get("f:\\test2\\file2.txt");
        try {
            Files.move(sourcePath, destinationPath,
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            //移动文件失败
            e.printStackTrace();
        }
    }

}
