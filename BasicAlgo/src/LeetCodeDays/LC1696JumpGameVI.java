package LeetCodeDays;

import com.sun.java.swing.plaf.windows.resources.windows;
import sun.awt.image.ImageWatched;
import sun.plugin2.os.windows.Windows;

import java.util.LinkedList;
import java.util.List;

public class LC1696JumpGameVI {
    public static int maxResult(int[] arr, int k) {
        int N = arr.length;
        LinkedList<Integer> queue = new LinkedList<>();
        int ans = arr[0];
        int L = 1, R = 1;
        int[] dp = new int[N];
        dp[0] = arr[0];
        queue.add(0);
        for (int i = 1; i < N; i++) {
            while (!queue.isEmpty() && (i - queue.peekFirst() > k)) {
                queue.pollFirst();
            }
            dp[i] = arr[i] + dp[queue.peekFirst()];
            while (!queue.isEmpty() && dp[queue.peekLast()] <= dp[i]) {
                queue.pollFirst();
            }
            queue.addLast(i);
        }
        return dp[N - 1];
    }
//    public static class Windows{
//        public int[] arr;
//        public Windows(int n){
//
//        }
//    }


    public static void main(String[] args) {
        int[] arr = {100,-1,-100,-1,100};
        int num = 2;
        System.out.println(maxResult(arr, num));

    }
}
