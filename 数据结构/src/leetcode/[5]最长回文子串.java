//package leet.leetcode.editor.cn;//给你一个字符串 s，找到 s 中最长的回文子串。
////
////
////
//// 示例 1：
////
////
////输入：s = "babad"
////输出："bab"
////解释："aba" 同样是符合题意的答案。
////
////
//// 示例 2：
////
////
////输入：s = "cbbd"
////输出："bb"
////
////
//// 示例 3：
////
////
////输入：s = "a"
////输出："a"
////
////
//// 示例 4：
////
////
////输入：s = "ac"
////输出："a"
////
////
////
////
//// 提示：
////
////
//// 1 <= s.length <= 1000
//// s 仅由数字和英文字母（大写和/或小写）组成
////
//// Related Topics 字符串 动态规划 👍 4545 👎 0
//
//
//import leetcode.simple.Jiao_ji;
//
////leetcode submit region begin(Prohibit modification and deletion)
//class Solution5 {
//    public String longestPalindrome(String s) {
//        int maxStr;
//        for (int i = s.length(); i >0; i--) {
//            for (int j = 0; j <i; j++) {
//                if
//
//            }
//        }
//
//
//
//    }
//
//    //是否为回文串
//    public boolean isPalindrome(String s){
//        int length = s.length();
//        for (int i = 0; i < length / 2; i++) {
//            if(s.charAt(i) != s.charAt(length-1-i)){
//                return false;
//            }
//        }
//        return true;
//    }
//}
////leetcode submit region end(Prohibit modification and deletion)
