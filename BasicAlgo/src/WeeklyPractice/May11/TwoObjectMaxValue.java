package WeeklyPractice.May11;


// 给定N件物品，每个物品有重量(w[i])、有价值(v[i])
// 只能最多选两件商品，重量不超过bag，返回价值最大能是多少？
// N <= 10^5, w[i] <= 10^5, v[i] <= 10^5, bag <= 10^5
// 本题的关键点：什么数据范围都很大，唯独只需要最多选两件商品，这个可以利用一下


import basicAlgo.mergesorted.ans;

import java.io.PipedWriter;
import java.util.Arrays;

public class TwoObjectMaxValue {
    public static int max2(int[] w, int[] v, int bag) {
        int n = w.length;
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = w[i];
            arr[i][1] = v[i];
        }
        Arrays.sort(arr, (a, b) -> a[0] - b[0]);
        int ans = 0;
        RMQ rmq = new RMQ(arr);
        for (int i = 0, j = 1; i < n && arr[i][0] <= bag; i++, j++) {
            int restW = bag - arr[i][0];
            int right = findRight(arr, restW) + 1;
            int rest = 0;
            if (right < j) {
                rest = rmq.max(1, right);
            } else if (right == j) {
                rest = rmq.max(1, right - 1);
            } else {
                rest = Math.max(rmq.max(1, j - 1), rmq.max(j + 1, right));
            }
            ans = Math.max(ans, arr[i][1] + rest);
        }
        return ans;
    }


    public static class RMQ {
        private int[][] m;

        public RMQ(int[][] arr) {
            int n = arr.length;
            int k = power(n);
            m = new int[n + 1][k + 1];
            for (int i = 1; i <= n; i++) {
                m[i][0] = arr[i - 1][1];
            }
            for (int power = 1; (1 << power) <= n; power++) {
                for (int index = 1; index + (1 << power) - 1 <= n; index++) { // -1?
                    m[index][power] = Math.max(m[index][power - 1],
                            m[index + (1 << (power - 1))][power - 1]);
                }
            }
        }

        public int max(int left, int right) {
            if (left > right) {
                return 0;
            }
            int k = power(right - left + 1);
            return Math.max(m[left][k], m[right - (1 << k) + 1][k]);
        }
    }

    public static int power(int n) {
        int ans = 0;
        while ((1 << ans) <= (n >> 1)) {
            ans++;
        }
        return ans;
    }

    public static int findRight(int[][] matrix, int rest) {
        int left = 0;
        int right = matrix.length - 1;
        int mid = 0;
        int ans = -1;
        while (left <= right) {
            mid = (right + left) / 2;
            if (matrix[mid][0] <= rest) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }

    public static int max1(int[] w, int[] v, int bag) {
        return process1(w, v, 0, 2, bag);
    }

    public static int process1(int[] w, int[] v, int index, int restNumber, int restWeight) {
        if (restNumber < 0 || restWeight < 0) {
            return -1;
        }
        if (index == w.length) {
            return 0;
        }
        int p1 = process1(w, v, index + 1, restNumber, restWeight);
        int p2 = -1;
        int next = process1(w, v, index + 1, restNumber - 1, restWeight - w[index]);
        if (next != -1) {
            p2 = v[index] + next;
        }
        return Math.max(p1, p2);
    }


    // 为了测试
    public static int[] randomArray(int n, int v) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = (int) (Math.random() * v);
        }
        return arr;
    }

    // 为了测试
    public static void main(String[] args) {
        int N = 12;
        int V = 20;
        int testTimes = 5000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            int n = (int) (Math.random() * N) + 1;
            int[] w = randomArray(n, V);
            int[] v = randomArray(n, V);
            int bag = (int) (Math.random() * V * 3);
            int ans1 = max1(w, v, bag);
            int ans2 = max2(w, v, bag);
            if (ans1 != ans2) {
                System.out.println("OOPS!");
                break;
            }
        }
        System.out.println("测试结束");
    }
}

















