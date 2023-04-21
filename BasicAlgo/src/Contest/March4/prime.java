package Contest.March4;

import com.sun.xml.internal.ws.runtime.config.TubelineFeatureReader;
import jdk.management.resource.internal.inst.SocketOutputStreamRMHooks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

public class prime {
    public static List<Long> minOperations(int[] nums, int[] queries) {
        int n = nums.length;
        Arrays.sort(nums);
        long pre = 0;
        long[] preSum = new long[n];
        for (int i = 0; i < n; i++) {
            pre += nums[i];
            preSum[i] = pre;
        }
        List<Long> ans = new ArrayList<>();
        for (int query : queries) {
            int[] search = search(query, nums);
            long curAns = 0l;
            // for left part  (0 ~ [0] - 1)的数到 query
            if (search[0] != -1) {
                int leftNum;
                long leftSum;
                if (nums[search[0]] == query) {// from [0] - 1
                    leftSum = search[0] == 0 ? 0 : preSum[search[0] - 1];
                    leftNum = Math.max(0, search[0]);
                } else {
                    leftSum = preSum[search[0]];
                    leftNum = search[0] + 1;
                }

                curAns = ((long) query) * leftNum - leftSum;
            }
            if (search[1] != -1) {
                int rightNum;
                long rightSum;
                if (nums[search[1]] == query) { // from [1] + 1 ~ n - 1
                    rightNum = Math.max(0, n - 1 - search[1] - 1 + 1);
                    rightSum = preSum[n - 1] - preSum[search[1]];
                } else { //from [1] ~ n - 1
                    rightNum = n - 1 - search[1] + 1;
                    rightSum = search[1] == 0 ? preSum[n - 1] : preSum[n - 1] - preSum[search[1] - 1];
                }
                curAns += rightSum - ((long) query) * rightNum;
            }
            ans.add(curAns);
        }
        return ans;


    }

    public static int[] search(int num, int[] arr) {
        int l = 0;
        int r = arr.length - 1;
        int m;
        int left = -1;
        while (l <= r) {
            m = l + ((r - l) >> 1);
            if (arr[m] >= num) {
                r = m - 1;
                left = m;
            } else {
                l = m + 1;
            }
        }
        l = 0;
        r = arr.length - 1;
        int right = -1;
        while (l <= r) {
            m = l + ((r - l) >> 1);
            if (arr[m] <= num) {
                l = m + 1;
                right = m;
            } else {
                r = m - 1;
            }
        }
        return new int[]{right, left};

    }

    public static void main(String[] args) {
        // nums = [3,1,6,8], queries = [1,5]
        int[] nums = new int[]{3, 1, 6, 8};
        int[] queries = new int[]{1, 5};
        System.out.println(minOperations(nums, queries));
    }
}
