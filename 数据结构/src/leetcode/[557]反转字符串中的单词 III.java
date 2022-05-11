package leetcode;//给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
//
// 
//
// 示例： 
//
// 输入："Let's take LeetCode contest"
//输出："s'teL ekat edoCteeL tsetnoc"
// 
//
// 
//
// 提示： 
//
// 
// 在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。 
// 
// Related Topics 双指针 字符串 👍 393 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution557 {

    public static void main(String[] args) {
        String s ="Let's take LeetCode contest";
        Solution557 demo = new Solution557();
        String s1 = demo.reverseWords(s);
        System.out.println(s1);
    }
        public String reverseWords(String s) {
            StringBuilder str = new StringBuilder(s.length());

            int temp = 0;
            for(int i = 0 ; i < s.length() ; i++){
                if (s.charAt(i) ==' '){
                    str.append(reverseString(s.substring(temp , i)));
                    str.append(' ');
                    temp = i + 1;
                }
                if(i == s.length() - 1){
                    str.append(reverseString(s.substring(temp , i+1)));
                }
            }

            return str.toString();

        }


        public char[] reverseString(String str) {
            char[] s = str.toCharArray();
            int left = 0;
            int right = s.length - 1;
            char temp;
            while(left < right){
                temp = s[left];
                s[left] = s[right];
                s[right ] = temp;
                left ++;
                right --;
            }
            return s;
        }
}
//leetcode submit region end(Prohibit modification and deletion)
