package WeeklyPractice.July20;

import java.util.HashMap;
import java.util.Map.Entry;

public class test {
    public static int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int i = map.size();
        int[][] arr = new int[i][2];
        for (Entry<Integer, Integer> entry : map.entrySet()) {
            arr[--i][0] = entry.getKey();
            arr[i][1] = entry.getValue();
        }
        moreLess(arr, 0, arr.length - 1, k);
        int[] ans = new int[k];
        for (; i < k; i++) {
            ans[i] = arr[i][0];
        }
        return ans;
    }

    public static void moreLess(int[][] arr, int L, int R, int k) {
        if (k == R - L + 1) {
            return;
        }
        swap(arr, R, L + (int) (Math.random() * (R - L + 1)));
        int pivot = partition(arr, L, R);
        if (pivot - L == k) {
            return;
        } else if (pivot - L > k) {
            moreLess(arr, L, pivot - 1, k);
        } else {
            moreLess(arr, pivot, R, k - pivot + L);
        }
    }

    public static int partition(int[][] arr, int l, int r) {
        int left = l - 1;
        int index = l;
        while (index < r) {
            if (arr[index][1] <= arr[r][1]) {
                index++;
            } else {
                swap(arr, ++left, index++);
            }
        }
        swap(arr, ++left, r);
        return left;
    }

    public static void swap(int[][] arr, int i, int j) {
        int[] tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
