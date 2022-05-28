package basicAlgo.QuadrangleInequality;

import javax.swing.plaf.basic.BasicTextAreaUI;
import java.io.PipedWriter;

public class BestSplitForAll {
    public static int bestSplit1(int[] arr) {
        int sum = 0;
        for (int i : arr) {
            sum += i;
        }
        int ans = 0;
        int left = 0;
        for (int i = 0; i < arr.length; i++) {
            left += arr[i];
            ans = Math.max(ans, Math.min(left, sum - left));
        }
        return ans;
    }

    public static int sum(int[] arr, int l, int r) {
        int ans = 0;
        for (; l <= r; l++) {
            ans += arr[l];
        }
        return ans;
    }

    public static int bestSplit2(int[] arr) {
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            int left = sum(arr, 0, i);
            int right = sum(arr, i + 1, arr.length - 1);
            ans = Math.max(ans, Math.min(left, right));
        }
        return ans;
    }


    public static int bestSplit3(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int N = arr.length;
        int ans = 0;
        for (int s = 0; s < N - 1; s++) {
            int sumL = 0;
            for (int L = 0; L <= s; L++) {
                sumL += arr[L];
            }
            int sumR = 0;
            for (int R = s + 1; R < N; R++) {
                sumR += arr[R];
            }
            ans = Math.max(ans, Math.min(sumL, sumR));
        }
        return ans;
    }


    public static int[] generateArray(int maxValue, int maxLen) {
        int n = (int) (Math.random() * maxLen);
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = (int) (Math.random() * maxValue);
        }
        return ans;

    }

    public static void main(String[] args) {
//        int[] arr = {1,2,3,4};
//        System.out.println(sum(arr,0,3));

        int testTime = 1000000;
        int maxValue = 30;
        int maxLen = 20;
        System.out.println("test begin");
        for (int i = 1; i <= testTime; i++) {
            int[] arr = generateArray(maxValue, maxLen);
            int ans1 = bestSplit1(arr);
            int ans2 = bestSplit2(arr);
            int ans3 = bestSplit3(arr);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("oops!");
                break;
            }
//            System.out.println("1");
        }
        System.out.println("test end");

    }
}
