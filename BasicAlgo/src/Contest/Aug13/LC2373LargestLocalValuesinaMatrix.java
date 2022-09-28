package Contest.Aug13;

import java.util.ArrayList;
import java.util.LinkedList;

public class LC2373LargestLocalValuesinaMatrix {
    public static int[][] largestLocal(int[][] grid) {
        int N = grid.length;
//        PriorityQueue<Integer> queue = new PriorityQueue<>();
        int[][] ans = new int[N - 2][N - 2];
        ArrayList<LinkedList<Integer>> comp = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            comp.add(new LinkedList<>());
            if (grid[0][i] <= grid[1][i]) {
                comp.get(i).add(1);
            } else {
                comp.get(i).add(0);
                comp.get(i).addLast(1);
            }
        }
        for (int i = 2; i < N; i++) {
            for (int j = 0; j < N; j++) {
                while (!comp.get(j).isEmpty() && grid[comp.get(j).peekLast()][j] <= grid[i][j]) {
                    comp.get(j).pollLast();
                }
                comp.get(j).addLast(i);
                if (comp.get(j).peekFirst() <= i - 3) {
                    comp.get(j).pollFirst();
                }
            }
            for(int j = 0,  k = 1; j < ans.length; j ++, k ++){
                ans[i - 2][j] = Math.max(grid[comp.get(k - 1).peekFirst()][k -1], Math.max(grid[comp.get(k).peekFirst()][k], grid[comp.get(k + 1).peekFirst()][k + 1]));
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] grid = {{9,9,8,1},{5,6,2,6},{8,2,6,4},{6,2,2,}};
        System.out.println(largestLocal(grid));
    }
}
