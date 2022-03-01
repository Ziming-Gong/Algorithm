package basicAlgo.DynamicProgramming;

public class BobDie {
    public static double livePossibility1(int row, int col, int k, int N, int M) {
        return (double) process(row, col, k, N, M) / Math.pow(4, k);
    }

    public static long process(int row, int col, int rest, int N, int M) {
        if (row == N || row < 0 || col == M || col < 0) {
            return 0;
        }
        if (rest == 0) {
            return 1;
        }
        long ways = process(row + 1, col, rest - 1, N, M);
        ways += process(row - 1, col, rest - 1, N, M);
        ways += process(row, col + 1, rest - 1, N, M);
        ways += process(row, col - 1, rest - 1, N, M);
        return ways;
    }

    public static double livePossibility2(int row, int col, int k, int N, int M) {

        long[][][] dp = new long[N][M][k + 1];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dp[i][j][0] = 1;
            }
        }
        for (int rest = 1; rest <= k; rest++) {
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < M; c++) {
                    long ways = pick(dp, rest - 1, r, c - 1, N, M);
                    ways += pick(dp, rest - 1, r, c + 1, N, M);
                    ways += pick(dp, rest - 1, r + 1, c, N, M);
                    ways += pick(dp, rest - 1, r - 1, c, N, M);
                    dp[r][c][rest] = ways;
                }
            }
        }


        return (double) dp[row][col][k] / Math.pow(4, k);
    }

    public static long pick(long[][][] dp, int rest, int row, int col, int n, int m) {
        if (row < 0 || row >= n || col < 0 || col >= m) {
            return 0;
        }
        return dp[row][col][rest];
    }

    public static void main(String[] args) {
        System.out.println(livePossibility1(6, 6, 10, 50, 50));
        System.out.println(livePossibility2(6, 6, 10, 50, 50));
    }

}
