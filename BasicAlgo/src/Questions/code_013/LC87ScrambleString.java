package Questions.code_013;

import WeeklyPractice.April02.PerfectPairNumber;
import basicAlgo.DynamicProgramming.KillerMonster;
import com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets;

public class LC87ScrambleString {
    public boolean isScramble(String s1, String s2) {
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        if (!valid(str1, str2)) {
            return false;
        }
        return process(str1, 0, str1.length - 1, str2, 0, str1.length - 1);
    }

    public boolean valid(char[] str1, char[] str2) {
        if (str1.length != str2.length) {
            return false;
        }
        char[] arr = new char[26];
        for (int i = 0; i < str1.length; i++) {
            arr[str1[i] - 'a']++;
            arr[str2[i] - 'a']--;
        }
        for (int i = 0; i < str1.length; i++) {
            if (arr[i] != 0) {
                return false;
            }
        }
        return true;
    }

    public boolean process(char[] str1, int l1, int r1, char[] str2, int l2, int r2) {
        if (l1 == r1) {
            return str1[r1] == str2[r2];
        }

        for (int i = l1; i < r1; i++) {
            boolean p1 = process(str1, l1, i, str2, l2, l2 + i - l1)
                    && process(str1, i + 1, r1, str2, l2 + (i - l1 + 1), r2);
            boolean p2 = process(str1, l1, i, str2, r2 - (i - l1), r2)
                    && process(str1, i + 1, r1, str2, l2, r2 - (i - l1) - 1);
            if (p1 || p2) {
                return true;
            }
        }
        return false;
    }

    public boolean isScramble1(String s1, String s2) {
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        if (!valid(str1, str2)) {
            return false;
        }
        int n = str1.length;
        int[][][] dp = new int[n][n][n];
        return process1(str1, str2, 0, 0, n - 1, dp);
    }

    public boolean process1(char[] str1, char[] str2, int l1, int l2, int n, int[][][] dp) {
        if (dp[l1][l2][n] != 0) {
            return dp[l1][l2][n] == 1;
        }
        if (n == 0) {
            return str1[l1] == str2[l2];
        }
        for (int i = 0; i < n; i++) {
            boolean p1 = process1(str1, str2, l1, l2, i, dp)
                    && process1(str1, str2, l1 + i + 1, l2 + i + 1, n - i - 1, dp);
            boolean p2 = process1(str1, str2, l1, l2 + (n - i), i, dp)
                    && process1(str1, str2, l1 + i + 1, l2, n - i - 1, dp);
            if (p1 || p2) {
                dp[l1][l2][n] = 1;
                return true;
            }
        }
        dp[l1][l2][n] = -1;
        return false;
    }

    public boolean isScramble3(String s1, String s2) {
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        if (!valid(str1, str2)) {
            return false;
        }
        int n = str1.length;
        boolean[][][] dp = new boolean[n][n][n + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j][1] = str1[i] == str2[j];
            }
        }
        for (int size = 2; size <= n; size++) {
            for (int L1 = 0; L1 <= n - size; L1++) {
                for (int L2 = 0; L2 <= n- size; L2++) {
                    for (int leftPart = 1; leftPart < size; leftPart++) {
                        if(dp[L1][L2][leftPart] && dp[L1 + leftPart][L2 + leftPart][size - leftPart] ||
                                dp[L1][L2 + (size - leftPart)][leftPart] && dp[L1 + leftPart][L2][size - leftPart]){
                            dp[L1][L2][size] = true;
                            break;
                        }
                    }
                }
            }
        }
        return dp[0][0][n];

    }
}
