package Questions.code_2;

import com.sun.corba.se.impl.oa.toa.TOA;
import org.omg.IOP.TAG_ORB_TYPE;

import java.sql.PreparedStatement;
import java.util.Arrays;

public class Driver {

    public static int maxMoney1(int[][] matrix) {
        int N = matrix.length;
        if ((N & 1) == 1) {
            return 0;
        }
        return process1(matrix, 0, N >> 1);
    }

    public static int process1(int[][] income, int index, int rest) {
        if (index == income.length) {
            return 0;
        }

        //如果剩下的司机全是A
        if (income.length - index == rest) {
            return income[index][0] + process1(income, index + 1, rest - 1);
        }
        //如果剩下的司机全是B
        if (rest == 0) {
            return income[index][1] + process1(income, index + 1, rest);
        }
        //都可以选
        int p1 = income[index][0] + process1(income, index + 1, rest - 1);
        int p2 = income[index][1] + process1(income, index + 1, rest);
        return Math.max(p1, p2);
    }

    //    public static int process1(int[][] income, int index, int rest) {
//        if (index == income.length) {
//            return 0;
//        }
//
//        //如果剩下的司机全是A
//        if (income.length - index == income.length/2 - rest) {
//            return income[index][0] + process1(income, index + 1, rest + 1);
//        }
//        //如果剩下的司机全是B
//        if (rest == income.length/2) {
//            return income[index][1] + process1(income, index + 1, rest);
//        }
//        //都可以选
//        int p1 = income[index][0] + process1(income, index + 1, rest + 1);
//        int p2 = income[index][1] + process1(income, index + 1, rest);
//        return Math.max(p1, p2);
//    }
    // 严格位置依赖的动态规划版本
    public static int maxMoney2(int[][] matrix) {
        int N = matrix.length;
        int M = (N >> 1);
        if ((N & 1) != 0) {
            return 0;
        }
        int[][] dp = new int[N + 1][M + 1];
//        for (int i = N - 1; i >= 0; i--) {
//            dp[i][N - i] = matrix[i][0] + dp[i + 1][N - i -1];
//        }
//        for (int i = N - 1; i >= 0; i--) {
//            dp[i][0] = matrix[i][1] + dp[i + 1][0];
//        }
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j <= M; j++) {
                if (N - i == j) {
                    dp[i][j] = matrix[i][0] + dp[i + 1][j - 1];
                } else if (j == 0) {
                    dp[i][j] = matrix[i][1] + dp[i + 1][j];
                } else {
                    int p1 = matrix[i][0] + dp[i + 1][j - 1];
                    int p2 = matrix[i][1] + dp[i + 1][j];
                    dp[i][j] = Math.max(p1, p2);
                }
            }
        }
        return dp[0][M];

    }

    // 这题有贪心策略 :
    // 假设一共有10个司机，思路是先让所有司机去A，得到一个总收益
    // 然后看看哪5个司机改换门庭(去B)，可以获得最大的额外收益
    // 这道题有贪心策略，打了我的脸
    // 但是我课上提到的技巧请大家重视
    // 根据数据量猜解法可以省去大量的多余分析，节省时间
    // 这里感谢卢圣文同学
    public static int maxMoney3(int[][] income) {
        int N = income.length;
        int[] arr = new int[N];
        int sum = 0;
        for (int i = 0; i < N; i++) {
            arr[i] = income[i][1] - income[i][0];
            sum += income[i][0];
        }
        Arrays.sort(arr);
        int M = N >> 1;
        for (int i = N - 1; i >= M; i--) {
            sum += arr[i];
        }
        return sum;
    }


    // 返回随机len*2大小的正数矩阵
    // 值在0~value-1之间
    public static int[][] randomMatrix(int len, int value) {
        int[][] ans = new int[len << 1][2];
        for (int i = 0; i < ans.length; i++) {
            ans[i][0] = (int) (Math.random() * value);
            ans[i][1] = (int) (Math.random() * value);
        }
        return ans;
    }

    public static void main(String[] args) {
        int N = 10;
        int value = 100;
        int testTime = 500;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * N) + 1;
            int[][] matrix = randomMatrix(len, value);
            int ans1 = maxMoney1(matrix);
            int ans2 = maxMoney2(matrix);
            int ans3 = maxMoney3(matrix);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans3);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");
    }
}
