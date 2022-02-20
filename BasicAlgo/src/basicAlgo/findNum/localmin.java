package basicAlgo.findNum;

import java.util.HashMap;
import java.util.TreeMap;

public class localmin {
    public static int aMinIndex(int[] arr){
        if( arr == null || arr.length == 0){
            return -1;
        }
        if (arr.length == 1) {
            return 0;
        }
        if (arr.length == 2) {
            return arr[0] < arr[1]? 0:1;
        }

        if(arr[0] < arr[1]){
            return 0;
        }
        if( arr[arr.length -1] < arr[arr.length -2 ]){
            return arr.length -1;
        }
        int left = 0;
        int right = arr.length -1;
        int ans = -1;
        while ( left < right -1){
            int mid = (left + right)/2;
            if( arr[ mid] < arr[mid-1] && arr[mid] < arr[mid]+1){
                return mid;
            }
            if( arr[ mid] > arr[mid-1]){
                right = mid - 1;
                continue;
            }
            if( arr[ mid] > arr[mid+1]){
                left = mid + 1;
                continue;
            }
        }
        return arr[left] < arr[right] ? left : right;
    }

    public static int[] randomArray(int maxLen, int maxValue){
        int len = (int) (Math.random()*maxLen);
        int[] arr = new int[len];
        if(len> 0){
            arr[0] = (int) (Math.random() * maxValue);
            for (int i = 1; i < len; i++){
                do {
                    arr[i] = (int) (Math.random()*maxValue);
                }while (arr[i] == arr[i-1]);
            }
        }
        return arr;
    }
    // for test
    public static boolean check( int[] arr, int index){
        if(arr.length == 0){
            return index == -1;
        }
        int left = index -1;
        int right = index +1;
        boolean leftBigger = left >= 0 ? arr[left] > arr[index] : true;
        boolean rightBigger = right < arr.length? arr[right] > arr[index] : true;
        return  leftBigger || rightBigger;

    }
    public static void print(int[] arr){
        for (int num : arr){
            System.out.print(num+", ");

        }
        System.out.println();
    }
    public static void main(String[] args) {
        int testTime = 10000;
        int maxLen = 10;
        int maxValue = 200;
        for (int i = 0; i < testTime; i ++){
            int[] arr = randomArray(maxLen, maxValue);
            //print(arr);
            int ans = aMinIndex(arr);
            if( !check(arr, ans)){
                System.out.println("wrong");
                print(arr);
                System.out.println(ans);
                break;

            }


        }

    }
}
