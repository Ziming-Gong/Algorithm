package WeeklyPractice.June15;

import basicAlgo.mergesorted.ans;

public class LC2281wizard {
    public int MOD = 1000000007;

    public int totalStrength(int[] arr) {
        int N = arr.length;
        long preSum = arr[0];
        long[] sumSum = new long[N];
        sumSum[0] = arr[0];
        for (int i = 1; i < N; i++) {
            preSum += arr[i];
            sumSum[i] = (sumSum[i - 1] + preSum) % MOD;
        }
        int[] stack = new int[N];
        int size = 0;
        long ans = 0;
        for (int i = 0; i < N; i++) {
            while (size > 0 && arr[stack[size - 1]] >= arr[i]) {
                int m = stack[--size];
                int l = size <= 0 ? -1 : stack[size - 1];
//                ans += f(l, arr[m], i, m, sumSum);
                ans += magicSum(arr, sumSum, l, m, i);
                ans %= MOD;

            }
            stack[size++] = i;
        }
        while (size > 0) {
//            ans += f(l, arr[stack[size - 1]], N, stack[--size], sumSum);
            int m = stack[--size];
            int l = size <= 0 ? -1 : stack[size - 1];

            ans += magicSum(arr, sumSum, l, m, N);
            ans %= MOD;
        }
        return (int) ans;
    }


    public long magicSum(int[] arr, long[] sumSum, int l, int m, int r) {
        long left = (long) (m - l) * (sumSum[r - 1] - (m - 1 >= 0 ? sumSum[m - 1] : 0) + MOD) % MOD;
        long right = (long) (r - m) * ((m - 1 >= 0 ? sumSum[m - 1] : 0) - (l - 1 >= 0 ? sumSum[l - 1] : 0) + MOD) % MOD;
        return (long) arr[m] * ((left - right + MOD) % MOD);
    }
}
