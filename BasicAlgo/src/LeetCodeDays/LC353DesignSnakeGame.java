package LeetCodeDays;

import java.util.LinkedList;
import java.util.Queue;

public class LC353DesignSnakeGame {

    public class SnakeGame {
        LinkedList<int[]> body;
        boolean[][] map;
        int[][] food;
        int[] cur;
        int n;
        int m;
        int length;
        int curFood;
        boolean haveDead;


        public SnakeGame(int width, int height, int[][] f) {
            n = height;
            m = width;
            food = f;
            length = 1;
            body = new LinkedList<>();
            body.add(new int[]{0, 0});
            map = new boolean[n][m];
            map[0][0] = true;
            cur = new int[]{0, 0};
            curFood = 0;
            haveDead = false;
        }

        public int move(String direction) {
            if (haveDead) {
                return -1;
            }
            int up = 0;
            int right = 0;
            if (direction.equals("R")) {
                right = 1;
            } else if (direction.equals("L")) {
                right = -1;
            } else if (direction.equals("U")) {
                up = -1;
            } else {
                up = 1;
            }
            cur[0] += up;
            cur[1] += right;
            if (cur[0] < 0 || cur[0] > n || cur[1] < 0 || cur[1] > m && map[cur[0]][cur[1]]) {
                haveDead = true;
                return -1;
            }
            body.addFirst(new int[]{cur[0], cur[1]});
            map[cur[0]][cur[1]] = true;
            if (curFood < food.length && cur[0] == food[curFood][0] && cur[1] == food[curFood][1]) {
                curFood++;
                length++;
            } else {
                int[] remove = body.pollLast();
                map[remove[0]][remove[1]] = false;

            }
            return length;

        }
    }

}
