package algorithm.kmp;

public class ViolenceMatch {
    public static void main(String[] args) {
        String s1 = "09809";
        String s2 = "12345hello";
        int i = violenceMatch(s2, s1);
        System.out.println(i);


    }


    //暴力匹配算法的实现
    public static int violenceMatch(String str1,String str2){
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();

        int s1Len = s1.length;
        int s2Len = s2.length;

        int i = 0;
        int j = 0;

        while (i < s1Len && j < s2Len ){
           if(s1[i] == s2[j]){
               j++;
               i++;
           }else {
               i++;
           }
        }
        if(j == s2Len){
            return i-j;
        }
        return -1;
    }
}
