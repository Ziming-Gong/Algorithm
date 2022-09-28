package LeetCodeDays;

import java.util.ArrayList;
import java.util.Properties;

public class LC5LongestPalindromicSubstring {
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        int[] ans = manacher(s);
        int st = ans[0];
        int ed = ans[1];
        return s.substring(st,ed);



    }


    public int[] manacher(String s) {
        char[] str = manacherStr(s);
        int N = str.length;
        int[] ans = new int[N];
        int R = -1;
        int C = -1;
        int res = Integer.MIN_VALUE;
        int index = -1;
        for (int i = 0; i < N; i++) {
            ans[i] = R > i ? Math.min(ans[2 * C - i], R - i) : 1;
            while (i - ans[i] >= 0 && i + ans[i] < N) {
                if (str[i - ans[i]] != str[i + ans[i]]) {
                    break;
                }
                ans[i]++;
            }
            if (i + ans[i] > R) {
                R = i + ans[i];
                C = i;
            }
            if (ans[i] > res) {
                index = i;
                res = ans[i];
            }

        }


        return process(str, index, res);
    }

    public int[] process(char[] str, int index, int res) {
        int[] ans = new int[2];
        int start = index - res + 2;
        start = (start - 1)/2;
        int end = index + res - 2;
        end = (end - 1)/2;
        return new int[]{start, end};
    }

    public char[] manacherStr(String s) {
        char[] str = s.toCharArray();
        int N = str.length;
        char[] ans = new char[2 * N + 1];
        for (int i = 0; i < N; i++) {
            ans[2 * i] = '#';
            ans[2 * i + 1] = str[i];
        }
        ans[2 * N] = '#';
        return ans;
    }
}
