package WeeklyPractice.FebSe;

import java.util.HashMap;

// leet code: https://leetcode.com/problems/minimum-number-of-days-to-eat-n-oranges/
public class MinimumNumberOfDaysToEatNOranges {

    public static HashMap<Integer,Integer> dp = new HashMap<>();
    public int minDays(int n) {
        if(n <= 1){
            return n;
        }
        if(dp.containsKey(n)){
            return dp.get(n);
        }
        int dividedByThree = n%3 +1 + minDays(n/3);
        int dividedByTwo = n%2 + 1 + minDays(n/2);
        int ans = Math.min(dividedByThree, dividedByTwo);
        dp.put(n, ans);
        return ans;
    }
}

