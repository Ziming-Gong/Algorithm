package Questions.code_013;

public class LC803BricksFallingWhenHit {
    public int[] hitBricks(int[][] grid, int[][] hits) {
        for (int[] hit : hits) {
            if (grid[hit[0]][hit[1]] == 1) {
                grid[hit[0]][hit[1]] = 2;
            }
        }
        UnionFind uf = new UnionFind(grid);
        int N = hits.length;
        int[] ans = new int[N];
        for (int i = N - 1; i >= 0; i--) {
            if (grid[hits[i][0]][hits[i][1]] == 2) {
                ans[i] = uf.finger(hits[i][0], hits[i][1]);
            }
        }

        return ans;
    }

    public static class UnionFind {
        private int N;
        private int M;
        // 有多少块砖，连到了天花板上
        private int cellingAll;
        // 原始矩阵，因为炮弹的影响，1 -> 2
        private int[][] grid;
        // cellingSet[i] = true; i 是头节点，所在的集合是天花板集合
        private boolean[] cellingSet;
        private int[] fatherMap;
        private int[] sizeMap;
        private int[] stack;

        public UnionFind(int[][] grid) {
            this.grid = grid;
            int n = grid.length;
            int m = grid[0].length;
            sizeMap = new int[n * m];
            fatherMap = new int[n * m];
            stack = new int[n * m];
            N = n;
            M = m;
            cellingAll = 0;
            cellingSet = new boolean[n * m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (grid[i][j] == 1) {
                        int index = index(i, j);
                        sizeMap[index] = 1;
                        fatherMap[index] = index;
                        if (i == 0) {
                            cellingAll++;
                            cellingSet[index] = true;
                        }
                    }
                }
            }
            initConnect();
        }


        private void initConnect() {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    union(i, j, i + 1, j);
                    union(i, j, i - 1, j);
                    union(i, j, i, j + 1);
                    union(i, j, i, j - 1);
                }
            }
        }

        public int find(int row, int col) {
            int index = 0;
            int cur = index(row, col);
            while (cur != fatherMap[cur]) {
                stack[index++] = cur;
                cur = fatherMap[cur];
            }
            while (index > 0) {
                fatherMap[stack[--index]] = cur;
            }
            return cur;
        }

        private void union(int r1, int c1, int r2, int c2) {
            if (valid(r1, c1) && valid(r2, c2)) {
                int father1 = find(r1, c1);
                int father2 = find(r2, c2);
                if (father1 != father2) {
                    int size1 = sizeMap[father1];
                    int size2 = sizeMap[father2];
                    boolean statue1 = cellingSet[father1];
                    boolean statue2 = cellingSet[father2];
                    if (size1 <= size2) {
                        sizeMap[father2] = size1 + size2;
                        fatherMap[father1] = father2;
                        if (statue1 ^ statue2) {
                            cellingSet[father2] = true;
                            cellingAll += statue1 ? size2 : size1;
                        }
                    } else {
                        sizeMap[father1] = size1 + size2;
                        fatherMap[father2] = father1;
                        if (statue1 ^ statue2) {
                            cellingSet[father1] = true;
                            cellingAll += statue1 ? size2 : size1;
                        }
                    }
                }
            }
        }

        private boolean valid(int row, int col) {
            return row >= 0 && row < N && col >= 0 && col < M && grid[row][col] == 1;
        }

        public int cellingNum() {
            return cellingAll;
        }

        public int finger(int r1, int c1) {
            grid[r1][c1] = 1;
            int index = index(r1, c1);
            if (r1 == 0) {
                cellingAll++;
                cellingSet[index] = true;
            }
            fatherMap[index] = index;
            sizeMap[index] = 1;
            int pre = cellingAll;
            union(r1, c1, r1 + 1, c1);
            union(r1, c1, r1 - 1, c1);
            union(r1, c1, r1, c1 + 1);
            union(r1, c1, r1, c1 - 1);
            if (r1 == 0) {
                return cellingAll - pre;
            } else {
                return cellingAll == pre ? 0 : cellingAll - pre - 1;
            }
        }
        public int index(int i, int j) {
            return i * M + j;
        }
    }
}
