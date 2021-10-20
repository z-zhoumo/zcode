package study.zfk.recursion;

/**
 * @create 09-17 1:05
 */
public class MiGong {
    public static void main(String[] args) {
        //二维数组，模拟迷宫
        int [][] map = new int [8][7];
        //使用1表示墙，上下全部置为1
        for (int i = 0; i < map[1].length; i++) {
            map[0][i] = 1;
            map[map.length-1][i] = 1;
        }
        //左右置为1
        for (int i = 0; i < map.length; i++) {
            map[i][0] = 1;
            map[i][map[0].length-1] = 1;
        }
        //设置挡板
        map[3][1] = 1;
        map[3][2] = 1;
        map[3][3] = 1;
        map[3][4] = 1;





        System.out.println("路径");
        setWay(map,6,5);

        list(map);



    }


    //使用递归回溯给小球找路
    /*
    * 1 map为地图
    * 2 i，j 表示从地图的哪个位置开始出发（1，1）
    * 3 如果小球能找到[6][5]位置，则说明通路找到
    * 4 约定：0，通路 1墙 2路径 3 位置走过，但是走不通
    * 5 走迷宫时，需要策略 下 右 上 左，走不通回溯
    * */

    /**
     *
     * @param map   地图
     * @param i     i j为出发点坐标，从那个位置开始找
     * @param j
     * @return      找到路，返回true，否则false
     */
    public static boolean setWay(int [][] map, int i , int j) {
        if (map[1][1] == 2) {//道路已经找到
            return true;
        } else {

            if (map[i][j] == 0) {//该点没有走过
                //按照策略 下    右   上   左
                map[i][j] = 2;
                if (setWay(map, i + 1, j)) {
                    return true;
                } else if (setWay(map, i, j + 1)) {
                    return true;
                } else if (setWay(map, i - 1, j)) {
                    return true;
                } else if (setWay(map, i, j - 1)) {
                    return true;
                } else {
                    //走不通，死路
                    map[i][j] = 3;
                    return false;
                }
            } else { //如果map[i][j] != 0，可能是123
                return false;
            }

        }
    }




    //遍历迷宫
    public static void list(int [][] map){
        for (int[] ints : map) {
            for (int anInt : ints) {
                System.out.print("  "+anInt);
            }
            System.out.println();
        }
    }


}
