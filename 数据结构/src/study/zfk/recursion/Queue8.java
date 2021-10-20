package study.zfk.recursion;

/**
 * @create 09-17 10:37
 */
public class Queue8 {

    int max = 8;//皇后数量
    //定义数组，保存皇后放置位置的结果，数组索引为行号，值为列号
    int array[]=new int[max];
    int count = 0;
    int judgeCount = 0;

    public static void main(String[] args) {
        Queue8 queue8 = new Queue8();
        queue8.check(0);
        System.out.println("解法数量："+ queue8.count);
        System.out.println("判断次数："+ queue8.judgeCount);
    }

    //写一个方法，可以将皇后摆放的位置输出
    public  void print(){
        count++;
        for (int i : array) {
            System.out.print(i+" ");
        }
        System.out.println();
    }

    /*
    * 放置n个皇后
    * */
    public void check(int n){
        if(n == max){
            print();
            return;
        }
        //依次放入皇后，判断是否冲突
        for (int i = 0; i < max; i++) {
            //当x=n时，y=i，y依次判断
            array[n] = i;
            if(judge(n)){//不冲突，放置下一个
                check(n+1);
            }
            //冲突，y++，依次匹配，如果全部冲突，不进入check(n+1),
            // 进而将上一个下移，即i+1就回溯
        }
    }


    //判断是否冲突
    /*
       查看当我们放置n个皇后时，检查是否和前面的皇后是否冲突
     */
    public boolean judge(int n){
        judgeCount ++;
        for (int i = 0; i < n; i++) {
            if(array[i] == array [n] ||
                    //比较x的值是否相等，x就是索引，判断是否时同一列
                    Math.abs(n-i) == Math.abs(array[n] - array[i])){
                    //y就是对应下标的值
                    //x1-x2==y1-y2，相等就是同一斜线
                return false;
            }
        }
        return true;
    }



}
