package basicAlgo.mergesorted;

public class mergedSort {

    public static void main(String[] args) {
        int[] arr = {2,1,4,3,6,5,8,7};
        mergeSort2(arr);
        printArr(arr);
    }
    public static void printArr(int[] arr){
        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i]);
        }
    }
    //递归
    public static void mergeSorted(int[] arr){
        if (arr.length < 2 || arr == null) {
            return;
        }
        process( arr, 0, arr.length -1);

    }
    public static void process( int[] arr, int left, int right){
        if( left == right){
            return;
        }
        int mid = left + ((right - left) >>1);
        process(arr, left, mid);
        process( arr, mid +1 , right);
        merge( arr, left, mid,right);
    }
    public static void merge(int[] arr, int left, int mid, int right){

        int[] help = new int[right - left+1];
        int lPoint = left;
        int rPoint = mid +1 ;
        int i = 0;
        while(lPoint <= mid && rPoint <= right){
            help[i++] = arr[lPoint] >= arr[rPoint] ? arr[rPoint++] : arr[lPoint ++];
        }
        while(rPoint <= right){
            help[i++] = arr[rPoint++];
        }
        while (lPoint <= left) {
            help[i++] = arr[lPoint ++];
        }
        for( int j = 0; j < help.length ; j ++){
            arr[left + j] = help[j];
        }
    }
    //非递归
    public static void mergeSort2(int[] arr){
        if (arr == null || arr.length < 2) {
            return;
        }
        int step = 1;
        int size = arr.length;
        while (step < size) {
            int left = 0;

            while( left < size){
                if (step >= size-left) {
                    break;
                }
                int mid = left + step - 1;
                int right = mid + Math.min(step, size - mid - 1);
                merge(arr,left,mid,right);
                left = right +1;
            }
            if( step > size/2){
                break;
            }
            step <<= 1;

        }
    }

}
