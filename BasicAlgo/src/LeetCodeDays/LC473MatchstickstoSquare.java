package LeetCodeDays;

import Questions.code_5.Hash;

import java.util.Arrays;
import java.util.HashMap;

public class LC473MatchstickstoSquare {
    public boolean makesquare(int[] arr) {
        if (arr.length < 4) {
            return false;
        }
        int N = arr.length;
        int sum = 0;
        for (int i = 0; i < N; i++) {
            sum += arr[i];
        }
        if (sum % 4 != 0) {
            return false;
        }
//        f(arr, 0, N - 1);

//        Arrays.sort(arr, (a,b)->(b - a));

        return process(arr, 0, 0, 0, 0, 0, sum / 4);

    }

    public boolean process(int[] arr, int index, int side1, int side2, int side3, int side4, int target) {
        if (side1 > target || side2 > target || side3 > target || side4 > target) {
            return false;
        }
        if (index == arr.length) {
            return side1 == target && side2 == target && side3 == target && side4 == target;
        }
        boolean p1 = process(arr, index + 1, side1 + arr[index], side2, side3, side4, target);
        boolean p2 = process(arr, index + 1, side1, side2 + arr[index], side3, side4, target);
        boolean p3 = process(arr, index + 1, side1, side2, side3 + arr[index], side4, target);
        boolean p4 = process(arr, index + 1, side1, side2, side3, side4 + arr[index], target);
        return p1 || p2 || p3 || p4;
    }

    public void f(int[] arr, int L, int R) {
        if (L == R) {
            return;
        }
        int m = (L + R) >> 1;
        f(arr, L, m);
        f(arr, m + 1, R);
        merge(arr, L, R, m);
    }

    public void merge(int[] arr, int L, int R, int M) {
        int[] help = new int[R - L + 1];
        int lPoint = L;
        int rPoint = M + 1;
        int index = 0;
        while (lPoint <= M && rPoint <= R) {
            help[index++] = arr[lPoint] <= arr[rPoint] ? arr[rPoint++] : arr[lPoint ++];;
        }
        while (lPoint <= M) {
            help[index++] = arr[lPoint++];
        }
        while (rPoint <= R) {
            help[index++] = arr[rPoint++];
        }
        for (int i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
    }

//    public boolean makesquare(int[] arr) {
//        if (arr.length < 4) {
//            return false;
//        }
//        int sum = 0;
//        int N = arr.length;
//        for (int i = 0; i < N; i++) {
//            sum += arr[i];
//        }
//        if (sum % 4 != 0) {
//            return false;
//        }
//        int side = sum / 4;
//
//        for (int i = 0; i < N; i++) {
//            int s = arr[i];
//            int ans = 0;
//            if (s <= side) {
//                ans |= (1 << i);
//                for (int j = i + 1; j < N; j++) {
//                    if (s + arr[j] <= side) {
//                        s += arr[j];
//
//                        for (int k = j + 1; k < N; k++) {
//                            for (int h = 0; h < N; h++) {
//
//                            }
//                        }
//                    }
//
//                }
//            }
//
//        }
//    }


    public static void main(String[] args) {

    }

}









