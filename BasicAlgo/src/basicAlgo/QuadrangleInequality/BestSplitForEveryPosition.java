package basicAlgo.QuadrangleInequality;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import com.sun.prism.es2.ES2Graphics;

public class BestSplitForEveryPosition {

    public static int[] bestSplit1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return new int[0];
        }
        int n = arr.length;
        int[] ans = new int[n];
        ans[0] = 0;
        for (int range = 1; range < n; range++) {
            for (int s = 0; s < range; s++) {
                int sumL = 0;
                for (int l = 0; l <= s; l++) {
                    sumL += arr[l];
                }
                int sumR = 0;
                for (int r = s + 1; r <= range; r++) {
                    sumR += arr[r];
                }
                ans[range] = Math.max(ans[range], Math.min(sumR, sumL));
            }
        }
        return ans;
    }

    public static int[] bestSplit2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return new int[0];
        }
        int n = arr.length;
        int[] sum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + arr[i - 1];
        }
        int[] ans = new int[n];
        for (int range = 1; range < n; range++) {
            for (int i = 0; i < range; i++) {
                int sumL = sum(sum, 0, i);
                int sumR = sum(sum, i + 1, range);
                ans[range] = Math.max(ans[range], Math.min(sumL, sumR));
            }
        }
        return ans;
    }

    public static int[] bestSplit3(int[] arr) {
        if (arr == null || arr.length == 0) {
            return new int[0];
        }
        int n = arr.length;
        int[] sum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i] + arr[i];
        }
        int[] ans = new int[n];
        int line = 0;
        for (int range = 1; range < n; range++) {
            while(line + 1 < range){
                int before = Math.min(sum(sum, 0, line),sum(sum, line+1,range));
                int after = Math.min(sum(sum,0,line+1), sum(sum, line + 2, range));
                if(before <= after){
                    line ++;
                }else {
                    break;
                }

            }
            ans[range] = Math.min(sum(sum, 0, line),sum(sum, line+1,range));
        }
        return ans;
    }


    public static int sum(int[] arr, int left, int right) {
        return arr[right + 1] - arr[left];
    }

    public static int[] randomArray(int len, int max) {
        int[] ans = new int[len];
        for (int i = 0; i < len; i++) {
            ans[i] = (int) (Math.random() * max);
        }
        return ans;
    }

    public static boolean isSameArray(int[] arr1, int[] arr2) {
        if (arr1.length != arr2.length) {
            return false;
        }
        int N = arr1.length;
        for (int i = 0; i < N; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int N = 20;
        int max = 30;
        int testTime = 1000000;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * N);
            int[] arr = randomArray(len, max);
            int[] ans1 = bestSplit1(arr);
            int[] ans2 = bestSplit2(arr);
            int[] ans3 = bestSplit3(arr);
            if (!isSameArray(ans1, ans2) || !isSameArray(ans1, ans3)) {
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("test end");
    }
//

//    public static void main(String[] args) {
//        int[] arr = {4, 9, 5, 7};
//        int[] ans = bestSplit3(arr);
//        for (int i : ans) {
//            System.out.println(i);
//        }
//    }


}
