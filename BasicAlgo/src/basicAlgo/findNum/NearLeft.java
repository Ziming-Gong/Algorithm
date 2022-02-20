package basicAlgo.findNum;

import java.util.Arrays;

public class NearLeft {
    public static void main(String[] args) {
        int testTimes = 5000000;
        int maxSize = 10;
        int maxValue = 100;
        boolean succed = true;
        for( int i = 1; i <= testTimes; i++){
            int[] arr = generateRandomArray(maxSize, maxValue);
            Arrays.sort(arr);
            int value = (int) ((maxValue+1) * Math.random()) - (int)((maxValue +1) * Math.random());
            if( test(arr, value) != theMostLeftNum(arr, value)){
                succed = false;
                break;
            }
        }
        System.out.println(succed);
    }

    public static int theMostLeftNum(int[] arr, int num){
        if( arr == null || arr.length == 0){
            return -1;
        }
        int left = 0;
        int right = arr.length -1;
        int ans = -1;
        while( left <= right){
            int mid = (left + right)/2;
            if (arr[mid] >= num) {
                ans = mid;
                right = mid -1;
            }else{
                left = mid +1 ;
            }
        }
        return ans;
    }
    //test
    public static int test(int[] arr, int num){
        for (int i = 0; i < arr.length; i++){
            if( arr[i] >= num ){
                return i;
            }
        }
        return -1;
    }
    //对数器
    public static int[] generateRandomArray(int maxSize, int maxValue){
        int[] arr = new int[(int) ((maxSize+1) * Math.random())];
        for (int i = 0; i < arr.length; i ++){
            arr[i] = (int) ((maxValue+1) * Math.random())- (int) ((maxValue+ 1)* Math.random());
        }
        return arr;
    }
}
