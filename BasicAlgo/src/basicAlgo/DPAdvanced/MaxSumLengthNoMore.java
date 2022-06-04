package basicAlgo.DPAdvanced;


import java.util.LinkedList;

// 给定一个数组arr，和一个正数M
// 返回在子数组长度不大于M的情况下，最大的子数组累加和
public class MaxSumLengthNoMore {
    public static int test(int[] arr, int M) {
        if (arr == null || arr.length == 0 || M < 1) {
            return 0;
        }
        int N = arr.length;
        int max = Integer.MIN_VALUE;
        for (int L = 0; L < N; L++) {
            int sum = 0;
            for (int R = L; R < N; R++) {
                if (R - L + 1 > M) {
                    break;
                }
                sum += arr[R];
                max = Math.max(max, sum);
            }
        }
        return max;
    }


    public static int maxSum(int[] arr, int M) {
        if (arr == null || arr.length == 0 || M < 1) {
            return 0;
        }
        int N = arr.length;
        int[] sum = new int[N];
        sum[0] = arr[0];
        for (int i = 1; i < N; i++) {
            sum[i] = sum[i - 1] + arr[i];
        }
        LinkedList<Integer> maxQ = new LinkedList<>();
        int R = 0;
        int end = Math.min(N, M);
        for (; R < end; R++) {
            while (!maxQ.isEmpty() && sum[maxQ.peekLast()] <= sum[R]) {
                maxQ.pollLast();
            }
            maxQ.addLast(R);
        }
        int ans = sum[maxQ.peekFirst()];
        int L = 0;
        for (; R < N; L++, R++) {
            if (maxQ.peekFirst() == L) {
                maxQ.pollFirst();
            }
            while (!maxQ.isEmpty() && sum[maxQ.peekLast()] <= sum[R]) {
                maxQ.pollLast();
            }
            maxQ.addLast(R);
            ans = Math.max(ans, sum[maxQ.peekFirst()] - sum[L]);
        }
        for (; L < N - 1; L++) {
            if(maxQ.peekFirst() == L){
                maxQ.pollFirst();
            }
            ans = Math.max(ans, sum[maxQ.peekFirst()] - sum[L]);
        }
        return ans;
    }

    // 用作测试
    public static int[] randomArray(int len, int max) {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * max) - (int) (Math.random() * max);
        }
        return arr;
    }

    // 用作测试
    public static void main(String[] args) {
        int maxN = 50;
        int maxValue = 100;
        int testTime = 1000000;
        System.out.println("测试开始");
        int count = 0;
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * maxN);
            int M = (int) (Math.random() * maxN);
            int[] arr = randomArray(N, maxValue);
            int ans1 = test(arr, M);
            int ans2 = maxSum(arr, M);
            if (ans1 != ans2) {
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("Oops!");
                System.out.println(count);
                break;
            }
            count++;
        }
        System.out.println("测试结束");
    }
}
