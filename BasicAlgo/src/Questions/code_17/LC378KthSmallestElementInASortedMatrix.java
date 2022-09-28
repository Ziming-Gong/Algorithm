package Questions.code_17;

import basicAlgo.mergesorted.ans;
import sun.lwawt.LWKeyboardFocusManagerPeer;

public class LC378KthSmallestElementInASortedMatrix {
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int m = matrix[0].length;
        int left = matrix[0][0];
        int right = matrix[n - 1][m - 1];

        int ans = 0;
        int count = 0;

        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            int[] cur = binarySearch(matrix, mid);
            if (cur[0] >= k) {
                right = mid - 1;
                ans = cur[1];
            } else {
                left = mid + 1;
            }
        }
        return ans;


    }

    public int[] binarySearch(int[][] matrix, int target) {
        int i = 0;
        int j = matrix[0].length - 1;
        int nums = 0;
        int max = Integer.MIN_VALUE;
        while (i < matrix.length && j >= 0) {
            while (j >= 0 && matrix[i][j] > target) {
                j--;
            }
            nums += j + 1;
            if(j < 0){
                break;
            }
            max = Math.max(max, matrix[i][j]);
            i++;
        }


        return new int[]{nums, max};
    }
}
