package WeeklyPractice.April03;

public class RMQQuestion {
    public static class RMQ {
        public static int[][] max;

        public RMQ(int[] arr) {
            int N = arr.length;
            int k = power2(N);
            max = new int[N + 1][k + 1];
            for (int i = 1; i <= N; i++) {
                max[i][0] = arr[i - 1];
            }
            for (int j = 1; (1 << j) <= N; j++) {
                for (int i = 1; i + (1 << j) - 1 <= N; i++) {
                    max[i][j] = Math.max(max[i][j - 1], max[i + (1 << (j - 1))][j - 1]);
                }
            }
        }

        public int max(int l, int r) {
            int k = power2(r - l + 1);
            return Math.max(max[l][k], max[r - (1 << k) + 1][k]);
        }


        private int power2(int N) {
            int ans = 0;
            while ((1 << ans) <= (N >> 1)) {
                ans++;
            }
            return ans;
        }
    }


    // for test
    public static class Right {
        public int[][] max;

        public Right(int[] arr) {
            int n = arr.length;
            max = new int[n + 1][n + 1];
            for (int l = 1; l <= n; l++) {
                max[l][l] = arr[l - 1];
                for (int r = l + 1; r <= n; r++) {
                    max[l][r] = Math.max(max[l][r - 1], arr[r - 1]);
                }
            }
        }

        public int max(int l, int r) {
            return max[l][r];
        }

    }

    // for test
    public static int[] randomArray(int n, int v) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = (int) (Math.random() * v);
        }
        return arr;
    }

    // for test
    public static void main(String[] args) {
        int N = 150;
        int V = 200;
        int testTimeOut = 20000;
        int testTimeIn = 200;
        System.out.println("begin");
        for (int i = 0; i < testTimeOut; i++) {
            int n = (int) (Math.random() * N) + 1;
            int[] arr = randomArray(n, V);
            int m = arr.length;
            RMQ rmq = new RMQ(arr);
            Right right = new Right(arr);
            for (int j = 0; j < testTimeIn; j++) {
                int a = (int) (Math.random() * m) + 1;
                int b = (int) (Math.random() * m) + 1;
                int l = Math.min(a, b);
                int r = Math.max(a, b);
                int ans1 = rmq.max(l, r);
                int ans2 = right.max(l, r);
                if (ans1 != ans2) {
                    System.out.println("fuck!");
                    break;
                }
            }
        }
        System.out.println("end");
    }
}
