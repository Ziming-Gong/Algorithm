package WeeklyPractice.Jan022023;

import java.util.ArrayList;
import java.util.Arrays;

public class LC2193MinimumNumberofMovestoMakePalindrome {
    public int minMovesToMakePalindrome(String s) {

        char[] str = s.toCharArray();
        int n = str.length;
        int[] arr = new int[n + 1];
        IndexTree it = new IndexTree(n);

        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            list.add(new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            list.get(str[i] - 'a').add(i);
        }

        for (int i = 0, l = 1; i < n; i++, l++) {
            if (arr[l] != 0) {
                int right = list.get(str[i] - 'a').remove(list.get(str[i] - 'a').size() - 1);
                if (l == right) {
                    arr[i] = (n + 1) / 2;
                    it.add(l, -1);
                } else {
                    int kth = it.sum(l);
                    arr[l] = kth - 1;
                    arr[right] = n - kth + 1;
                    it.add(right, -1);
                }
            }
        }

        return number(arr, new int[n + 1], 1, n);
    }

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

    public class IndexTree {
        int[] arr;
        int size;

        public IndexTree(int n) {
            size = n + 1;
            arr = new int[n + 1];
            for (int i = 1; i < arr.length; i++) {
                add(i, 1);
            }
        }

        public void add(int index, int num) {
            while (index < size) {
                arr[index] += num;
                index += (index & -index);
            }
        }

        public int sum(int index) {
            int res = 0;
            while (index > 0) {
                res += arr[index];
                index -= (index & -index);
            }
            return res;
        }


    }
}
