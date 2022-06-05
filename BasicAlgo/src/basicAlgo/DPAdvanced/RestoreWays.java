package basicAlgo.DPAdvanced;

import com.sun.xml.internal.bind.v2.model.core.ID;
import org.omg.CORBA.OMGVMCID;
import sun.jvm.hotspot.jdi.VoidValueImpl;

// 整型数组arr长度为n(3 <= n <= 10^4)，最初每个数字是<=200的正数且满足如下条件：
// 1. 0位置的要求：arr[0]<=arr[1]
// 2. n-1位置的要求：arr[n-1]<=arr[n-2]
// 3. 中间i位置的要求：arr[i]<=max(arr[i-1],arr[i+1])
// 但是在arr有些数字丢失了，比如k位置的数字之前是正数，丢失之后k位置的数字为0
// 请你根据上述条件，计算可能有多少种不同的arr可以满足以上条件
// 比如 [6,0,9] 只有还原成 [6,9,9]满足全部三个条件，所以返回1种，即[6,9,9]达标
public class RestoreWays {
    public static int ways0(int[] arr) {
        return process0(arr, 0);
    }

    public static int process0(int[] arr, int index) {
        if (index == arr.length) {
            return isValid(arr) ? 1 : 0;
        } else {
            if (arr[index] != 0) {
                return process0(arr, index + 1);
            } else {
                int ways = 0;
                for (int v = 1; v < 201; v++) {
                    arr[index] = v;
                    ways += process0(arr, index + 1);
                }
                arr[index] = 0;
                return ways;
            }
        }
    }

    public static boolean isValid(int[] arr) {
        if (arr[0] > arr[1]) {
            return false;
        }
        if (arr[arr.length - 1] > arr[arr.length - 2]) {
            return false;
        }
        for (int i = 1; i < arr.length - 1; i++) {
            if (arr[i] > Math.max(arr[i - 1], arr[i + 1])) {
                return false;
            }
        }
        return true;
    }

    public static int ways1(int[] arr) {
        int N = arr.length;
        if (arr[N - 1] != 0) {
            return process1(arr, N - 1, arr[N - 1], 2);
        } else {
            int way = 0;
            for (int i = 1; i <= 200; i++) {
                way += process1(arr, N - 1, i, 2);
            }
            return way;
        }

    }

    /**
     * @param arr
     * @param index 当前index
     * @param value 给index这个value
     * @param s     0: arr[index] < arr[index + 1]
     *              1: arr[index] == arr[index  + 1]
     *              2: arr[index] > arr[index  + 1]
     * @return
     */
    public static int process1(int[] arr, int index, int value, int s) {
        if (arr[index] != 0 && arr[index] != value) {
            return 0;
        }
        if (index == 0) {
            return s != 2 ? 1 : 0;
        }
        int ways = 0;
        if (s == 1 || s == 0) { // arr[index] <= arr[index + 1] ====> arr[index-1]随便选
            for (int i = 1; i <= 200; i++) {
                ways += process1(arr, index - 1, i, (i < value ? 0 : (i == value ? 1 : 2)));
            }
        } else {//只能选比他大的
            for (int i = value; i <= 200; i++) {
                ways += process1(arr, index - 1, i, (i == value ? 1 : 2));
            }
        }
        return ways;
    }

