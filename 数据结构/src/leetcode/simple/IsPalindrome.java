package leetcode.simple;

/*
给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 */
public class IsPalindrome {
    public static void main(String[] args) {
        String s = "A man a plan a canal Panama";
        boolean palindrome = isPalindrome(s);
        System.out.println(palindrome);

    }


    public static boolean isPalindrome(String s) {
        char[] chars = s.toCharArray();
        int left = 0;
        int right = chars.length - 1;
        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }

            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }

            if ( Character.toLowerCase(chars[left]) != Character.toLowerCase(chars[right])) {
                return false;
            }
            left++;
            right--;
        }
        return true;


    }
}
