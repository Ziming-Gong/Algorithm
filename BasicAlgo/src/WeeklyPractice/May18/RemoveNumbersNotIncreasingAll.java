package WeeklyPractice.May18;

// 来自京东
// 4.2笔试
// 给定一个数组arr，长度为N，arr中所有的值都在1~K范围上
// 你可以删除数字，目的是让arr的最长递增子序列长度小于K
// 返回至少删除几个数字能达到目的
// N <= 10^4，K <= 10^2
public class RemoveNumbersNotIncreasingAll {
    public static int minRemove1(int[] arr, int k) {
        return process(arr, k, 0, 0);
    }

    //arr[index ---- n - 1]上做决定
    //
    public static int process(int[] arr, int k, int index, int length) {
        if (length == k) {
            return Integer.MAX_VALUE;
        }
        if (index == arr.length) {
            return 0;
        }
        int cur = arr[index];
        if (cur <= length || cur > length + 1) {
            return process(arr, k, index + 1, length);
        }
        // cur == length + 1
        // eg. 1,2,3     4
        //retain cur
        int p1 = process(arr, k, index + 1, length + 1);
        //delete cur
        int p2 = Integer.MAX_VALUE;
        int next = process(arr, k, index + 1, length);
        if (next != Integer.MAX_VALUE) {
            p2 = 1 + next;
        }
        return Math.min(p1, p2);
    }

    public static int minRemove2(int[] arr, int k) {
        return process1(arr, 0, new int[arr.length], 0, k);
    }

    public static int process1(int[] arr, int index, int[] path, int size, int k) {
        if (index == arr.length) {
            return lengthOfLIS(path, size) < k ? (arr.length - size) : Integer.MAX_VALUE;
        } else {
            int p1 = process1(arr, index + 1, path, size, k);
            path[size] = arr[index];
            int p2 = process1(arr, index + 1, path, size + 1, k);
            return Math.min(p1, p2);
        }
    }

    public static int lengthOfLIS(int[] arr, int size) {
        if (size == 0) {
            return 0;
        }
        int[] ends = new int[size];
        ends[0] = arr[0];
        int right = 0;
        int l = 0;
        int r = 0;
        int m = 0;
        int max = 1;
        for (int i = 1; i < size; i++) {
            l = 0;
            r = right;
            while (l <= r) {
                m = (l + r) / 2;
                if (arr[i] > ends[m]) {
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }
            right = Math.max(right, l);
            ends[l] = arr[i];
            max = Math.max(max, l + 1);
        }
        return max;
    }

    // 为了验证
    public static int[] randomArray(int len, int k) {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * k) + 1;
        }
        return arr;
    }

    // 为了验证
    public static void main(String[] args) {
        int N = 15;
        int K = 6;
        int testTime = 10000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * N) + 1;
            int k = (int) (Math.random() * K) + 1;
            int[] arr = randomArray(len, k);
            int ans1 = minRemove1(arr, k);
            int ans2 = minRemove2(arr, k);
            if (ans1 != ans2) {
                System.out.println("出错了！");
                for (int num : arr) {
                    System.out.print(num + " ");
                }
                System.out.println();
                System.out.println("k : " + k);
                System.out.println("ans1 : " + ans1);
                System.out.println("ans2 : " + ans2);
                break;
            }
        }
        System.out.println("测试结束");
    }
}
