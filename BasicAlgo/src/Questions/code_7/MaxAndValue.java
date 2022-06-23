package Questions.code_7;

// 给定一个正数组成的数组，长度一定大于1，求数组中哪两个数与的结果最大
public class MaxAndValue {
    /**
     * 暴力解
     */
    public static int maxValue(int[] arr) {
        int N = arr.length;
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                ans = Math.max(ans, arr[i] & arr[j]);
            }
        }
        return ans;
    }

    /**
     * 优化
     */
    public static int maxValue2(int[] arr) {
        int N = arr.length;
        int ans = 0;
        int R = N;
        for (int move = 30; move >= 0; move--) {
            int L = 0;
            int temp = R;
            while (L < R) {
                if (((arr[L] >> move) & 1) != 1) {
                    swap(arr, L, --R);
                }else{
                    L++;
                }
            }
            if (L == 2) {
                return arr[0] & arr[1];
            }else if (L < 2){
                R = temp;
            }else{
                ans ^= (1 << move);
            }
        }
        return ans;
    }

    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static int[] randomArray(int size, int range) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * range) + 1;
        }
        return arr;
    }

    public static void main(String[] args) {
        int maxSize = 50;
        int range = 30;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int size = (int) (Math.random() * maxSize) + 2;
            int[] arr = randomArray(size, range);
            int ans1 = maxValue(arr);
//            System.out.println("1");
            int ans2 = maxValue2(arr);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                break;
            }
//            System.out.println("1");
        }
        System.out.println("测试结束");

    }
}











