package study.zfk.zhangsanTest;

import java.util.Scanner;

/**
 * @create 09-20 12:16
 */
public class AIMain {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String str;
        while (true){
            str = scan.next();
             str = str.replace("吗", "");
             str = str.replace("?", "!");
             str = str.replace("？", "!");
            System.out.println(str);
        }
    }
}
