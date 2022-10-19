package OA;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Code01_NodePickMostKEdgesMaxValue {

    public static int maxSum1(int n, int k, int[][] edges) {
        return process(edges, 0, new boolean[edges.length], n, k);
    }

    public static int process(int[][] edges, int i, boolean[] pick, int n, int k) {
        if (i == edges.length) {
            int[] cnt = new int[n];
            int ans = 0;
            for (int j = 0; j < edges.length; j++) {
                if (pick[j]) {
                    cnt[edges[j][0]]++;
                    cnt[edges[j][1]]++;
                    ans += edges[j][2];
                }
            }
            for (int j = 0; j < n; j++) {
                if (cnt[j] > k) {
                    return -1;
                }
            }
            return ans;
        } else {
            pick[i] = true;
            int p1 = process(edges, i + 1, pick, n, k);
            pick[i] = false;
            int p2 = process(edges, i + 1, pick, n, k);
            return Math.max(p1, p2);
        }
    }

    public static long[][] dp = new long[100001][2];

    public static long[] help = new long[100001];

    public static long maxSum2(int n, int k, List<Integer> from, List<Integer> to, List<Integer> weight) {
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
        }
        dfs(0, -1, k, graph);
        return dp[0][0];
    }

    public static void dfs(int cur, int father, int k, ArrayList<ArrayList<int[]>> graph) {
        ArrayList<int[]> edges = graph.get(cur);
        for (int i = 0; i < edges.size(); i++) {
            int next = edges.get(i)[0];
            if (next != father) {
                dfs(next, cur, k, graph);
            }
        }
        long ans0 = 0;
        long ans1 = 0;
        int m = 0;
        for (int i = 0; i < edges.size(); i++) {
            int next = edges.get(i)[0];
            int weight = edges.get(i)[1];
            if (next != father) {
                ans0 += dp[next][0];
                ans1 += dp[next][0];
                if (dp[next][0] < dp[next][1] + weight) {
                    help[m++] = dp[next][1] + weight - dp[next][0];
                }
            }
        }
        Arrays.sort(help, 0, m);
        for (int i = m - 1, cnt = 1; i >= 0 && cnt <= k; i--, cnt++) {
            if (cnt <= k - 1) {
                ans0 += help[i];
                ans1 += help[i];
            }
            if (cnt == k) {
                ans0 += help[i];
            }
        }
        dp[cur][0] = ans0;
        dp[cur][1] = ans1;
    }

    // 为了测试
    // 生成无环无向图
    public static int[][] randomEdges(int n, int v) {
        int[] order = new int[n];
        for (int i = 0; i < n; i++) {
            order[i] = i;
        }
        for (int i = n - 1; i >= 0; i--) {
            swap(order, i, (int) (Math.random() * (i + 1)));
        }
        int[][] edges = new int[n - 1][3];
        for (int i = 1; i < n; i++) {
            edges[i - 1][0] = order[i];
            edges[i - 1][1] = order[(int) (Math.random() * i)];
            edges[i - 1][2] = (int) (Math.random() * v) + 1;
        }
        return edges;
    }

    // 为了测试
    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    // 为了测试
    public static void main(String[] args) {
        List<Integer> from = new ArrayList<>();
        from.add(0);
        from.add(0);
        from.add(1);
        from.add(1);
        from.add(2);
        List<Integer> to = new ArrayList<>();
        to.add(1);
        to.add(2);
        to.add(2);
        to.add(3);
        to.add(4);
        List<Integer> weight = new ArrayList<>();
        weight.add(9);
        weight.add(9);
        weight.add(9);
        weight.add(5);
        weight.add(5);
        int k1 = 2;
        int n1 = 5;
        System.out.println(maxSum2(n1, k1, from, to, weight));






        int N = 16;
        int V = 50;
        int testTimes = 2000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            int n = (int) (Math.random() * N) + 1;
            int k = (int) (Math.random() * n) + 1;
            int[][] edges = randomEdges(n, V);
            int ans1 = maxSum1(n, k, edges);
//            int ans2 = maxSum2(n, k, edges);
//            if (ans1 != ans2) {
//                System.out.println("出错了!");
//            }
        }
        System.out.println("测试结束");
    }


//    public static int maxSum3(int n, int k, int[][] edges) {
//        ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
//        for (int i = 0; i < n; i++) {
//            graph.add(new ArrayList<>());
//        }
//        for (int[] edge : edges) {
//            int a = edge[0];
//            int b = edge[1];
//            int c = edge[2];
//            graph.get(a).add(new int[] { b, c });
//            graph.get(b).add(new int[] { a, c });
//        }
//        for (int i = 0; i < n; i++) {
//            dp[i][0] = -1;
//            dp[i][1] = -1;
//        }
//        dfs(0, -1, k, graph);
//        return dp[0][0];
//    }

//
}
