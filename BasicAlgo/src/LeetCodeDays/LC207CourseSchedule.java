package LeetCodeDays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class LC207CourseSchedule {
    public boolean canFinish(int n, int[][] pre) {
        HashSet<Integer> set = new HashSet<>();
        for(int i = 0; i < n; i ++){
            set.add(i);
        }
        HashMap<Integer, HashSet<Integer>> lock = new HashMap<>();
        HashMap<Integer, HashSet<Integer>> free = new HashMap<>();
        for(int[] cur : pre){
            set.remove(cur[0]);
            if(!lock.containsKey(cur[0])){
                lock.put(cur[0], new HashSet<>());
            }
            lock.get(cur[0]).add(cur[1]);
            if(lock.containsKey(cur[1]) && lock.get(cur[1]).contains(cur[0])){
                return false;
            }
            if(!free.containsKey(cur[1])){
                free.put(cur[1], new HashSet<>());
            }
            free.get(cur[1]).add(cur[0]);
        }
        if(set.isEmpty()){
            return false;
        }
        int[] stack = new int[n];
        int index = 0;
        Arrays.fill(stack, -1);
        for(int i : set){
            stack[index ++] = i;
        }
        for(int i = 0; i < n; i ++){
            if(stack[i] == -1){
                return false;
            }
            int cur = stack[i];
            HashSet<Integer> curSet = free.get(cur);
            if(curSet.isEmpty()){
                continue;
            }
            for(int c : curSet){
                if(!lock.containsKey(c)){
                    continue;
                }
                lock.get(c).remove(i);
                if(lock.get(c).isEmpty()){
                    stack[index ++] = c;
                    lock.remove(c);
                }
            }
        }
        return true;

    }
}
