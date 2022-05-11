package leetcode;//罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。
//
// 
//字符          数值
//I             1
//V             5
//X             10
//L             50
//C             100
//D             500
//M             1000 
//
// 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做 XXVII, 即为 XX + V + 
//II 。 
//
// 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5
// 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况： 
//
// 
// I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。 
// X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
// C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。 
// 
//
// 给你一个整数，将其转为罗马数字。 
//
// 
//
// 示例 1: 
//
// 
//输入: num = 3
//输出: "III" 
//
// 示例 2: 
//
// 
//输入: num = 4
//输出: "IV" 
//
// 示例 3: 
//
// 
//输入: num = 9
//输出: "IX" 
//
// 示例 4: 
//
// 
//输入: num = 58
//输出: "LVIII"
//解释: L = 50, V = 5, III = 3.
// 
//
// 示例 5: 
//
// 
//输入: num = 1994
//输出: "MCMXCIV"
//解释: M = 1000, CM = 900, XC = 90, IV = 4. 
//
// 
//
// 提示： 
//
// 
// 1 <= num <= 3999 
// 
// Related Topics 哈希表 数学 字符串 👍 874 👎 0


import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution12 {
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

//leetcode submit region end(Prohibit modification and deletion)
