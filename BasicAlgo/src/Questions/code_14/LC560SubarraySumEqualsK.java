package Questions.code_14;

import java.util.TreeMap;
import java.util.TreeSet;

public class LC560SubarraySumEqualsK {
    public int subarraySum(int[] arr, int k) {
        int N = arr.length;
        TreeMap<Integer, Integer> set = new TreeMap();
        set.put(0, 1);
        int preSum = 0;
        int ans = 0;
        for (int i = 0; i < N; i++) {
            preSum += arr[i];
            int target = k - preSum;
            Integer ceiling = set.ceilingKey(target);
            if (ceiling != null && ceiling == target){
                ans += set.get(target);
            }
            set.put(preSum, set.getOrDefault(preSum, 0) + 1);
        }
        return ans;

    }
}
