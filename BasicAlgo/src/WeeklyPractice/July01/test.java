package WeeklyPractice.July01;

import java.util.ArrayList;

public class test {
    public static int cnt;

    public static int minimumScore(int[] nums, int[][] edges) {
        int n = nums.length;
        // 先建立图
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        // 4个点，0、1、2、3
        // 0 : {}
        // 1 : {}
        // 2 : {}
        // 3 : {}
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            // a,b
            // graph.get(a).add(b);
            // graph.get(b).add(a);
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        // 无向边组成的无环图
        // 为了方便，就认为0是头
        // dfn[i] = ?
        int[] dfn = new int[n];
        // xor[i] 以i为头的整棵树，整体异或的结果是多少？
        int[] xor = new int[n];
        // size[i] 以i为头的整棵树，一共几个点？
        int[] size = new int[n];
        cnt = 1;
        dfs(graph,nums,   dfn, xor, size,0);
        int ans = Integer.MAX_VALUE, m = edges.length, cut1, cut2, pre, pos, part1, part2, part3, max, min;
        for (int i = 0; i < m; i++) {
            // i，要删掉的第一条边，i号边
            // edges[i][0]   edges[i][1]  dfn 谁大，谁就是删掉之后的树的头！cut1
            //      a            b                cut1
            // { a, b}
            //   0  1
            int a = edges[i][0];
            int b = edges[i][1];
            cut1 = dfn[a] < dfn[b] ? b : a;
            for (int j = i + 1; j < m; j++) {
                // j, 要删掉的第二条边，j号边
                // { c, d}
                //   0  1
                int c = edges[j][0];
                int d = edges[j][1];
                cut2 = dfn[c] < dfn[d] ? d : c;
                // cut1，cut2
                pre = dfn[cut1] < dfn[cut2] ? cut1 : cut2;
                pos = pre == cut1 ? cut2 : cut1;
                // 早 pre  晚 pos
                part1 = xor[pos];
                // pos为头的树，是pre为头的树的子树！
                if (dfn[pos] < dfn[pre] + size[pre]) {
                    part2 = xor[pre] ^ xor[pos];
                    part3 = xor[0] ^ xor[pre];
                } else { // pos为头的树，不是pre为头的树的子树！
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

    // 所有节点的值，存在nums数组里
    // 整个图结构，存在graph里
    // 当前来到的是cur号点
    // 请把cur为头，整棵树，所有节点的dfn、size、xor填好！
    // 返回！
    public static void dfs(
                           ArrayList<ArrayList<Integer>> graph,int[] nums,

                           int[] dfn, int[] xor, int[] size,int cur) {
        // 当前节点了！，
        dfn[cur] = cnt++;
        // 只是来到了cur的头部！
        xor[cur] = nums[cur];
        size[cur] = 1;
        // 遍历所有的孩子！
        for (int next : graph.get(cur)) {
            // 只有dfn是0的孩子，才是cur在树中的下级！！！！
            if (dfn[next] == 0) {
                // cur某个孩子是next
                dfs(graph,nums,   dfn, xor, size,next);
                // next整棵树的异或和，
                xor[cur] ^= xor[next];
                // next整棵树的size
                size[cur] += size[next];
            }
        }
    }
}
