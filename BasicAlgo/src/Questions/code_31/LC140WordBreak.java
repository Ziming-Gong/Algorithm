package Questions.code_31;

import java.util.ArrayList;
import java.util.List;

public class LC140WordBreak {
    public static  List<String> wordBreak(String s, List<String> wordDict) {
        char[] str = s.toCharArray();
        Node head = new Node();
        for(String word : wordDict){
            build(head, word);
        }
        List<String> ans = new ArrayList<>();
        process(head, new ArrayList<>(), ans, str, 0);
        return ans;
    }

    public static void process(Node head, List<String> path, List<String> ans, char[] str, int index){
        if(index == str.length){
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < path.size() - 1; i ++){
                sb.append(path.get(i) + " ");
            }
            sb.append(path.get(path.size() - 1));
            ans.add(sb.toString());
            return;
        }

        Node curLevel = head.nexts[str[index] - 'a'];
        // if(curLevel == null){
        //     return;
        // }
        while(curLevel != null){
            if(curLevel.isEnd){
                path.add(curLevel.val);
                process(head, path, ans, str, index + 1);
                path.remove(path.size() - 1);
            }
            index ++;
            curLevel = curLevel.nexts[str[index] - 'a'];
        }

    }


    public static class Node{
        public boolean isEnd = false;
        public Node[] nexts = new Node[26];
        public String val = null;
    }

    public static void build(Node head, String s){
        Node cur = head;
        char[] str = s.toCharArray();
        for(int i = 1;  i< str.length; i ++){
            if(cur.nexts[str[i] - 'a'] == null){
                cur.nexts[str[i] - 'a'] = new Node();
            }
            cur = cur.nexts[str[i] - 'a'];
        }
        cur.isEnd = true;
        cur.val = s;
    }

    public static void main(String[] args) {
        String str = "catsanddog";
        List<String> words = new ArrayList<>();
        words.add("cats");
        words.add("and");
        words.add("sand");
        words.add("dog");
        wordBreak(str, words);
    }
}
