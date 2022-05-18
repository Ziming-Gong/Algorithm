package WeeklyPractice.May06;

import java.util.ArrayList;
import java.util.HashMap;

//https://leetcode-cn.com/problems/jump-game-iv/
public class JumMinSameValue {
    public int minJumps(int[] arr) {
        if(arr == null || arr.length <= 1){
            return 0;
        }
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        for(int i = 0; i < arr.length; i ++){
            if(!map.containsKey(arr[i])){
                map.put(arr[i], new ArrayList<>());
            }
            map.get(arr[i]).add(i);
        }
        int[] queue = new int[arr.length];
        int l = 0;
        int r = 1;
        int ans = 0;
        int index = 0;
        boolean[] visited = new boolean[arr.length];
        visited[0] = true;
        while(r != l){
            int temp = r;
            while(l < temp){
                int curIndex = queue[l++];
                if(curIndex == arr.length - 1){
                    return ans;
                }
                if(curIndex - 1 >= 0 && !visited[curIndex - 1]){
                    queue[r++] = curIndex - 1;
                    visited[curIndex - 1] = true;
                }
                if(curIndex + 1 < arr.length && !visited[curIndex + 1]){
                    queue[r++] = curIndex + 1;
                    visited[curIndex + 1] = true;
                }
                for(int next : map.get(arr[curIndex])){
                    if(visited[next] == false){
                        queue[r++] = next;
                        visited[next] = true;
                    }
                }
                map.get(arr[curIndex]).clear();
            }
            ans ++;
        }
        return -1;
    }
}
