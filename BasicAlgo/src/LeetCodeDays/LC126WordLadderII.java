package LeetCodeDays;

import Questions.code_5.Hash;

import javax.xml.stream.FactoryConfigurationError;
import java.util.*;

public class LC126WordLadderII {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        HashMap<String, List<String>> graph = createGraph(wordList);
        if (!graph.containsKey(endWord) || !graph.containsKey(beginWord)) {
            return new LinkedList<>();
        }
        HashMap<String, Integer> fromDistance = getDistance(graph, beginWord);
        HashMap<String, Integer> toDistance = getDistance(graph, endWord);
        List<List<String>> ans = new LinkedList();
        getShortestPath(graph, fromDistance, toDistance, beginWord, endWord, new LinkedList<>(), ans);
        return ans;
    }

    public void getShortestPath(HashMap<String, List<String>> graph, HashMap<String, Integer> fromDistance, HashMap<String, Integer> toDistance, String cur, String endWord, LinkedList<String> path, List<List<String>> ans) {
        path.add(cur);
        if (cur.equals(endWord)) {
            ans.add(new LinkedList<>(path));
            return;
        }
        for (String next : graph.get(cur)) {
            if (fromDistance.get(next) + 1 == fromDistance.get(cur) && toDistance.get(next) - 1 == toDistance.get(cur)) {
                getShortestPath(graph, fromDistance, toDistance, next, endWord, path, ans);
            }
        }
        path.pollLast();
    }

    public HashMap<String, Integer> getDistance(HashMap<String, List<String>> graph, String begin) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put(begin, 0);
        Queue<String> queue = new LinkedList<>();
        queue.add(begin);
        while (!queue.isEmpty()) {
            Queue<String> nexts = new LinkedList<>();
            while (!queue.isEmpty()) {
                String str = queue.poll();
                for (String cur : graph.get(str)) {
                    if (!map.containsKey(cur)) {
                        map.put(cur, map.get(str) + 1);
                    }
                }
            }
            queue = nexts;
        }
        return map;

    }

    public HashMap<String, List<String>> createGraph(List<String> wordList) {
        HashMap<String, List<String>> res = new HashMap<>();
        HashSet<String> set = new HashSet(wordList);
        for (String cur : wordList) {
            res.put(cur, new ArrayList<>());
            char[] str = cur.toCharArray();
            for (int i = 0; i < str.length; i++) {
                for (char a = 'a'; a <= 'z'; a++) {
                    if (str[i] != a) {
                        char temp = str[i];
                        str[i] = a;
                        if (set.contains(String.valueOf(str))) {
                            res.get(cur).add(String.valueOf(str));
                        }
                        str[i] = temp;
                    }
                }
            }
        }
        return res;
    }

}










