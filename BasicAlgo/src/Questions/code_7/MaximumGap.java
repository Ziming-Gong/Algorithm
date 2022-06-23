package Questions.code_7;

public class MaximumGap {
    public int maximumGap(int[] arr) {
        if(arr == null || arr.length == 0){
            return 0;
        }
        int maxValue = Integer.MIN_VALUE;
        int minValue = Integer.MAX_VALUE;
        int N = arr.length;
        for(int i = 0; i < N; i ++){
            maxValue = Math.max(maxValue, arr[i]);
            minValue = Math.min(minValue, arr[i]);
        }
        if(maxValue == minValue){
            return 0;
        }
        int buckets = N + 1;
        int[] min = new int[buckets];
        int[] max = new int[buckets];
        boolean[] hasUsed = new boolean[buckets];
        for(int i = 0; i < N; i ++){
            int curB = func(maxValue, minValue, N, arr[i]);
            if(hasUsed[curB]){
                min[curB] = Math.min(min[curB], arr[i]);
                max[curB] = Math.max(max[curB], arr[i]);
            }else{
                min[curB] = arr[i];
                max[curB] = arr[i];
                hasUsed[curB] = true;

            }

        }
        int ans = Integer.MIN_VALUE;
        int small = max[0];
        for(int i = 1; i <= N; i ++){
            if(hasUsed[i]){
                ans = Math.max(ans, min[i] - small);
                small = max[i];
            }
        }
        return ans;
    }

    public int func(long max, long min, long buckets, long num){
        return (int)((num - min)*buckets/(max-min));
    }
}
