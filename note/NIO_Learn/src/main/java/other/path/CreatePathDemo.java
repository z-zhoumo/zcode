package other.path;

import java.nio.file.Path;
import java.nio.file.Paths;

public class CreatePathDemo {
    public static void main(String[] args) {
        // 获取一个 path 绝对路径实例
        Path absolutePath = Paths.get("f:\\text\\test.txt");
        System.out.println(absolutePath);
        //获取一个 path 的相对路径
        Path relativePath = Paths.get("f:\\text", "test.txt");
        System.out.println(relativePath);
        //标准化路径
        Path originalPath = Paths.get("f:\\text\\.\\text.txt");
        System.out.println(originalPath);
        // .和 .. 表示当前和上一级路径
        Path normalize = originalPath.normalize();
        System.out.println(normalize);
    }
}
