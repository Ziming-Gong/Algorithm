package LeetCodeDays;

import basicAlgo.mergesorted.ans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;
import java.util.TreeSet;

public class LC2035PartitionArrayIntoTwoArraystoMinimizeSumDifference {


    public int minimumDifference(int[] arr) {
        int n = arr.length;

        int total = 0;
        for (int i : arr) {
            total += arr[i];
        }


        ArrayList<TreeSet<Integer>> left = new ArrayList<>();
        ArrayList<TreeSet<Integer>> right = new ArrayList<>();

        for (int i = 0; i <= n / 2; i++) {
            left.add(new TreeSet<>());
            right.add(new TreeSet<>());
        }

        process(left, arr, 0, n / 2, 0, 0);
        process(right, arr, n / 2, n, 0, 0);
        n /= 2;
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n / 2; i++) {
            for (int cur : left.get(i)) {
                int best = (total / 2) - cur;
                Integer floor = right.get(n - i - 1).floor(best);
                Integer ceiling = right.get(n - i - i).ceiling(best);
                if (floor != null) {
                    ans = Math.min(ans, floor + cur - (total - floor - cur));
                }
                if (ceiling != null) {
                    ans = Math.min(ans, ceiling + cur - (total - ceiling - cur));
                }
            }
        }
        return ans;


    }

    public void process(ArrayList<TreeSet<Integer>> list, int[] arr, int cur, int end, int count, int sum) {
        if (cur == end) {
            list.get(count).add(sum);
        } else {
            process(list, arr, cur + 1, end, count + 1, sum + arr[cur]);
            process(list, arr, cur + 1, end, count, sum);
        }
    }
}
