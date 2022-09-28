package Questions.code_23;

public class MaxABSBetweenLeftAndRight {

    public static int maxABS(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        return max - Math.min(arr[0], arr[arr.length - 1]);
    }


    public static int maxABS2(int[] arr) {
        int n = arr.length;
        int[] leftMax = new int[n];
        int[] rightMax = new int[n];
        leftMax[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], arr[i]);
        }
        rightMax[n - 1] = arr[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(arr[i], rightMax[i + 1]);
        }
        int ans = 0;
        for (int i = 1; i < n; i++) {
            ans = Math.max(ans, Math.abs(leftMax[i - 1] - rightMax[i]));
        }
        return ans;

    }


    public static int[] generateArr(int maxLen, int maxVal) {
        int n = (int) (Math.random() * maxLen + 1);
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = (int) (Math.random() * maxVal);
        }
        return ans;
    }

    public static void main(String[] args) {
        int maxLen = 10000;
        int maxVal = 10000;
        int testTime = 10000;
        for (int i = 1; i <= testTime; i++) {
            int[] arr = generateArr(maxLen, maxVal);
            int ans1 = maxABS(arr);
            int ans2 = maxABS2(arr);
            if (ans1 != ans2) {
                System.out.println(ans1);
                System.out.println(ans2);
//                print(arr);
                System.out.println("oops");
                break;
            }
        }
        System.out.println("test end");
    }


}
