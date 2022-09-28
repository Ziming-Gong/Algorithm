package LeetCodeDays;

import Questions.code_5.Hash;

import javax.xml.stream.FactoryConfigurationError;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class LC126WordLadderII {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        HashMap<String, List<String>> map = new HashMap<>();
        HashSet<String> set = new HashSet<>();
        int N = 0;
        wordList.add(beginWord);
        for (String str : wordList) {
            set.add(str);
            map.put(str, new ArrayList<>());
            N++;
        }
        if (!set.contains(endWord)) {
            return new ArrayList<>();
        }
        getNexts(map, set, wordList);

        HashSet<String> start = new HashSet<>();
        HashSet<String> end = new HashSet<>();
        start.add(beginWord);
        end.add(endWord);
        HashSet<String> used = new HashSet<>();
        List<List<String>> ans = new ArrayList<>();

        for (int min = 2; min <= N; min++) {
            HashSet<String> nexts = new HashSet<>();
            for (String s : start) {
                List<String> list = map.get(s);
                for (int i = 0; i < list.size(); i++) {
                    String cur = list.get(i);
                    if (!used.contains(cur) && set.contains(cur)) {
                        used.add(cur);
                        nexts.add(cur);
                    }
                    if (end.contains(cur)) {
                        break;
                    }
                }
            }
            start = nexts.size() > end.size() ? end : nexts;
            end = start == end ? nexts : end;
        }
//        if(len == Integer.MAX_VALUE){
//            return new ArrayList<>();
//        }
//        List<String> cur = new ArrayList<>();
//        cur.add(beginWord);
//        process(map, ans, cur, new HashSet<>(), endWord, len);
        return ans;
    }

    public void process(HashMap<String, List<String>> map, List<List<String>> ans, List<String> list, HashSet<String> used, String end, int min) {
        if (list.size() == min) {
            if (list.get(list.size() - 1).equals(end)) {
                List<String> copy = copy(list);
                ans.add(copy);
            }
            return;
        }
        String word = list.get(list.size() - 1);
        List<String> nexts = map.get(word);
        for (String next : nexts) {
            if (!used.contains(next)) {
                used.add(next);
                list.add(next);
                process(map, ans, list, used, end, min);
                used.remove(next);
                list.remove(list.size() - 1);
            }

        }


    }

    public List<String> copy(List<String> list) {
        List<String> ans = new ArrayList<>();
        ans.addAll(list);
        return ans;
    }

    public void getNexts(HashMap<String, List<String>> map, HashSet<String> set, List<String> wordList) {
        for (String s : wordList) {
            char[] str = s.toCharArray();
            for (int i = 0; i < str.length; i++) {
                for (char c = 'a'; c <= 'z'; c++) {
                    if (c != str[i]) {
                        char temp = str[i];
                        str[i] = c;
                        String cur = String.valueOf(str);
                        if (set.contains(cur)) {
                            map.get(cur).add(s);
                        }
                        str[i] = temp;
                    }
                }
            }
        }
    }


    public List<List<String>> findLadders1(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> set = new HashSet<>();
        int N = 0;
        wordList.add(beginWord);
        for (String cur : wordList) {
            set.add(cur);
            N++;
        }
        if (!set.contains(endWord)) {
            return new ArrayList<>();
        }
        List<List<String>> startList = new ArrayList<>();
        startList.add(new ArrayList<>());
        startList.get(0).add(beginWord);
        HashSet<String> used = new HashSet<>();
        used.add(beginWord);
        List<List<String>> ans = new ArrayList<>();
        boolean flag = false;
        for (int len = 2; len <= N; len++) {
            List<List<String>> nexts = new ArrayList<>();
            for (List<String> curList : startList) {
                char[] str = curList.get(curList.size() - 1).toCharArray();
                for (int i = 0; i < str.length; i++) {
                    for (char c = 'a'; c < 'z'; c++) {
                        if (str[i] != c) {
                            char temp = str[i];
                            str[i] = c;
                            String cur = String.valueOf(str);
                            if( set.contains(cur)){
                                used.add(cur);
                                List<String> copy = copy(curList, cur);
                                nexts.add(copy);
                                if(cur.equals(endWord)){
                                    List<String> a = copy(curList, cur);
                                    ans.add(a);
                                    flag = true;
                                    used.remove(cur);
                                }

                            }
                            str[i] = temp;
                        }

                    }
                }
            }
            if(flag){
                break;
            }
            startList = nexts;
        }
        return ans;
    }

    public List<String> copy(List<String> list, String end){
        List<String> ans = new ArrayList<>();
        ans.addAll(list);
        ans.add(end);
        return ans;
    }

}










