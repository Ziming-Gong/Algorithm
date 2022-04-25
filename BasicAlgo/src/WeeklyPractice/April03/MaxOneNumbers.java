package WeeklyPractice.April03;

public class MaxOneNumbers {
    public static int maxOneNumbers1(int[] arr) {
        if(arr == null || arr.length == 0){
            return 0;
        }
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                reverse(arr, i, j);
                max = Math.max(max, count(arr));
                reverse(arr, i, j);
            }
        }
        return max;
    }

    public static void reverse(int[] arr, int l, int r) {
        for (int i = l; i <= r; i++) {
            arr[i] ^= 1;
        }
    }

    public static int count(int[] arr) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 1) {
                count++;
            }
        }
        return count;
    }

    public static int maxOneNumbers2(int[] arr) {
        if(arr == null || arr.length == 0){
            return 0;
        }
        int count = 0;
        for(int i = 0; i < arr.length; i ++){
            if(arr[i] == 1){
                count ++;
            }
            arr[i] = arr[i] == 0 ? 1 : -1;
        }
        int cur = 0;
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < arr.length; i ++){
            cur+= arr[i];
            max = Math.max(max, cur);
            cur = cur < 0 ? 0 : cur;
        }
        return count + max;
    }
    // for test
    public static int[] randomArray(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = (int) (Math.random() * 2);
        }
        return arr;
    }

    // for test
    public static void main(String[] args) {
        int N = 100;
        int testTime = 20000;
        System.out.println("begin");
        for (int i = 0; i < testTime; i++) {
            int n = (int) (Math.random() * N) + 1;
            int[] arr = randomArray(n);
            int ans1 = maxOneNumbers1(arr);
            int ans2 = maxOneNumbers2(arr);
            if (ans1 != ans2) {
                System.out.println("fuck！");
            }
        }
        System.out.println("end");
    }

}
