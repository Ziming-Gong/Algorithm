package Questions.code_6;

import java.awt.*;

public class MaxXOr {

    /*
    数组中所有的数都异或起来的和
    给定一个数组arr，染灰arr的最大子数组异或和
     */

    /**
     * 暴力解
     * Xor数组和累加和数组有一样的性质 但是没有累加和的单调性
     */
    public static int maxXorSubarray1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int n = arr.length;
        int[] xor = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            xor[i] = xor[i - 1] ^ arr[i - 1];
        }
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                ans = Math.max(ans, xor[i] ^ xor[j]);
            }
        }
        return ans;
    }

    public static class Node {
        public Node[] nexts = new Node[2];
    }

    public static class NumTier {
        public Node head;

        public NumTier() {
            head = new Node();
        }


        public void add(int value) {
            Node cur = head;
            for (int i = 31; i >= 0; i--) {
                int v = (value >> i) & 1;
                if (cur.nexts[v] == null) {
                    cur.nexts[v] = new Node();
                }
                cur = cur.nexts[v];
            }
        }

        public int maxEor(int value) {
            Node cur = head;
            int ans = 0;
            for (int i = 31; i >= 0; i--) {
                int path = (value >> i) & 1;
                int best = i == 31 ? path : path ^ 1;//如果i=31 最好是正数
                best = cur.nexts[best] == null ? best ^ 1 : best;
                ans |= (path ^ best) << i;
                cur = cur.nexts[best];
            }
            return ans;
        }
    }

    public static int maxXorSubarray2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int ans = Integer.MIN_VALUE;
        NumTier nt = new NumTier();
        nt.add(0);
        int xor = 0;
        for (int i = 0; i < arr.length; i++) {
            xor ^= arr[i];
            ans = Math.max(ans, nt.maxEor(xor));
            nt.add(xor);
        }
        return ans;
    }


    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 30;
        int maxValue = 50;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int comp = maxXorSubarray1(arr);
            int res = maxXorSubarray2(arr);
            if (res != comp) {
                succeed = false;
                printArray(arr);
                System.out.println(res);
                System.out.println(comp);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }


}
