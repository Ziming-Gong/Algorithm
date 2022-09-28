package Questions.code_22;

import java.util.Comparator;
import java.util.PriorityQueue;

public class LC407TrappingRainWaterII {
    public int trapRainWater(int[][] grid) {
        if(grid.length <= 2 || grid[0].length <= 2){
            return 0;
        }
        int n = grid.length;
        int m = grid[0].length;
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comp());
        boolean[][] visited = new boolean[n][m];
        for(int i = 0; i < m; i ++){
            queue.offer(new int[] {grid[0][i], 0, i});
            queue.offer(new int[] {grid[n - 1][i], n - 1, i});
            visited[0][i] = true;
            visited[n - 1][i]= true;
        }
        for(int j = 1; j < n - 1; j ++){
            queue.offer(new int[] {grid[j][0], j, 0});
            queue.offer(new int[] {grid[j][m - 1], j, m - 1});
            visited[j][0] = true;
            visited[j][m - 1] = true;
        }
        int max = 0, ans = 0;

        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            int x = cur[1];
            int y = cur[2];
            int h = cur[0];
            max = Math.max(h, max);
            if(x - 1 >= 0 && !visited[x - 1][y]){
                ans += Math.max(0, max - grid[x - 1][y]);
                queue.offer(new int[] {grid[x - 1][y], x - 1, y});
                visited[x - 1][y] = true;
            }
            if(x + 1 < n && !visited[x + 1][y]){
                ans += Math.max(0, max - grid[x + 1][y]);
                queue.offer(new int[] {grid[x + 1][y], x + 1, y});
                visited[x + 1][y] = true;
            }
            if(y + 1 < m && !visited[x][y + 1]){
                ans += Math.max(0, max - grid[x][y + 1]);
                queue.offer(new int[] {grid[x][y + 1], x, y + 1});
                visited[x][y + 1] = true;
            }
            if(y - 1 >= 0 && !visited[x][y - 1]){
                ans += Math.max(0, max - grid[x][y - 1]);
                queue.offer(new int[] {grid[x][y - 1], x, y - 1});
                visited[x][y - 1] = true;
            }
        }
        return ans;
    }

    public class Comp implements Comparator<int[]> {
        @Override
        public int compare(int[] o1, int[] o2){
            return o1[0] != o2[0] ? o1[0] - o2[0] : (o1[1] != o2[1] ? o1[1] - o2[1] : o1[2] - o2[2]);
        }
    }
}
