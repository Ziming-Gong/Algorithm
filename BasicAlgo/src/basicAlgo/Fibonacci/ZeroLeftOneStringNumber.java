package basicAlgo.Fibonacci;

import basicAlgo.mergesorted.ans;

public class ZeroLeftOneStringNumber {
    public static int getNum1(int n) {
        if (n < 1) {
            return 0;
        }
        return process(1, n);
    }

    public static int process(int start, int n) {
        if (start == n - 1) {
            return 2;
        }
        if (start == n) {
            return 1;
        }
        return process(start + 1, n) + process(start + 2, n);
    }

    public static int getNum2(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        int pre = 1;
        int post = 2;
        int temp1 = 1;
        for (int i = 3; i <= n; i++) {
            temp1 = pre;
            pre = post;
            post = pre + temp1;
        }
        return post;
    }

    public static int getNum3(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        int[][] m = {{1, 1}, {1, 0}};
        int[][] ans = multi(m, n - 2);
        return 2 * ans[0][0] + ans[1][0];
    }

    public static int[][] multi(int[][] m, int p) {
        int[][] ans = new int[m.length][m[0].length];
        for (int i = 0; i < ans.length; i++) {
            ans[i][i] = 1;
        }
        int[][] t = m;
        for (; p != 0; p >>= 1) {
            if((p & 1) == 1){
                ans = matrixPower(t,ans);
            }
            t = matrixPower(t,t);
        }
        return ans;
    }

    public static int[][] matrixPower(int[][] a, int[][] b){
        int m = a.length;
        int n = b[0].length;
        int k = a[0].length;
        int[][] ans = new int[m][n];
        for(int i = 0; i < m; i ++){
            for(int j = 0; j < n; j ++){
                for(int c = 0; c < k; c ++){
                    ans[i][j] += a[i][c]*b[c][j];
                }
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        for (int i = 0; i != 20; i++) {
            System.out.println(getNum1(i));
            System.out.println(getNum2(i));
            System.out.println(getNum3(i));
            System.out.println("===================");
        }
    }
}
