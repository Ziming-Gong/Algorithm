package Questions.code_2;

//leetcode 581 https://leetcode.com/problems/shortest-unsorted-continuous-subarray/
public class MinLenForSort {
    public static int findUnsortedSubarray(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        int max = Integer.MIN_VALUE;
        int lastMaxIndex = 0;
        for (int i = 0; i < N; i++) {
            if (max <= arr[i]) {
                max = arr[i];
            } else {
                lastMaxIndex = i;

            }
        }
        int min = Integer.MAX_VALUE;
        int lastMinIndex = 0;
        for (int i = N - 1; i >= 0; i++) {
            if (min >= arr[i]) {
                min = arr[i];
            } else {
                lastMinIndex = i;
            }
        }
        return lastMaxIndex - lastMinIndex + 1;
    }
}
