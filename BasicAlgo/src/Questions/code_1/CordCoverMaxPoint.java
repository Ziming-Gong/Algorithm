package Questions.code_1;

import javax.sound.midi.MidiChannel;
import java.util.Arrays;

public class CordCoverMaxPoint {

    public static int maxPoint1(int[] arr, int L) {
        int N = arr.length;
        int count = 1;
        for (int i = 0; i < N; i++) {
            int left = getLeft(arr, arr[i] - L, i);
            count = Math.max(count, i - left + 1);
        }
        return count;
    }

    public static int getLeft(int[] arr, int target, int R) {
        int L = 0;
        int res = R;
        while (L <= R) {
            int m = (L + R) >> 1;
            if (arr[m] >= target) {
                R = m - 1;
                res = m;
            } else {
                L = m + 1;
            }
        }
        return res;
    }

    public static int maxPoint2(int[] arr, int L) {
        int l = 0;
        int r = 0;
        int ans = 0;
        while (r < arr.length) {
            while (r+1 < arr.length && arr[r + 1] - arr[l] <= L) {
                r++;
            }
            ans = Math.max(ans, r - l + 1);
            l++;
            r ++;
        }
        return ans;
    }

    // for test
    public static int test(int[] arr, int L) {
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            int pre = i - 1;
            while (pre >= 0 && arr[i] - arr[pre] <= L) {
                pre--;
            }
            max = Math.max(max, i - pre);
        }
        return max;
    }

    // for test
    public static int[] generateArray(int len, int max) {
        int[] ans = new int[(int) (Math.random() * len) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (int) (Math.random() * max);
        }
        Arrays.sort(ans);
        return ans;
    }

    public static void main(String[] args) {
        int len = 100;
        int max = 1000;
        int testTime = 1000000;
        System.out.println("test start");
        for (int i = 0; i < testTime; i++) {
            int L = (int) (Math.random() * max);
            int[] arr = generateArray(len, max);
            int ans1 = maxPoint1(arr, L);
            int ans2 = maxPoint2(arr, L);
            int ans3 = test(arr, L);
            if (ans1 != ans2 || ans2 != ans3) {
                System.out.println("oops!");
                break;
            }
        }
        System.out.println("test end");

    }


}
