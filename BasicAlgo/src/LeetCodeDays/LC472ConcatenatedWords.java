package LeetCodeDays;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class LC472ConcatenatedWords {
    public static List<String> findAllConcatenatedWordsInADict(String[] words) {
        Node head = new Node();
        for(String word : words){
            build(head, word);
        }

        List<String> ans = new ArrayList<>();
        for(String word : words){
            if(dfs(head, word.toCharArray(), 0, new HashSet<>()) >= 2){
                ans.add(word);
            }
        }
        return ans;
    }

    public static int dfs(Node head, char[] str, int index, HashSet<String> set){
        if(index == str.length){
            return 0;
        }
        Node cur = head;
        int ans = 0;
        for(int i = index; i < str.length; i ++){
            if(cur.nexts[str[i] - 'a'] == null){
                return -1;
            }
            cur = cur.nexts[str[i] - 'a'];
            if(cur.isEnd){
                String string = cur.content;
                set.add(string);
                int nexts = dfs(head, str, i + 1, set);

                set.remove(string);
                if(nexts != -1){
                    if(!set.isEmpty()){
                        return set.size() + 1;
                    }
                }
            }

        }
        return ans;
    }

    public static void build(Node head, String s){
        Node cur = head;
        for(char c : s.toCharArray()){
            if(cur.nexts[c - 'a'] == null){
                cur.nexts[c - 'a'] = new Node();
            }
            cur = cur.nexts[c - 'a'];
        }
        cur.isEnd = true;
        cur.content = s;
    }

    public static class Node{
        public Node[] nexts;
        public String content;
        public boolean isEnd;

        public Node(){
            nexts = new Node[26];
            isEnd = false;
            content = null;
        }

        public Node (String str){
            content = str;
            nexts = new Node[26];
            isEnd = true;
        }
    }

    public static void main(String[] args) {
        String[] str = {"catsdogcats","cats","cat","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"};
        findAllConcatenatedWordsInADict(str);
    }
}
