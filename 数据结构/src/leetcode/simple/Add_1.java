package leetcode.simple;


/*
给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。

最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。

你可以假设除了整数 0 之外，这个整数不会以零开头。

 */
public class Add_1 {
    public static void main(String[] args) {
        int []digits = {9,9};
        int[] ints = plusOne(digits);

    }


    public static int[] plusOne(int[] digits) {
        //方向错了， 99+1 = 100
        //9 --》10 加一位
        if(digits[digits.length-1]%10 == 9){
            int length = digits.length;

            int res[] = new int [length+1];
            for (int i = 0; i < length-1; i++) {
                res[i] = digits[i];
            }
            res[length]= (digits[length-1]+1)%10;//最后一位
            res[length-1] = (digits[length-1]+1)/10;//倒数第二位
            return res;

        }
        digits[digits.length-1] = digits[digits.length-1] +1;
        return digits;





    }

}
