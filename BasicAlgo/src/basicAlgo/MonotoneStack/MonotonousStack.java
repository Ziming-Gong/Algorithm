package basicAlgo.MonotoneStack;

import basicAlgo.Recursive.ReverseStackUsingRecursive;

import java.util.ArrayList;
import java.util.Stack;

public class MonotonousStack {
    public static int[][] getNearLessNoRepeat(int[] arr){

        int N = arr.length;

        Stack<Integer> stack = new Stack<>();
        int[][] result = new int[N][2];
        for(int i = 0; i < N; i ++){
            while(!stack.isEmpty() && arr[stack.peek()] > arr[i]){
                int cur = stack.pop();
                result[cur][0] = stack.isEmpty() ? -1 : stack.peek();
                result[cur][1] = i;
            }
            stack.push(i);
        }

        while(!stack.isEmpty()){
            int cur = stack.pop();
            result[cur][0] = stack.isEmpty() ? -1 : stack.peek();
            result[cur][1] = -1;
        }
        return result;
    }
    public static int[][] getNearLess(int[] arr) {
        int N = arr.length;
        int[][] result = new int[N][2];
        Stack<ArrayList<Integer>> stack = new Stack<>();
        for(int i = 0; i < N; i ++){
            while(!stack.isEmpty() && arr[stack.peek().get(0)] > arr[i]){
                ArrayList<Integer> list = stack.pop();
                for(int cur : list){
                    result[cur][0] = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
                    result[cur][1] = i;
                }
            }
            if(!stack.isEmpty() && arr[stack.peek().get(0)] == arr[i]){
                stack.peek().add(i);
            }else{
                ArrayList<Integer> list = new ArrayList<>();
                list.add(i);
                stack.push(list);
            }
        }

        while(!stack.isEmpty()){
            ArrayList<Integer> list = stack.pop();
            for(int cur : list){
                result[cur][0] = stack.isEmpty() ? -1: stack.peek().get(stack.peek().size() - 1);
                result[cur][1] = -1;
            }
        }
        return result;
    }

    // for test
    public static int[] getRandomArrayNoRepeat(int size) {
        int[] arr = new int[(int) (Math.random() * size) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        for (int i = 0; i < arr.length; i++) {
            int swapIndex = (int) (Math.random() * arr.length);
            int tmp = arr[swapIndex];
            arr[swapIndex] = arr[i];
            arr[i] = tmp;
        }
        return arr;
    }

    // for test
    public static int[] getRandomArray(int size, int max) {
        int[] arr = new int[(int) (Math.random() * size) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * max) - (int) (Math.random() * max);
        }
        return arr;
    }

    // for test
    public static int[][] rightWay(int[] arr) {
        int[][] res = new int[arr.length][2];
        for (int i = 0; i < arr.length; i++) {
            int leftLessIndex = -1;
            int rightLessIndex = -1;
            int cur = i - 1;
            while (cur >= 0) {
                if (arr[cur] < arr[i]) {
                    leftLessIndex = cur;
                    break;
                }
                cur--;
            }
            cur = i + 1;
            while (cur < arr.length) {
                if (arr[cur] < arr[i]) {
                    rightLessIndex = cur;
                    break;
                }
                cur++;
            }
            res[i][0] = leftLessIndex;
            res[i][1] = rightLessIndex;
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[][] res1, int[][] res2) {
        if (res1.length != res2.length) {
            return false;
        }
        for (int i = 0; i < res1.length; i++) {
            if (res1[i][0] != res2[i][0] || res1[i][1] != res2[i][1]) {
                return false;
            }
        }

        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int size = 10;
        int max = 20;
        int testTimes = 2000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            int[] arr1 = getRandomArrayNoRepeat(size);
            int[] arr2 = getRandomArray(size, max);
            if (!isEqual(getNearLessNoRepeat(arr1), rightWay(arr1))) {
                System.out.println("Oops1!");
                printArray(arr1);
                break;
            }
            if (!isEqual(getNearLess(arr2), rightWay(arr2))) {
                System.out.println("Oops2!");
                printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");
    }
}
