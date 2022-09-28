package Questions.code_17;

import com.sun.org.apache.xpath.internal.operations.Mod;

import java.util.HashMap;

public class LC940DistinctSubsequencesII {
    public int mod = (int) 1e9 + 7;
    public int distinctSubseqII(String s) {
        int all = 1;
        HashMap<Character, Integer> map = new HashMap<>();
        char[] str =  s.toCharArray();
        for(int i = 0; i < str.length; i ++){
            int curAll = all;
            int newAll = all;
            curAll = (curAll + newAll) % mod;
            all = (curAll - (map.containsKey(str[i]) ? map.get(str[i]) : 0) + mod) % mod;
            map.put(str[i], curAll);

        }
        return all - 1;
    }

}
