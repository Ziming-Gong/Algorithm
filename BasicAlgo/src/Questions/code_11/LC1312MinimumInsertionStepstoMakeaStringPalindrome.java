package Questions.code_11;

import basicAlgo.DynamicProgramming.KillerMonster;
import basicAlgo.mergesorted.ans;

import java.util.ArrayList;
import java.util.List;

public class LC1312MinimumInsertionStepstoMakeaStringPalindrome {
    public static int minCut(String s) {
        if (s == null || s.length() < 2) {
            return 0;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        boolean[][] checkMap = checkMap(str);
        int[] dp = new int[N + 1];
        dp[N] = 0;
        for (int i = N - 1; i >= 0; i--) {
            if (checkMap[i][N - 1]) {
                dp[i] = 1;
            } else {
                int next = Integer.MAX_VALUE;
                for (int j = i; j < N; j++) {
                    if (checkMap[i][j]) {
                        next = Math.min(next, dp[j + 1] + 1);
                    }
                }
                dp[i] = next;
            }
        }
        return dp[0] - 1;

    }

    public static boolean[][] checkMap(char[] str) {
        int N = str.length;
        boolean[][] ans = new boolean[N][N];
        for (int i = 0; i < N - 1; i++) {
            ans[i][i] = true;
            ans[i][i + 1] = str[i] == str[i + 1];
        }
        ans[N - 1][N - 1] = true;
        for (int i = N - 3; i >= 0; i--) {
            for (int j = i + 2; j < N; j++) {
                ans[i][j] = (str[i] == str[j] && ans[i + 1][j - 1]);
            }
        }
        return ans;
    }

    //返回其中一种结果
    public static List<String> minCutOneWay(String s) {
        List<String> ans = new ArrayList<>();
        if (s == null || s.length() < 2) {
            ans.add(s);
        } else {
            char[] str = s.toCharArray();
            int N = str.length;
            int[] dp = new int[N + 1];
            dp[0] = 0;
            boolean[][] checkMap = checkMap(str);
            for (int i = N - 1; i >= 0; i--) {
                if (checkMap[i][N - 1]) {
                    dp[i] = 1;
                } else {
                    int next = Integer.MAX_VALUE;
                    for (int j = i; j < N; j++) {
                        if (checkMap[i][j]) {
                            next = Math.min(next, dp[j + 1]);
                        }
                    }
                    dp[i] = next + 1;
                }
            }

            for (int i = 0, j = 1; j <= N; j++) {
                if (checkMap[i][j - 1] && dp[i] == dp[j] + 1) {
                    ans.add(s.substring(i, j));
                    i = j;
                }
            }
        }
        return ans;
    }

    public static List<List<String>> minCutAllWays(String s) {
        List<List<String>> ans = new ArrayList<>();
        if (s == null || s.length() < 2) {
            List<String> list = new ArrayList<>();
            list.add(s);
            ans.add(list);
        } else {
            char[] str = s.toCharArray();
            int N = str.length;
            int[] dp = new int[N + 1];
            dp[0] = 0;
            boolean[][] checkMap = checkMap(str);
            for (int i = N - 1; i >= 0; i--) {
                if (checkMap[i][N - 1]) {
                    dp[i] = 1;
                } else {
                    int next = Integer.MAX_VALUE;
                    for (int j = i; j < N; j++) {
                        if (checkMap[i][j]) {
                            next = Math.min(next, dp[j + 1]);
                        }
                    }
                    dp[i] = next + 1;
                }
            }

            process(s, dp, checkMap, ans, 0, 1, new ArrayList<>());
        }
        return ans;
    }

    public static void process(String s, int[] dp, boolean[][] checkMap, List<List<String>> ans, int i, int j, ArrayList<String> cur) {
        if (j == s.length()) {
            if (checkMap[i][j - 1] && dp[i] == dp[j] + 1) {
                cur.add(s.substring(i, j));
                ans.add(copy(cur));
                cur.remove(cur.size() - 1);
            }
        } else {
            process(s, dp, checkMap, ans, i, j + 1, cur);
            if (checkMap[i][j - 1] && dp[i] == dp[j] + 1) {
                cur.add(s.substring(i, j));
                process(s, dp, checkMap, ans, j, j + 1, cur);
                cur.remove(cur.size() - 1);
            }
        }
    }

    public static List<String> copy(List<String> list) {
        List<String> ans = new ArrayList<>();
        for (String str : list) {
            ans.add(str);
        }
        return ans;
    }


    public static String minInsertionsOneWay(String s) {
        char[] str = s.toCharArray();
        int N = str.length;
        int[][] dp = new int[N][N];
        for (int i = 0; i < N - 1; i++) {
            dp[i][i + 1] = str[i] == str[i + 1] ? 0 : 1;
        }
        for (int i = N - 3; i >= 0; i--) {
            for (int j = i + 2; j < N; j++) {
                dp[i][j] = Math.min(dp[i + 1][j], dp[i][j - 1]) + 1;
                if (str[i] == str[j]) {
                    dp[i][j] = dp[i + 1][j - 1];
                }
            }
        }
        char[] ans = new char[N + dp[0][N - 1]];
        int i = 0;
        int j = N - 1;
        int left = 0;
        int right = ans.length - 1;
        while (i < j) {
            if (dp[i][j] == dp[i + 1][j - 1] && str[i] == str[j]) {
                ans[left++] = str[i];
                ans[right--] = str[i];
                i++;
                j--;
            } else if (dp[i][j] == (dp[i + 1][j] + 1)) {
                ans[left++] = str[i];
                ans[right--] = str[i];
                i++;
            } else {
                ans[left++] = str[j];
                ans[right++] = str[j];
                j--;
            }
        }
        if (i == j) {
            ans[left] = str[i];
        }

        return String.valueOf(ans);
    }

    public static List<String> minInsertionsAllWays(String s) {
        List<String> ans = new ArrayList<>();
        if (s == null || s.length() < 2) {
            ans.add(s);
        } else {
            char[] str = s.toCharArray();
            int N = str.length;
            int[][] dp = new int[N][N];
            for (int i = 0; i < N - 1; i++) {
                dp[i][i + 1] = str[i] == str[i + 1] ? 0 : 1;
            }
            for (int i = N - 3; i >= 0; i--) {
                for (int j = i + 2; j < N; j++) {
                    dp[i][j] = Math.min(dp[i][j - 1], dp[i + 1][j]) + 1;
                    if (str[i] == str[j]) {
                        dp[i][j] = Math.min(dp[i][j], dp[i + 1][j - 1]);
                    }
                }
            }
            int M = N + dp[0][N - 1];
            char[] path = new char[M];
            process(str, dp, 0, N - 1, path, 0, M - 1, ans);
        }
        return ans;
    }


    public static void process(char[] str, int[][] dp, int L, int R, char[] path, int pl, int pr, List<String> ans) {
        if (L >= R) {
            if (L == R) {
                path[pl] = str[L];
            }
            ans.add(String.valueOf(path));
        } else {
            if ((dp[L][R] == dp[L + 1][R - 1] || L == R - 1) && str[L] == str[R]) {
                path[pl] = str[L];
                path[pr] = str[L];
                process(str, dp, L + 1, R - 1, path, pl + 1, pr - 1, ans);
            }
            if (dp[L][R] == dp[L + 1][R] + 1) {
                path[pl] = str[L];
                path[pr] = str[L];
                process(str, dp, L + 1, R, path, pl + 1, pr - 1, ans);
            }
            if (dp[L][R] == dp[L][R - 1] + 1){
                path[pl] = str[R];
                path[pr] = str[R];
                process(str, dp, L, R - 1, path, pl + 1, pr - 1, ans);
            }
        }
    }


    public static void main(String[] args) {
        String s = null;
        String ans2 = null;
        List<String> ans3 = null;

        System.out.println("本题第二问，返回其中一种结果测试开始");
        s = "mbadm";
        ans2 = minInsertionsOneWay(s);
        System.out.println(ans2);

        s = "leetcode";
        ans2 = minInsertionsOneWay(s);
        System.out.println(ans2);

        s = "ababb";
        ans2 = minInsertionsOneWay(s);
        System.out.println(ans2);
        System.out.println("本题第二问，返回其中一种结果测试结束");

        System.out.println();

        System.out.println("本题第三问，返回所有可能的结果测试开始");
        s = "mbadm";
        ans3 = minInsertionsAllWays(s);
        for (String way : ans3) {
            System.out.println(way);
        }
        System.out.println();

        s = "leetcode";
        ans3 = minInsertionsAllWays(s);
        for (String way : ans3) {
            System.out.println(way);
        }
        System.out.println();

        s = "aabaa";
        ans3 = minInsertionsAllWays(s);
        for (String way : ans3) {
            System.out.println(way);
        }
        System.out.println();
        System.out.println("本题第三问，返回所有可能的结果测试结束");
    }
}
