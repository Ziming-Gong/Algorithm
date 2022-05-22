package basicAlgo.MakingMap;
// int[] d d[i]：i号怪兽的武力
// int[] p p[i]：i号怪兽要求的钱
// ability 当前你所具有的能力
// index 来到了第index个怪兽的面前

public class MoneyMonster {
    public static long func1(int[] d, int[] p) {
        return process1(d, p, 0, 0);
    }

    public static long process1(int[] d, int[] p, int index, int ability) {
        if (index >= d.length) {
            return 0L;
        }
        if (ability >= d[index]) {
            long p1 = p[index] + process1(d, p, index + 1, ability + d[index]);
            long p2 = process1(d, p, index + 1, ability);
            return Math.min(p1, p2);
        } else {
            return p[index] + process1(d, p, index + 1, ability + d[index]);
        }
    }

    // func1 DP
    public static long func2(int[] d, int[] p) {
        int sum = 0;
        for (int i : d) {
            sum += i;
        }
        int n = d.length;
        long[][] dp = new long[n + 1][sum + 1];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < sum; j++) {
                if (j + d[i] > sum) {
                    continue;
                }
                if (j < d[i]) {
                    dp[i][j] = p[i] + dp[i + 1][j + d[i]];
                } else {
                    dp[i][j] = Math.min(p[i] + dp[i + 1][j + d[i]], dp[i + 1][j]);
                }
            }
        }
        return dp[0][0];
    }

    public static long func3(int[] d, int[] p) {
        int AllMoney = 0;
        for (int i : p) {
            AllMoney += i;
        }
        for (int i = 0; i < AllMoney; i++) {
            if (process2(d, p, d.length - 1, i) != -1) {
                return i;
            }
        }
        return AllMoney;
    }

    public static long process2(int[] d, int[] p, int index, int money) {
        if (index == -1) {
            return money == 0 ? 0 : -1;
        }
        //如果不贿赂
        long ans = -1;
        long preAbility = process2(d, p, index - 1, money);
        if (preAbility != -1 && preAbility >= d[index]) {
            ans = preAbility;
        }
        //如果贿赂
        long preMaxAbility = process2(d, p, index - 1, money - p[index]);
        if (preMaxAbility != -1) {
            ans = Math.max(ans, d[index] + preMaxAbility);
        }
        return ans;
    }

    //func3 DP
    public static long func4(int[] d, int[] p) {
        int allMoney = 0;
        for (int a : p) {
            allMoney += a;
        }
        int N = d.length;
        long[][] dp = new long[N][allMoney + 1];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= allMoney; j++) {
                dp[i][j] = -1;
            }
        }
        dp[0][p[0]] = d[0];
        for (int index = 1; index < N; index++) {
            for (int money = 0; money <= allMoney; money++) {
                dp[index][money] = dp[index - 1][money] >= d[index] ? dp[index - 1][money] : -1;
                if (money - p[index] >= 0 && dp[index - 1][money - p[index]] != -1) {
                    dp[index][money] = Math.max(dp[index][money], d[index] + dp[index - 1][money - p[index]]);
                }
            }
        }
        for (int j = 0; j < allMoney; j++) {
            if (dp[N-1][j] != -1) {
                return j;
            }
        }
        return allMoney;
    }


    public static int[][] generateTwoRandomArray(int len, int value) {
        int size = (int) (Math.random() * len) + 1;
        int[][] arrs = new int[2][size];
        for (int i = 0; i < size; i++) {
            arrs[0][i] = (int) (Math.random() * value) + 1;
            arrs[1][i] = (int) (Math.random() * value) + 1;
        }
        return arrs;
    }

    public static void main(String[] args) {
        int len = 10;
        int value = 20;
        int testTimes = 10000;
        for (int i = 0; i < testTimes; i++) {
            int[][] arrs = generateTwoRandomArray(len, value);
            int[] d = arrs[0];
            int[] p = arrs[1];
            long ans1 = func1(d, p);
            long ans2 = func2(d, p);
            long ans3 = func3(d, p);
            long ans4 = func4(d, p);
            if (ans1 != ans2 || ans2 != ans3 || ans1 != ans4) {
                System.out.println("oops!");
            }
        }

    }
}
