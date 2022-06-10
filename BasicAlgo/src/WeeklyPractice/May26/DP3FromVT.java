package WeeklyPractice.May26;

//X

// 绳子总长度为M
// 100 -> M
// (6, 100) (7,23) (10,34) -> arr
// 每一个长度的绳子对应一个价格，比如(6, 10)表示剪成长度为6的绳子，对应价格10
// 可以重复切出某个长度的绳子
// 定义递归如下：
// 所有可以切出来的长度 对应 价值都在数组ropes里
// ropes[i] = {6, 10} 代表i方案为：切出长度为6的绳子，可以卖10元
// index....所有的方案，随便选择。index之前的方案，不能选择
// 返回最大的价值
// 自己去改动态规划
// arr[i][0] -> i号方案能切多少长度
// arr[i][1] -> 切出来这个长度，就能获得的价值
// arr[index....]自由选择，绳子还剩restLen长度
// 返回，最大价值
public class DP3FromVT {

    public static int MaxValue1(int[][] matrix, int M) {
        return process(matrix, 0, M);
    }

//    public static int process(int[][] matrix, int index, int rest) {
//        if (index >= matrix.length || rest < 0) {
//            return 0;
//        }
//
//        int p1 = process(matrix, index + 1, rest);
//        int p2 = 0;
//        if (rest >= matrix[index][0]) {
//            p2 = matrix[index][1] + process(matrix, index, rest - matrix[index][0]);
//        }
//        return Math.max(p1, p2);
//    }
    public static int process(int[][] arr, int index, int restLen) {
        if (restLen <= 0 || index == arr.length) {
            return 0;
        }
        // 绳子还有剩余、且还有方案
        // index号方案
        // 不选
        int p1 = process(arr, index + 1, restLen);
        // 选
        int p2 = 0;
        if (arr[index][0] <= restLen) { // 剩余绳子够长，才能选当前方案
            p2 = arr[index][1] + process(arr, index, restLen - arr[index][0]);
        }
        return Math.max(p1, p2);
    }

    public static int maxValue2(int[][] matrix, int M) {
        int N = matrix.length;
        int[][] dp = new int[N][M + 1];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < matrix[0].length; j++)
                dp[i][j] = -1;
        }
        return process2(matrix , 0, M, dp);
    }


    public static int maxValue4(int[][] matrix, int M) {
        return process4(matrix, 0, M);
    }

    public static int process4(int[][] matrix, int index, int rest) {
        if (index >= matrix.length || rest < 0) {
            return 0;
        }

        int p1 = process4(matrix, index + 1, rest);
        int p2 = 0;
        if (rest >= matrix[index][0]) {
            p2 = matrix[index][1] + process4(matrix, index, rest - matrix[index][0]);
        }
        return Math.max(p1, p2);
    }

    public static int process2(int[][] matrix, int index, int rest, int[][] dp) {
        if (index >= matrix.length || rest < 0) {
            return 0;
        }

        if (dp[index][rest] != -1) {
            return dp[index][rest];
        }
        int ans = 0;
        int p1 = process2(matrix, index + 1, rest, dp);
        int p2 = 0;
        if (rest >= matrix[index][0]) {
            p2 = matrix[index][1] + process2(matrix, index, rest - matrix[index][0], dp);
        }
        ans = Math.max(p1, p2);
        dp[index][rest] = ans;
        return ans;
    }

    public static int maxValue3(int[][] matrix, int M) {
        int N = matrix.length;
        int[][] dp = new int[N + 1][M + 1];
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j <= M; j++) {
                dp[i][j] = dp[i + 1][j];
                if (j - matrix[i][0] >= 0) {
                    dp[i][j] = Math.max(matrix[i][1] + dp[i][j - matrix[i][0]], dp[i][j]);
                }
            }
        }
        return dp[N][M];
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
            int M = (int) (Math.random() * 5 * maxVal);
            int ans1 = MaxValue1(matrix, M);
            int ans2 = maxValue4(matrix, M);
//            int ans3 = maxValue3(matrix, M);
            if (ans1 != ans2 ) {//|| ans1 != ans3
                System.out.println("fxxking oops");
                break;
            }
            System.out.println("1");
        }
        System.out.println("test end");
    }
}















