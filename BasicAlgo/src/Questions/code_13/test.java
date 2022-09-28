package Questions.code_13;

import java.util.Arrays;

public class test {

    public static int swimInWater1(int[][] grid) {
        // 行号
        int n = grid.length;
        // 列号
        int m = grid[0].length;
        // [0,0,5]
        // [0,1,3]....
        int[][] points = new int[n * m][3];
        int pi = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                points[pi][0] = i;
                points[pi][1] = j;
                points[pi++][2] = grid[i][j];
            }
        }
        // 所有格子小对象，生成好了!
        // 排序！[a,b,c]  [d,e,f]
        Arrays.sort(points, (a, b) -> a[2] - b[2]);
        // 生成并查集！n * m
        // 初始化的时候，把所有格子独自成一个集合！
        UnionFind uf = new UnionFind(n, m);
        int ans = 0;
        for (int i = 0; i < points.length; i++) {
            int r = points[i][0];
            int c = points[i][1];
            int v = points[i][2];
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

    public static class UnionFind {
        public int col;
        public int pointsSize;
        public int[] father;
        public int[] size;
        public int[] help;

        public UnionFind(int n, int m) {
            col = m;
            pointsSize = n * m;
            father = new int[pointsSize];
            size = new int[pointsSize];
            help = new int[pointsSize];
            for (int i = 0; i < pointsSize; i++) {
                father[i] = i;
                size[i] = 1;
            }
        }

        private int find(int i) {
            int hi = 0;
            while (i != father[i]) {
                help[hi++] = i;
                i = father[i];
            }
            while (hi > 0) {
                father[help[--hi]] = i;
            }
            return i;
        }

        private int index(int i, int j) {
            return i * col + j;
        }

        public void union(int row1, int col1, int row2, int col2) {
            int f1 = find(index(row1, col1));
            int f2 = find(index(row2, col2));
            if (f1 != f2) {
                if (size[f1] >= size[f2]) {
                    father[f2] = f1;
                    size[f1] += size[f2];
                } else {
                    father[f1] = f2;
                    size[f2] += size[f1];
                }
            }
        }

        public boolean isSameSet(int row1, int col1, int row2, int col2) {
            return find(index(row1, col1)) == find(index(row2, col2));
        }

    }
}
