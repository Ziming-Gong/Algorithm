package basicAlgo.MonotoneStack;

import java.util.Stack;

public class AllTimesMinToMax {
    public static int max1(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                int minNum = Integer.MAX_VALUE;
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += arr[k];
                    minNum = Math.min(minNum, arr[k]);
                }
                max = Math.max(max, minNum * sum);
            }
        }
        return max;
    }

    public static int max2(int[] arr) {
        int N = arr.length;
        int max = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<>();
        int[] prefix = new int[N];
        prefix[0] = arr[0];
        for (int i = 1; i < N; i++) {
            prefix[i] = arr[i] + prefix[i - 1];
        }
        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                int cur = stack.pop();
                max = Math.max(max, (stack.isEmpty() ? prefix[i - 1] : prefix[i - 1] - prefix[stack.peek()]) * arr[cur]);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int cur = stack.pop();
            max = Math.max(max, (stack.isEmpty() ? prefix[N - 1] : prefix[N - 1] - prefix[stack.peek()]) * arr[cur]);
        }
        return max;

    }

    public static int[] gerenareRondomArray() {
        int[] arr = new int[(int) (Math.random() * 20) + 10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 101);
        }
        return arr;
    }

    public static void main(String[] args) {
        int testTimes = 2000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            int[] arr = gerenareRondomArray();
            if (max1(arr) != max2(arr)) {
                System.out.println("FUCK!");
                System.out.println(max1(arr));
                System.out.println(max2(arr));
                break;
            }
        }
        System.out.println("test finish");
    }



//https://leetcode.com/problems/3sum-with-multiplicity/
    public int maxSumMinProduct(int[] nums) {

        int N = nums.length;
        long[] sums = new long[N];
        sums[0] = nums[0];
        for(int i = 1; i < N; i ++){
            sums[i] = sums[i-1]+nums[i];
        }
        int[] stack = new int[N];
        int size = 0;
        long max = Long.MIN_VALUE;
        for(int i = 0; i < N; i ++){
            while(size != 0 && nums[stack[size - 1]] >= nums[i]){
                int cur = stack[--size];
                max = Math.max(max, (size == 0 ? sums[i-1] : (sums[i-1] - sums[stack[size-1]]))*nums[cur]);
            }
            stack[size++] = i;
        }
        while(size != 0){
            int j = stack[--size];
            max = Math.max(max, (size == 0 ? sums[N-1]:sums[N-1]-sums[stack[size-1]])*nums[j]);
        }
        return (int) (max % 1000000007);
    }
}
