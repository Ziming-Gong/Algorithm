package basicAlgo.Window;

import java.util.LinkedList;

public class AllLessNumSubArray {

    public static int right(int[] arr, int sum) {
        if (arr == null || arr.length == 0 || sum < 0) {
            return 0;
        }

        int result = 0;

        for (int L = 0; L < arr.length; L++) {
            for (int R = L; R < arr.length; R++) {
                int max = arr[L];
                int min = arr[L];
                for (int i = L; i <= R; i++) {
                    min = Math.min(min, arr[i]);
                    max = Math.max(max, arr[i]);
                }
                if (max - min <= sum) {
                    result++;
                }
            }
        }
        return result;
    }

    public static int num(int[] arr, int sum) {
        if (arr == null || arr.length == 0 || sum < 0) {
            return 0;
        }
        LinkedList<Integer> minQ = new LinkedList<>();
        LinkedList<Integer> maxQ = new LinkedList<>();
        int result = 0;
        int R = 0;
        for (int L = 0; L < arr.length; L++) {
            while (R < arr.length) {
                while (!minQ.isEmpty() && arr[minQ.peekLast()] >= arr[R]) {
                    minQ.pollLast();
                }
                while (!maxQ.isEmpty() && arr[maxQ.peekLast()] <= arr[R]) {
                    maxQ.pollLast();
                }
                minQ.addLast(R);
                maxQ.addLast(R);
                if (arr[maxQ.peekFirst()] - arr[minQ.peekFirst()] <= sum) {
                    R++;
                } else {
                    break;
                }
            }

            result += R - L;
            if(minQ.peekFirst() == L){
                minQ.pollFirst();
            }
            if(maxQ.peekFirst() == L){
                maxQ.pollFirst();
            }
        }
        return result;
    }

    // for test
    public static int[] generateRandomArray(int maxLen, int maxValue) {
        int len = (int) (Math.random() * (maxLen + 1));
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr != null) {
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int maxLen = 100;
        int maxValue = 200;
        int testTime = 100000;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxLen, maxValue);
            int sum = (int) (Math.random() * (maxValue + 1));
            int ans1 = right(arr, sum);
            int ans2 = num(arr, sum);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(sum);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("end");

    }




}
