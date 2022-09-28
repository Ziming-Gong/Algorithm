package Questions.code_22;

//大厂刷题班22节环形山

import java.util.HashSet;
import java.util.Stack;

public class VisibleMountains {

    public static int getVisibleNum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int maxIndex = -1;
        int max = Integer.MIN_VALUE;
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            if (max < arr[i]) {
                max = arr[i];
                maxIndex = i;
            }
        }
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{max, 1});
        int ans = 0;
        int i = maxIndex + 1 == n ? 0 : maxIndex + 1;
        while (i != maxIndex) {
            while (stack.peek()[0] < arr[i]) {
                int[] cur = stack.pop();
                ans += cur[1] * 2 + Cx2(cur[1]);
            }
            if (stack.peek()[0] == arr[i]) {
                stack.peek()[1]++;
            } else {
                stack.push(new int[]{arr[i], 1});
            }
            i++;
            if (i == n) {
                i = 0;
            }
        }

        // P1
        while (stack.size() > 2) {
            int[] cur = stack.pop();
            ans += cur[1] * 2 + Cx2(cur[1]);
        }
        if (stack.size() == 2) {
            int[] cur = stack.pop();
            if (stack.peek()[1] >= 2) {
                ans += cur[1] * 2 + Cx2(cur[1]);
            } else {
                ans += cur[1] + Cx2(cur[1]);
            }
        }
        int[] cur = stack.pop();
        ans += Cx2(cur[1]);
        return ans;

    }

    public static int Cx2(int x) {
        return x == 1 ? 0 : (x * (x - 1) / 2);
    }


    public static int getInternalSum(int k) {
        return k == 1 ? 0 : (k * (k - 1) / 2);
    }

    // 环形数组中当前位置为i，数组长度为size，返回i的下一个位置
    public static int nextIndex(int i, int size) {
        return i < (size - 1) ? (i + 1) : 0;
    }

    // 环形数组中当前位置为i，数组长度为size，返回i的上一个位置
    public static int lastIndex(int i, int size) {
        return i > 0 ? (i - 1) : (size - 1);
    }

    // for test, O(N^2)的解法，绝对正确
    public static int rightWay(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int res = 0;
        HashSet<String> equalCounted = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            // 枚举从每一个位置出发，根据“小找大”原则能找到多少对儿，并且保证不重复找
            res += getVisibleNumFromIndex(arr, i, equalCounted);
        }
        return res;
    }

    public static int getVisibleNumFromIndex(int[] arr, int index,
                                             HashSet<String> equalCounted) {
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i != index) { // 不找自己
                if (arr[i] == arr[index]) {
                    String key = Math.min(index, i) + "_" + Math.max(index, i);
                    // 相等情况下，确保之前没找过这一对
                    if (equalCounted.add(key) && isVisible(arr, index, i)) {
                        res++;
                    }
                } else if (isVisible(arr, index, i)) { // 不相等的情况下直接找
                    res++;
                }
            }
        }
        return res;
    }

    public static boolean isVisible(int[] arr, int lowIndex, int highIndex) {
        if (arr[lowIndex] > arr[highIndex]) { // “大找小”的情况直接返回false
            return false;
        }
        int size = arr.length;
        boolean walkNext = true;
        int mid = nextIndex(lowIndex, size);
        // lowIndex通过next方向走到highIndex，沿途不能出现比arr[lowIndex]大的数
        while (mid != highIndex) {
            if (arr[mid] > arr[lowIndex]) {
                walkNext = false;// next方向失败
                break;
            }
            mid = nextIndex(mid, size);
        }
        boolean walkLast = true;
        mid = lastIndex(lowIndex, size);
        // lowIndex通过last方向走到highIndex，沿途不能出现比arr[lowIndex]大的数
        while (mid != highIndex) {
            if (arr[mid] > arr[lowIndex]) {
                walkLast = false; // last方向失败
                break;
            }
            mid = lastIndex(mid, size);
        }
        return walkNext || walkLast; // 有一个成功就是能相互看见
    }

    // for test
    public static int[] getRandomArray(int size, int max) {
        int[] arr = new int[(int) (Math.random() * size)];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * max);
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int size = 10;
        int max = 10;
        int testTimes = 3000000;
        System.out.println("test begin!");
        for (int i = 0; i < testTimes; i++) {
            int[] arr = getRandomArray(size, max);
            if (rightWay(arr) != getVisibleNum(arr)) {
                printArray(arr);
                System.out.println(rightWay(arr));
                System.out.println(getVisibleNum(arr));
                System.out.println("fxxking oops");
                break;
            }
        }
        System.out.println("test end!");
    }


}
