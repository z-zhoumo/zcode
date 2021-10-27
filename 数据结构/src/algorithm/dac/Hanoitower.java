package algorithm.dac;

public class Hanoitower {
    public static void main(String[] args) {
        hanoiTower(5,'A','B','C');

    }



    //汉诺塔的移动方法
    //使用分治算法

    /**
     *
     * @param num   需要移动盘的数量
     * @param a     盘最初的位置 源盘
     * @param b     帮助移动的盘  辅助盘
     * @param c     盘最后的位置  结果盘
     */
    public static void hanoiTower(int num,char a,char b,char c){
        //只有一个盘
        if(num == 1 ){
            System.out.println("第1个盘从"+a+"-->"+c);
        }else {
            // 如果我们有 n >= 2情况，我们总是可以看做是两个盘
            //最下面一个盘是盘 1  ，上面所有盘是盘 2
            //1 先把上面的所有盘 A->B,移动过程使用到 C
            hanoiTower(num-1,a,c,b);
            //2 把最下面的盘 A -> C
            System.out.println("第"+num+"个盘从"+a+"->"+c);
            //3 把B塔所有的盘从 B ->C,一定过程使用到 A 塔
            hanoiTower(num - 1,b,a,c);
        }
    }
}
