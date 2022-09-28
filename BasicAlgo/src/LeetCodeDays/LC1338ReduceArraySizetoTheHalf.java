package LeetCodeDays;

import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Map.Entry;

public class LC1338ReduceArraySizetoTheHalf {
    public int minSetSize(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < arr.length; i ++){
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> (b[1] - a[1]));
        for(Entry<Integer, Integer> entry : map.entrySet()){
            queue.add(new int[]{entry.getKey(), entry.getValue()});
        }
        int ans = 0;
        int sum = 0;
        while(sum < (arr.length >> 1)){
            ans ++;
            sum += queue.poll()[1];
        }
        return ans;

    }
}
