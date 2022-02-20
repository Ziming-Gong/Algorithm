package basicAlgo.Greedy;

import java.util.PriorityQueue;

public class SplitGold {
    //暴力
    public static int lessMoney1(int[] arr){
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return process(arr, 0);
    }
    public static int process(int[] arr, int cost){
        if (arr.length == 1) {
            return cost;
        }
        int ans = Integer.MAX_VALUE;
        for( int i = 0; i < arr.length; i ++){
            for( int k = i + 1; k < arr.length; k ++){
                ans = Math.min(ans, process(copyAndMergeTwo(arr, i ,k), cost + arr[i] + arr[k]));
            }
        }
        return ans;
    }
    public static int[] copyAndMergeTwo(int[] arr, int i, int j){
        int[] ans = new int[arr.length - 1];
        int index = 0;
        for( int k = 0; k < arr.length; k ++){
            if (k != i && k != j) {
                ans[index++] = arr[k];
            }
        }
        ans[index] = arr[i] + arr[j];
        return ans;

    }


    //非暴力
    public static int lessMoney2(int[] arr){
        if(arr == null && arr.length == 0){
            return 0;
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for( int i = 0; i < arr.length; i ++){
            pq.add(arr[i]);
        }
        int ans = 0;
        while (pq.size() > 1) {
            int sum = pq.poll() + pq.poll();
            ans += sum;
            pq.add( sum );
        }
        return ans;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    public static void main(String[] args) {
        int testTime = 100000;
        int maxSize = 6;
        int maxValue = 1000;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            if (lessMoney1(arr) != lessMoney2(arr)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
