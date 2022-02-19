package com.zim.QuickSort;

public class QuickSort_01 {
    public static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    //在arr中，
    public static int partition(int[] arr, int L, int R){
        if (L > R) {
            return -1;
        }
        if (L == R) {
            return L;
        }
        int index = L;
        int less = L-1;
        while (index < R) {
            if( arr[index] <= arr[R]){
                swap(arr, index, ++less);
            }
            index ++;
        }
        swap( arr, ++less, R);
        return less;
    }
    public static int[] netherlandsFlag(int[] arr, int L, int R){
        if (L > R) {
            return new int[]{-1,-1};
        }
        if (L == R) {
            return new int[] {L, R};
        }
        int less = L-1;
        int more = R;
        int index = L;
        while (index < more){
            if( arr[index] == arr[R]){
                index ++;
            }else if( arr[index] < arr[R]){
                swap(arr, index ++, ++less);
            } else{
                swap(arr, index, --more);
            }
        }
        swap(arr, more, R);
        return new int[]{less+1, more};
    }
    public static void quickSort1(int[] arr){
        if (arr == null || arr.length < 2) {
            return;
        }
        process1(arr, 0, arr.length -1);
    }
    public static void process1(int[] arr, int L, int R){
        if (L >= R) {
            return;
        }
        int mid = partition(arr, L, R);
        process1( arr, L, mid -1);
        process1( arr, mid +1, R);
    }


    public static void quickSort2(int[] arr){
        if (arr == null || arr.length < 2) {
            return;
        }
        process2(arr, 0 , arr.length -1 );
    }
    public static void process2(int[] arr, int L, int R){
        if (L >= R) {
            return;
        }
        int[] op = netherlandsFlag(arr, L, R);
        process2(arr, L, op[0]-1);
        process2(arr, op[1]+1, R);
    }


    public static void quickSort3(int[] arr){
        if (arr.length < 2 || arr == null) {
            return;
        }
        process3(arr, 0 , arr.length -1 );
    }
    public static void process3( int[] arr, int L, int R){
        if (L >= R) {
            return;
        }
        int target = (int) Math.random() * (R - L + 1);
        swap(arr, L + target, R);
        int[] op = netherlandsFlag(arr, L, R);
        process3(arr, L, op[0]-1);
        process3(arr, op[1]+1, R);
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            int[] arr3 = copyArray(arr1);
            quickSort1(arr1);
            quickSort2(arr2);
            quickSort3(arr3);
            if (!isEqual(arr1, arr2) || !isEqual(arr2, arr3)) {
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                printArray(arr3);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Oops!");

    }










}
