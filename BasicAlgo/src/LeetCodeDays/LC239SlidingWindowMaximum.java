package LeetCodeDays;

import java.util.*;

public class LC239SlidingWindowMaximum {
    public int[] maxSlidingWindow(int[] arr, int k) {
        int n = arr.length;
        Deque<Integer> list = new LinkedList<>();
        for(int i = 0; i < Math.min(k, n); i ++){
            while(list.size() > 0 && arr[list.peekLast()] < arr[i]){
                list.pollLast();
            }
            list.addLast(i);
        }
        if(n <= k){
            return new int[] {arr[list.peekFirst()]};
        }
        int[] ans = new int[n - k + 1];
        int index = 0;
        ans[index ++] = arr[list.peekFirst()];
        for(int i = k; i < n; i ++){
            while(list.size() > 0 && arr[list.peekLast()] < arr[i]){
                list.pollLast();
            }
            if(list.peekFirst() == i - k){
                list.pollFirst();
            }
            list.addLast(i);
            ans[index ++] = arr[list.peekFirst()];
        }
        return ans;


    }
}

