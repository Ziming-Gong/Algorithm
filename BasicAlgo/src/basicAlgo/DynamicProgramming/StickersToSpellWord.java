package basicAlgo.DynamicProgramming;

import java.util.HashMap;

//https://leetcode.com/problems/stickers-to-spell-word

public class StickersToSpellWord {
    //This method will Time Limit Exceeded
    public int minStickers1(String[] stickers, String target) {
        int ans = process(stickers, target);
        return ans != Integer.MAX_VALUE ? ans : -1;
    }

    public static int process(String[] stickers, String target) {
        if (target.length() == 0) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        for (String str : stickers) {
            String rest = minus(target, str);
            if (target.length() != rest.length()) {
                ans = Math.min(ans, process(stickers, rest));
            }
        }
        return ans + (ans == Integer.MAX_VALUE ? 0 : 1);
    }

    //target - str
    public static String minus(String target, String str) {
        char[] targetList = target.toCharArray();
        char[] strList = str.toCharArray();

        int[] num = new int[26];
        for (char c : targetList) {
            num[(int) c - 'a']++;
        }
        for (char c : strList) {
            num[(int) c - 'a']--;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < num.length; i++) {
            if (num[i] > 0) {
                for (int j = 0; j < num[i]; j++) {
                    sb.append((char) (i + 'a'));
                }
            }
        }
        return sb.toString();
    }

    public int minStickers2(String[] stickers, String target) {
        int N = stickers.length;
        int[][] dp = new int[N][26];
        for (int i = 0; i < N; i++) {
            for (char c : stickers[i].toCharArray()) {
                dp[i][c - 'a']++;
            }
        }
        int ans = process2(dp, target);
        return ans != Integer.MAX_VALUE ? ans : -1;
    }

    public static int process2(int[][] stickers, String t) {
        if (t.length() == 0) {
            return 0;
        }
        char[] target = t.toCharArray();
        int[] targetNum = new int[26];
        for (int i = 0; i < target.length; i++) {
            targetNum[(target[i] - 'a')]++;
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < stickers.length; i++) {
            int[] sticker = stickers[i];
            if (sticker[target[0] - 'a'] > 0) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < 26; j++) {
                    if (targetNum[j] > 0) {
                        int nums = targetNum[j] - sticker[j];
                        for (int k = 0; k < nums; k++) {
                            sb.append((char) (j + 'a'));
                        }
                    }
                }
                String rest = sb.toString();
                ans = Math.min(ans, process2(stickers, rest));
            }
        }
        return ans + (ans == Integer.MAX_VALUE ? 0 : 1);
    }

    public int minStickers3(String[] stickers, String target) {
        int N = stickers.length;
        int[][] sticker = new int[N][26];
        for (int i = 0; i < N; i++) {
            for (char c : stickers[i].toCharArray()) {
                sticker[i][c - 'a']++;
            }
        }
        HashMap<String, Integer> dp = new HashMap<>();
        dp.put("", 0);

        int ans = process(sticker, target, dp);
        return ans != Integer.MAX_VALUE ? ans : -1;
    }

    public static int process(int[][] stickers, String t, HashMap<String, Integer> dp) {
        if (dp.containsKey(t)) {
            return dp.get(t);
        }
        if (t.length() == 0) {
            return 0;
        }
        char[] target = t.toCharArray();
        int[] targetNum = new int[26];
        for (int i = 0; i < target.length; i++) {
            targetNum[(target[i] - 'a')]++;
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < stickers.length; i++) {
            int[] sticker = stickers[i];
            if (sticker[target[0] - 'a'] > 0) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < 26; j++) {
                    if (targetNum[j] > 0) {
                        int nums = targetNum[j] - sticker[j];
                        for (int k = 0; k < nums; k++) {
                            sb.append((char) (j + 'a'));
                        }
                    }
                }
                String rest = sb.toString();
                min = Math.min(min, process(stickers, rest, dp));
            }
        }
        int ans = min + (min == Integer.MAX_VALUE ? 0 : 1);
        dp.put(t, ans);

        return ans;
    }
}
