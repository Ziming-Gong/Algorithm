package WeeklyPractice.Auguest31;

public class test {
    public static long maxSum2(int[] arr) {
        int n = arr.length;
        // 只放下标，只要有下标，arr可以拿到值
        int[] stack = new int[n];
        int size = 0;
        long[] dp = new long[n];
        long ans = 0;
        for (int i = 0; i < n; i++) {
            // i -> arr[i] 依次把收益！得到！
            int curVal = arr[i];
            int curIdx = i;
            //        20
            //        17
            while (curVal > 0 && size > 0) {
                //  100
                //  16
                int leftIdx = stack[size - 1];
                int leftVal = arr[leftIdx];
                if (leftVal >= curVal) {
                    size--;
                } else {
                    // leftVal < curVal
                    //       8     20
                    //      15     17
                    int idxDiff = curIdx - leftIdx;
                    int valDiff = curVal - leftVal;

                    //  12           2
                    //             8  19 20
                    //            15  16 17
                    if (valDiff >= idxDiff) {
                        dp[i] += sum(curVal, idxDiff) + dp[leftIdx];
                        curVal = 0;
                        curIdx = 0;
                        break;
                    } else {
                        //   18          20
                        //   13 14 15 16 17
                        //      17 18 19 20
                        //   16
                        dp[i] += sum(curVal, idxDiff);
                        //   16
                        //   13
                        curVal -= idxDiff;
                        curIdx = leftIdx;
                        size--;
                    }
                }
            }
            if (curVal > 0) {
                dp[i] += sum(curVal, curIdx + 1);
            }
            stack[size++] = i;
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    public static long sum(int max, int n) {
        n = Math.min(max, n);
        return (((long) max * 2 - n + 1) * n) / 2;
    }
}
