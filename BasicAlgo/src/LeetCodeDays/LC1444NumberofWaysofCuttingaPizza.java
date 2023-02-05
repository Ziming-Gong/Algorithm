package LeetCodeDays;

public class LC1444NumberofWaysofCuttingaPizza {
    public int ways(String[] pizza, int k) {
        int n = pizza.length;
        int m = pizza[0].length();
        int[][] grid = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (pizza[i].charAt(j) == 'A') {
                    grid[i][j] = 1;
                }
            }
        }

        int[][] map = generateMap(grid);


        return process(map, 0, n - 1, 0, m - 1, k);

    }

    public static int[][] generateMap(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] map = new int[n][m];
        map[0][0] = grid[0][0];
        for (int i = 1; i < n; i++) {
            map[i][0] = map[i - 1][0] + grid[i][0];
        }
        for (int j = 1; j < m; j++) {
            map[0][j] = map[0][j - 1] + grid[0][j];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                map[i][j] = map[i - 1][j] + map[i][j - 1] + grid[i][j];
            }
        }
        return map;
    }

    public static int process(int[][] map, int up, int down, int left, int right, int rest) {
        if (left > right || up > down) {
            return -1;
        }
        if ((map[down][right] - (up > 0 ? map[up - 1][right] : 0) - (left > 0 ? map[down][left - 1] : 0) + (left > 0 && up > 0 ? map[up - 1][left - 1] : 0)) <= 0) {
            return -1;
        }

        if (rest == 0) {
            return 1;
        }

        int ans = 0;
        for (int split = up + 1; split < down; split++) {
            if (map[split - 1][right] != 0) {
                int d = process(map, split, down, left, right, rest - 1);
                if (d != -1) {
                    ans += d;
                }
            }

        }
        for (int split = left + 1; split < right; split++) {
            if (map[down][split - 1] != 0) {
                int r = process(map, up, down, split, right, rest - 1);
                if (r != -1) {
                    ans += r;
                }
            }

        }
        return ans;


    }

}
