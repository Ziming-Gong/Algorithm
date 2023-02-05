package LeetCodeDays;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BISchemaBinding;

import java.util.PriorityQueue;

public class LC505TheMazeII {
    public static int shortestDistance(int[][] maze, int[] start, int[] end) {
        if (maze[start[0]][start[1]] == 1) {
            return -1;
        }
        int n = maze.length;
        int m = maze[0].length;

        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> (a[2] - b[2]));
        boolean[][] visited = new boolean[n][m];
        visited[start[0]][start[1]] = true;

        addDirection(maze, queue, start[0], start[1], 5, 0);

        int ans = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int path = cur[2];
            int x = cur[0];
            int y = cur[1];
            int direction = cur[3];

            int[] next = findNext(maze, x, y, direction);
            if (!visited[next[0]][next[1]]) {
                addDirection(maze, queue, next[0], next[1], direction, path + next[2]);
                if (next[0] == end[0] && next[1] == end[1]) {
                    ans = Math.min(ans, path + next[2]);
                }else{
                    visited[next[0]][next[1]] = true;
                }
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public static int[] findNext(int[][] maze, int x, int y, int direction) {
        int path = 0;
        if (direction == 1) {
            while (x - 1 >= 0 && maze[x - 1][y] == 0) {
                x--;
                path++;
            }
        } else if (direction == 2) {
            while (x + 1 < maze.length && maze[x + 1][y] == 0) {
                x++;
                path++;
            }

        } else if (direction == 3) {
            while (y - 1 >= 0 && maze[x][y - 1] == 0) {
                y--;
                path++;
            }
        } else {
            while (y + 1 < maze[0].length && maze[x][y + 1] == 0) {
                y++;
                path++;

            }
        }
        return new int[]{x, y, path};
    }


    // cur[3]   1 : up
    //          2 : down
    //          3 : left
    //          4 : right
    public static void addDirection(int[][] maze, PriorityQueue<int[]> queue, int x, int y, int c, int path) {
        if (c != 3 && c != 4) {
            if (y < maze[0].length - 1 && maze[x][y + 1] != 1) {
                queue.add(new int[]{x, y, path, 4});
            }
            if (y > 0 && maze[x][y - 1] != 1) {
                queue.add(new int[]{x, y, path, 3});
            }
        }
        if (c != 1 && c != 2) {
            if (x > 0 && maze[x - 1][y] != 1) {
                queue.add(new int[]{x, y, path, 1});
            }
            if (x < maze.length - 1 && maze[x + 1][y] != 1) {
                queue.add(new int[]{x, y, path, 2});
            }
        }
    }

    public static void main(String[] args) {
        int[][] arr = {{0, 0, 1, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 1, 0}, {1, 1, 0, 1, 1}, {0, 0, 0, 0, 0}};
        int[] start = {0, 4};
        int[] end = {4, 4};

        System.out.println(shortestDistance(arr, start, end));

    }


}
