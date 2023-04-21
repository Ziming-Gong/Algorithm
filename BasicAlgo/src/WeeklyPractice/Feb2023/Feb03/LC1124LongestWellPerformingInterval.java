package WeeklyPractice.Feb2023.Feb03;

import java.util.HashMap;

public class LC1124LongestWellPerformingInterval {
    public int longestWPI(int[] hours) {
        for(int i = 0; i < hours.length; i ++){
            hours[i] = hours[i] < 8 ? -1 : 1;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        map.put(0, -1);
        int sum = 0;
        for(int i = 0; i < hours.length; i ++){
            sum += hours[i];
            if(sum > 0){
                ans = Math.max(ans, i + 1);
            }else{
                if(map.containsKey(sum - 1)){
                    ans = Math.max(ans, i - map.get(sum - 1) + 1);
                }
            }
            map.put(sum, map.getOrDefault(sum, i));
        }

        return ans;

    }
}
