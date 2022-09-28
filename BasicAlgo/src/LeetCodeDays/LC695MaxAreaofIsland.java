package LeetCodeDays;

import basicAlgo.bfprt.FindMinKth;
import sun.util.resources.cldr.ta.CurrencyNames_ta;

public class LC695MaxAreaofIsland {

    public int maxAreaOfIsland(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        boolean[][] dp = new boolean[n][m];
        int ans = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1 && !dp[i][j]) {
                    ans = Math.max(ans, process(grid, i, j, dp));
                }
            }
        }
        return ans == Integer.MIN_VALUE ? 0 : ans;

    }

    public int process(int[][] grid, int i, int j, boolean[][] dp) {
        if (i >= grid.length || i < 0 || j >= grid[0].length || j < 0 || dp[i][j]) {
            return 0;
        }
        if (grid[i][j] == 0) {
            return 0;
        }
        dp[i][j] = true;
        int ans = 1;
        ans += process(grid, i + 1, j, dp);
        ans += process(grid, i - 1, j, dp);
        ans += process(grid, i, j + 1, dp);
        ans += process(grid, i, j - 1, dp);
        return ans;
    }

    public int maxAreaOfIsland1(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        UnionFind uf = new UnionFind(grid);
        for (int i = 0; i < n; i++) {
            uf.union(grid[i][0], m * i, true);
            for (int j = 1; j < m; j++) {
                uf.union(grid[i][j], m * i + j, false);
            }
        }

        return uf.getMax();

    }

    public class UnionFind {
        public int[] size;
        public int[] father;
        public int[] help;
        public int MAX_SIZE;
        public int N;
        public int max;

        public UnionFind(int[][] grid) {
            int n = grid.length;
            N = grid[0].length;
            MAX_SIZE = n * N;
            size = new int[MAX_SIZE];
            father = new int[MAX_SIZE];
            help = new int[MAX_SIZE];
            for (int i = 0; i < MAX_SIZE; i++) {
                father[i] = -1;
            }
            max = Integer.MIN_VALUE;
        }

        public void union(int curValue, int curIndex, boolean isLeft) {
            if(curValue == 0){
                return;
            }
            int leftFather = !isLeft && father[curIndex - 1] != -1 ? find(curIndex - 1) : -1;
            int rightFather = curIndex >= N && father[curIndex - N] != -1 ? find(curIndex - N) : -1;
            size[curIndex] = 1;
            if (leftFather != rightFather) {
                if (leftFather == -1) {
                    father[curIndex] = rightFather;
                    size[rightFather]++;
                    max = Math.max(max, size[rightFather]);

                } else if (rightFather == -1) {
                    father[curIndex] = leftFather;
                    size[leftFather]++;
                    max = Math.max(max, size[leftFather]);

                } else {
                    int big = size[leftFather] > size[rightFather] ? leftFather : rightFather;
                    int small = big == leftFather ? rightFather : leftFather;
                    size[big] += size[small] + 1;
                    // size[small] += 1;
                    father[small] = big;
                    father[curIndex] = big;
                    max = Math.max(max, size[big]);

                }
            } else {
                if (leftFather == -1) {
                    father[curIndex] = curIndex;
                    max = Math.max(max, size[curIndex]);

                } else {
                    father[curIndex] = leftFather;
                    size[leftFather] += 1;
                    max = Math.max(max, size[leftFather]);
                }
            }
        }

        public int find(int cur) {
            if(father[cur] == -1){
                return -1;
            }
            int index = 0;
            while (father[cur] != cur) {
                help[index++] = cur;
                cur = father[cur];
            }
            while (index > 0) {
                father[help[--index]] = cur;
            }
            return cur;

        }

        public int getMax() {
            return max == Integer.MIN_VALUE ? 0 : max;
        }

    }
    public static int mod = (int) 1e9 + 7;

}











