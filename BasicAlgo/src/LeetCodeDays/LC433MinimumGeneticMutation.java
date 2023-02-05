package LeetCodeDays;

import java.util.*;

public class LC433MinimumGeneticMutation {
    public char[] store = {'A','C','G','T'};
    public int minMutation(String start, String end, String[] bank) {
        HashMap<String, List<String>> graph = createGraph(bank, start);
        HashMap<String, Integer> fromDistance = getDistance(start, graph);
        // HashMap<String, Integer> toDistance = getDistance(end, graph);
        return fromDistance.containsKey(end) ? fromDistance.get(end) : -1;
    }

    // public int dfs(HashMap<String, List<String>> graph, HashMap<String, Integer> fromDistance, HashMap<String, Integer> toDistance, String cur, String end, )

    public HashMap<String, Integer> getDistance(String begin, HashMap<String, List<String>> graph){
        HashMap<String, Integer> res = new HashMap<>();
        res.put(begin, 0);
        // HashSet<String> set = new HashSet<>();
        // set.add(begin);
        Queue<String> queue = new LinkedList<>();
        queue.add(begin);
        while(!queue.isEmpty()){
            Queue<String> nexts = new LinkedList<>();
            while(!queue.isEmpty()){
                String cur = queue.poll();
                for(int i = 0; i < graph.get(cur).size(); i ++){
                    if(!res.containsKey(graph.get(cur).get(i))){
                        res.put(graph.get(cur).get(i), res.get(cur) + 1);
                        nexts.add(graph.get(cur).get(i));
                    }
                }
                queue = nexts;
            }
        }
        return res;
    }

    public HashMap<String, List<String>> createGraph(String[] list, String start){
        HashSet<String> set = new HashSet<>();
        HashMap<String, List<String>> res = new HashMap<>();
        for(int i = 0; i < list.length; i ++){
            res.put(list[i], new ArrayList<>());
            set.add(list[i]);
        }
        if(!set.contains(start)){
            set.add(start);
            res.put(start, new ArrayList<>());
        }
        for(int i = 0; i < list.length;i ++){
            String curString = list[i];
            char[] cur = list[i].toCharArray();
            for(int j = 0; j < cur.length; j ++){
                for(int k = 0; k < store.length; k ++){
                    if(cur[j] != store[k]){
                        cur[j] = store[k];
                        if(set.contains(String.valueOf(cur))){
                            res.get(curString).add(String.valueOf(cur));
                        }
                    }
                }
            }
        }
        return res;
    }
}
