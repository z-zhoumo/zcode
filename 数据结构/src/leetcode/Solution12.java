package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author niko
 * <p>
 * <p>
 * I             1
 * V             5
 * IV            4
 * X             10
 * IX            9
 * L             50
 * XL           40
 * C             100
 * XC            90
 * D             500
 * CD           400
 * M             1000
 * CM           900
 */
public class Solution12 {
    public static void main(String[] args) {

//        intToRoman(3544);// MMMDXLIV
//        intToRoman(88);// VIII
        intToRoman(1994);// MCMXCIV

    }

   private static Map<Integer, String> map = new HashMap<>();
    static {
        map.put(1, "I");
        map.put(4, "IV");
        map.put(5, "V");
        map.put(9, "IX");
        map.put(10, "X");
        map.put(40, "XL");
        map.put(50, "L");
        map.put(90, "XC");
        map.put(100, "C");
        map.put(400, "CD");
        map.put(500, "D");
        map.put(900, "CM");
        map.put(1000, "M");
    }


    public static String intToRoman(int num) {


        Map<Integer, String> map = new HashMap<>();
        map.put(1, "I");
        map.put(4, "IV");
        map.put(5, "V");
        map.put(9, "IX");
        map.put(10, "X");
        map.put(40, "XL");
        map.put(50, "L");
        map.put(90, "XC");
        map.put(100, "C");
        map.put(400, "CD");
        map.put(500, "D");
        map.put(900, "CM");
        map.put(1000, "M");


        StringBuilder stringBuilder = new StringBuilder();
        int[] numArr = new int[4];
        int ind = 3;

        while (num != 0) {
            numArr[ind] = num % 10;
            num /= 10;
            ind--;
        }


        //遍历 num 组成的数组
        for (int i = 0; i < 4; i++) {
            //获取其中的数
            //首先获取最高位数
            for (int j = 0; j < numArr[i]; j++) {

                //4 和 9 作为特殊值出现
                if (numArr[i] == 4 || numArr[i] == 9) {

                    int temp = (int) Math.pow(10, 3 - i) * numArr[i];
                    stringBuilder.append(map.get(temp));
                    break;
                } else if (numArr[i] >= 5) {
                    stringBuilder.append(map.get((int) Math.pow(10, 3 - i) * 5));
                    numArr[i] = numArr[i] - 5;
                    j--;
                } else {
                    stringBuilder.append(map.get((int) Math.pow(10, 3 - i)));

                }
            }
        }


        return stringBuilder.toString();


    }
}
