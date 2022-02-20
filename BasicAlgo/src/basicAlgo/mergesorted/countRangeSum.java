package basicAlgo.mergesorted;

public class countRangeSum {
    public int countRangeSum(int[] nums, int lower, int upper) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        long[] preSum = new long[nums.length];
        preSum[0] = nums[0];
        for(int i = 1; i < nums.length; i++){
            preSum[i] = nums[i] + preSum[i - 1];
        }
        return process(preSum, 0, nums.length - 1, lower, upper);
    }
    public static int process(long[] arr, int L, int R, int lower, int upper){
        if(L == R){
            return arr[L] >= lower && arr[L] <= upper ? 1 : 0;
        }
        int mid = L + ((R - L) >> 1);
        return  process(arr, L , mid, lower, upper) +
                process(arr, mid +1, R, lower, upper) +
                merge(arr, L, mid, R, lower, upper);
    }
    public static int merge(long[] arr, int L, int mid, int R, int lower, int upper){
        int count = 0;
        int p1 = L;
        int p2 = L;
        for( int i = mid +1 ; i <=R ; i ++){
            long max = arr[i] - lower;
            long min = arr[i] - upper;
            while( p1 <= mid && p1 < min){
                p1 ++;
            }
            while( p2 <= mid && p2 <=max){
                p2 ++;
            }
            count += p2 - p1 ;
        }
        long[] help = new long[R - L +1 ];
        p1 = L;
        p1 = mid + 1;
        int index = 0;
        while( p1 <= mid && p2 <= R){
            help[index++] = arr[p1] <= arr[p2]? arr[p1++] : arr[p2++];
        }
        while( p1 <= mid){
            help[index++] = arr[p1++];
        }
        while( p2 <= R){
            help[index++] = arr[p2++];
        }
        for(int i = 0; i < help.length; i++){
            arr[i+L] = help[i];
        }
        return count;
    }
}
