package Method;

public class ReversedPairs {
    public static int number(int[] arr, int[] help, int l, int r) {
        if (l >= r) {
            return 0;
        }
        int mid = l + ((r - l) >> 1);
        return number(arr, help, l, mid) + number(arr, help, mid + 1, r) + merge(arr, help, l, mid, r);
    }

    public static int merge(int[] arr, int[] help, int l, int m, int r) {
        int i = r;
        int p1 = m;
        int p2 = r;
        int ans = 0;
        while (p1 >= l && p2 > m) {
            ans += arr[p1] > arr[p2] ? (p2 - m) : 0;
            help[i--] = arr[p1] > arr[p2] ? arr[p1--] : arr[p2--];
        }
        while (p1 >= l) {
            help[i--] = arr[p1--];
        }
        while (p2 > m) {
            help[i--] = arr[p2--];
        }
        for (i = l; i <= r; i++) {
            arr[i] = help[i];
        }
        return ans;
    }
}
