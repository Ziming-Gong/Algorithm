import java.util.function.IntPredicate;

public class sortedMerge {

    public static void main(String[] args) {
        int[] arr = {4,5,3,7,4,2,1};
        int p1 =smallSumProcess(arr,0,arr.length-1);
        //process(arr,0,arr.length-1);
        //print(arr);
        System.out.println(p1);

    }
    public static void print(int[] arr){
        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i]);
        }
    }
    //归并排序
    //递归方法
    //T(N) = 2T(N/2)+O(N)
    //时间复杂度O（NlogN）
    public static void process(int[] arr, int left, int right){
        if (left == right) {
            return;
        }
        int mid = left + ((right-left)>>1);
        process(arr, left,mid);
        process(arr, mid+1, right);
        merge(arr,left,right,mid);
    }
    public static void merge(int[] arr, int left, int right, int mid){
        int[] merge = new int[right-left+1];
        int i = 0;
        int p1 = left;
        int p2 = mid+1;
        while( p1 <= mid && p2 <=right){
            merge[i++] = arr[p1]<= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= mid) {
            merge[i++] = arr[p1++];
        }
        while (p2 <= right) {
            merge[i++] = arr[p2++];
        }
        for( i = 0; i < merge.length; i++){
            arr[left+i] = arr[i];
        }
    }
    //归并排序
    //非递归方法
    public static void mergeSorted(int[] arr){
        if(arr == null || arr.length< 2){
            return;
        }
        int mergeSize = 1;
        while( mergeSize < arr.length){
            int left = 0;
            while(left < arr.length){
                int mid = left + mergeSize -1;
                if( mid >= arr.length){
                    break;
                }
                int right = mid + 1;
                merge(arr, left, right, mid);
                left = right +1;
            }
            //防止溢出
            if( mergeSize > arr.length /2){
                break;
            }
            mergeSize <<=1;//mergeSize乘2
        }
    }


    //小和问题 求每个数左边的比他小的数的和
    public static int smallSum(int[] arr){
        if(arr == null || arr.length < 2){
            return 0;
        }
        return smallSumProcess(arr, 0, arr.length -1);
    }
    public static int smallSumProcess(int[] arr, int left, int right){
        if (left == right) {
            return 0;
        }
        int mid = left + ((right - left)>>1);
        return smallSumProcess(arr, left, mid)+
                smallSumProcess(arr, mid + 1, right)+
                smallSumMerge(arr, left, right, mid);
    }
    public static int smallSumMerge(int[] arr,int left, int right, int mid){
        int[] sorted = new int[right-left +1];
        int mergeSize = 1;
        int index = 0;
        int p1 = left;
        int p2 = mid + 1;
        int result = 0;
        while (p1<= mid && p2 <=right){
            result += arr[p1]< arr[p2] ? arr[p1] * (right - p2 +1): 0;
            sorted[index++] = arr[p1] <arr[p2] ? arr[p1++] : arr[p2++];
        }
        while( p1 <= mid){
            sorted[index++] = arr[p1++];
        }
        while (p2 <= right) {
            sorted[index++] = arr[p2++];
        }
        for(int i = 0; i < sorted.length; i++){
            arr[left+i] = sorted[i];
        }

        return result;
    }











}
