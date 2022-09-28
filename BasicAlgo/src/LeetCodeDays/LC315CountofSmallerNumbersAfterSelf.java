package LeetCodeDays;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.util.ArrayList;
import java.util.List;

public class LC315CountofSmallerNumbersAfterSelf {
    public List<Integer> countSmaller(int[] nums) {
        int N = nums.length;
        int[] arr = new int[N];
        int[] stack = new int[N];
        int index = 0;
        for (int i = 0; i < N; i++) {
            while (index > 0 && nums[stack[index - 1]] > nums[i]) {
                arr[stack[--index]] = i;
            }
            stack[index++] = i;
        }
        while (index > 0) {
            arr[stack[--index]] = -1;
        }
        for(int i = N - 1; i >= 0; i ++){
            if(arr[i] == -1){
                arr[i] = 0;
            }else{
                arr[i] = arr[arr[i]] + 1;
            }
        }
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < N; i ++){
            list.add(arr[i]);
        }
        return list;

    }
}
