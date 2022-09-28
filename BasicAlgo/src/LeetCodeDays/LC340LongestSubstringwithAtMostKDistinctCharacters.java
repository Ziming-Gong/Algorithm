package LeetCodeDays;

import java.util.HashSet;

public class LC340LongestSubstringwithAtMostKDistinctCharacters {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if(k == 0){
            return 0;
        }
        if(s.length() <= k){
            return s.length();
        }
        int[] map = new int[256];
        char[] str = s.toCharArray();
        int L = 0, R = 1, kind = 1, n = str.length;
        HashSet<Character> set = new HashSet<>();
        map[str[0]] ++;
        set.add(str[0]);
        int ans = 0;
        while(R < n){
            while(R < n && kind <= k){
                if(set.contains(str[R])){
                    map[str[R]] ++;
                    R ++;
                }else{
                    map[str[R]] ++;
                    set.add(str[R]);
                    R ++;
                    kind ++;
                }
            }
            ans = Math.max(ans, R - L + 1);
            while(L < R - 1 && kind > k){
                map[str[L]] --;
                if(map[str[L]] == 0){
                    set.remove(str[L]);
                    kind --;
                }
            }
        }
        return ans;

    }
}
