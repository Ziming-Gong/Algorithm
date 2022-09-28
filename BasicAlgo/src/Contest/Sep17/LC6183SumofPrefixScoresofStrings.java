package Contest.Sep17;

import sun.util.resources.in.CurrencyNames_in_ID;

import java.util.HashMap;

public class LC6183SumofPrefixScoresofStrings {
    public int[] sumPrefixScores(String[] words) {
        HashMap<String, Integer> map = new HashMap<>();
        for (String s : words) {
            StringBuilder sb = new StringBuilder();
            char[] str = s.toCharArray();
            for (int i = 0; i < str.length; i++) {
                sb.append(str[i]);
                if (!map.containsKey(sb.toString())) {
                    map.put(sb.toString(), 0);
                }
                map.put(sb.toString(), map.get(sb.toString()) + 1);
            }
        }
        int n = words.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            String s = words[i];
            char[] str = s.toCharArray();
            for (int j = 0; j < str.length; j++) {
                sb.append(str[j]);
                int score = map.get(sb.toString());
                ans[i] += score;
            }
        }
        return ans;
    }

    public int[] sumPrefixScores2(String[] words) {
        Node head = new Node();
        for(String word : words){
            build(head, word);
        }
        int n = words.length;
        int[] ans = new int[n];
        for(int i = 0; i < n; i ++){
            ans[i] = cal(head, words[i]);
        }
        return ans;

    }

    public int cal(Node head, String word){
        Node cur = head;
        int ans = 0;
        for(char c : word.toCharArray()){
            cur = cur.nexts[c - 'a'];
            ans += cur.val;
        }
        return ans;
    }

    public void build(Node head, String s){
        char[] str = s.toCharArray();
        Node cur = head;
        for(char c : str){
            if(cur.nexts[c - 'a'] == null){
                cur.nexts[c - 'a'] = new Node();
            }
            cur = cur.nexts[c - 'a'];
            cur.val ++;
        }
    }

    public class Node{
        public Node[] nexts = new Node[26];
        public int val = 0;
    }
}
