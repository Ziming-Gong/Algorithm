package Questions.code_03;

import java.util.ArrayList;
import java.util.HashMap;

public class FreedomTrails {
    public int findRotateSteps(String ring, String key) {
        HashMap<Character, ArrayList<Integer>> map = new HashMap<>();
        char[] strRing = ring.toCharArray();
        for(int i = 0; i < strRing.length; i ++){
            if(!map.containsKey(strRing[i])){
                map.put(strRing[i], new ArrayList<>());
            }
            map.get(strRing[i]).add(i);
        }
        char[] strKey = key.toCharArray();
        int[][] dp = new int[strKey.length + 1][strRing.length];
        for(int i = 0; i < dp.length; i ++){
            for(int j = 0; j < dp[0].length; j ++){
                dp[i][j] = -1;
            }
        }
        return process(map, strRing, strKey,0,0,dp);
    }
    public int process(HashMap<Character, ArrayList<Integer>> map, char[] strRing, char[] strKey, int index, int pre, int[][] dp){
        if(dp[index][pre] != -1){
            return dp[index][pre];
        }
        int ans = 0;
        if(index == strKey.length){
            ans = 0;
        }else{
            int sum = Integer.MAX_VALUE;
            for(int i : map.get(strKey[index])){
                sum = Math.min(sum, process(map, strRing,strKey, index + 1, i,dp) + f(pre,i, strRing.length) + 1);
            }
            ans = sum;
        }
        dp[index][pre] = ans;
        return ans;


    }
    public int f(int pre, int cur, int N){
        return Math.min(Math.abs(Math.min(pre, cur) - Math.max(cur, pre) + N), Math.abs(pre - cur));
    }
}
