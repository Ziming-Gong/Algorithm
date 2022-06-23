package Questions.code_8;

import java.util.Arrays;

public class SnackGame {
    public static class data {
        public int yes;
        public int no;

        public data(int n, int y) {
            yes = y;
            no = n;
        }
    }

    public static int walk2(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int ans = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                data cur = process(matrix, i, j);
                ans = Math.max(ans, Math.max(cur.no, cur.yes));
            }
        }
        return ans;

    }

    public static data process(int[][] matrix, int i, int j) {
        if (j == 0) {
            int no = Math.max(-1, matrix[i][0]);
            int yes = Math.max(-1, -matrix[i][0]);
            return new data(no, yes);
        }
        data pre = process(matrix, i, j - 1);
        int preNo = Math.max(pre.no, -1);
        int preYes = Math.max(-1, pre.yes);
        if (i > 0) {
            pre = process(matrix, i - 1, j - 1);
            preNo = Math.max(preNo, pre.no);
            preYes = Math.max(preYes, pre.yes);
        }
        if (i < matrix.length - 1) {
            pre = process(matrix, i + 1, j - 1);
            preNo = Math.max(preNo, pre.no);
            preYes = Math.max(preYes, pre.yes);
        }
        int no = preNo == -1 ? -1 : Math.max(-1, preNo + matrix[i][j]);
        //以前使用过
        int p1 = preYes == -1 ? -1 : Math.max(preYes + matrix[i][j], -1);
        //当前这次使用魔法
        int p2 = preNo == -1 ? -1 : Math.max(preNo - matrix[i][j], -1);

        int yes = Math.max(-1, Math.max(p1, p2));
        return new data(no, yes);
    }

    public static int walk1(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int N = matrix.length;
        int M = matrix[0].length;
        int ans = 0;

        int[][][] dp = new int[N][M][2];//0 : unused; 1 : used
        for (int i = 0; i < N; i++) {
            dp[i][0][0] = Math.max(matrix[i][0], -1);
            dp[i][0][1] = Math.max(-matrix[i][0], -1);
            ans = Math.max(ans, Math.max(dp[i][0][0], dp[i][0][1]));
        }
        for (int j = 1; j < M; j++) {
            for (int i = 0; i < N; i++) {
                int preUsed = Math.max(dp[i][j - 1][1], -1);
                int preUnused = Math.max(dp[i][j - 1][0], -1);
                if (i > 0) {
                    preUnused = Math.max(preUnused, dp[i - 1][j - 1][0]);
                    preUsed = Math.max(preUsed, dp[i - 1][j - 1][1]);
                }
                if (i < N - 1) {
                    preUnused = Math.max(preUnused, dp[i + 1][j - 1][0]);
                    preUsed = Math.max(preUsed, dp[i + 1][j - 1][1]);
                }
                int unUsed = preUnused == -1 ? -1 : Math.max(-1, preUnused + matrix[i][j]);
                int p1 = preUsed == -1 ? -1 : Math.max(-1, preUsed + matrix[i][j]);
                int p2 = preUnused == -1 ? -1 : Math.max(-1, preUnused - matrix[i][j]);
                dp[i][j][0] = unUsed;
                dp[i][j][1] = Math.max(p1, p2);
                ans = Math.max(ans, Math.max(dp[i][j][0], dp[i][j][1]));
            }
        }
        return ans;


    }


    public static int[][] generateRandomArray(int row, int col, int value) {
        int[][] arr = new int[row][col];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                arr[i][j] = (int) (Math.random() * value) * (Math.random() > 0.5 ? -1 : 1);
            }
        }
        return arr;
    }


    public static void main(String[] args) {
        int N = 7;
        int M = 7;
        int V = 10;
        int times = 1000000;
        for (int i = 0; i < times; i++) {
            int r = (int) (Math.random() * (N + 1));
            int c = (int) (Math.random() * (M + 1));
            int[][] matrix = generateRandomArray(r, c, V);
            int ans1 = walk1(matrix);
            int ans2 = walk2(matrix);
            if (ans1 != ans2) {
                for (int j = 0; j < matrix.length; j++) {
                    System.out.println(Arrays.toString(matrix[j]));
                }
                System.out.println("Oops   ans1: " + ans1 + "   ans2:" + ans2);
                break;
            }
        }
        System.out.println("finish");
    }
}
