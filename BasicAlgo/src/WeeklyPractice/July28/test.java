package WeeklyPractice.July28;

import Questions.code_7.MaxAndValue;
import com.sun.openpisces.TransformingPathConsumer2D;

import javax.sound.midi.Soundbank;
import java.util.*;

public class test {
    public static int minPathSum(int[][] grid) {
        int N = grid.length;
        int M = grid[0].length;
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> (a[0] - b[0]));
        queue.add(new int[]{grid[0][0], 0, 0});
        int ans = 0;
        boolean[][] visited = new boolean[N][M];
        visited[0][0] = true;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (cur[1] == N - 1 && cur[2] == M - 1) {
                ans = cur[0];
                break;
            }
            int x = cur[1];
            int y = cur[2];
            if (x - 1 >= 0 && !visited[x - 1][y]) {
                queue.add(new int[]{cur[0] + grid[x - 1][y], x - 1, y});
                visited[x - 1][y] = true;
            }
            if (x + 1 < N && !visited[x + 1][y]) {
                queue.add(new int[]{cur[0] + grid[x + 1][y], x + 1, y});
                visited[x + 1][y] = true;
            }
            if (y - 1 >= 0 && !visited[x][y - 1]) {
                queue.add(new int[]{cur[0] + grid[x][y - 1], x, y - 1});
                visited[x][y - 1] = true;
            }
            if (y + 1 < M && !visited[x][y + 1]) {
                queue.add(new int[]{cur[0] + grid[x][y + 1], x, y + 1});
                visited[x][y + 1] = true;
            }
        }
        return ans;
    }


    public static int calculate(int[][] grid, int i, int j) {
        if (i == grid.length || j == grid[0].length) return Integer.MAX_VALUE;
        if (i == grid.length - 1 && j == grid[0].length - 1) return grid[i][j];
        return grid[i][j] + Math.min(calculate(grid, i + 1, j), calculate(grid, i, j + 1));
    }
    public static int minPathSum1(int[][] grid) {
        return calculate(grid, 0, 0);
    }

    public static int[][] generate(int N, int M, int val){
        int[][] ans = new int[N][M];
        for(int i = 0; i < N; i ++){
            for(int j = 0; j < M; j ++){
                ans[i][j] = (int) (Math.random() * val);
            }
        }
        return ans;
    }

    public static void print(int[][] matrix){
        int N = matrix.length;
        int M = matrix[0].length;
        for(int i = 0; i < N; i ++){
            for(int j = 0; j < M; j ++){
                System.out.printf(matrix[i][j] + " ,");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int N = 5;
        int M = 5;
        int val = 5;
        int testTime = 1000000;
        for(int i = 1; i <= testTime; i ++){
            int[][] matrix = generate(N, M, val);
            int p1 = minPathSum(matrix);
            int p2 = minPathSum1(matrix);
            if(p1 != p2){
                System.out.println("oops");
                print(matrix);
                System.out.println();
                System.out.println(p1);
                System.out.println(p2);
                break;
            }
        }
        System.out.println("test ending");
    }

}
