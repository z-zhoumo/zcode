package study.zfk.sort;

/**
 * @create 09-19 15:20
 */
public class Radix {

    //基数排序
    public static void radixSort(int []arr){

        //根据推到，得到最后的代码

        //1 得到数组中最大的位数
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if(arr[i]>max){
                max = arr[i];
            }
        }
        //得到最大的位数
        int maxLength = max + 1;


        //定义一个二维数组，表示10个桶，每个桶就是一个一维数组
        //说明
        //1 二维数组包含10个一维数组
        //2 为了防止在放入数的时候，数据溢出，则每个一维数组下的数组的长度为arr。length
        //3 基数排序是用空间换时间的经典算法
        int[][] bucket = new int[10][arr.length];
        //为了记录每个桶中，实际存放了多少个数据，我们定义一个一维数组，来记录各个桶中的数量
        int [] bucketElementCounts = new int[10];

        //使用循环处理数据
        for (int i = 0,n=1; i < maxLength; i++,n*=10) {

            //第i轮（针对元素的位数进行排序
            int digitOfElement;
            for (int j = 0; j < arr.length; j++) {
                digitOfElement = arr[j]/n%10;
                bucket[digitOfElement]
                        [bucketElementCounts[digitOfElement]]
                        =arr[j];
                bucketElementCounts[digitOfElement]++;
            }
            //按照桶的顺序（一维数组的下标依次取出数据，放入原来的数组）
            int index = 0;
            //遍历每一个桶，并将桶中的数据，放入到原数组中、
            for (int k = 0;k<bucketElementCounts.length;k++){
                //桶中有数据就放入数据
                if(bucketElementCounts[k]!= 0){
                    //有数据，循环遍历
                    for (int m = 0; m < bucketElementCounts[k]; m++) {
                        //取出元素就，放入到arr中
                        arr[index++] = bucket[k][m];
                    }
                }
                //第i轮处理将记录桶中的数据清空bucketElement[k]=0
                bucketElementCounts[k] = 0;
            }
//            System.out.println("第"+(i+1)+"轮的处理结果"+ Arrays.toString(arr));
        }




//        //第一轮（针对元素的个位数进行排序
//        int digitOfElement;
//        for (int j = 0; j < arr.length; j++) {
//            digitOfElement = arr[j]%10;
//            bucket[digitOfElement]
//                    [bucketElementCounts[digitOfElement]]
//                    =arr[j];
//            bucketElementCounts[digitOfElement]++;
//        }
//        //按照桶的顺序（一维数组的下标依次取出数据，放入原来的数组）
//        int index = 0;
//        //遍历每一个桶，并将桶中的数据，放入到原数组中、
//        for (int k = 0;k<bucketElementCounts.length;k++){
//            //桶中有数据就放入数据
//            if(bucketElementCounts[k]!= 0){
//                //有数据，循环遍历
//                for (int i = 0; i < bucketElementCounts[k]; i++) {
//                    //取出元素就，放入到arr中
//                    arr[index++] = bucket[k][i];
//
//                }
//
//            }
//            //第一轮处理将记录桶中的数据清空bucketElement[k]=0
//            bucketElementCounts[k] = 0;
//        }
//        System.out.println("对各位的排序处理"+ Arrays.toString(arr));
//
//
//        System.out.println("*******************");
//        //第二轮（针对元素的十位数进行排序
//        for (int j = 0; j < arr.length; j++) {
//            digitOfElement = arr[j]/10%10;
//            bucket[digitOfElement]
//                    [bucketElementCounts[digitOfElement]]
//                    =arr[j];
//            bucketElementCounts[digitOfElement]++;
//        }
//        //按照桶的顺序（一维数组的下标依次取出数据，放入原来的数组）
//         index = 0;
//        //遍历每一个桶，并将桶中的数据，放入到原数组中、
//        for (int k = 0;k<bucketElementCounts.length;k++){
//            //桶中有数据就放入数据
//            if(bucketElementCounts[k]!= 0){
//                //有数据，循环遍历
//                for (int i = 0; i < bucketElementCounts[k]; i++) {
//                    //取出元素就，放入到arr中
//                    arr[index++] = bucket[k][i];
//                }
//            }
//            //第一轮处理将记录桶中的数据清空bucketElement[k]=0
//            bucketElementCounts[k] = 0;
//        }
//        System.out.println("对十位的排序处理"+ Arrays.toString(arr));
//
//        System.out.println("*******************");
//        //第三轮（针对元素的百位数进行排序
//        for (int j = 0; j < arr.length; j++) {
//            digitOfElement = arr[j]/100%10;
//            bucket[digitOfElement]
//                    [bucketElementCounts[digitOfElement]]
//                    =arr[j];
//            bucketElementCounts[digitOfElement]++;
//        }
//        //按照桶的顺序（一维数组的下标依次取出数据，放入原来的数组）
//        index = 0;
//        //遍历每一个桶，并将桶中的数据，放入到原数组中、
//        for (int k = 0;k<bucketElementCounts.length;k++){
//            //桶中有数据就放入数据
//            if(bucketElementCounts[k]!= 0){
//                //有数据，循环遍历
//                for (int i = 0; i < bucketElementCounts[k]; i++) {
//                    //取出元素就，放入到arr中
//                    arr[index++] = bucket[k][i];
//                }
//            }
//        }
//        System.out.println("对百位的排序处理"+ Arrays.toString(arr));


    }
}
