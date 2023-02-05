package LeetCodeDays;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

public class LC2131LongestPalindromebyConcatenatingTwoLetterWords {
    public int longestPalindrome(String[] words) {

        HashMap<String, Integer> map = new HashMap<>();
        for(int i = 0; i < words.length; i ++){
            map.put(words[i], map.getOrDefault(words[i], 0) + 1);
        }
        HashSet<String> set = new HashSet<>();
        int ans = 0;
        for(int i = 0; i < words.length; i ++){
            if(!set.contains(words[i])){
                char[] pair = new char[2];
                pair[0] = words[i].charAt(1);
                pair[1] = words[i].charAt(0);
                ans += 2 * (Math.min(map.get(words[i]), map.getOrDefault(String.valueOf(pair), 0)));
                set.add(String.valueOf(pair));
                set.add(words[i]);
            }
        }
        Stack<Integer> sta = new Stack<>();

        return ans;

    }
}
