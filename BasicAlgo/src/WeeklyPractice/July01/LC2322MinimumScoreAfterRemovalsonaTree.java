package WeeklyPractice.July01;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LC2322MinimumScoreAfterRemovalsonaTree {
    public int cnt = 1;

    public int minimumScore(int[] nums, int[][] edges) {

        List<List<Integer>> graph = new ArrayList<>();
        int N = nums.length;
        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] cur : edges) {
            graph.get(cur[0]).add(cur[1]);
            graph.get(cur[1]).add(cur[0]);
        }
        int[] dfn = new int[N];
        int[] xor = new int[N];
        int[] size = new int[N];
        DFS(graph, nums, dfn, xor, size, 0);
        int ans = Integer.MAX_VALUE, m = edges.length,min, max, part1, part2, part3, pos, pre, cut1, cut2;
        for (int i = 0; i < edges.length; i++) {
            // a , b
            int a = edges[i][0];
            int b = edges[i][1];

            cut1 = dfn[a] < dfn[b] ? b : a;
            for (int j = i + 1; j < m; j++) {
                int c = edges[j][0];
                int d = edges[j][1];
                cut2 = dfn[c] < dfn[d] ? d : c;
                pre = dfn[cut1] < dfn[cut2] ? cut1 : cut2;
                pos = pre == cut1 ? cut2 : cut1;
                part1 = xor[pos];
                if (dfn[pre] + size[pre] > dfn[pos]) {
                    part2 = xor[pre] ^ xor[pos];
                    part3 = xor[0] ^ xor[pre];

                } else {
                    part2 = xor[pre];
                    part3 = xor[0] ^ part1 ^ part2;
                }
                max = Math.max(Math.max(part1, part2), part3);
                min = Math.min(Math.min(part1, part2), part3);
                ans = Math.min(ans, max - min);
            }
        }
        return ans;

    }

    public void DFS(List<List<Integer>> graph, int[] nums, int[] dfn, int[] xor, int[] size, int cur) {
        dfn[cur] = cnt++;
        size[cur] = 1;
        xor[cur] = nums[cur];

        for (int next : graph.get(cur)) {
            if (dfn[next] == 0) {
                DFS(graph, nums, dfn, xor, size, next);
                size[cur] += size[next];
                xor[cur] ^= xor[next];
            }
        }
    }

    public int compare(int a, int b, int c) {
        int big = Math.max(a, Math.max(b, c));
        int small = Math.min(a, Math.min(b, c));
        return big - small;
    }
}
