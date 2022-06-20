package Questions.code_6;

import basicAlgo.mergesorted.ans;

import javax.print.attribute.standard.PrintQuality;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
    数组中所有数都异或起来的结果叫做异或和
    给定一个数组arr，可以任意切分成若干个不想交的子数组
    其中一定存在一种最优方案，使得切出异或和为0的子数组最多
    返回这个最多数量
 */
public class MostXorZero {
    /**
     * 暴力解
     */
    public static int maxXor(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[] eor = new int[arr.length];
        int xor = arr[0];
        eor[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            eor[i] = eor[i - 1] ^ arr[i];
        }
        return process(eor, 1, new ArrayList<>());

    }

    public static int process(int[] eor, int i, List<Integer> list) {
        if (i == eor.length) {
            list.add(i);
            int ans = max(eor, list);
            list.remove(list.size() - 1);
            return ans;
        } else {
            list.add(i);
            int p1 = process(eor, i + 1, list);
            list.remove(list.size() - 1);
            int p2 = process(eor, i + 1, list);
            return Math.max(p1, p2);
        }
    }

    public static int max(int[] eor, List<Integer> list) {
        int ans = (eor[list.get(0) - 1] == 0) ? 1 : 0;
        for (int i = 1; i < list.size(); i++) {
            if ((eor[list.get(i) - 1] ^ eor[list.get(i - 1) - 1]) == 0) {
                ans++;
            }
        }
        return ans;
    }

    // 时间复杂度O(N)的方法
    public static int mostXor(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        int[] dp = new int[N];
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int xor = 0;
        for (int i = 0; i < N; i++) {
            xor ^= arr[i];
            if (i > 0) {
                dp[i] = dp[i - 1];
            }
            if (map.containsKey(xor)) {
                dp[i] = Math.max(dp[i], map.get(xor) == -1 ? 1 : 1 + dp[map.get(xor)]);
            }
            map.put(xor, i);
        }
        return dp[N - 1];
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random());
        }
        return arr;
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
    public static void main(String[] args) {
        int testTime = 150000;
        int maxSize = 12;
        int maxValue = 5;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int res = mostXor(arr);
            int comp = maxXor(arr);
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
