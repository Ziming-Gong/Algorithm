package Questions.code_18;

import java.util.LinkedList;

public class LC934 {
    public int shortestBridge(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        LinkedList<Integer> cur = new LinkedList<>();
        int[] nexts = new int[n * m];
        int[] record = new int[n * m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    int size = infect(grid, cur, record, i, j, 0);
                    int ans = bfs(grid, cur, record, size);
                    return ans - 1;
                }
            }
        }
        return -1;

    }

    public int infect(int[][] grid, LinkedList<Integer> cur, int[] record, int x, int y, int index) {
        if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length || grid[x][y] != 1) {
            return index;
        }

        int p = x * grid[0].length + y;
        record[p] = 1;
        cur.add(p);
        index++;
        grid[x][y] = 2;

        index = infect(grid, cur, record, x + 1, y, index);
        index = infect(grid, cur, record, x - 1, y, index);
        index = infect(grid, cur, record, x, y + 1, index);
        index = infect(grid, cur, record, x, y - 1, index);
        return index;
    }

    public int bfs(int[][] grid, LinkedList<Integer> cur, int[] record, int size) {
        int level = 2;
        int temp = 0;
        int ans = -1;
        while (!cur.isEmpty()) {
            boolean flag = false;
            while (size > 0) {
                int curIndex = cur.poll();
                size--;
                int x = curIndex < grid[0].length ? 0 : curIndex / grid[0].length;
                int y = curIndex % grid[0].length;

                if (x - 1 >= 0 && record[curIndex - grid[0].length] == 0) {
                    record[curIndex - grid[0].length] = level;
                    cur.add(curIndex - grid[0].length);
                    temp++;
                    if (grid[x - 1][y] == 1) {
                        ans = level;
                        flag = true;
                        break;

                    }
                }
                if (x + 1 < grid.length && record[curIndex + grid[0].length] == 0) {
                    record[curIndex - grid[0].length] = level;
                    cur.add(curIndex + grid[0].length);
                    temp++;
                    if (grid[x + 1][y] == 1) {
                        ans = level;
                        flag = true;
                        break;

                    }
                }
                if (y - 1 >= 0  && record[curIndex - 1] == 0) {
                    record[curIndex - 1] = level;
                    cur.add(curIndex - 1);
                    temp++;
                    if (grid[x][y - 1] == 1) {
                        ans = level;
                        flag = true;
                        break;

                    }
                }
                if (y + 1 < grid[0].length && record[curIndex + 1] == 0) {
                    record[curIndex + 1] = level;
                    cur.add(curIndex + 1);
                    temp++;
                    if (grid[x][y + 1] == 1) {
                        ans = level;
                        flag = true;
                        break;

                    }
                }
            }
            size = temp;
            temp = 0;
            level ++;
            if (flag) {
                break;
            }
        }
        return ans;
    }
}
