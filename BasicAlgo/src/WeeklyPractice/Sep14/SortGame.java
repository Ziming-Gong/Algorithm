package WeeklyPractice.Sep14;


// 来自百度
// 二狗买了一些小兵玩具，和大胖一起玩
// 一共有n个小兵，这n个小兵拍成一列
// 第i个小兵战斗力为hi，然后他们两个开始对小兵进行排列
// 一共进行m次操作，二狗每次操作选择一个数k，将前k个小兵战斗力从小到大排列
// 大胖每次操作选择一个数k，将前k个小兵战斗力从大到小排列
// 问所有操作结束后，排列顺序什么样
// 给定一个长度为n的数组arr，表示每个小兵的战斗力
// 给定一个长度为m的数组op,
// op[i] = { k , 0 }, 表示对前k个士兵执行从小到大的操作
// op[i] = { k , 1 }, 表示对前k个士兵执行从大到小的操作
// 返回数组ans，表示最终的排列
// 1 <= n, m <= 2 * 10^5
// - 10^9 <= arr[i] <= + 10^9

import com.sun.source.util.Trees;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeSet;

public class SortGame {

    public static int[] game2(int[] arr, int[][] op) {
        int n = arr.length;
        int m = op.length;
        int[] stack = new int[m];
        int[] ans = new int[n];
        int size = 0;
        for (int i = 0; i < op.length; i++) {
            while (size > 0 && op[i][0] >= op[stack[size - 1]][0]) {
                size--;
            }
            stack[size++] = i;
        }

        int l = 0;
        int r = size;
        for (int i = op[stack[0]][0]; i < n; i++) {
            ans[i] = arr[i];
        }
        TreeSet<Node> set = new TreeSet<>(new NodeComparator());
        for (int i = 0; i < op[stack[0]][0]; i++) {
            set.add(new Node(i, arr[i]));
        }
        for (int i = 0; i < size - 1; i++) {
            int curOp = stack[i];
            int nextOp = stack[i + 1];
            int diff = op[curOp][0] - op[nextOp][0];
            boolean ascending = op[curOp][1] == 0;
            for (int j = op[curOp][0] - 1; j >= op[nextOp][0]; j--) {
                ans[j] = ascending ? set.pollLast().value : set.pollFirst().value;
            }
        }
        boolean ascending = op[stack[size - 1]][1] == 0;
        for (int i = 0; i < op[stack[size - 1]][0]; i++) {
            ans[i] = ascending ? set.pollFirst().value : set.pollLast().value;
        }
        return ans;


    }

    public static class Node {
        public int cnt;
        public int value;

        public Node(int c, int v) {
            cnt = c;
            value = v;
        }
    }

    public static class NodeComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o1.value != o2.value ? o1.value - o2.value : o1.cnt - o2.cnt;
        }
    }

    public static int[] game1(int[] arr, int[][] op) {
        int n = arr.length;
        Integer[] help = new Integer[n];
        for (int i = 0; i < n; i++) {
            help[i] = arr[i];
        }
        for (int[] o : op) {
            if (o[1] == 0) {
                Arrays.sort(help, 0, o[0], (a, b) -> a - b);
            } else {
                Arrays.sort(help, 0, o[0], (a, b) -> b - a);
            }
        }
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = help[i];
        }
        return ans;
    }

    // 为了测试
    public static int[] randomArray(int n, int v) {
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = (int) (Math.random() * v) + 1;
        }
        return ans;
    }

    // 为了测试
    public static int[][] randomOp(int m, int n) {
        int[][] ans = new int[m][2];
        for (int i = 0; i < m; i++) {
            ans[i][0] = (int) (Math.random() * (n + 1));
            ans[i][1] = Math.random() < 0.5 ? 0 : 1;
        }
        return ans;
    }

    // 为了测试
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    // 为了测试
    public static void main(String[] args) {
        int N = 100;
        int M = 100;
        int V = 200;
        int testTimes = 5000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            int n = (int) (Math.random() * N) + 1;
            int m = (int) (Math.random() * M) + 1;
            int[] arr = randomArray(n, V);
            int[][] op = randomOp(m, n);
            int[] ans1 = game1(arr, op);
            int[] ans2 = game2(arr, op);
            if (!isEqual(ans1, ans2)) {
                System.out.println("出错了!");
            }
        }
        System.out.println("测试结束");
    }


}
