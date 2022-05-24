package basicAlgo.SubarrayLength;

public class LongestLessSumSubArrayLength {
    public static int maxLengthAwesome(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[] minSum = new int[arr.length];
        int[] minSumEnd = new int[arr.length];
        int n = arr.length;
        minSum[n - 1] = arr[n - 1];
        minSumEnd[n - 1] = n - 1;
        for (int i = arr.length - 2; i >= 0; i--) {
            if (minSum[i + 1] <= 0) {
                minSum[i] = arr[i] + minSum[i + 1];
                minSumEnd[i] = minSumEnd[i + 1];
            } else {
                minSum[i] = arr[i];
                minSumEnd[i] = i;
            }
        }
        int ans = 0;
        int end = 0;
        int r = 0;
        int sum = 0;

        for (int i = 0; i < n; i++) {
            while (end < arr.length && sum + minSum[end] <= k) {
                sum += minSum[end];
                r = minSumEnd[end];
                end = r + 1;
            }
            ans = Math.max(ans, end - i);
            if (i < end) {
                sum -= arr[i];
            } else {
                end = i + 1;
            }
        }
        return ans;
    }


    public static int maxLength(int[] arr, int k) {
        int[] h = new int[arr.length + 1];
        int sum = 0;
        h[0] = sum;
        for (int i = 0; i != arr.length; i++) {
            sum += arr[i];
            h[i + 1] = Math.max(sum, h[i]);
        }
        sum = 0;
        int res = 0;
        int pre = 0;
        int len = 0;
        for (int i = 0; i != arr.length; i++) {
            sum += arr[i];
            pre = getLessIndex(h, sum - k);
            len = pre == -1 ? 0 : i - pre + 1;
            res = Math.max(res, len);
        }
        return res;
    }

    public static int getLessIndex(int[] arr, int num) {
        int low = 0;
        int high = arr.length - 1;
        int mid = 0;
        int res = -1;
        while (low <= high) {
            mid = (low + high) / 2;
            if (arr[mid] >= num) {
                res = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return res;
    }

    // for test
    public static int[] generateRandomArray(int len, int maxValue) {
        int[] res = new int[len];
        for (int i = 0; i != res.length; i++) {
            res[i] = (int) (Math.random() * maxValue) - (maxValue / 3);
        }
        return res;
    }

    public static void main(String[] args) {
//        int[] arr = {5, 3, 6, -4, -2, 1, -6, 5};
//        int[] minSum = new int[arr.length];
//        int[] minSumEnd = new int[arr.length];
//        int n = arr.length;
//        minSum[n - 1] = arr[n - 1];
//        minSumEnd[n - 1] = n - 1;
//        for (int i = arr.length - 2; i >= 0; i--) {
//            if (minSum[i + 1] <= 0) {
//                minSum[i] = arr[i] + minSum[i + 1];
//                minSumEnd[i] = minSumEnd[i + 1];
//            } else {
//                minSum[i] = arr[i];
//                minSumEnd[i] = i;
//            }
//        }
//        for (int i : minSum) {
//            System.out.printf(i + ", ");
//        }
//        System.out.println();
//        for (int i : minSumEnd) {
//            System.out.printf(", " + i);
//        }


        System.out.println("test begin");
        for (int i = 0; i < 10000000; i++) {
            int[] arr = generateRandomArray(10, 20);
            int k = (int) (Math.random() * 20) - 5;
            if (maxLengthAwesome(arr, k) != maxLength(arr, k)) {
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("test finish");
    }
}
