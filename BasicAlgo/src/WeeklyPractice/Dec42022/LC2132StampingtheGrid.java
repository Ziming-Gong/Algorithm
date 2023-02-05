package WeeklyPractice.Dec42022;

public class LC2132StampingtheGrid {

    public boolean possibleToStamp(int[][] grid, int stampHeight, int stampWidth) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] graph = new int[n + 2][m + 2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                graph[i + 1][j + 1] = grid[i][j];
            }
        }
        graph[0][0] = grid[0][0];

        for (int i = 1; i < n + 2; i++) {
            for (int j = 1; j < m + 2; j++) {
                graph[i][j] += graph[i - 1][m] + graph[i][j - 1] - graph[i - 1][j - 1];
            }
        }

        int[][] diff = new int[n + 2][m + 2];
        for (int i = 0; i <= n - stampHeight + 1; i++) {
            for (int j = 0; j <= m - stampWidth + 1; j++) {
                if (grid[i][j] != 1) {
                    if (graph[i + stampHeight][j - stampWidth] - graph[i + stampHeight][j] - graph[i][j - stampWidth] + graph[i][j] == 0) {
                        diff[i + 1][j + 1] = 1;
                        diff[i + stampHeight][j + 1] = -1;
                        diff[i + 1][j + stampWidth] = -1;
                        diff[i + stampHeight][j + stampWidth] = 1;
                    }
                }
            }
        }

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                diff[i][j] += diff[i - 1][j - 1] - diff[i - 1][j] - diff[i][j - 1];
                if (grid[i - 1][j - 1] != 1 && diff[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;

    }

    public static void main(String[] args) {
        int cur = -1;
        System.out.println(cur << 1);
    }


}
