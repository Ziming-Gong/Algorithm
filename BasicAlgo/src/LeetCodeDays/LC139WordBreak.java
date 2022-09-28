package LeetCodeDays;

import sun.tools.attach.HotSpotVirtualMachine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class LC139WordBreak {
    public static boolean wordBreak(String s, List<String> wordDict) {

        Node head = new Node();
        Integer status = 0;
        for (String cur : wordDict) {
            status = build(head, cur.toCharArray(), status);
        }
        if (!isValid(s.toCharArray(), status)) {
            return false;
        }
        Node cur = head;
        HashMap<Integer, HashMap<Node, Boolean>> map = new HashMap<>();
        return process(s.toCharArray(), cur, head, 0, map);
    }

    public static boolean process(char[] str, Node cur, Node head, int index, HashMap<Integer, HashMap<Node, Boolean>> map) {
        if (index == str.length) {
            return cur.isEnd;
        }
        if (cur == null) {
            return false;
        }
//        if (map.containsKey(index) && map.get(index).containsKey(cur)) {
//            return map.get(index).get(cur);
//        }
        boolean p1 = false;

        while(index < str.length && cur != null && cur.next != null && cur.next[str[index] - 'a'] != null && !cur.isEnd){

            cur = cur.next[str[index] - 'a'];
            index ++;

        }

        if (cur != null && cur.isEnd) {
            p1 |= process(str, head, head, index, map);
        }
        if(index == str.length){
            return cur.isEnd;
        }

        if (cur != null && index < str.length && !p1 && cur.next[str[index] - 'a'] != null) {
            p1 |= process(str, cur.next[str[index] - 'a'], head, index + 1, map);
        }

//        map.put(index, map.getOrDefault(cur, new HashMap<>()));
//        map.get(index).put(cur, p1);

        return p1;


    }

    public static class Node {
        public Node[] next = new Node[26];
        public boolean isEnd = false;
    }

    public static int build(Node head, char[] str, Integer status) {
        Node cur = head;
        for (int i = 0; i < str.length; i++) {
            if (cur.next[str[i] - 'a'] == null) {
                cur.next[str[i] - 'a'] = new Node();
            }
            cur = cur.next[str[i] - 'a'];
            status |= 1 << (str[i] - 'a');
        }
        cur.isEnd = true;
        return status;
    }

    public static boolean isValid(char[] str, Integer status) {
        int cur = 0;
        for (char c : str) {
            cur |= 1 << (c - 'a');
        }
        for (int i = 0; i < 26; i++) {
            if (((1 << i) & cur) != 0 && ((1 << i) & status) == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String str = "leetcode";
        List<String> list = new ArrayList<>();
        list.add("leet");
        list.add("code");
        System.out.println(wordBreak(str, list));
    }
}
