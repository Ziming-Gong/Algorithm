package LeetCodeDays;

import WeeklyPractice.April02.PerfectPairNumber;
import oracle.jrockit.jfr.openmbean.EventSettingType;

public class LC2104SumofSubarrayRanges {
    public static long subArrayRanges(int[] arr) {

        int n = arr.length;
        int[] help = new int[n];
        int size = 0;


        int[] rightLarger = new int[n];
        help[size++] = 0;


        //rightLarger
        //右边比这个数大的第一个下标
        for (int i = 1; i < arr.length; i++) {
            while (size > 0 && arr[help[size - 1]] < arr[i]) {
                rightLarger[help[--size]] = i;
            }
            help[size++] = i;
        }
        while (size > 0) {
            rightLarger[help[--size]] = -1;
        }


        //rightSmaller
        //右边比这个小的第一个下标
        help[0] = 0;
        size = 1;
        int[] rightSmaller = new int[n];
        for (int i = 1; i < arr.length; i++) {
            while (size > 0 && arr[help[size - 1]] > arr[i]) {
                rightSmaller[help[--size]] = i;
            }
            help[size++] = i;
        }
        while (size > 0) {
            rightSmaller[help[--size]] = -1;
        }

        //leftLarge
        //左边比这个大的第一个下标
        help[size++] = n - 1;
        int[] leftLarger = new int[n];
        for (int i = n - 2; i >= 0; i--) {
            while (size > 0 && arr[help[size - 1]] <= arr[i]) {
                leftLarger[help[--size]] = i;
            }
            help[size++] = i;
        }
        while (size > 0) {
            leftLarger[help[--size]] = -1;
        }

        //leftSmaller
        //左边比这个小的第一个下标
        help[size++] = n - 1;
        int[] leftSmaller = new int[n];
        for (int i = n - 2; i >= 0; i--) {
            while (size > 0 && arr[help[size - 1]] >= arr[i]) {
                leftSmaller[help[--size]] = i;
            }
            help[size++] = i;
        }
        while (size > 0) {
            leftSmaller[help[--size]] = -1;
        }


        long ans = 0;
        long large = 0;
        long small = 0;
        for (int i = 0; i < n; i++) {
            //以它为最小
            int leftMax = Math.max(0, leftSmaller[i]);
            long rightMax = rightSmaller[i] == -1 ? n : rightSmaller[i];
            long leftNumMax = i - leftSmaller[i];
            long rightNumMax = rightMax - i;
            ans -= leftNumMax * rightNumMax * arr[i];

            //以它为最大
            int leftMin = Math.max(0, leftLarger[i]);
            long rightMin = rightLarger[i] == -1 ? n : rightLarger[i];
            long leftNumMin = i - leftLarger[i];
            long rightNumMin = rightMin - i;
            ans += leftNumMin * rightNumMin * arr[i];

        }
        return ans;
    }

    public static long right(int[] arr) {
        long ans = 0;
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            int max = arr[i];
            int min = arr[i];
            for (int j = i; j < n; j++) {
                max = Math.max(max, arr[j]);
                min = Math.min(min, arr[j]);
                ans += max - min;
            }
        }
        return ans;
    }


    public static int[] generateArr(int len, int val) {
        int[] ans = new int[(int) (Math.random() * len) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (Math.random() < 0.5 ? -1 : 1) * ((int) (Math.random() * val) + 1);
        }
        return ans;
    }

    public static void main(String[] args) {

        int testTime = 100000;
        int maxLen = 1000;
        int maxVal = (int) 1e9;
        for (int i = 1; i <= testTime; i++) {
            int[] arr = generateArr(maxLen, maxVal);
            long ans1 = subArrayRanges(arr);
            long ans2 = right(arr);
            if (ans1 != ans2) {
                print(arr);
                System.out.println();
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("oops");
                System.out.println(i);
                break;
            }
        }
        System.out.println("test end");
    }

    public static void print(int[] arr) {
        for (int i : arr) {
            System.out.printf(i + ", ");
        }
    }


}
