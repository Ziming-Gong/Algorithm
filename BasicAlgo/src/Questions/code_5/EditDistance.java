package Questions.code_5;

import Questions.code_2.ChooseWork;

//https://leetcode.com/problems/edit-distance/ 拓展
public class EditDistance {

    public static int editDistance1(String s1, String s2, int a, int r, int d) {
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int n = str1.length, m = str2.length;
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            dp[i][0] = i * d;
        }
        for (int j = 1; j <= m; j++) {
            dp[0][j] = j * a;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                dp[i][j] = dp[i - 1][j - 1] + (str1[i - 1] == str2[j - 1] ? 0 : r);
                //add
                dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + a);
                //delete
                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + d);
                //replace and stay

            }
        }
        return dp[n][m];
    }

    public static int editDistance2(String s1, String s2, int a, int r, int d) {
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int n = str1.length, m = str2.length;
        int[] dp = new int[m + 1];
        for (int i = 1; i <= m; i++) {
            dp[i] = i * a;
        }
        int pre = 0;
        for (int i = 1; i <= n; i++) {
            pre = dp[0];
            dp[0] = i * d;
            for (int j = 1; j <= m; j++) {
                int temp = dp[j];
                dp[j] = dp[j] + d;
                dp[j] = Math.min(dp[j], pre + (str1[i - 1] == str2[j - 1] ? 0 : r));
                dp[j] = Math.min(dp[j], dp[j - 1] + a);
                pre = temp;
            }
        }
        return dp[m];
    }

    public static void main(String[] args) {
        String str1 = "ab12cd3";
        String str2 = "abcdf";
        System.out.println(editDistance1(str1, str2, 5, 2, 3));
        System.out.println(editDistance2(str1, str2, 5, 2, 3));

        str1 = "abcdf";
        str2 = "ab12cd3";
        System.out.println(editDistance1(str1, str2, 3, 4, 2));
        System.out.println(editDistance2(str1, str2, 3, 4, 2));


        str1 = "";
        str2 = "ab12cd3";
        System.out.println(editDistance1(str1, str2, 1, 5, 7));
        System.out.println(editDistance2(str1, str2, 1, 5, 7));

        str1 = "abcdf";
        str2 = "";
        System.out.println(editDistance1(str1, str2, 2, 8, 9));
        System.out.println(editDistance2(str1, str2, 2, 8, 9));

    }
}
