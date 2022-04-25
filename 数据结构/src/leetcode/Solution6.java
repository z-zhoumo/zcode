package leetcode;

/**
 * @Author niko
 */
public class Solution6 {
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
