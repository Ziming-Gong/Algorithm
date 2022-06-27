package Contest.June4;

import WeeklyPractice.May18.MaxNumUnderLimit;
import sun.misc.PostVMInitHook;

public class MaximumScoreOfSplicedArray {
    public static int maximumsSplicedArray(int[] nums1, int[] nums2) {
        int N = nums1.length;
        int[] help = new int[N];
        int sum1 = 0;
        int sum2 = 0;
        for (int i = 0; i < N; i++) {
            help[i] = nums1[i] - nums2[i];
            sum1 += nums1[i];
            sum2 += nums2[i];
        }
        int preP = 0;
        int preN = 9;
        int maxP = 0;
        int maxN = 0;
        for (int i = 0; i < N; i++) {
            preP += help[i];
            preN += help[i];
            if (preP < 0) {
                preP = 0;
            }
            if (preN > 0) {
                preN = 0;
            }
            maxP = Math.max(maxP, preP);
            maxN = Math.min(maxN, preN);
        }
//        int ans = Math.max(max1, max2);
        int ans = Math.max(sum2 + maxP, sum1 + Math.abs(maxN));
        return ans;
    }

    public static int maximumsSplicedArray1(int[] nums1, int[] nums2) {
        int N = nums1.length;
        int[] preSum1 = new int[N + 1];
        int[] preSum2 = new int[N + 1];
        preSum1[1] = nums1[0];
        preSum2[1] = nums2[0];
        for (int i = 1; i <= N; i++) {
            preSum1[i] = preSum1[i - 1] + nums1[i - 1];
            preSum2[i] = preSum2[i - 1] + nums2[i - 1];
        }
        int max = preSum1[N];
        int p1 = preSum1[N];
        int p2 = preSum2[N];
        for (int l = 0; l <= N; l++) {
            for (int r = l; r <= N; r++) {
                int m1 = preSum1[r] - (l == 0 ? 0 : preSum1[l - 1]);
                int m2 = preSum2[r] - (l == 0 ? 0 : preSum2[l - 1]);
                max = Math.max(max, Math.max(p1 - m1 + m2, p2 - m2 + m1));
            }
        }
        return max;
    }

    public static int[] generateRandom(int maxValue, int N) {
        int[] ans = new int[N];
        for (int i = 0; i < N; i++) {
            ans[i] = (int) (Math.random() * maxValue) + 1;
        }
        return ans;
    }

    public static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.printf(arr[i] + ", ");
        }
    }

    public static void main(String[] args) {
        int[] a1 = {3, 5, 4, 8, 3, 6, 1, 3, 7, 4};
        int[] a2 = {5, 1, 5, 1, 4, 9, 9, 9, 9, 10};
        System.out.println(maximumsSplicedArray(a1, a2));

        int maxValue = 10;
        int maxLen = 10;
        int testTime = 10000;
        System.out.println("start");
        for (int i = 1; i <= testTime; i++) {
            int N = (int) (Math.random() * maxLen) + 1;
            int[] arr1 = generateRandom(maxValue, N);
            int[] arr2 = generateRandom(maxValue, N);
            int ans1 = maximumsSplicedArray1(arr1, arr2);
            int ans2 = maximumsSplicedArray(arr1, arr2);
            if (ans1 != ans2) {
                print(arr1);
                System.out.println();
                print(arr2);
                System.out.println();
                System.out.println("oops");
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }

        System.out.println("end");

    }
    //在以
}
