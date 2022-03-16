package basicAlgo.DynamicProgramming;

public class SplitSumClosedSizeHalf {
    public static int right(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int sum = 0;
        for (int i : arr) {
            sum += i;
        }
        sum /= 2;

        if (arr.length % 2 == 0) {
            return process(arr, 0, arr.length / 2, sum);
        } else {
            return Math.max(process(arr, 0, (arr.length + 1) / 2, sum), process(arr, 0, arr.length / 2, sum));
        }
    }

    public static int process(int[] arr, int index, int picks, int rest) {
        if (index == arr.length) {
            return picks == 0 ? 0 : -1;
        }

        int p1 = process(arr, index + 1, picks, rest);
        int p2 = -1;
        int next = -1;
        if (rest - arr[index] >= 0) {
            next = process(arr, index + 1, picks - 1, rest - arr[index]);
        }
        if (next != -1) {
            p2 = arr[index] + next;
        }
        return Math.max(p1, p2);
    }

    public static int dp(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int sum = 0;
        for (int i : arr) {
            sum += i;
        }
        sum /= 2;
        int N = arr.length;
        int M = (N + 1) / 2;
        int[][][] dp = new int[N + 1][sum + 1][M + 1];

        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= sum; j++) {
                for (int k = 0; k <= M; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }
        for (int i = 0; i <= sum; i++) {
            dp[N][i][0] = 0;
        }
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= sum; rest++) {
                for (int picks = 0; picks <= M; picks++) {
                    int p1 = dp[index + 1][rest][picks];
                    int p2 = -1;
                    int next = -1;
                    if (rest - arr[index] >= 0 && picks - 1 >= 0) {
                        next = dp[index + 1][rest - arr[index]][picks - 1];
                    }
                    if (next != -1) {
                        p2 = arr[index] + next;
                    }
                    dp[index][rest][picks] = Math.max(p1, p2);
                }
            }
        }
        if (N % 2 == 0) {
            return dp[0][sum][N / 2];
        } else {
            return Math.max(dp[0][sum][(N + 1) / 2], dp[0][sum][N / 2]);
        }
    }

    public static int dp2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        sum >>= 1;
        int N = arr.length;
        int M = (arr.length + 1) >> 1;
        int[][][] dp = new int[N][M + 1][sum + 1];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= M; j++) {
                for (int k = 0; k <= sum; k++) {
                    dp[i][j][k] = Integer.MIN_VALUE;
                }
            }
        }
        for (int i = 0; i < N; i++) {
            for (int k = 0; k <= sum; k++) {
                dp[i][0][k] = 0;
            }
        }
        for (int k = 0; k <= sum; k++) {
            dp[0][1][k] = arr[0] <= k ? arr[0] : Integer.MIN_VALUE;
        }
        for (int i = 1; i < N; i++) {
            for (int j = 1; j <= Math.min(i + 1, M); j++) {
                for (int k = 0; k <= sum; k++) {
                    dp[i][j][k] = dp[i - 1][j][k];
                    if (k - arr[i] >= 0) {
                        dp[i][j][k] = Math.max(dp[i][j][k], dp[i - 1][j - 1][k - arr[i]] + arr[i]);
                    }
                }
            }
        }
        return Math.max(dp[N - 1][M][sum], dp[N - 1][N - M][sum]);
    }

    // for test
    public static int[] randomArray(int len, int value) {
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * value);
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int maxLen = 10;
        int maxValue = 50;
        int testTime = 100000;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * maxLen);
            int[] arr = randomArray(len, maxValue);
            int ans1 = right(arr);
            int ans2 = dp(arr);
            int ans3 = dp2(arr);
            if (ans1 != ans2 || ans1 != ans3) {
                printArray(arr);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans3);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("test end");
    }


}
