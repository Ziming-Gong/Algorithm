package Questions.code_31;

import java.util.ArrayList;
import java.util.List;

public class LC139WordBreak {
    public static boolean wordBreak(String st, List<String> wordDict) {
        Node head = new Node();
        for (String s : wordDict) {
            build(head, s);
        }
        char[] str = st.toCharArray();
        int N = str.length;
        boolean[] dp = new boolean[N + 1];
        dp[N] = true;
        for (int i = N - 1; i >= 0; i--) {
            Node cur = head;
            for (int j = i; j < N; j++) {
                if (cur.isEnd) {
                    dp[i] |= dp[j + 1];
                }
                if (dp[i] || cur.nexts[str[j] - 'a'] == null) {
                    break;
                }
                cur = cur.nexts[str[j] - 'a'];
            }
        }
        return dp[0];

    }

    public static class Node {
        public Node[] nexts = new Node[26];
        public boolean isEnd = false;
        ;
    }

    public static void build(Node head, String s) {
        char[] str = s.toCharArray();
        Node cur = head;
        for (char c : str) {
            if (cur.nexts[c - 'a'] == null) {
                cur.nexts[c - 'a'] = new Node();
            }
            cur = cur.nexts[c - 'a'];
        }
        cur.isEnd = true;
    }

    public static void main(String[] args) {
        String str = "leetcode";
        List<String> list = new ArrayList<>();
        list.add("leet");
        list.add("code");
        System.out.println(wordBreak(str, list));
    }
}