    public static int ways2(int[] arr) {
        int N = arr.length;
        int[][][] dp = new int[N][201][3];
        if (arr[0] != 0) {
            dp[0][arr[0]][1] = 1;
            dp[0][arr[0]][0] = 1;
        } else {
            for (int j = 1; j <= 200; j++) {
                dp[0][j][0] = 1;
                dp[0][j][1] = 1;
            }
        }

        for (int index = 1; index < N; index++) {
            for (int value = 1; value <= 200; value++) {
                for (int s = 0; s <= 2; s++) {
                    if (arr[index] == 0 || arr[index] == value) {
                        int cur = 0;
                        if (s == 1 || s == 0) {
                            for (int i = 1; i < value; i++) {
                                cur += dp[index - 1][i][0];
                            }
                        }
                        for (int i = value; i <= 200; i++) {
                            cur += dp[index - 1][i][(value == i ? 1 : 2)];
                        }
                        dp[index][value][s] = cur;
                    }
                }

            }
        }


        if (arr[N - 1] != 0) {
            return dp[N - 1][arr[N - 1]][2];
        } else {
            int ways = 0;
            for (int i = 1; i <= 200; i++) {
                ways += dp[N - 1][i][2];
            }
            return ways;
        }
    }

    public static int ways3(int[] arr) {
        int N = arr.length;
        int[][][] dp = new int[N][201][3];
        if (arr[0] != 0) {
            dp[0][arr[0]][0] = 1;
            dp[0][arr[0]][1] = 1;
        } else {
            for (int value = 0; value <= 200; value++) {
                dp[0][value][0] = 1;
                dp[0][value][1] = 1;
            }
        }

        int[][] preSum = new int[201][3];
        preSum[1][0] = dp[0][1][0];
        preSum[1][1] = dp[0][1][1];
        preSum[1][2] = dp[0][1][2];
        for (int j = 0; j <= 2; j++) {
            for (int i = 2; i <= 200; i++) {
                preSum[i][j] = dp[0][i][j] + preSum[i - 1][j];
            }
        }

        for (int index = 1; index < N; index++) {
            for (int value = 1; value <= 200; value++) {
                for (int s = 0; s <= 2; s++) {
                    if (arr[index] == 0 || arr[index] == value) {
                        if (s == 0 || s == 1) {
                            dp[index][value][s] += getSum(preSum, 1, value - 1, 0);
                        }
                        dp[index][value][s] += dp[index - 1][value][1];
                        dp[index][value][s] += getSum(preSum, value + 1, 200, 2);
                    }
                }
            }
            for (int value = 1; value <= 200; value++) {
                for (int s = 0; s <= 2; s++) {
                    preSum[value][s] = preSum[value - 1][s] + dp[index][value][s];
                }
            }
        }


        if (arr[N - 1] != 0) {
            return dp[N - 1][arr[N - 1]][2];
        } else {
            int ways = 0;
            for (int i = 1; i <= 200; i++) {
                ways += dp[N - 1][i][2];
            }
            return ways;
        }
    }

    public static int getSum(int[][] preSum, int start, int end, int s) {
        return preSum[end][s] - preSum[start - 1][s];
    }


    // for test
    public static int[] generateRandomArray(int len) {
        int[] ans = new int[len];
        for (int i = 0; i < ans.length; i++) {
            if (Math.random() < 0.5) {
                ans[i] = 0;
            } else {
                ans[i] = (int) (Math.random() * 200) + 1;
            }
        }
        return ans;
    }

    // for test
    public static void printArray(int[] arr) {
        System.out.println("arr size : " + arr.length);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int len = 4;
        int testTime = 15;
        System.out.println("test start");
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * len) + 2;
            int[] arr = generateRandomArray(N);
            int ans0 = ways0(arr);
            int ans1 = ways1(arr);
            int ans2 = ways2(arr);
            int ans3 = ways3(arr);
//            if (ans0 != ans1 || ans2 != ans3 || ans0 != ans2) {
//                System.out.println("Oops!");
//            }
            if (ans0 != ans1 || ans0 != ans2 || ans2 != ans3) {
                System.out.println("Oops!");
                System.out.println(ans0);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans3);
                break;
            }
        }
        System.out.println("test end");
        System.out.println("===========");
        int N = 100000;
        int[] arr = generateRandomArray(N);
        long begin = System.currentTimeMillis();
        ways3(arr);
        long end = System.currentTimeMillis();
        System.out.println("test ways3 run time : " + (end - begin) + " ms");
    }


}






