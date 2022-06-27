package Questions.code_6;

import java.util.ArrayList;

public class test {


    // 暴力方法
    public static int comparator(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        int[] eor = new int[N];
        eor[0] = arr[0];
        for (int i = 1; i < N; i++) {
            eor[i] = eor[i - 1] ^ arr[i];
        }
        return process(eor, 1, new ArrayList<>());
    }

    // index去决定：前一坨部分，结不结束！
    // 如果结束！就把index放入到parts里去
    // 如果不结束，就不放
    public static int process(int[] eor, int index, ArrayList<Integer> parts) {
        int ans = 0;
        if (index == eor.length) {
            parts.add(eor.length);
            ans = eorZeroParts(eor, parts);
            parts.remove(parts.size() - 1);
        } else {
            int p1 = process(eor, index + 1, parts);
            parts.add(index);
            int p2 = process(eor, index + 1, parts);
            parts.remove(parts.size() - 1);
            ans = Math.max(p1, p2);
        }
        return ans;
    }

    public static int eorZeroParts(int[] eor, ArrayList<Integer> list) {
        int L = 0;
        int ans = 0;
        for (Integer end : list) {
            if ((eor[end - 1] ^ (L == 0 ? 0 : eor[L - 1])) == 0) {
                ans++;
            }
            L = end;
        }
        return ans;
    }

}
