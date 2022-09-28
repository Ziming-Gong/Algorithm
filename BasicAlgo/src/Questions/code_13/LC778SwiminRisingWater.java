package Questions.code_13;

import LeetCodeDays.LC695MaxAreaofIsland;
import Questions.code_2.ChooseWork;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class LC778SwiminRisingWater {
    public int swimInWater(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> (a[2] - b[2]));
        queue.add(new int[]{0, 0, grid[0][0]});
        boolean[][] visited = new boolean[n][m];
        int ans = 0;
        visited[0][0] = true;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int i = cur[0];
            int j = cur[1];
            if (i > 0 && !visited[i - 1][j]) {
                queue.add(new int[]{i - 1, j, grid[i - 1][j]});
                visited[i - 1][j] = true;
            }
            if (i < n - 1 && !visited[i + 1][j]) {
                queue.add(new int[]{i + 1, j, grid[i + 1][j]});
                visited[i + 1][j] = true;
            }
            if (j > 0 && !visited[i][j - 1]) {
                queue.add(new int[]{i, j - 1, grid[i][j - 1]});
                visited[i][j - 1] = true;
            }
            if (j < m - 1 && !visited[i][j + 1]) {
                queue.add(new int[]{i, j + 1, grid[i][j + 1]});
                visited[i][j + 1] = true;
            }
            ans = Math.max(ans, cur[2]);
            if (i == n - 1 && j == m - 1) {
                break;
            }
        }
        return ans;

    }

    public int swimInWater1(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] points = new int[n * m][3];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                points[i * n + j][0] = i;
                points[i * n + j][1] = j;
                points[i * n + j][2] = grid[i][j];
            }
        }
        Arrays.sort(points, (a, b) -> (a[2] - b[2]));
        UnionFind uf = new UnionFind(grid);
        int ans = 0;
        for (int[] cur : points) {
            int r = cur[0];
            int c = cur[1];
            int v = cur[2];
            if (r > 0 && grid[r - 1][c] <= v) {
                uf.union(r, c, r - 1, c);
            }
            if (r < n - 1 && grid[r + 1][c] <= v) {
                uf.union(r, c, r + 1, c);
            }
            if (c > 0 && grid[r][c - 1] <= v) {
                uf.union(r, c, r, c - 1);
            }
            if (c < m - 1 && grid[r][c + 1] <= v) {
                uf.union(r, c, r, c + 1);
            }
            if (uf.isSameSet(0, 0, n - 1, m - 1)) {
                ans = v;
                break;
            }
        }
        return ans;

    }

    public class UnionFind {
        public int[] size;
        public int[] father;
        public int[] help;
        public int col;


        public UnionFind(int[][] grid) {
            int n = grid.length;
            int m = grid[0].length;
            size = new int[n * m];
            father = new int[n * m];
            help = new int[n * m];
            col = m;
            for (int i = 0; i < n * m; i++) {
                size[i] = 1;
                father[i] = i;
            }
        }

        public void union(int i, int j, int p, int q) {
            int indexM = getIndex(i, j);
            int indexT = getIndex(p, q);
            int fatherM = father[indexM];
            int fatherT = father[indexT];
            if (fatherM != fatherT) {
                if (size[fatherM] > size[fatherT]) {
                    size[fatherM] += size[fatherT];
                    father[fatherT] = fatherM;
                } else {
                    size[fatherT] += size[fatherM];
                    father[fatherM] = fatherT;
                }
            }

        }

        public int find(int cur) {
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

        public boolean isSameSet(int i, int j, int p, int q) {
            return find(getIndex(i, j)) == find(getIndex(p, q));
        }

        private int getIndex(int i, int j) {
            return i * col + j;
        }
    }


}
