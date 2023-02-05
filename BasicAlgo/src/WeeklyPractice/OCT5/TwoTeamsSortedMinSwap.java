package WeeklyPractice.OCT5;

// 来自美团
// 两种颜色的球，蓝色和红色，都按1～n编号，共计2n个
// 为方便放在一个数组中，红球编号取负，篮球不变，并打乱顺序，
// 要求同一种颜色的球按编号升序排列，可以进行如下操作：
// 交换相邻两个球，求最少操作次数。
// [3,-3,1,-4,2,-2,-1,4]
// 最终交换结果为
// [1,2,3,-1,-2,-3,-4,4]
// 最少交换次数为10
// n <= 1000

import com.sun.javafx.image.ByteToBytePixelConverter;

import java.util.HashMap;
import java.util.concurrent.CompletionStage;

public class TwoTeamsSortedMinSwap {
    public static int minSwaps(int[] arr) {
        int n = arr.length;
        HashMap<Integer, Integer> indexMap = new HashMap<>();
        int topA = 0;
        int topB = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] > 0) {
                topA = Math.max(topA, arr[i]);
            } else {
                topB = Math.max(topB, Math.abs(arr[i]));
            }
            indexMap.put(arr[i], i);
        }
        IndexTree it = new IndexTree(n);
        for (int i = 0; i < n; i++) {
            it.add(i, 1);
        }
        return f(it, topA, topB, n - 1, indexMap);

    }

    public static int f(IndexTree it, int topA, int topB, int end, HashMap<Integer, Integer> indexMap) {
        if (topA == 0 && topB == 0) {
            return 0;
        }
        int p1 = Integer.MAX_VALUE;
        int p2 = Integer.MAX_VALUE;
        if (topA != 0) {
            int topIndex = indexMap.get(topA);
            int cost = it.sum(topIndex, end) - 1;
            it.add(topIndex, -1);
            int next = f(it, topA - 1, topB, end, indexMap);
            it.add(topIndex, 1);
            p1 = cost + next;
        }
        if (topB != 0) {
            int topIndex = indexMap.get(-topB);
            int cost = it.sum(topIndex, end) - 1;
            it.add(topIndex, -1);
            int next = f(it, topA, topB - 1, end, indexMap);
            it.add(topIndex, 1);
            p2 = cost + next;
        }
        return Math.min(p1, p2);

    }

    public static class IndexTree {
        public int[] tree;
        public int size;

        public IndexTree(int n) {
            this.size = n;
            tree = new int[size + 1];
        }

        public void add(int index, int val) {
            index++;
            while (index <= size) {
                tree[index] += val;
                index += index & -index;
            }
        }

        public int sum(int l, int r) {
            return l == 0 ? sum(r + 1) : sum(r + 1) - sum(l);
        }

        public int sum(int index) {
            int ans = 0;
            while (index > 0) {
                ans += tree[index];
                index -= index & -index;
            }
            return ans;
        }


    }

    public static void main(String[] args) {
        int[] arr = {3, -3, 1, -4, 2, -2, -1, 4};
        System.out.println(minSwaps(arr));
    }
}
