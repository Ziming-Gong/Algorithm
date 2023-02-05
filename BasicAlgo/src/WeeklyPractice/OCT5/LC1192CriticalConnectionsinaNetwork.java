package WeeklyPractice.OCT5;

import java.util.ArrayList;
import java.util.List;

public class LC1192CriticalConnectionsinaNetwork {
    public int[] dfn;
    public int[] low;
    public int dfnCnt = 0;


    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (List<Integer> connection : connections) {
            graph.get(connection.get(0)).add(connection.get(1));
            graph.get(connection.get(1)).add(connection.get(0));
        }
        List<List<Integer>> ans = new ArrayList<>();
        dfn = new int[n];
        low = new int[n];
        tarjan(graph, ans, 0, -1);
        return ans;


    }

    public void tarjan(List<List<Integer>> graph, List<List<Integer>> ans, int cur, int father) {
        dfn[cur] = low[cur] = ++dfnCnt;
        for (Integer next : graph.get(cur)) {
            if (next != father) {
                if (dfn[next] == 0) {
                    tarjan(graph, ans, next, cur);
                    low[cur] = Math.min(low[cur], low[next]);
                } else {
                    low[cur] = Math.min(low[cur], low[next]);
                }
            }
        }
        if (dfn[cur] == low[cur] && cur != 0) {
            List<Integer> list = new ArrayList<>();
            list.add(cur);
            list.add(father);
            ans.add(list);
        }
    }
}
