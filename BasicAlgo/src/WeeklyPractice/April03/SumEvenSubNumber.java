package WeeklyPractice.April03;

import java.util.Map;

public class SumEvenSubNumber {

    public static int number1(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k < 1 || k > arr.length) {
            return 0;
        }
        return process(arr, 0, 0, k);
    }

    public static int process(int[] arr, int index, int pre, int rest) {
        if (index == arr.length) {
            return rest == 0 && pre % 2 == 0 ? 1 : 0;  //
        }
        return process(arr, index + 1, pre + arr[index], rest - 1) + process(arr, index + 1, pre, rest);
    }

    public static int number2(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k < 1 || k > arr.length) {
            return 0;
        }
        int n = arr.length;
        // even[i][j] : 在前i个数的范围上(0...i-1)，一定选j个数，加起来是偶数的子序列个数
        // odd[i][j]  : 在前i个数的范围上(0...i-1)，一定选j个数，加起来是奇数的子序列个数
        int[][] evenDP = new int[n + 1][k + 1];
        int[][] oddDP = new int[n + 1][k + 1];

        for (int i = 0; i <= arr.length; i++) {
//            if ((arr[i] & 1) == 0) {
            evenDP[i][0]++;
//            } else {
//                oddDP[i][0]++;
//            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= Math.min(i, k); j++) {
                evenDP[i][j] = evenDP[i - 1][j];
                oddDP[i][j] = oddDP[i - 1][j];
                evenDP[i][j] += (arr[i - 1] & 1) == 0 ? evenDP[i - 1][j - 1] : oddDP[i - 1][j - 1];
                oddDP[i][j] += (arr[i - 1] & 1) == 0 ? oddDP[i - 1][j - 1] : evenDP[i - 1][j - 1];
            }
        }
        return evenDP[n][k];


    }


    // for test
    public static int[] randomArray(int n, int v) {
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = (int) (Math.random() * v);
        }
        return ans;
    }

    // for test
    public static void main(String[] args) {
        int N = 20;
        int V = 30;
        int testTimes = 3000;
        System.out.println("begin");
        for (int i = 0; i < testTimes; i++) {
            int n = (int) (Math.random() * N) + 1;
            int[] arr = randomArray(n, V);
            int k = (int) (Math.random() * n) + 1;
            int ans1 = number1(arr, k);
            int ans2 = number2(arr, k);
            if (ans1 != ans2) {
                System.out.println("fuck!");
                for (int num : arr) {
                    System.out.print(num + " ");
                }
                System.out.println();
                System.out.println(k);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("end");
    }
}
