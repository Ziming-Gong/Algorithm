package basicAlgo.findNum;

import java.util.Arrays;

public class Search {
    public static void main(String[] args) {
        int testTimes = 500000;
        int maxSize = 10;
        int maxValue = 100;
        boolean succed = true;
        for( int i = 1; i <= testTimes; i++){
            int[] arr = generateRandomArray(maxSize, maxValue);
            Arrays.sort(arr);
            int value = (int) ((maxValue+1) * Math.random()) - (int)((maxValue +1) * Math.random());
            if( test(arr, value) != find(arr, value)){
                succed = false;
                break;
            }
        }
        System.out.println(succed);

    }
    //arr is sorted
    public static boolean find(int[] arr, int num){
        if( arr == null || arr.length ==0){
            return false;
        }
        int left = 0;
        int right = arr.length-1;
        while( left <= right){
            int mid = (left + right)/2;
            if(arr[mid] == num){
                return true;
            }
            if(arr[mid] < num){
                left = mid + 1;
            }else {
                right = mid - 1;
            }
        }
        return false;
    }
    //for test
    public static boolean test(int[] arr, int num){
        for( int i: arr){
            if( i == num){
                return true;
            }
        }
        return false;
    }
    //for test 对数器
    public static int[] generateRandomArray(int maxSize, int maxValue){
        int[] arr = new int[(int) ((maxSize+1) * Math.random())];
        for (int i = 0; i < arr.length; i ++){
            arr[i] = (int) ((maxValue+1) * Math.random())- (int) ((maxValue+ 1)* Math.random());
        }
        return arr;
    }
}
