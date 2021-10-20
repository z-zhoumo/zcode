package leetcode.simple;

/**
 *给定一个数组 prices ，其中prices[i] 是一支给定股票第 i 天的价格。
 *
 * 设计一个算法来计算你所能获取的最大利润。
 * 你可以尽可能地完成更多的交易（多次买卖一支股票）。
 *
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *

 */
public class Solution02 {
    public static void main(String[] args) {
        int prices[] = {7,1,5,3,6,4};
        int i = maxProfit(prices);
    }
    public static int maxProfit (int [] prices){
        int result = 0;
        for (int i = 1; i < prices.length; i++) {
            if(prices[i-1] < prices[i]){
                result =result + prices[i]- prices [i-1];
            }
        }
        return result;
    }
}
