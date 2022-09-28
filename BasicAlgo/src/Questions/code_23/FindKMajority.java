package Questions.code_23;

import java.util.HashMap;
import java.util.Map.Entry;

public class FindKMajority {
    public static int FindMajority(int[] arr) {
        int n = arr.length;
        int HP = 0;
        int cur = 0;
        for (int i = 0; i < n; i++) {
            if (HP == 0) {
                cur = arr[i];
                HP = 1;
                continue;
            }
            if (cur != arr[i]) {
                HP--;
            } else {
                HP++;
            }
        }
        if (HP == 0) {
            return -1;
        }
        int sum = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] == cur) {
                sum++;
            }
        }
        return sum > n / 2 ? cur : -1;
    }

    public static int right(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : arr) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        int n = arr.length;
        n /= 2;
        for (Entry<Integer, Integer> curM : map.entrySet()) {
            if (curM.getValue() > n) {
                return curM.getKey();
            }
        }
        return -1;
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
        int maxVal = 100;
        int testTime = 10000;
        for (int i = 1; i <= testTime; i++) {
            int[] arr = generateArr(maxLen, maxVal);
            int ans1 = FindMajority(arr);
            int ans2 = right(arr);
            if (ans1 != ans2) {
                System.out.println(ans1);
                System.out.println(ans2);
                print(arr);
                System.out.println("oops");
                break;
            }
        }
        System.out.println("test end");
    }

    public static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.printf(arr[i] + ", ");
        }
        System.out.println();
    }

}



















