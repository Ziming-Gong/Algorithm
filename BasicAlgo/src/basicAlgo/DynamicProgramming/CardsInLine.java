package basicAlgo.DynamicProgramming;

import java.awt.*;

public class CardsInLine {
    public static int win1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int first = f1(arr, 0, arr.length - 1);
        int second = g1(arr, 0, arr.length - 1);
        return Math.max(first, second);
    }

    public static int f1(int[] arr, int L, int R) {
        if (L == R) {
            return arr[L];
        }
        int p1 = arr[L] + g1(arr, L + 1, R);
        int p2 = arr[R] + g1(arr, L, R - 1);
        return Math.max(p1, p2);
    }

    public static int g1(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }
        int p1 = f1(arr, L + 1, R);
        int p2 = f1(arr, L, R - 1);
        return Math.min(p1, p2);
    }

    public static int win2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int n = arr.length;
        int[][] firstDP = new int[n][n];
        int[][] secondDP = new int[n][n];
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                firstDP[row][col] = -1;
                secondDP[row][col] = -1;

            }
        }
        int first = f2(arr, 0, n - 1, firstDP, secondDP);
        int second = g2(arr, 0, n - 1, firstDP, secondDP);
        return Math.max(first, second);
    }

    public static int f2(int[] arr, int L, int R, int[][] firstDP, int[][] secondDP) {
        if (firstDP[L][R] != -1) {
            return firstDP[L][R];
        }

        int ans = 0;
        if (L == R) {
            ans = arr[L];
        } else {
            int p1 = arr[L] + g2(arr, L + 1, R, firstDP, secondDP);
            int p2 = arr[R] + g2(arr, L, R - 1, firstDP, secondDP);
            ans = Math.max(p1, p2);
        }
        firstDP[L][R] = ans;
        return ans;
    }

    public static int g2(int[] arr, int L, int R, int[][] firstDP, int[][] secondDP) {
        if (secondDP[L][R] != -1) {
            return secondDP[L][R];
        }
        int ans = 0;
        if (L != R) {
            ans = Math.min(f2(arr, L + 1, R, firstDP, secondDP), f2(arr, L, R - 1, firstDP, secondDP));
        }
        secondDP[L][R] = ans;
        return ans;
    }

    public static int win3(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int n = arr.length;
        int[][] firstDP = new int[n][n];
        int[][] secondDP = new int[n][n];
        for (int i = 0; i < n; i++) {
            firstDP[i][i] = arr[i];
        }
        for (int col = 1; col < n; col++) {
            int L = 0;
            int R = col;
            while (R < n) {
                firstDP[L][R] = Math.max(arr[L] + secondDP[L + 1][R], arr[R] + secondDP[L][R - 1]);
                secondDP[L][R] = Math.min(firstDP[L + 1][R], firstDP[L][R - 1]);

                L++;
                R++;
            }
        }
        return Math.max(firstDP[0][n - 1], secondDP[0][n - 1]);
    }


    public static void main(String[] args) {
        int[] arr = {5, 7, 4, 5, 8, 1, 6, 0, 3, 4, 6, 1, 7};
        System.out.println(win1(arr));
        System.out.println(win2(arr));
        System.out.println(win3(arr));

    }
}
