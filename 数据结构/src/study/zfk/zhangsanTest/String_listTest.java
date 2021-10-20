package study.zfk.zhangsanTest;

import java.net.SocketTimeoutException;
import java.util.Scanner;

/*
    我尽然不会这个题，(*^_^*)了
 */
public class String_listTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入字符串"  );
        String str =scanner.next();
        int charNumber = 0;
        int intNumber = 0;
        int otherNumber = 0;
        char[] chars = str.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            if(('a'<= chars[i] && chars[i]<='z')
                    ||(chars[i] >= 'A' && chars[i]<='Z')){
                charNumber++;
            }else if(chars[i] >= '1' && chars[i]<= '9'){
                intNumber++;
            }else{
                otherNumber++;
            }

        }
        System.out.println("charNumber   "+charNumber+"\n"
        +"intNumber   "+intNumber+"\n"
        +"otherNumber    "+otherNumber);


    }
}
