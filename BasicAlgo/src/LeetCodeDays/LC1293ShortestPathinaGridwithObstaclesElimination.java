package LeetCodeDays;

import basicAlgo.mergesorted.ans;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class LC1293ShortestPathinaGridwithObstaclesElimination {
    public int shortestPath(int[][] grid, int k) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int n = grid.length;
        int m = grid[0].length;
        int[][][] dp = new int[n][m][k + 1];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                for (int p = 0; p <= k; p++) {
                    dp[i][j][p] = -2;
                }
            }
        }
        int ans = process(grid, 0, 0, k, visited, dp);
        for (int i = 0; i < k; i++) {
            int next = process(grid, 0, 0, i, visited, dp);
            ans = Math.min(ans, next);
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;

    }

    public int process(int[][] grid, int x, int y, int rest, boolean[][] visited, int[][][] dp) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || rest < 0) {
            return Integer.MAX_VALUE;
        }
        if (x == grid.length - 1 && y == grid[0].length - 1) {
            return 0;
        }
        if (dp[x][y][rest] != -2) {
            return dp[x][y][rest];
        }

        int ans = Integer.MAX_VALUE;


        if (x + 1 < grid.length && !visited[x + 1][y]) {
            visited[x + 1][y] = true;
            int next = process(grid, x + 1, y, rest - grid[x + 1][y], visited, dp);
            if (next != Integer.MAX_VALUE) {
                ans = Math.min(next + 1, ans);
            }
            visited[x + 1][y] = false;
        }

        if (y + 1 < grid[0].length && !visited[x][y + 1]) {
            visited[x][y + 1] = true;
            int next = process(grid, x, y + 1, rest - grid[x][y + 1], visited, dp);
            if (next != Integer.MAX_VALUE) {
                ans = Math.min(ans, next + 1);
            }
            visited[x][y + 1] = false;
        }

        if (x - 1 >= 0 && !visited[x - 1][y]) {
            visited[x - 1][y] = true;
            int next = process(grid, x - 1, y, rest - grid[x - 1][y], visited, dp);
            if (next != Integer.MAX_VALUE) {
                ans = Math.min(next + 1, ans);
            }
            visited[x - 1][y] = false;
        }

        if (y - 1 >= 0 && !visited[x][y - 1]) {
            visited[x][y - 1] = true;
            int next = process(grid, x, y - 1, rest - grid[x][y - 1], visited, dp);
            if (next != Integer.MAX_VALUE) {
                ans = Math.min(ans, next + 1);
            }
            visited[x][y - 1] = false;
        }
        dp[x][y][rest] = ans;

        return ans;
    }

    public int shortestPath1(int[][] grid, int k) {


        int n = grid.length;
        int m = grid[0].length;
        boolean[][][] visited = new boolean[n][m][k];


        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0, k});
        for (int i = 0; i <= k; i++) {
            visited[0][0][k] = true;
        }
        int ans = 0;
        while (!queue.isEmpty()) {
            Queue<int[]> next = new LinkedList<>();
            while (!queue.isEmpty()) {
                int[] cur = queue.poll();
                int x = cur[0];
                int y = cur[1];
                int used = cur[2];

                if (x + 1 < n) {
                    if (grid[x + 1][y] == 0 && !visited[x + 1][y][used]) {
                        next.add(new int[]{x + 1, y, used});
                        visited[x + 1][y][used] = true;
                    } else if (grid[x + 1][y] == 1 && used > 0 && !visited[x + 1][y][used - 1]) {
                        next.add(new int[]{x + 1, y, used - 1});
                        visited[x + 1][y][used - 1] = true;
                    }
                }

                if (y + 1 < m) {
                    if (grid[x][y + 1] == 0 && !visited[x][y + 1][used]) {
                        next.add(new int[]{x, y + 1, used});
                        visited[x][y + 1][used] = true;
                    } else if (grid[x][y + 1] == 1 && used > 0 && !visited[x][y + 1][used - 1]) {
                        next.add(new int[]{x, y + 1, used - 1});
                        visited[x][y + 1][used - 1] = true;
                    }
                }
                if (x - 1 >= 0) {
                    if (grid[x - 1][y] == 0 && !visited[x - 1][y][used]) {
                        next.add(new int[]{x - 1, y, used});
                        visited[x - 1][y][used] = true;
                    } else if (grid[x - 1][y] == 1 && used > 0 && !visited[x - 1][y][used - 1]) {
                        next.add(new int[]{x - 1, y, used - 1});
                        visited[x - 1][y][used - 1] = true;
                    }
                }

                if (y - 1 >= 0) {
                    if (grid[x][y - 1] == 0 && !visited[x][y - 1][used]) {
                        next.add(new int[]{x, y - 1, used});
                        visited[x][y - 1][used] = true;
                    } else if (grid[x][y - 1] == 1 && used > 0 && !visited[x][y - 1][used - 1]) {
                        next.add(new int[]{x, y - 1, used - 1});
                        visited[x][y - 1][used - 1] = true;
                    }
                }
                if (x + 1 == n - 1 && y + 1 == m - 1) {
                    return ans + 1;
                }
            }

            ans ++;
            queue = next;
        }
        return ans;


    }
}
