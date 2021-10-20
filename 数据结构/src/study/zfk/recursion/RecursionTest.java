package study.zfk.recursion;

/**
 * 递归调用规则：
 * 当程序调用时，就会开辟一个独立的空间（栈）
 * 每个空间的数据（局部变量），是独立的
 */
public class RecursionTest {
    public static void main(String[] args) {
        test(4);
        System.out.println(factorial(30));

        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
        //1409286144
        //2147483647
        //-2147483648
    }

    public static void test(int n ){
        if(n > 2){
            test(n - 1);
        }
        System.out.println("n = "+n);
    }


     public static int factorial(int n){
        if(n == 1){
            return 1 ;
        }else {
            return factorial(n-1)*n;
        }
     }


}
