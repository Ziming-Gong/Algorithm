package WeeklyPractice.June8;

import java.util.HashMap;
import java.util.Map.Entry;

public class test {
    public int largestVariance(String s) {
        int[] arr = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            arr[i] = s.charAt(i) - 'a';
        }
        int n = arr.length;
        int ans = 0;
        int[][] continuous = new int[26][26];
        int[][] dp = new int[26][26];
        boolean[][] appear = new boolean[26][26];
        for (int i : arr) {
            for (int j = 0; j < 26; j++) {
                if (i == j) {
                    continue;
                }


                continuous[i][j]++;
                if (appear[i][j]) {
                    dp[i][j]++;
                }
                if (!appear[j][i]) {
                    appear[j][i] = true;
                    dp[j][i] = continuous[j][i] - 1;
                } else {
                    dp[j][i] = Math.max(dp[j][i], continuous[j][i]) - 1;
                }
                continuous[j][i] = 0;
                ans = Math.max(dp[j][i], Math.max(dp[j][i], dp[i][j]));


//                 if(arr[i] == more){
//                     continuous ++;
//                     if(appear)
//                         max++;
//                 }else if(arr[i] == less){
//                      max = Math.max(max,continuous) - 1;
//                     continuous = 0;
//                     appear = true;
//                 }
//                 ans = Math.max(max, ans);
            }
        }
        return ans;

    }

}
