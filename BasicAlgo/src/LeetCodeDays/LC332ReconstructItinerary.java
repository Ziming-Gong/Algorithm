package LeetCodeDays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class LC332ReconstructItinerary {
    public List<String> findItinerary(List<List<String>> tickets) {
        HashMap<String, List<String>> graph = new HashMap<>();
        HashMap<String, List<Integer>> visited = new HashMap<>();
        for (List<String> cur : tickets) {
            if (!graph.containsKey(cur.get(0))) {
                graph.put(cur.get(0), new ArrayList<>());
                visited.put(cur.get(0), new ArrayList<>());
            }
            graph.get(cur.get(0)).add(cur.get(1));
            visited.get(cur.get(0)).add(1);
        }
        List<String> ans = new ArrayList<>();
        for (List<String> list : graph.values()) {
            Collections.sort(list, (a, b) -> (a.compareTo(b)));
        }
        if (!graph.containsKey("JFK")) {
            return ans;
        }
        dfs(graph, visited, ans, "JFK", new ArrayList<>());
        return ans;
    }

    public void dfs(HashMap<String, List<String>> graph, HashMap<String, List<Integer>> visited, List<String> ans, String cur, List<String> curList) {
        if (!graph.containsKey(cur)) {
            if (ans != null || curList.size() > ans.size()) {
                List<String> best = new ArrayList<>();
                for (String str : curList) {
                    best.add(str);
                }
                ans = best;
                return;
            }
        }
        int cnt = 0;
        for (int i = 0; i < graph.get(cur).size(); i++) {
            if (visited.get(cur).get(i) == 1) {
                visited.get(cur).set(i, 2);
                curList.add(graph.get(cur).get(i));
                dfs(graph, visited, ans, graph.get(cur).get(i), curList);
                visited.get(cur).set(i, 1);
                curList.remove(curList.size() - 1);
                cnt++;
            }
        }
        if (cnt == 0) {
            if (ans != null || curList.size() > ans.size()) {
                List<String> best = new ArrayList<>();
                for (String str : curList) {
                    best.add(str);
                }
                ans = best;
                ans.clear();
            }
        }
    }
}
