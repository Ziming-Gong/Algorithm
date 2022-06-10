package WeeklyPractice.May26;


import WeeklyPractice.May06.JumMinSameValue;
import sun.java2d.xr.MaskTile;

// 题目1
// 方案1 : {7, 10}
// xxxx : {a , b}
// 1 2 3 4
// FunnyGoal = 100
// OffenseGoal = 130
// 找到一个最少方案数，让FunnyGoal、OffenseGoal，都大于等于
// 定义如下尝试过程
// 贴纸数组stickers
// stickers[i][0] : i号贴纸的Funny值
// stickers[i][1] : i号贴纸的Offense值
// index....所有的贴纸，随便选择。index之前的贴纸不能选择，
// 在让restFunny和restOffense都小于等于0的要求下，返回最少的贴纸数量
public class DP1FromVT {
    public static int minStickers1(int[][] matrix, int funnyGoal, int offense) {
        return process(matrix, funnyGoal, offense, 0);
    }

    public static int process(int[][] matrix, int restFunny, int restOffense, int index) {
        if (restFunny <= 0 && restOffense <= 0) {
            return 0;
        }
        if (index == matrix.length) {
            return Integer.MAX_VALUE;
        }
        int next = process(matrix, restFunny - matrix[index][0], restOffense - matrix[index][1], index + 1);
        int p1 = process(matrix, restFunny, restOffense, index + 1);
        int p2 = Integer.MAX_VALUE;
        if (next != Integer.MAX_VALUE) {
            p2 = next + 1;
        }
        return Math.min(p1, p2);
    }

    public static int minStickers2(int[][] matrix, int funnyGoal, int offense) {
        int N = matrix.length;
        int[][][] dp = new int[N + 1][funnyGoal + 1][offense + 1];
        for (int k = 0; k <= offense; k++) {
            for (int j = 0; j <= funnyGoal; j++) {
                if (k != 0 || j != 0) {
                    dp[N][j][k] = Integer.MAX_VALUE;
                }
            }
        }

        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j <= funnyGoal; j++) {
                for (int k = 0; k <= offense; k++) {
                    dp[i][j][k] = dp[i + 1][j][k];
                    int p1 = Integer.MAX_VALUE;
                    int next = dp[i + 1][Math.max(0, j - matrix[i][0])][Math.max(0, k - matrix[i][1])];
                    if (next != Integer.MAX_VALUE) {
                        p1 = next + 1;
                    }
                    dp[i][j][k] = Math.min(dp[i][j][k], p1);
                }
            }
        }
        return dp[0][funnyGoal][offense];
    }

    // 改动态规划
    public static int minStickers3(int[][] stickers, int funnyGoal, int offenseGoal) {
        int[][][] dp = new int[stickers.length][funnyGoal + 1][offenseGoal + 1];
        for (int i = 0; i < stickers.length; i++) {
            for (int j = 0; j <= funnyGoal; j++) {
                for (int k = 0; k <= offenseGoal; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }
        return process2(stickers, 0, funnyGoal, offenseGoal, dp);
    }

    public static int process2(int[][] stickers, int index, int restFunny, int restOffense, int[][][] dp) {
        if (restFunny <= 0 && restOffense <= 0) {
            return 0;
        }
        if (index == stickers.length) {
            return Integer.MAX_VALUE;
        }
        if (dp[index][restFunny][restOffense] != -1) {
            return dp[index][restFunny][restOffense];
        }
        // 不选当前的贴纸
        int p1 = process2(stickers, index + 1, restFunny, restOffense, dp);
        // 选当前贴纸
        int p2 = Integer.MAX_VALUE;
        int next2 = process2(stickers, index + 1, Math.max(0, restFunny - stickers[index][0]),
                Math.max(0, restOffense - stickers[index][1]), dp);
        if (next2 != Integer.MAX_VALUE) {
            p2 = next2 + 1;
        }
        int ans = Math.min(p1, p2);
        dp[index][restFunny][restOffense] = ans;
        return ans;
    }

    public static int[][] generateMatrix(int maxVal, int maxLength) {
        int N = (int) (Math.random() * maxLength) + 1;
        int[][] ans = new int[N][2];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 2; j++) {
                ans[i][j] = (int) (Math.random() * maxVal);
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        int maxVal = 10;
        int maxLength = 10;
        int testTime = 100000;
        System.out.println("test begin");
        for (int i = 1; i <= testTime; i++) {
            int[][] matrix = generateMatrix(maxVal, maxLength);
            int funnyGoal = (int) (Math.random() * maxVal) * (int) (Math.random() * maxLength);
            int offense = (int) (Math.random() * maxVal) * (int) (Math.random() * maxLength);
            int ans1 = minStickers1(matrix, funnyGoal, offense);
            int ans2 = minStickers2(matrix, funnyGoal, offense);
            int ans3 = minStickers3(matrix, funnyGoal, offense);
            if (ans1 != ans3 || ans2 != ans3) {
                System.out.println("fxxking oops");
                break;
            }
        }
        System.out.println("test end");
    }

}
