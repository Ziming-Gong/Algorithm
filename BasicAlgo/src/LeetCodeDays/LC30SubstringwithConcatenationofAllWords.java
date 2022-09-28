package LeetCodeDays;

import java.util.*;

public class LC30SubstringwithConcatenationofAllWords {
    public static List<Integer> findSubstring(String s, String[] words) {
        Node head = new Node();
        int len = 0;
        for (int i = 0; i < words.length; i++) {
            build(head, words[i], i);
            len += words[i].length();
        }
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        char[] str = s.toCharArray();
        for (int i = 0; i < str.length; i++) {
            search(head, str, i, map);
        }
        List<Integer> ans = new ArrayList<>();
        System.out.println(str.length);
        System.out.println(len);
        for (int i = 0; i < str.length - len + 1; i++) {
            if (process(str, map, i, i, new HashSet<>(), len, words.length, words)) {
                ans.add(i);
            }
        }
        return ans;


    }

    public static boolean process(char[] str, HashMap<Integer, ArrayList<Integer>> map, int start, int end, HashSet<Integer> set, int len, int max, String[] words) {
        if (end >= str.length) {
            return false;
        }
        if (end - start > len) {
            return false;
        }

        if (end - start == len) {
            return set.size() == max;
        }
        boolean ans = false;
        if(!map.containsKey(end)){
            return false;
        }
        for (Integer i : map.get(end)) {
            if (set.contains(i)) {
                continue;
            }
            set.add(i);
            ans = process(str, map, start, end + words[i].length(), set, len, max, words);
            set.remove(i);
            if (ans) {
                return true;
            }
        }
        return false;
    }

    public static void search(Node head, char[] str, int index, HashMap<Integer, ArrayList<Integer>> map) {
        Node cur = head;
        int i = index;
        for (; index < str.length; index++) {
            if (cur.nexts[str[index] - 'a'] == null) {
                break;
            }
            cur = cur.nexts[str[index] - 'a'];
            if (cur.isEnd) {
                if (!map.containsKey(i)) {
                    map.put(i, new ArrayList<>());
                }
                map.get(i).addAll(cur.index);
            }
        }
    }


    public static void build(Node head, String s, int index) {
        char[] str = s.toCharArray();
        Node cur = head;
        for (int i = 0; i < str.length; i++) {
            if (cur.nexts[str[i] - 'a'] == null) {
                cur.nexts[str[i] - 'a'] = new Node();
            }
            cur = cur.nexts[str[i] - 'a'];
        }
        cur.isEnd = true;
        cur.word = s;
        cur.index.add(index);


    }


    public static class Node {
        public Node[] nexts = new Node[26];
        public boolean isEnd = false;
        public String word = null;
        public ArrayList<Integer> index = new ArrayList<>();
    }

    public static void main(String[] args) {
//        String str = "goodgoodbestword";
//        String[] words = {"word","good","best","good"};
//        System.out.println(findSubstring(str, words));
//
//        HashSet<Integer> set1 = new HashSet<>();
//        set1.add(1);
//        set1.add(2);
//        set1.add(3);
//        HashSet<Integer> set2 = new HashSet<>();
//        set2.add(1);
//        set2.add(2);
//        set2.add(3);
//        System.out.println(set1.);
    }


}
