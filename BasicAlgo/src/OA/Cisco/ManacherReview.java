package OA.Cisco;

import basicAlgo.mergesorted.ans;

import java.util.PriorityQueue;

public class ManacherReview {
    public static String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        String res = manacher(s);
        String ans = res.replaceAll("#", "");
        return ans;
    }


    public static String manacher(String s) {
        String manacherStr = manacherStr(s);
        char[] str = manacherStr.toCharArray();
        int N = str.length;
        int[] ans = new int[N];
        int R = -1;
        int C = -1;
        int res = Integer.MIN_VALUE;
        int index = -1;
        PriorityQueue<String> queue = new PriorityQueue<>();
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
            if (str[i] == '#' && ans[i] >= res && ans[i] != 1) {
                int start = i - ans[i] + 1;
                int len = 2 * ans[i] - 1;
                if (ans[i] == res) {
                    queue.add(manacherStr.substring(i - ans[i] + 1, 2 * ans[i] - 1 + start));
                } else {
                    queue = new PriorityQueue<>();
                    queue.add(manacherStr.substring(i - ans[i] + 1, 2 * ans[i] - 1 + start));
                }
                res = ans[i];
            }

        }
        return queue.poll();
    }


    public static String manacherStr(String s) {
        StringBuilder sb = new StringBuilder();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            sb.append('#');
            sb.append(s.charAt(i));
        }
        sb.append('#');
        return sb.toString();
    }

    public static void main(String[] args) {
        String str = "zbbccbbhzabccbah";
        System.out.println(longestPalindrome(str));

//        String str = "#z#a#b#c#c#b#a#z#";
//        System.out.println(str.substring(0, 1));

    }


}
