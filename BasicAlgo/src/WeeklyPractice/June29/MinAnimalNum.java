package WeeklyPractice.June29;

import WeeklyPractice.May06.FindDuplicateOnlyOne;
import basicAlgo.mergesorted.ans;
import sun.util.calendar.CalendarSystem;

import java.io.File;
import java.util.Arrays;

// 有n个动物重量分别是a1、a2、a3.....an，
// 这群动物一起玩叠罗汉游戏，
// 规定从左往右选择动物，每只动物左边动物的总重量不能超过自己的重量
// 返回最多能选多少个动物，求一个高效的算法。
// 比如有7个动物，从左往右重量依次为：1，3，5，7，9，11，21
// 则最多能选5个动物：1，3，5，9，21
// 注意本题给的例子是有序的，但是实际给定的动物数组，可能是无序的，
// 要求从左往右选动物，且不能打乱原始数组
public class MinAnimalNum {

    public static int maxAnimals1(int[] arr) {
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        int[][] dp = new int[arr.length][sum + 1];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j <= sum; j++) {
                dp[i][j] = -1;
            }
        }
        return process1(arr, 0, 0, dp);
    }

    public static int process1(int[] arr, int index, int pre, int[][] dp) {
        if (index == arr.length) {
            return 0;
        }
        if (dp[index][pre] != -1) {
            return dp[index][pre];
        }
        int p1 = process1(arr, index + 1, pre, dp);
        int p2 = 0;
        if (arr[index] >= pre) {
            p2 = 1 + process1(arr, index + 1, pre + arr[index], dp);
        }
        int ans = Math.max(p1, p2);
        dp[index][pre] = ans;
        return ans;
    }

    public static int maxAnimals2(int[] arr) {
        if(arr == null || arr.length == 0){
            return 0;
        }
        int N = arr.length;
        int[] ends = new int[N + 1];//arr 从左往右挑选，凑出i+1长度的最小值
        ends[0] = 0;
        int R = 1;
        for (int i = 0; i < N; i++) {
            int cur = search(ends, arr[i], R - 1);
//            R = Math.max(cur + 1, R);
            if(cur == R - 1){
                ends[R] = ends[R - 1] + arr[i];
                R ++;
            }else{
                ends[cur + 1] = Math.min(ends[cur + 1], ends[cur] + arr[i]);
            }

        }
        return R - 1;
    }

    public static int search(int[] arr, int target, int R) {
        int L = 0;
        int M = 0;
        int ans = 0;
        while (L <= R) {
            M = (L + R) / 2;
            if (arr[M] <= target) {
                L = M + 1;
                ans = M;
            } else {
                R = M - 1;
            }
        }
        return ans;
    }

    public static int[] randomArray(int n, int v) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = (int) (Math.random() * v) + 1;
        }
        return arr;
    }

    public static void main(String[] args) {
        int N = 100;
        int V = 1000;
        int testTime = 2000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int n = (int) (Math.random() * N) + 1;
            int[] arr = randomArray(n, V);
            int ans1 = maxAnimals1(arr);
            int ans2 = maxAnimals2(arr);
            if (ans1 != ans2) {
                System.out.println("出错了");
                for (int num : arr) {
                    System.out.print(num + " ");
                }
                System.out.println();
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("测试结束");
    }


}
