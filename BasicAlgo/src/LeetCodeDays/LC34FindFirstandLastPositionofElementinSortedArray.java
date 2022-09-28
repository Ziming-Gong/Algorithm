package LeetCodeDays;

public class LC34FindFirstandLastPositionofElementinSortedArray {
    public int[] searchRange(int[] arr, int target) {
        if (arr.length == 0) {
            return new int[]{-1, -1};
        }
        int left = 0;
        int right = arr.length - 1;
        int mid = right >> 1;
        int L = -1;
        int R = -1;
        while (left < right) {
            mid = (right - left) / 2;
            if (arr[mid] == target) {
                L = mid;
                right = mid - 1;
            } else if (arr[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        left = 0;
        right = arr.length - 1;
        while (left < right) {
            mid = (right - left) / 2;
            if (arr[mid] == target) {
                R = mid;
                left = mid - 1;
            } else if (arr[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return new int[]{L, R};
    }

    public static void main(String[] args) {
        System.out.println(Math.pow(2,15));
    }


}
