package basicAlgo.Fibonacci;

public class FibonacciProblem {

    public static int F1(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }

        return F1(n - 1) + F1(n - 2);

    }

    public static int F2(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        int pre = 1;
        int temp = 1;
        int post = 1;
        for (int i = 3; i <= n; i++) {
            temp = pre;
            pre = post;
            post = temp + pre;
        }
        return post;
    }

    public static int F3(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }

        int[][] t = {{1, 1},
                {1, 0}};

        int[][] ans = multi(t, n - 2);
        return ans[0][1] + ans[0][0];
    }

    public static int[][] multi(int[][] m, int p) {
        int[][] ans = new int[m.length][m[0].length];
        for (int i = 0; i < m.length; i++) {
            ans[i][i] = 1;
        }
        int[][] t = m;
        for (; p != 0; p >>= 1) {
            if ((p & 1) == 1) {
                ans = matrixPower(t, ans);
            }
            t = matrixPower(t, t);
        }
        return ans;

    }

    public static int[][] matrixPower(int[][] a, int[][] b) {
        int m = a.length; // row of OA.MaxNumDinstinctNum.a
        int n = b[0].length; // column of b
        int k = a[0].length; // column of OA.MaxNumDinstinctNum.a and row of b
        int[][] ans = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int c = 0; c < k; c++){
                    ans[i][j] += a[i][c]*b[c][j];
                }
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        int n = 6;
        System.out.println(F2(n));
        System.out.println(F1(n));
        System.out.println(F3(n));
    }

}
