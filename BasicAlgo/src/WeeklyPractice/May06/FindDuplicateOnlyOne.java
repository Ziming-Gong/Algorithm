package WeeklyPractice.May06;


import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

// 1、2、3...n-1、n、n、n+1、n+2...
// 在这个序列中，只有一个数字有重复(n)
// 这个序列是无序的，找到重复数字n
// 这个序列是有序的，找到重复数字n
public class FindDuplicateOnlyOne {
    public static int right(int[] arr) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            if (set.contains(arr[i])) {
                return arr[i];
            }
            set.add(arr[i]);
        }
        return -1;
    }

    //unsorted array
    public static int findDuplicate(int[] arr) {
        if (arr == null || arr.length < 1) {
            return -1;
        }
        int slow = arr[0];
        int fast = arr[arr[0]];
        while (slow != fast) {
            slow = arr[slow];
            fast = arr[arr[fast]];
        }
        slow = 0;
        while (slow != fast) {
            slow = arr[slow];
            fast = arr[fast];
        }
        return slow;
    }

    //sorted array
    public static int findDuplicateSorted(int[] arr) {
        if (arr == null || arr.length < 1) {
            return -1;
        }
        return process(arr, 0, arr.length - 1);
    }

    public static int process(int[] arr, int left, int right) {
        if (left == right) {
            return left;
        }
        int mid = left + (right - left) / 2;
        if(arr[mid] == mid){
            return process(arr, left, mid);
        }else{
            return process(arr, mid + 1, right);
        }
    }

    // 为了测试
    public static int[] randomArray(int n) {
        int[] ans = new int[n + 1];
        for (int i = 0; i < n; i++) {
            ans[i] = i + 1;
        }
        ans[n] = (int) (Math.random() * n) + 1;
        for (int i = n; i > 0; i--) {
            int j = (int) (Math.random() * (i + 1));
            int tmp = ans[i];
            ans[i] = ans[j];
            ans[j] = tmp;
        }
        return ans;
    }

    // 为了测试
    public static void main(String[] args) {
        int N = 10;
        int testTime = 5000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray((int) (Math.random() * N) + 1);
            if (right(arr) != findDuplicate(arr)) {
                System.out.println("未排序情况出错!");
            }
            Arrays.sort(arr);
            if (right(arr) != findDuplicateSorted(arr)) {
                System.out.println("排序情况出错!");
                for (int num : arr) {
                    System.out.print(num + " ");
                }
                System.out.println();
                System.out.println(right(arr));
                System.out.println(findDuplicateSorted(arr));
                break;
            }
        }
        System.out.println("测试结束");
    }
}
