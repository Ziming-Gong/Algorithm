package WeeklyPractice.May11;


// 小红拿到了一个大立方体，该大立方体由1*1*1的小方块拼成，初始每个小方块都是白色。
// 小红可以每次选择一个小方块染成红色
// 每次小红可能选择同一个小方块重复染色
// 每次染色以后，你需要帮小红回答出当前的白色连通块数
// 如果两个小方块共用同一个面，且颜色相同，则它们是连通的
// 给定n、m、h，表示大立方体的长、宽、高
// 给定k次操作，每一次操作用(OA.MaxNumDinstinctNum.a, b, c)表示在大立方体的该位置进行染色
// 返回长度为k的数组，表示每一次操作后，白色方块的连通块数
// n * m * h <= 10 ^ 5，k <= 10 ^ 5
public class RedAndWhiteSquares {

    // 暴力方法
    // 时间复杂度(k * n * m * h);
    public static int[] blocks1(int n, int m, int h, int[][] ops) {
        int k = ops.length;
        int[][][] cube = new int[n][m][h];
        int value = 1;
        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            cube[ops[i][0]][ops[i][1]][ops[i][2]] = -1;
            for (int x = 0; x < n; x++) {
                for (int y = 0; y < m; y++) {
                    for (int z = 0; z < h; z++) {
                        if (cube[x][y][z] != -1 && cube[x][y][z] != value) {
                            ans[i]++;
                            infect(cube, x, y, z, value);
                        }
                    }
                }
            }
            value++;
        }
        return ans;
    }

    public static void infect(int[][][] cube, int a, int b, int c, int change) {
        if (a < 0 || a == cube.length || b < 0 || b == cube[0].length || c < 0 || c == cube[0][0].length
                || cube[a][b][c] == -1 || cube[a][b][c] == change) {
            return;
        }
        cube[a][b][c] = change;
        infect(cube, a - 1, b, c, change);
        infect(cube, a + 1, b, c, change);
        infect(cube, a, b - 1, c, change);
        infect(cube, a, b + 1, c, change);
        infect(cube, a, b, c - 1, change);
        infect(cube, a, b, c + 1, change);
    }


    public static int[] blocks2(int n, int m, int h, int[][] ops) {
        int k = ops.length;
        int[][][] cube = new int[n][m][h];
        for (int[] op : ops) {
            cube[op[0]][op[1]][op[2]]++;
        }
        UnionFind uf = new UnionFind(n, m, h, cube);
        int[] ans = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            ans[i] = uf.sets;
            int x = ops[i][0];
            int y = ops[i][1];
            int z = ops[i][2];
            if(-- cube[x][y][z] == 0){
                uf.figure(x,y,z);
            }
        }
        return ans;

    }

    public static class UnionFind {
        private int n;
        private int m;
        private int h;
        private int[] father;
        private int[] help;
        private int[] size;
        private int sets;

        public UnionFind(int a, int b, int c, int[][][] red) {
            n = a;
            m = b;
            h = c;
            int length = a * b * c;
            father = new int[length];
            help = new int[length];
            size = new int[length];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    for (int z = 0; z < h; z++) {
                        if (red[i][j][z] == 0) {
                            figure(i, j, z);
                        }
                    }
                }
            }
        }

        public void figure(int x, int y, int z) {

            int i = index(x, y, z);
            father[i] = i;
            size[i] = 1;
            sets++;
            union(i, x - 1, y, z);
            union(i, x + 1, y, z);
            union(i, x, y - 1, z);
            union(i, x, y + 1, z);
            union(i, x, y, z + 1);
            union(i, x, y, z - 1);
        }

        private void union(int root, int x, int y, int z) {
            if (x < 0 || y < 0 || z < 0 || x >= n || y >= m || z >= h) {
                return;
            }
            int cur = index(x, y, z);
            if(size[cur] == 0){
                return;
            }

            int i = findFather(root);
            int j = findFather(cur);
            if (i != j) {
                if (size[i] >= size[j]) {
                    father[j] = i;
                    size[i] += size[j];
                } else {
                    father[i] = j;
                    size[j] += size[i];
                }
                sets--;
            }

        }

        private int findFather(int i) {
            int count = 0;
            while (i != father[i]) {
                help[count++] = i;
                i = father[i];
            }
            while (count != 0) {
                father[help[--count]] = i;
            }
            return i;
        }

        public int index(int x, int y, int z) {
            return x + m * n * z + y * n;
        }


    }


    // for test
    public static int[][] randomOps(int n, int m, int h) {
        int size = (int) (Math.random() * (n * m * h)) + 1;
        int[][] ans = new int[size][3];
        for (int i = 0; i < size; i++) {
            ans[i][0] = (int) (Math.random() * n);
            ans[i][1] = (int) (Math.random() * m);
            ans[i][2] = (int) (Math.random() * h);
        }
        return ans;
    }

    // for test
    public static void main(String[] args) {
        int size = 10;
        int testTime = 5000;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int n = (int) (Math.random() * size) + 1;
            int m = (int) (Math.random() * size) + 1;
            int h = (int) (Math.random() * size) + 1;
            int[][] ops = randomOps(n, m, h);
            int[] ans1 = blocks1(n, m, h, ops);
            int[] ans2 = blocks2(n, m, h, ops);
            for (int j = 0; j < ops.length; j++) {
                if (ans1[j] != ans2[j]) {
                    System.out.println("ops!");
                    break;
                }
            }
        }
        System.out.println("test end");

        // 立方体达到10^6规模
        int n = 100;
        int m = 100;
        int h = 100;
        int len = n * m * h;
        // 操作条数达到10^6规模
        int[][] ops = new int[len][3];
        for (int i = 0; i < len; i++) {
            ops[i][0] = (int) (Math.random() * n);
            ops[i][1] = (int) (Math.random() * m);
            ops[i][2] = (int) (Math.random() * h);
        }
        long start = System.currentTimeMillis();
        blocks2(n, m, h, ops);
        long end = System.currentTimeMillis();
        System.out.println("运行时间(毫秒) : " + (end - start));

    }

}
