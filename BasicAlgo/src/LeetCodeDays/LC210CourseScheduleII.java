package LeetCodeDays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class LC210CourseScheduleII {
    public int[] findOrder(int n, int[][] arr) {
        int[] ans = new int[n];
        Arrays.sort(arr, (a, b) -> a[1] - b[1]);
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            set.add(i);
        }
        HashMap<Integer, HashSet<Integer>> waitList = new HashMap<>();
        HashMap<Integer, HashSet<Integer>> baseCourse = new HashMap<>();
        for (int[] cur : arr) {
            set.remove(cur[0]);
            if (!waitList.containsKey(cur[0])) {
                waitList.put(cur[0], new HashSet<>());
            }
            if (!baseCourse.containsKey(cur[1])) {
                baseCourse.put(cur[1], new HashSet<>());
            }
            baseCourse.get(cur[1]).add(cur[0]);
            waitList.get(cur[0]).add(cur[1]);
        }
        if (set.isEmpty()) {
            return new int[0];
        }
        int index = 0;
        for (int i : set) {
            ans[index++] = i;
        }
        for (int i : set) {
            for (int num : baseCourse.get(i)) {
//                if(!waitList.containsKey(num)){
//                    continue;
//                }
                waitList.get(num).remove(i);
                if(!waitList.get(num).isEmpty()){
                    waitList.remove(num);
                    ans[index ++] = num;
                    set.add(num);
                }
                if(index == n){
                    break;
                }

            }
        }
        return index == n ? ans : new int[0];

    }
}
