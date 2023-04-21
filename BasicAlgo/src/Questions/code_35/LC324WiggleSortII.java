package Questions.code_35;

public class LC324WiggleSortII {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6};
        System.out.println(findTopK(nums, 0, nums.length, 4));

    }

    public static int findTopK(int[] arr, int left, int right, int k) {
        if (left == right) {
            return left;
        }
        int pivot = (int) (Math.random() * right - left + 1);
        int partition = partition(left, right, pivot, arr);
        if (partition > k) {
            return findTopK(arr, left, partition - 1, k);
        } else if (partition < k) {
            return findTopK(arr, partition + 1, right, k);
        } else {
            return partition;
        }
    }

    public static int partition(int left, int right, int pivot, int[] arr) {
        int l = left;
        int r = right;
        int point = left;
        while (point < right) {
            if (arr[point] == pivot) {
                point++;
            } else if (arr[point] < pivot) {
                int temp = arr[point];
                arr[point] = arr[l];
                arr[l++] = temp;
                point++;
            } else {
                int temp = arr[point];
                arr[point] = arr[r];
                arr[r] = temp;
                r--;
            }
        }
        return point;


    }

}
