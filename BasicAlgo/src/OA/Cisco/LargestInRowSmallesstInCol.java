package OA.Cisco;


import WeeklyPractice.April02.PerfectPairNumber;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

//在2d matrix中找elements which are the largest in the row and smallest in the column。
public class LargestInRowSmallesstInCol {

    public static List<Integer> solve(int[][] grid) {
        List<HashSet<Integer>> map = new ArrayList<>();
        int n = grid.length;
        int m = grid[0].length;
        for (int i = 0; i < m; i++) {
            map.add(new HashSet<>());
        }
        for (int i = 0; i < n; i++) {
            int max = grid[i][0];
            List<Integer> list = new ArrayList<>();
            list.add(0);
            for (int j = 1; j < m; j++) {
                if (max < grid[i][j]) {
                    max = grid[i][j];
                    list = new ArrayList<>();
                    list.add(j);
                } else if (max == grid[i][j]) {
                    list.add(j);
                }
            }
            for (int j : list) {
                map.get(j).add(i);
            }
        }

        List<Integer> ans = new ArrayList<>();

        for (int j = 0; j < m; j++) {
            int min = grid[0][j];
            List<Integer> list = new ArrayList<>();
            list.add(0);
            for (int i = 1; i < n; i++) {
                if (min > grid[i][j]) {
                    min = grid[i][j];
                    list = new ArrayList<>();
                    list.add(i);
                } else if (min == grid[i][j]) {
                    list.add(i);
                }
            }
            for (int i : list) {
                if (map.get(j).contains(i)) {
                    ans.add(grid[i][j]);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] grid = new int[4][4];
        grid[0] = new int[]{180, 50, 40, 60};
        grid[1] = new int[]{170, 100, 1, 6};
        grid[2] = new int[]{140, 123, 56, 32};
        grid[3] = new int[]{190, 478, 56, 890};
        List<Integer> ans = solve(grid);
        for (int i : ans) {
            System.out.println(i);
        }

    }

}
