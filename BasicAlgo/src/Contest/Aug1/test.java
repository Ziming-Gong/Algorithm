package Contest.Aug1;

import java.util.Arrays;

public class test {
    public boolean validPartition(int[] arr) {
        if (arr.length == 2) {
            return arr[1] == arr[0];
        }
        Arrays.sort(arr);
        boolean isSame = false;
        boolean isDiff = false;
        int N = arr.length;
        boolean t1 = false;
        boolean t2 = (arr[0] == arr[1]);
        boolean t3 = (t2 && arr[1] == arr[2]) || (arr[1] - arr[0] == 1 && arr[2] - arr[1] == 1);
        for (int i = 3; i < N; i++) {
            boolean temp1 = (arr[i] == arr[i - 1]) && t2;
            boolean temp2 = (arr[i] - arr[i - 1] == 1) && (arr[i - 1] - arr[i - 2] == 1) && t1;
            boolean temp3 = (arr[i] == arr[i - 1] && arr[i - 1] == arr[i - 2]) && t1;
            boolean t = temp1 || temp2 || temp3;
            t1 = t2;
            t2 = t3;
            t3 = t;
            if (!t1 && !t2) {
                return false;
            }
        }
        return true;

    }
}
