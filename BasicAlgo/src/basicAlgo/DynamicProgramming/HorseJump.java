package basicAlgo.DynamicProgramming;

public class HorseJump {
    public static int jump(int a, int b, int k) {
        return process(0, 0, k, a, b);
    }

    public static int process(int x, int y, int rest, int a, int b) {
        if (x < 0 || x > 9 || y < 0 || y > 8) {
            return 0;
        }
        if (rest == 0) {
            return (x == a && y == b) ? 1 : 0;
        }
        int ans = process(x + 2, y + 1, rest - 1, a, b);
        ans += process(x + 2, y - 1, rest - 1, a, b);
        ans += process(x + 1, y - 2, rest - 1, a, b);
        ans += process(x + 1, y + 2, rest - 1, a, b);
        ans += process(x - 1, y - 2, rest - 1, a, b);
        ans += process(x - 1, y + 2, rest - 1, a, b);
        ans += process(x - 2, y + 1, rest - 1, a, b);
        ans += process(x - 2, y - 1, rest - 1, a, b);
        return ans;
    }

    public static int dp(int a, int b, int k) {
        int[][][] dp = new int[k + 1][10][9];

        dp[0][a][b] = 1;

        for (int rest = 1; rest <= k; rest++) {
            for (int x = 0; x < 10; x++) {
                for (int y = 0; y < 9; y++) {
                    int p = pick(dp, rest - 1, x + 2, y + 1);
                    p += pick(dp, rest - 1, x + 2, y - 1);
                    p += pick(dp, rest - 1, x + 1, y - 2);
                    p += pick(dp, rest - 1, x + 1, y + 2);
                    p += pick(dp, rest - 1, x - 1, y - 2);
                    p += pick(dp, rest - 1, x - 1, y + 2);
                    p += pick(dp, rest - 1, x - 2, y + 1);
                    p += pick(dp, rest - 1, x - 2, y - 1);
                    dp[rest][x][y] = p;
                }
            }
        }
        return dp[k][0][0];
    }

    public static int pick(int[][][] dp, int k, int x, int y) {
        if (x < 0 || x > 9 || y < 0 || y > 8) {
            return 0;
        }
        return dp[k][x][y];
    }


    public static void main(String[] args) {
        int x = 7;
        int y = 7;
        int step = 10;
        //System.out.println(ways(x, y, step));
        System.out.println(dp(x, y, step));

        System.out.println(jump(x, y, step));
    }

}
