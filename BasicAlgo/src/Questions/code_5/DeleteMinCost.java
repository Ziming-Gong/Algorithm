package Questions.code_5;

// 题目：
// 给定两个字符串s1和s2，问s2最少删除多少字符可以成为s1的子串？
// 比如 s1 = "abcde"，s2 = "axbc"
// 返回 1

import Questions.code_2.ChooseWork;

import java.util.*;

public class DeleteMinCost {

    /**
     * 在s2特别小的时候可以用这个方法
     */

    public static int minCost1(String s1, String s2) {
        List<String> list = new ArrayList<>();
        process(s2, 0, list, "");
        list.sort(new lengthComparator());
        for (String cur : list) {
            if (s1.indexOf(cur) != -1) {
                return s2.length() - cur.length();
            }
        }
        return s2.length();
    }

    public static void process(String str, int index, List<String> list, String path) {
        if (index == str.length()) {
            list.add(path);
            return;
        }
        process(str, index + 1, list, path + str.charAt(index));
        process(str, index + 1, list, path);

    }

    public static class lengthComparator implements Comparator<String> {
        @Override
        public int compare(String s1, String s2) {
            return s2.length() - s1.length();
        }
    }

    /**
     * 当s1的值比较小的时候， 遍历s1所有的子串 与s2算最少删除
     *
     * @param s1
     * @param s2
     * @return
     */
    public static int minCost2(String s1, String s2) {
        if (s1.length() == 0 || s2.length() == 0) {
            return s2.length();
        }
        int n = s1.length();
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                ans = Math.min(ans, onlyDelete(s2, s1.substring(i, j)));
            }
        }
        return ans == Integer.MAX_VALUE ? s2.length() : ans;
    }

    /**
     * s1 需要经过多少次删除 得到s2
     * dp[i][j] str1[0...i-1] ----> str2[0...j-1]  需要多少次删除操作可以得到
     *
     * @param s1
     * @param s2
     * @return
     */
    public static int onlyDelete(String s1, String s2) {
        if (s1.length() < s2.length()) {
            return Integer.MAX_VALUE;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int n = str1.length, m = str2.length;
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        for (int i = 0; i <= n; i++) {
            dp[i][0] = i;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= Math.min(i, m); j++) {
                if (str1[i - 1] == str2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                if (dp[i - 1][j] != Integer.MAX_VALUE) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + 1);
                }
            }
        }
        return dp[n][m];
    }

    /**
     * 优化minCost2，以每个str1的某个字符开头的遍历一次
     */

    public static int minCost3(String s1, String s2) {
        if (s1.length() == 0 || s2.length() == 0) {
            return s2.length();
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int n = str1.length, m = str2.length;
        int[][] dp = new int[m][n];
        int ans = m;
        for (int start = 0; start < n; start++) {
            //第一列 start
            dp[0][start] = str2[0] == str1[start] ? 0 : Integer.MAX_VALUE;//为什么是M
            for (int i = 1; i < m; i++) {
                dp[i][start] = (dp[i - 1][start] != Integer.MAX_VALUE || str2[i] == str1[start]) ? i : Integer.MAX_VALUE;
            }
            ans = Math.min(ans, dp[m - 1][start]);
            //start 列填好了 添start+1以后的列
            for (int end = start + 1; end < n && end - start < m; end++) {
                int first = end - start;
                dp[first][end] = (str1[end] == str2[first] && dp[first - 1][end - 1] == 0) ? 0 : Integer.MAX_VALUE;
                for (int i = first + 1; i < m; i++) {
                    dp[i][end] = Integer.MAX_VALUE;
                    if (dp[i - 1][end] != Integer.MAX_VALUE) {
                        dp[i][end] = dp[i - 1][end] + 1;
                    }
                    if (str1[end] == str2[i] && dp[i - 1][end - 1] != Integer.MAX_VALUE) {
                        dp[i][end] = Math.min(dp[i][end], dp[i - 1][end - 1]);
                    }
                }
                ans = Math.min(ans, dp[m - 1][end]);
            }

        }

        return ans;
    }

    public static String generateRandomString(int l, int v) {
        int len = (int) (Math.random() * l);
        char[] str = new char[len];
        for (int i = 0; i < len; i++) {
            str[i] = (char) ('a' + (int) (Math.random() * v));
        }
        return String.valueOf(str);
    }

    public static void main(String[] args) {

        String st1 = "cbb";
        String st2 = "ceabdb";
        System.out.print(minCost2(st1, st2));
//
//        System.out.println(onlyDelete(x, y));

        int str1Len = 20;
        int str2Len = 10;
        int v = 5;
        int testTime = 10000;
        boolean pass = true;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            String str1 = generateRandomString(str1Len, v);
            String str2 = generateRandomString(str2Len, v);
            int ans1 = minCost1(str1, str2);
            int ans2 = minCost2(str1, str2);
            int ans3 = minCost3(str1, str2);
//            int ans4 = minCost4(str1, str2);
            if (ans1 != ans2 || ans1 != ans3) {//|| ans3 != ans4
                pass = false;
                System.out.println(str1);
                System.out.println(str2);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans3);
//                System.out.println(ans4);
                break;
            }
        }
        System.out.println("test pass : " + pass);
    }

}
