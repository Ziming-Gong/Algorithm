package WeeklyPractice.May11;

import basicAlgo.mergesorted.ans;
import sun.font.TrueTypeFont;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.TreeMap;

// 给出一个有n个点，m条有向边的图
// 你可以施展魔法，把有向边，变成无向边
// 比如A到B的有向边，权重为7。施展魔法之后，A和B通过该边到达彼此的代价都是7。
// 求，允许施展一次魔法的情况下，1到n的最短路，如果不能到达，输出-1。
// n为点数, 每条边用(OA.MaxNumDinstinctNum.a,b,v)表示，含义是a到b的这条边，权值为v
// 点的数量 <= 10^5，边的数量 <= 2 * 10^5，1 <= 边的权值 <= 10^6
public class OneEdgeMagicMinPathSum {

    //from   to   weight
    public static int min1(int n, int[][] road) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < road.length; i++) {
            ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
            for (int j = 0; j <= n; j++) {
                graph.add(new ArrayList<>());
            }
            graph.get(road[i][1]).add(new int[]{road[i][0], road[i][2]});
            for (int[] r : road) {
                graph.get(r[0]).add(new int[]{r[1], r[2]});
            }
            min = Math.min(min, dijkstra1(n, graph));
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    public static int dijkstra1(int n, ArrayList<ArrayList<int[]>> graph) {
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> (a[1] - b[1]));
        boolean[] visited = new boolean[n + 1];
        heap.add(new int[]{1, 0});
        int ans = Integer.MAX_VALUE;
        while (!heap.isEmpty()) {
            int[] cur = heap.poll();
            if (cur[0] == n) {
                ans = cur[1];
                break;
            }
            if (visited[cur[0]]) {
                continue;
            }
            visited[cur[0]] = true;
            for (int[] next : graph.get(cur[0])) {
                int to = next[0];
                int weight = next[1];
                if (!visited[to]) {
                    heap.add(new int[]{to, cur[1] + weight});
                }

            }
        }
        return ans;
    }

    public static int min2(int n, int[][] roads) {
        ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] r : roads) {
            //         from           isMagic, to , weight
            graph.get(r[0]).add(new int[]{0, r[1], r[2]});
            graph.get(r[1]).add(new int[]{1, r[0], r[2]});
        }
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> (a[2] - b[2]));
        boolean[][] visited = new boolean[2][n + 1];
        heap.add(new int[]{0, 1, 0});
        int ans = Integer.MAX_VALUE;
        while (!heap.isEmpty()) {
            int[] cur = heap.poll();
            if (visited[cur[0]][cur[1]]) {
                continue;
            }
            visited[cur[0]][cur[1]] = true;
            if (cur[1] == n) {
                ans = Math.min(ans, cur[2]);
                if (visited[0][n] && visited[1][n]) {
                    break;
                }
            }
            for (int[] edge : graph.get(cur[1])) {
                //当前点 cur
                // 如果走过魔法路径 cur[0] = 1
                //如果没走过魔法精cur[0] = 0;
                //当边是edge
                //如果之前走过
                if (cur[0] + edge[0] == 0) {
                    if (!visited[0][edge[1]]) {
                        heap.add(new int[]{0, edge[1], cur[2] + edge[2]});
                    }
                }
                if (cur[0] + edge[0] == 1) {
                    if (!visited[1][edge[1]]) {
                        heap.add(new int[]{1, edge[1], cur[2] + edge[2]});
                    }
                }
            }
        }
        return ans != Integer.MAX_VALUE ? ans : -1;

    }


    // for test
    public static int[][] randomRoads(int n, int v) {
        int m = (int) (Math.random() * (n * (n - 1) / 2)) + 1;
        int[][] roads = new int[m][3];
        for (int i = 0; i < m; i++) {
            roads[i][0] = (int) (Math.random() * n) + 1;
            roads[i][1] = (int) (Math.random() * n) + 1;
            roads[i][2] = (int) (Math.random() * v) + 1;
        }
        return roads;
    }

    // for test
    public static void main(String[] args) {
        int N = 20;
        int V = 30;
        int testTime = 20000;
        System.out.println("begin");
        for (int i = 0; i < testTime; i++) {
            int n = (int) (Math.random() * N) + 1;
            int[][] roads = randomRoads(n, V);
            if (min1(n, roads) != min2(n, roads)) {
                System.out.println("oops!");
            }
        }
        System.out.println("end");

        // 点的数量10^5
        int n = 100000;
        // 边的数量2 * 10^5
        int m = 200000;
        // 时间复杂度很明显和边的权重是没关系的
        // 所以这里设置小一点，防止出现溢出的解
        int v = 100;
        int[][] roads = new int[m][3];
        for (int i = 0; i < m; i++) {
            roads[i][0] = (int) (Math.random() * n) + 1;
            roads[i][1] = (int) (Math.random() * n) + 1;
            roads[i][2] = (int) (Math.random() * v) + 1;
        }
        long start = System.currentTimeMillis();
        System.out.println("运行结果 : " + min2(n, roads));
        long end = System.currentTimeMillis();
        System.out.println("运行时间(毫秒) : " + (end - start));
    }

}
