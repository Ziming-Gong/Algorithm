package WeeklyPractice.OCT5;

// 来自Leetcode周赛
// 魔物了占领若干据点，这些据点被若干条道路相连接，
// roads[i] = [x, y] 表示编号 x、y 的两个据点通过一条道路连接。
// 现在勇者要将按照以下原则将这些据点逐一夺回：
// 在开始的时候，勇者可以花费资源先夺回一些据点，
// 初始夺回第 j 个据点所需消耗的资源数量为 cost[j]
// 接下来，勇者在不消耗资源情况下，
// 每次可以夺回一个和「已夺回据点」相连接的魔物据点，
// 并对其进行夺回
// 为了防止魔物暴动，勇者在每一次夺回据点后（包括花费资源夺回据点后），
// 需要保证剩余的所有魔物据点之间是相连通的（不经过「已夺回据点」）。
// 请返回勇者夺回所有据点需要消耗的最少资源数量。
// 输入保证初始所有据点都是连通的，且不存在重边和自环
// 测试链接 : https://leetcode.cn/problems/s5kipK/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CaptureStrongHold {

//    public long minimumCost(int[] cost, int[][] roads) {
//
//
//    }

    public static class BConnectionComponent {
        public int[] head;
        public int[] next;
        public int[] to;
        public int[] dfn;
        public int[] low;
        public boolean[] cut;
        public int[] stack;
        public List<List<Integer>> bcc;

        public static int edgeCnt;
        public static int dfnCnt;
        public static int root;
        public static int top;

        public BConnectionComponent(int[][] roads, int n, int m) {
            init(n, m);
            createGraph(roads);
            createBCC(n);
        }

        public void init(int n, int m) {
            head = new int[m];
            Arrays.fill(head, -1);
            next = new int[m];
            to = new int[m];
            dfn = new int[n];
            low = new int[n];
            cut = new boolean[n];
            stack = new int[n];
            bcc = new ArrayList<>();
            edgeCnt = 0;
            dfnCnt = 0;
            root = 0;
            top = 0;
        }

        public void createGraph(int[][] roads) {
            for (int[] road : roads) {
                add(road[0], road[1]);
                add(road[1], road[0]);
            }
        }

        public void add(int a, int b) {
            to[edgeCnt] = b;
            next[edgeCnt] = head[a];
            head[a] = edgeCnt++;
        }

        public void createBCC(int n) {
            for (int i = 0; i < n; i++) {
                if (dfn[i] == 0) {
                    root = i;
                    tarjan(i);
                }
            }
        }

        public void tarjan(int x) {
            dfn[x] = low[x] = ++dfnCnt;
            stack[top++] = x;
            int flag = 0;
            if (x == root && head[x] != -1) {
                bcc.add(new ArrayList<>());
                bcc.get(bcc.size() - 1).add(x);
            } else {
                for (int i = head[x]; i >= 0; i = next[i]) {
                    int y = to[i];
                    if (dfn[y] == 0) {
                        tarjan(y);
                        if (low[y] >= dfn[x]) {
                            flag++;
                            if (x != root || flag > 1) {
                                cut[x] = true;
                            }
                            List<Integer> ansArr = new ArrayList<>();
                            for (int z = stack[--top]; z != y; z = stack[--top]) {
                                ansArr.add(z);
                            }
                            ansArr.add(x);
                            ansArr.add(y);
                            bcc.add(ansArr);
                        }
                        low[x] = Math.min(low[x], low[y]);
                    } else {
                        low[x] = Math.min(low[x], dfn[y]);
                    }
                }
            }
        }

    }


}











