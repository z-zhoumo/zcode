package leetcode;

/**
//将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。 
//
// 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下： 
//
// 
//P   A   H   N
//A P L S I I G
//Y   I   R 
//
// 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。 
//
// 请你实现这个将字符串进行指定行数变换的函数： 
//
// 
//string convert(string s, int numRows); 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "PAYPALISHIRING", numRows = 3
//输出："PAHNAPLSIIGYIR"
// 
//示例 2：
//
// 
//输入：s = "PAYPALISHIRING", numRows = 4
//输出："PINALSIGYAHRPI"
//解释：
//P     I    N
//A   L S  I G
//Y A   H R
//P     I
// 
//
// 示例 3： 
//
// 
//输入：s = "A", numRows = 1
//输出："A"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 1000 
// s 由英文字母（小写和大写）、',' 和 '.' 组成 
// 1 <= numRows <= 1000 
// 
// Related Topics 字符串 👍 1671 👎 0

*/

//leetcode submit region begin(Prohibit modification and deletion)
class Solution6 {
    public static void main(String[] args) {
//        System.out.println(convert("dvdfvadgasdvbadfvbadf", 4));
//        System.out.println(convert2("dvdfvadgasdvbadfvbadf", 4));
        System.out.println(convert2("11111111111111111111111111", 4));
//        System.out.println(convert("a", 1));
    }


    public static String convert2(String s, int numRows) {
        int n = s.length(), r = numRows;
        if (r == 1 || r >= n) {
            return s;
        }
        //计算二维数组的 行数和列数
        int t = r * 2 - 2;
        int c = (n + t - 1) / t * (r - 1);
        char[][] mat = new char[r][c];
        for (int i = 0, x = 0, y = 0; i < n; ++i) {
            mat[x][y] = s.charAt(i);
            if (i % t < r - 1) {
                ++x; // 向下移动
            } else {
                --x;
                ++y; // 向右上移动
            }
        }
        StringBuffer ans = new StringBuffer();
        for (char[] row : mat) {
            for (char ch : row) {
                if (ch != 0) {
                    ans.append(ch);
                }
            }
        }
        return ans.toString();

    }


    public static String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }

        char[][] chars = new char[numRows][s.length()];

        int x = 0, y = 0;
        for (int i = 0; i < s.length(); i++) {
            /*
                3   0 2 4
                4   0 3 6
             */
            chars[x][y] = s.charAt(i);
//            lookArr(chars);
            if (y % (numRows - 1) == 0 && x < numRows - 1) {
                x++;
            } else {
                x--;
                y++;
            }
        }
        //遍历取出

        char[] res = new char[s.length()];
        int index = 0;
        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < chars[i].length; j++) {
                if (chars[i][j] != 0){
                    res[index] = chars[i][j];
                    index++;
                }

                if(index >= s.length()){
                    return String.valueOf(res);
                }

            }

        }
        return String.valueOf(res);


//        StringBuilder stringBuilder = new StringBuilder();
//        for (int i = 0; i < chars.length; i++) {
//            for (int j = 0; j < chars[i].length; j++) {
//                if(chars[i][j] != 0){
//
//                    stringBuilder.append(chars[i][j]);
//                }
//            }
//        }
//        System.out.println(stringBuilder);
//        return stringBuilder.toString();


    }

    private static void lookArr(char[][] chars) {
        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < chars[i].length; j++) {
                System.out.print(chars[i][j]);
            }
            System.out.println();

        }
        System.out.println();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
