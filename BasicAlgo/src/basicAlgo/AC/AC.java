package basicAlgo.AC;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AC {
    public static class Node {
        private String end;
        private Node[] next;
        private boolean isUsed;
        private Node fail;

        public Node() {
            end = null;
            next = new Node[26];
            isUsed = false;
            fail = null;
        }
    }

    public static class ACAutomation {
        private Node root;

        public ACAutomation() {
            root = new Node();
        }

        public void insert(String string) {
            char[] str = string.toCharArray();
            Node cur = root;
            int index = 0;
            for (int i = 0; i < str.length; i++) {
                index = str[i] - 'a';
                if (cur.next[index] == null) {
                    cur.next[index] = new Node();
                }
                cur = cur.next[index];
            }
            cur.end = string;
        }

        public void build() {
            Node cur = root;
            Queue<Node> queue = new LinkedList<>();
            queue.add(cur);
            Node cFail = null;
            while (!queue.isEmpty()) {
                cur = queue.poll();
                for (int i = 0; i < 26; i++) {
                    if (cur.next[i] != null) {
                        cur.next[i].fail = root;
                        cFail = cur.fail;
                        while (cFail != null) {
                            if (cFail.next[i] != null) {
                                cur.next[i].fail = cFail;
                                break;
                            }
                            cFail = cFail.fail;
                        }
                        queue.add(cur.next[i]);
                    }
                }
            }
        }

        public List<String> containWords(String content) {
            char[] str = content.toCharArray();
            Node cur = root;
            List<String> ans = new ArrayList<>();
            for (int i = 0; i < str.length; i++) {
                int index = str[i] - 'a';
                while(cur.next[index] == null && cur != root){
                    cur = cur.fail;
                }
                cur = cur.next[index] != null ? cur.next[index] : root;
                Node follow = cur;
                while (follow != root){
                    if(follow.isUsed){
                        break;
                    }
                    if(follow.end != null){
                        ans.add(follow.end);
                        follow.isUsed = true;
                    }
                    follow = follow.fail;
                }
            }
            return ans;
        }


    }

    public static void main(String[] args) {
        ACAutomation ac = new ACAutomation();
        ac.insert("after");
        ac.insert("lled");
        ac.insert("the");
        // 设置fail指针
        ac.build();

        List<String> contains = ac.containWords("iationbeforeafterthecallbackiscalledandwhentheinstanceisdestroyed");
        for (String word : contains) {
            System.out.println(word);
        }
    }

}
