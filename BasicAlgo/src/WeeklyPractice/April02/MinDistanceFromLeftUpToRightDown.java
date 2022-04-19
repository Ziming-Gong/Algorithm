package WeeklyPractice.April02;

import java.util.LinkedList;
import java.util.PriorityQueue;

public class MinDistanceFromLeftUpToRightDown {

    public static int bestWalk1(int[][] matrix) {
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        int n = matrix.length;
        int m = matrix[0].length;
        boolean[][] TFTable = new boolean[n][m];
//        int[] {a, b, c} a = cost b = row c = col
        heap.add(new int[]{0, 0, 0});
        int ans = 0;
        while (!heap.isEmpty()) {
            int[] cur = heap.poll();
            int cost = cur[0];
            int row = cur[1];
            int col = cur[2];
            if (TFTable[row][col]) {
                continue;
            }
            TFTable[row][col] = true;
            if( row == matrix.length - 1 && col == matrix[0].length - 1){
                ans = cost;
                break;
            }
            add(matrix, heap, row, col, row + 1, col, cost, matrix[row][col]);
            add(matrix, heap, row, col, row - 1, col, cost, matrix[row][col]);
            add(matrix, heap, row, col, row, col - 1, cost, matrix[row][col]);
            add(matrix, heap, row, col, row, col + 1, cost, matrix[row][col]);

        }
        return ans;

    }

    public static void add(int[][] matrix, PriorityQueue<int[]> heap, int curRow, int curCol, int nextRow, int nextCol, int curCost, int curVal) {
        if (nextRow < 0 || nextCol >= matrix[0].length || nextRow >= matrix.length || nextCol < 0) {
            return;
        }
        int nextVal = matrix[nextRow][nextCol];
        heap.add(new int[]{(nextVal == curVal ? curCost + 1 : curCost + 2), nextRow, nextCol});
    }


    public static int bestWalk2(int[][] map) {
        int n = map.length;
        int m = map[0].length;
        // 小根堆：[代价，行，列]
        // 根据代价，谁代价小，谁放在堆的上面
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        // poped[i][j] == true 已经弹出过了！不要再处理，直接忽略！
        // poped[i][j] == false 之间(i,j)没弹出过！要处理
        boolean[][] poped = new boolean[n][m];
        heap.add(new int[] { 0, 0, 0 });
        int ans = 0;
        while (!heap.isEmpty()) {
            // 当前弹出了，[代价，行，列]，当前位置
            int[] cur = heap.poll();
            int dis = cur[0];
            int row = cur[1];
            int col = cur[2];
            if (poped[row][col]) {
                continue;
            }
            // 第一次弹出！
            poped[row][col] = true;
            if (row == n - 1 && col == m - 1) {
                ans = dis;
                break;
            }
            add(dis, row - 1, col, map[row][col], n, m, map, poped, heap);
            add(dis, row + 1, col, map[row][col], n, m, map, poped, heap);
            add(dis, row, col - 1, map[row][col], n, m, map, poped, heap);
            add(dis, row, col + 1, map[row][col], n, m, map, poped, heap);
        }
        return ans;
    }

    // preDistance ： 之前的距离
    // int row, int col ： 当前要加入的是什么位置
    // preValue : 前一个格子是什么值，
    // int n, int m ：边界，固定参数
    // map: 每一个格子的值，都在map里
    // boolean[][] poped : 当前位置如果是弹出过的位置，要忽略！
    // PriorityQueue<int[]> heap : 小根堆
    public static void add(int preDistance,
                           int row, int col, int preValue, int n, int m,
                           int[][] map, boolean[][] poped,
                           PriorityQueue<int[]> heap) {
        if (row >= 0 && row < n && col >= 0 && col < m && !poped[row][col]) {
            heap.add(new int[] {
                    preDistance + (map[row][col] == preValue ? 1 : 2),
                    row, col });
        }
    }

    // 为了测试
    public static int[][] randomMatrix(int n, int m) {
        int[][] ans = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                ans[i][j] = (int) (Math.random() * 2);
            }
        }
        return ans;
    }

    // 为了测试
    public static void main(String[] args) {
        int n = 100;
        int m = 100;
        int testTime = 1000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[][] map = randomMatrix(n, m);
            int ans1 = bestWalk1(map);
            int ans2 = bestWalk2(map);
            if (ans1 != ans2) {
                System.out.println("出错了!");
                for (int[] arr : map) {
                    for (int num : arr) {
                        System.out.print(num + " ");
                    }
                    System.out.println();
                }
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("测试结束");
    }





}
