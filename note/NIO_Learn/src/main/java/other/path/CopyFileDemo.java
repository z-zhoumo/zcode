package other.path;

import other.fileLock.FileDemo;

import java.nio.file.*;

public class CopyFileDemo {
    public static void main(String[] args) {
        Path sourcePath = Paths.get("f:\\test2\\file1.txt");
        Path desPath = Paths.get("f:\\test2\\copyFile1.txt");
        try {
//            Files.copy(sourcePath,desPath);
            //覆盖现有文件
            Files.copy(sourcePath,desPath,
                    StandardCopyOption.REPLACE_EXISTING);
            System.out.println("复制成功");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("copy exception");
        }
    }
}
