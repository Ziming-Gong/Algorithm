package Contest.Aug27;

import WeeklyPractice.Aug03.LC716MaxStack;

public class LC6162MinimumAmountofTimetoCollectGarbage {

    // M P G
    public static int garbageCollection(String[] garbage, int[] travel) {
        int n = garbage.length;
        int[][] grid = new int[n][3];

        for (int i = 0; i < n; i++) {
            char[] str = garbage[i].toCharArray();
            for (char c : str) {
                if (c == 'M') {
                    grid[i][0]++;
                } else if (c == 'P') {
                    grid[i][1]++;
                } else {
                    grid[i][2]++;
                }
            }
        }
        int ans = 0;
        int a = 0, b = 0, c = 0;
        for (int i = n - 1; i > 0; i--) {
            a += grid[i][0];
            b += grid[i][1];
            c += grid[i][2];
            a += a > 0 ? travel[i - 1] : 0;
            b += b > 0 ? travel[i - 1] : 0;
            c += c > 0 ? travel[i - 1] : 0;
        }
        a += grid[0][0];
        b += grid[0][1];
        c += grid[0][2];
        return a + b + c;


    }

    public static void main(String[] args) {
        String[] str = {"MMM", "PGM","GP"};
        int[] arr = {3, 10};
        System.out.println(garbageCollection(str, arr));
    }
}
