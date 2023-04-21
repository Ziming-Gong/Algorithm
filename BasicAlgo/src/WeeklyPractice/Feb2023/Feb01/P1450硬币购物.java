package WeeklyPractice.Feb2023.Feb01;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class P1450硬币购物 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        int n = 0;
        int[][] event;
        int max = Integer.MIN_VALUE;
        int[] coins = new int[4];
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            coins[0] = (int) in.nval;
            in.nextToken();
            coins[1] = (int) in.nval;
            in.nextToken();
            coins[2] = (int) in.nval;
            in.nextToken();
            coins[3] = (int) in.nval;
            in.nextToken();
            n = (int) in.nval;
            event = new int[n][5];
            for (int i = 0; i < n; i++) {
                in.nextToken();
                event[i][0] = (int) in.nval;
                in.nextToken();
                event[i][1] = (int) in.nval;
                in.nextToken();
                event[i][2] = (int) in.nval;
                in.nextToken();
                event[i][3] = (int) in.nval;
                in.nextToken();
                event[i][4] = (int) in.nval;
                max = Math.max(max, event[i][4]);
            }
            int[] ans = coinShopping(coins, event, max);
            for (int i = 0; i < n; i++) {
                out.println(ans[i]);
            }
            out.flush();

        }
    }

    public static int[] coinShopping(int[] coins, int[][] events, int max) {
        int[] ans = new int[events.length];
        long[] dp = new long[max + 1];
        add(dp, coins[0]);
        add(dp, coins[1]);
        add(dp, coins[2]);
        add(dp, coins[3]);

        for (int i = 0; i < ans.length; i++) {
            long t = max;
            int[] cur = events[i];
            int minus = 0;
            int sign = -1;
            for (int j = 1; j < 16; j++) {
                for (int c = 1; c < 4; c++) {
                    if (((1 << c) & j) == 1 << c) {
                        t -= (long) coins[c] * (long) (cur[c] + 1);
                        sign = -sign;
                    }
                }
                if (t >= 0) {
                    minus += sign * dp[(int) t];
                }
            }
            ans[i] = minus;
        }


        return ans;
    }


    public static void add(long[] dp, int val) {
        for (int i = val; i < dp.length; i++) {
            dp[i] += dp[i - val];
        }
    }

}

















