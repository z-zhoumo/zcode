package demo;

import java.lang.reflect.Array;
import java.util.Arrays;

public class StringMethod {

    public static void main(String[] args) {
        String s = "1,2,3,5,6";

        String[] split = s.split(",");
        System.out.println(Arrays.toString(split) );//[1, 2, 3, 5, 6]
        String[] split1 = s.split(",", 3);
        System.out.println(Arrays.toString(split1));//[1, 2, 3,5,6]

        String replace = s.replace(",", "");
        System.out.println(replace);//12356


    }
}
