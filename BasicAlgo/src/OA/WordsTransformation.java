package OA;

import java.util.ArrayList;
import java.util.List;

public class WordsTransformation {
    public static class wordsTransformation {

        public static int solve(String s, int t) {
            int[] dp = new int[26];
            for (char c : s.toCharArray()) {
                dp[c - 'a']++;
            }
            for (int i = 1; i <= t; i++) {
                int old = dp[0];
                int _new = dp[0];
                for (int j = 1; j < 26; j++) {
                    _new = dp[j];
                    dp[j] = old;
                    old = _new;
                }
                dp[0] = _new;
                dp[1] += _new;
            }
            int ans = 0;
            for (int i : dp) {
                ans += i;
            }
            return ans;

        }

        public static int Right(String a, int t) {
            List<Character> list = new ArrayList<>();
            for (char c : a.toCharArray()) {
                list.add(c);
            }
            for (int i = 1; i <= t; i++) {
                List<Character> temp = new ArrayList<>();
                for (int j = 0; j < list.size(); j++) {
                    if (list.get(j) == 'z') {
                        temp.add('a');
                        temp.add('b');
                    } else {
                        temp.add((char) (list.get(j) + 1));
                    }
                }
                list = temp;
            }
            return list.size();

        }

        public static String generateString(int maxLen) {
            StringBuilder sb = new StringBuilder();
            int len = (int) (Math.random() * maxLen) + 1;
            for (int i = 0; i < len; i++) {
                sb.append((char) ('a' + ((int) (Math.random() * 26))));
            }
            return sb.toString();
        }

        public static boolean isString(String str){
            for(int i = 0; i < str.length(); i ++){
                if(str.charAt(i) < 'a' && str.charAt(i) > 'z'){
                    return false;
                }
            }
            return true;
        }

        public static void main(String[] args) {
            int testTime = 100000;
            int maxLen = (int) 1e3;
            int maxT = (int) 1e3;
            System.out.println("test Begin");
            for (int i = 1; i <= testTime; i++) {
                String cur = generateString(maxLen);
                int t = (int) (Math.random() * maxT);
                int ans1 = solve(cur, t);
                int ans2 = Right(cur, t);
                if(ans1 != ans2){
                    System.out.println("oops");
                    break;
                }
            }
            System.out.println("test ENd");
        }
    }
}
