package study.zfk.sparse;

/**
 * @author zfk
 * @create 2021-08-15 17:57
 */
public class SparseArray {
    public static void main(String[] args) {
        //创建一个原始的二维数组11*11
        int[][] chessArr1 = new int[11][11];
        //0：没有棋子，1黑子 2蓝子
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        chessArr1[9][3] = 2;

        System.out.println("原始二维数组");
        for (int[] data : chessArr1) {
            for (int i : data) {
                System.out.printf("%d\t", i);
            }
            System.out.println();
        }

        //将二维数组转化为稀疏数组
        int sum=0;
        for (int[] data : chessArr1) {
            for (int i : data) {
                if(i!=0){
                    sum++;
                }
            }
        }
        System.out.println("sum的值是" + sum);
        //创建对应的稀疏数组
        int sparseArr[][]=new int[sum+1][3];
        //给稀疏数组赋值
        sparseArr[0][0]=11;
        sparseArr[0][1]=11;
        sparseArr[0][2]=sum;

        //遍历二维数组，给稀疏数组赋值，
        int count=0;//记录第几个为非0数据
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if(chessArr1[i][j]!=0){
                    count++;
                    sparseArr[count][0]=i;
                    sparseArr[count][1]=j;
                    sparseArr[count][2]=chessArr1[i][j];
                }
            }
        }
       //遍历稀疏数组
        System.out.println("稀疏数组");
        for (int i = 0; i < sparseArr.length; i++) {
            System.out.printf("%d\t%d\t%d\n",sparseArr[i][0],
                                            sparseArr[i][1],
                                            sparseArr[i][2]);
        }
        //将稀疏数组---》恢复为二维数组
        //1.先读取第一行数据，得到行列数
         int chessArr2[][]=new int[sparseArr[0][0]]
                                    [sparseArr[0][1]];
        //2.恢复数组；
        for (int i = 1; i <sparseArr.length; i++) {
            chessArr2[sparseArr[i][0]][sparseArr[i][1]]=sparseArr[i][2];
        }

        System.out.println("遍历得到的新数组---------》》");
        for (int[] data : chessArr2) {
            for (int i : data) {
                System.out.printf("%d\t", i);
            }
            System.out.println();
        }
    }
}
