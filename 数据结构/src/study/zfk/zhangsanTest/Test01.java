package study.zfk.zhangsanTest;

import com.sun.javafx.image.IntPixelGetter;

import java.nio.charset.StandardCharsets;

/*

 */
public class Test01 {
    public static void main(String[] args) {
        String b = "10101000";
        byte i = (byte) Integer.parseInt(b, 2);
        System.out.println(i);
    }
}
