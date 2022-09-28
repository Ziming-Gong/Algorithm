package LeetCodeDays;

import java.awt.geom.FlatteningPathIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class LC792NumberofMatchingSubsequences {
    public int numMatchingSubseq(String s, String[] words) {

        char[] str = s.toCharArray();
        ArrayList<int[]> list = build(str);
        Node head = new Node();

        for (String cur : words) {
            buildTier(head, cur.toCharArray());
        }

        /*
        leetcode虽然过了但是错了
        第一个字母检查不出来
        如果加上下面两行代码
        "abcde"    ["aa","bb","acd","ace"] 出错 结果为3
        如果不加
        "abcde"    ["aa","bb","acd","ace"] 出错 结果为 0
        原因就是str第一个字母由于dfs中index为0默认第一个有 自闭了老师。。。。
        因为LeetCode没有我那个例子所以过了。。。
         */
        int[] arr = list.get(0);
        arr[str[0] - 'a'] = 0;

        // ***************

        int ans = dfs(0 , head, list);

        return ans;
    }
    // O(M * m);
    public int dfs(int index, Node cur, ArrayList<int[]> list) {
        if (index == list.size() || cur == null) {
            return 0;
        }
        int ans = cur.num   ;
        for (Node next : cur.nexts) {
            if(next == null){
                continue;
            }
            int i = list.get(index)[next.v - 'a'];
            if (i != -1) {
                ans += dfs(i, next, list);
            }
        }
        return ans;
    }

    //O(N)
    public ArrayList<int[]> build(char[] str) {
        ArrayList<int[]> ans = new ArrayList<>();
        int[] arr = new int[26];
        Arrays.fill(arr, -1);
        for (int i = 0; i < str.length - 1; i++) {
            ans.add(new int[26]);
        }
        ans.add(arr);
        int n = str.length;
        for (int i = n - 2; i >= 0; i--) {
            copy(ans.get(i), ans.get(i + 1));
            int[] cur = ans.get(i);
            cur[str[i + 1] - 'a'] = i + 1;
        }
        return ans;
    }

    public void copy(int[] arr1, int[] arr2) {
        for (int i = 0; i < arr1.length; i++) {
            arr1[i] = arr2[i];
        }
    }

    public void buildTier(Node head, char[] str) {
        Node cur = head;
        for (int i = 0; i < str.length; i++) {
            if (cur.nexts[str[i] - 'a'] == null) {
                cur.nexts[str[i] - 'a'] = new Node(str[i]);
            }
            cur = cur.nexts[str[i] - 'a'];
        }
        cur.isEnd = true;
        cur.num ++;
    }

    public class Node {
        public Node[] nexts = new Node[26];
        public boolean isEnd = false;
        public char v;
        public int num;

        public Node(char v) {
            nexts = new Node[26];
            isEnd = false;
            this.v = v;
            num = 0;
        }

        public Node() {
            nexts = new Node[26];
            isEnd = false;
            num = 0;
        }
    }

}
