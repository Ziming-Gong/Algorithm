package Questions.code_23;

import sun.jvm.hotspot.debugger.remote.arm.RemoteARMThread;

import java.util.HashSet;

public class LongestIntegratedLength {
    public static int maxLen(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;

        }


        int n = arr.length;
        HashSet<Integer> set = new HashSet<>();
        int ans = 0;


        for (int L = 0; L < n; L++) {
            set.clear();
            int min = arr[L];
            int max = arr[L];
            set.add(arr[L]);
            for (int R = L + 1; R < n; R++) {
                if (set.contains(arr[R])) {
                    break;
                }
                max = Math.max(max, arr[R]);
                min = Math.min(min, arr[R]);
                set.add(arr[R]);
                if (R - L == max - min) {
                    ans = Math.max(ans, R - L + 1);
                }
            }
        }
        return ans;
    }

    public static int getLIL2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int len = 0;
        int max = 0;
        int min = 0;
        HashSet<Integer> set = new HashSet<Integer>();
        for (int L = 0; L < arr.length; L++) { // L 左边界
            // L .......
            set.clear();
            max = Integer.MIN_VALUE;
            min = Integer.MAX_VALUE;
            for (int R = L; R < arr.length; R++) { // R 右边界
                // arr[L..R]这个子数组在验证 l...R L...r+1 l...r+2
                if (set.contains(arr[R])) {
                    // arr[L..R]上开始 出现重复值了，arr[L..R往后]不需要验证了，
                    // 一定不是可整合的
                    break;
                }
                // arr[L..R]上无重复值
                set.add(arr[R]);
                max = Math.max(max, arr[R]);
                min = Math.min(min, arr[R]);
                if (max - min == R - L) { // L..R 是可整合的
                    len = Math.max(len, R - L + 1);
                }
            }
        }
        return len;
    }

    public static void main(String[] args) {
        int[] arr = { 5, 5, 3, 2, 6, 4, 3 };
        System.out.println(maxLen(arr));
        System.out.println(getLIL2(arr));

    }
}
