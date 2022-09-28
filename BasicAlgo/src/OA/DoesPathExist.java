package OA;

import sun.awt.image.ImageWatched;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class DoesPathExist {
    //IMC
    public static boolean isExist(int x, int y, int X, int Y, int c) {
        if (x > X || y > Y) {
            return false;
        }
        LinkedList<int[]> list = new LinkedList<>();
        list.add(new int[]{x, y});
        boolean[][] visited = new boolean[X + 1][Y + 1];
        while (!list.isEmpty()) {
            LinkedList<int[]> nexts = new LinkedList<>();
            while (!list.isEmpty()) {
                int[] cur = list.poll();
                int a = cur[0];
                int b = cur[1];
                if (a == X && b == Y) {
                    return true;
                }
                if (isPower(a + b)) {
                    continue;
                }
                if (a + b <= X && !visited[a + b][b]) {
                    nexts.add(new int[]{a + b, b});
                    visited[a + b][b] = true;
                }
                if (a + b <= Y && !visited[a][a + b]) {
                    nexts.add(new int[]{a, a + b});
                    visited[a][a + b] = true;
                }
                if (a + c <= X && b + c <= Y && !visited[a + c][b + c]) {
                    nexts.add(new int[]{a + c, b + c});
                    visited[a + c][b + c] = true;
                }
            }
            list = nexts;
        }
        return false;
    }

    public static boolean isPower(int cur) {
        int L = 1, R = cur, mid;
        while (L <= R) {
            mid = (L + R) / 2;
            if ((mid * mid) == cur) {
                return true;
            } else if (mid * mid < cur) {
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }
        return false;
    }

    static HashSet<Integer> set;

    public static boolean Right(int x, int y, int X, int Y, int c) {
        if (x > X || y > Y) {
            return false;
        }
        set = new HashSet<>();
        for (int i = 1; i < 50; i++) {
            set.add(i * i);
        }
        int[][] dp = new int[X + 1][Y + 1];

        return process(x, y, X, Y, c, set, dp);

    }

    public static boolean process(int x, int y, int X, int Y, int c, HashSet<Integer> set, int[][] dp) {
        if (x > X || y > Y) {
            return false;
        }
        if (x == X && y == Y) {
            return true;
        }
        if (set.contains(x + y)) {
            return false;
        }
        if (dp[x][y] != 0) {
            return dp[x][y] == 1;
        }
        boolean ans = false;
        ans |= process(x + c, y + c, X, Y, c, set, dp);
        ans |= process(x + y, y, X, Y, c, set, dp);
        ans |= process(x, x + y, X, Y, c, set, dp);
        dp[x][y] = ans ? 1 : 2;
        return ans;
    }


    public static void main(String[] args) {
        int max = (int) 1e4;
        int maxC = 15;
        int testTime = 100000;
        System.out.println("test start");
        for (int i = 1; i <= testTime; i++) {
            int x = (int) (Math.random() * max);
            int X = (int) (Math.random() * max);
            int y = (int) (Math.random() * max);
            int Y = (int) (Math.random() * max);
            int c = (int) (Math.random() * maxC) + 1;
            boolean ans1 = isExist(x, y, X, Y, c);
//            System.out.println("1");
            boolean ans2 = Right(x, y, X, Y, c);
//            System.out.println("2");
            if (ans1 != ans2) {
                System.out.println("oops");
                System.out.println(x);
                System.out.println(y);
                System.out.println(X);
                System.out.println(Y);
                System.out.println(c);
                System.out.println(ans1);
                System.out.println(ans2);

                break;
            }
        }
        System.out.println("test end");
    }


}
