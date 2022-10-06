package OA;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Code01_NodePickMostKEdgesMaxValue {

    public static boolean[] visited = new boolean[100001];

    // dp[i][0] : 当i的父节点不与i连接时，以i号节点为头的子树的最大收益
    // dp[i][1] : 当i的父节点与i连接时，以i号节点为头的子树的最大收益
    public static int[][] dp = new int[100001][2];

    // 帮助排序的辅助数组
    public static int[][] help = new int[100001][2];

    // n :
    // 节点数量，编号0~n-1
    // k :
    // 每个节点最多k个连接
    // edges :
    // { {a,b,c}, {d,e,f} } 表示
    // a到b有一条无向边权值c，d到e有一条无向边权值f
    public static int maxSum(int k, int n, List<Integer> from, List<Integer> to, List<Integer> weight) {
        ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < from.size(); i++) {
            int a = from.get(i);
            int b = to.get(i);
            int c = weight.get(i);
            graph.get(a).add(new int[]{b, c});
            graph.get(b).add(new int[]{a, c});
        }
        for (int i = 0; i < n; i++) {
            dp[i][0] = -1;
            dp[i][1] = -1;
            visited[i] = false;
        }
        dfs(0, k, graph);
        return dp[0][0];
    }

    public static void dfs(int cur, int k, ArrayList<ArrayList<int[]>> graph) {
        visited[cur] = true;
        ArrayList<int[]> edges = graph.get(cur);
        int ans0 = 0;
        int ans1 = 0;
        int m = 0;
        for (int i = 0; i < edges.size(); i++) {
            int next = edges.get(i)[0];
            int weight = edges.get(i)[1];
            if (!visited[next]) {
                dfs(next, k, graph);
                ans0 += dp[next][0];
                ans1 += dp[next][0];
                if (dp[next][0] < dp[next][1] + weight) {
                    help[m][0] = next;
                    help[m++][1] = dp[next][1] + weight;
                }
            }
        }
        Arrays.sort(help, 0, m, (a, b) -> b[1] - a[1]);
        // ans0 最多可以选k条边
        // ans1 最多可以选k-1条边
        // 先选k-1条
        for (int i = 0; i < Math.min(k - 1, m); i++) {
            int next = help[i][0];
            ans0 += help[i][1] - dp[next][0];
            ans1 += help[i][1] - dp[next][0];
        }
        // ans0 还可以多选一条
        if (m >= k) {
            ans0 += help[k - 1][1] - dp[help[k - 1][0]][0];
        }
        dp[cur][0] = ans0;
        dp[cur][1] = ans1;
    }

}
