package basicAlgo.OverflowDynamicPrograming;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TSP {

    public static int t3(int[][] matrix) {
        List<Integer> set = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            set.add(1);
        }
        return process(matrix, set, 0);
    }

    public static int process(int[][] matrix, List<Integer> set, int start) {
        int nums = 0;
        for (int i = 0; i < set.size(); i++) {
            if (set.get(i) != null) {
                nums++;
            }
        }
        if (nums == 1) {
            return matrix[start][0];
        }
        int ans = Integer.MAX_VALUE;
        set.set(start, null);
        for (int i = 0; i < set.size(); i++) {
            if (set.get(i) != null) {
//                set.add(i, null);
                ans = Math.min(ans, process(matrix, set, i) + matrix[start][i]);
//                set.add(i, 1);
            }
        }
        set.set(start, 1);
        return ans;
    }

    public static int t4(int[][] matrix) {
        int n = matrix.length;
        int allCities = (1 << n) - 1;
        int[][] dp = new int[1 << n][n];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j] = -1;
            }
        }
        return process4(matrix, allCities, 0, dp);
    }

    public static int process4(int[][] matrix, int cityStatus, int start, int[][] dp) {
//        if ((cities & (~cities + 1)) == 0) {
//            return matrix[start][0];
//        }
        if (dp[cityStatus][start] != -1) {
            return dp[cityStatus][start];
        }
        if((cityStatus == (cityStatus & (-cityStatus))) ){
            dp[cityStatus][start] = matrix[start][0];
            return matrix[start][0];
        }

        int ans = Integer.MAX_VALUE;
        cityStatus &= (~(1 << start));
        for (int i = 0; i < matrix.length; i++) {
            if(i != start && ((cityStatus & (1 << i)) != 0)){
                int cur = matrix[start][i] + process4(matrix,cityStatus, i, dp);
                ans = Math.min(ans, cur);
            }
        }
        cityStatus |= (1 << start);
        dp[cityStatus][start] = ans;
        return ans;
    }


    public static int[][] generateGraph(int maxSize, int maxValue) {
        int len = (int) (Math.random() * maxSize) + 1;
        int[][] matrix = new int[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                matrix[i][j] = (int) (Math.random() * maxValue) + 1;
            }
        }
        for (int i = 0; i < len; i++) {
            matrix[i][i] = 0;
        }
        return matrix;
    }

    public static void main(String[] args) {
        int len = 10;
        int value = 100;
        System.out.println("功能测试开始");
        for (int i = 0; i < 1000; i++) {
            int[][] matrix = generateGraph(len, value);
            int origin = (int) (Math.random() * matrix.length);
            int ans1 = t3(matrix);
            int ans2 = t4(matrix);
//            int ans3 = tsp2(matrix, origin);
            if (ans1 != ans2) {
                System.out.println("fuck");
                break;
            }
        }
        System.out.println("功能测试结束");

//        len = 22;
//        System.out.println("性能测试开始，数据规模 : " + len);
//        int[][] matrix = new int[len][len];
//        for (int i = 0; i < len; i++) {
//            for (int j = 0; j < len; j++) {
//                matrix[i][j] = (int) (Math.random() * value) + 1;
//            }
//        }
//        for (int i = 0; i < len; i++) {
//            matrix[i][i] = 0;
//        }
//        long start;
//        long end;
//        start = System.currentTimeMillis();
//        t4(matrix);
//        end = System.currentTimeMillis();
//        System.out.println("运行时间 : " + (end - start) + " 毫秒");
//        System.out.println("性能测试结束");

    }
}
