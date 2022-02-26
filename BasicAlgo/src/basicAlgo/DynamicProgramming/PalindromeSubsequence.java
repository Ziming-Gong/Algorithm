package basicAlgo.DynamicProgramming;

// https://leetcode.com/problems/longest-palindromic-subsequence/
public class PalindromeSubsequence {

    //1. Use Longest Common Subsequence
//    public static int longestCommonSubsequence(String s1, String s2) {
//        if (s1 == null || s1.length() == 0 || s2 == null || s2.length() == 0) {
//            return 0;
//        }
//        return process(s1.toCharArray(),s2.toCharArray(), s1.length()-1,s2.length()-1);
//    }
//
//    public static int process(char[] str1, char[] str2, int index1, int index2) {
//        if (index1 == 0 && index2 == 0) {
//            return str1[index1] == str2[index2] ? 1 : 0;
//        } else if (index1 == 0) {
//            return str1[index1] == str2[index2] ? 1 : process(str1, str2, index1, index2 - 1);
//        } else if (index2 == 0) {
//            return str1[index1] == str2[index2] ? 1 : process(str1, str2, index1 - 1, index2);
//        }else {
//            int p1 = process(str1,str2,index1-1,index2-1);
//            int p2 = process(str1,str2,index1,index2-1);
//            int p3 = process(str1,str2,index1-1,index2);
//            int p4 = str1[index1] == str2[index2] ? 1 + process(str1,str2,index1-1,index2-1) : 0;
//            return Math.max(Math.max(p1,p2), Math.max(p3,p4));
//        }
//    }
    public int longestPalindromeSubseq(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int n = s.length();
        int[][] dp = new int[n][n];
        dp[n - 1][n - 1] = 1;
        for (int i = 0; i < n - 1; i++) {
            dp[i][i] = 1;
            dp[i][i + 1] = str[i] == str[i + 1] ? 2 : 1;
        }
        for (int L = n - 3; L >= 0; L--) {
            for (int R = L + 2; R < n; R++) {
                int p1 = dp[L + 1][R];
                int p2 = dp[L][R - 1];
                int p3 = str[L] == str[R] ? 2 + dp[L + 1][R - 1] : 0;
                dp[L][R] = Math.max(p1, Math.max(p2, p3));
            }
        }
        return dp[0][n - 1];

    }


}
