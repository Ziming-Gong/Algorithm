package basicAlgo.OverflowDynamicPrograming;

//你有无限个1*2个瓷砖，要铺满M*N的区域，不同的方法有多少种

public class PavingTile {
    public static int ways1(int N, int M) {
        int[] pre = new int[M];
        for (int i = 0; i < M; i++) {
            pre[i] = 1;
        }
        return process(pre, 0, N);
    }

    /**
     * @param pre int[] 前一行的状态：1代表有东西，0代表没有东西
     * @param N   总的列
     * @return 总方法数
     */
    public static int process(int[] pre, int level, int N) {
        if (level == N) {
            for (int i = 0; i < pre.length; i++) {
                if (pre[i] == 0)
                    return 0;
            }
            return 1;
        }

        int[] op = getOp(pre);
        return dfs(op, level, 0, N);
    }

    /**
     * @param op    当前行的状态
     * @param level
     * @param col
     * @param N
     * @return
     */
    public static int dfs(int[] op, int level, int col, int N) {
        if (col == op.length) {
            return process(op, level + 1, N);
        }
        int ans = 0;
        ans += dfs(op, level, col + 1, N);
        if (col + 1 < op.length && op[col] == 0 && op[col + 1] == 0) {
            op[col] = 1;
            op[col + 1] = 1;
            ans += dfs(op, level, col + 2, N);
            op[col] = 0;
            op[col + 1] = 0;
        }
        return ans;
    }

    public static int[] getOp(int[] arr) {
        int[] ans = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            ans[i] = arr[i] ^ 1;
        }
        return ans;
    }

    /**
     * math.min(M,N) <=32
     *
     * @param N
     * @param M
     * @return
     */
    public static int ways2(int N, int M) {
        int min = Math.min(N, M);
        int max = Math.max(N, M);
        int pre = (1 << min) - 1;
        return process2(pre, 0, max, min);
    }

    public static int process2(int pre, int level, int N, int M) {
        if (level == N) {
            if (pre == (1 << M) - 1) {
                return 1;
            }
            return 0;
        }
        int op = ((~pre) & ((1 << M) - 1));
        return dfs2(op, level, M - 1, N, M);
    }

    public static int dfs2(int op, int level, int col, int N, int M) {
        if (col == -1) {
            return process2(op, level + 1, N, M);
        }
        int ans = 0;
        ans += dfs2(op, level, col - 1, N, M);
        if (((1 << col) & op) == 0 && col - 1 >= 0 && ((1 << (col - 1)) & op) == 0) {
            ans += dfs2(op | (3 << (col - 1)), level, col - 2, N, M);
        }
        return ans;
    }

    public static int ways3(int N, int M) {
        int min = Math.min(N, M);
        int max = Math.max(N, M);
        int pre = (1 << min) - 1;
        int[][] dp = new int[1 << min][max + 1];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j] = -1;
            }
        }
        return process3(pre, 0, max, min, dp);
    }

    public static int process3(int pre, int level, int N, int M, int[][] dp) {
        if (dp[pre][level] != -1) {
            return dp[pre][level];
        }
        if (level == N) {
            int ans = pre == (1 << M) - 1 ? 1 : 0;
            dp[pre][level] = ans;
            return ans;
        }
        int op = (~pre) & ((1 << M) - 1);
        int ans = dfs3(op, M - 1, level, N, M, dp);
        dp[pre][level] = ans;
        return ans;
    }

    public static int dfs3(int op, int col, int level, int N, int M, int[][] dp) {
        if (col == -1) {
            return process3(op, level + 1, N, M, dp);
        }
//        if (dp[op][level] != -1) {
//            return dp[op][level];
//        }
        int ans = 0;
        ans += dfs3(op, col - 1, level, N, M, dp);
        if ((op & (1 << col)) == 0 && col - 1 >= 0 && (op & (3 << (col - 1))) == 0) {
            ans += dfs3(op | (3 << (col - 1)), col - 2,level, N, M, dp);
        }
//        dp[op][level] = ans;
        return ans;
    }

    public static int ways4(int N, int M) {
        if (N < 1 || M < 1 || ((N * M) & 1) != 0) {
            return 0;
        }
        if (N == 1 || M == 1) {
            return 1;
        }
        int big = N > M ? N : M;
        int small = big == N ? M : N;
        int sn = 1 << small;
        int limit = sn - 1;
        int[] dp = new int[sn];
        dp[limit] = 1;
        int[] cur = new int[sn];
        for (int level = 0; level < big; level++) {
            for (int status = 0; status < sn; status++) {
                if (dp[status] != 0) {
                    int op = (~status) & limit;
                    dfs4(dp[status], op, 0, small - 1, cur);
                }
            }
            for (int i = 0; i < sn; i++) {
                dp[i] = 0;
            }
            int[] tmp = dp;
            dp = cur;
            cur = tmp;
        }
        return dp[limit];
    }

    public static void dfs4(int way, int op, int index, int end, int[] cur) {
        if (index == end) {
            cur[op] += way;
        } else {
            dfs4(way, op, index + 1, end, cur);
            if (((3 << index) & op) == 0) { // 11 << index 可以放砖
                dfs4(way, op | (3 << index), index + 1, end, cur);
            }
        }
    }

    public static void main(String[] args) {
        int N = 8;
        int M = 6;
        System.out.println(ways1(N, M));
        System.out.println(ways2(N, M));
        System.out.println(ways3(N, M));
        System.out.println(ways4(N, M));

        N = 10;
        M = 10;
        System.out.println("=========");
        System.out.println(ways3(N, M));
        System.out.println(ways4(N, M));
    }


}
