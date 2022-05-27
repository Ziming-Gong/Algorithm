package WeeklyPractice.May18;

import sun.awt.image.OffScreenImage;

import java.util.Arrays;

// 来自字节
// 输入:
// 去重数组arr，里面的数只包含0~9
// limit，一个数字
// 返回:
// 要求比limit小的情况下，能够用arr拼出来的最大数字
public class MaxNumUnderLimit {

    public static int maxNumber2(int[] arr, int limit) {
        limit--;
        Arrays.sort(arr);
        int offset = 1;
        while (limit / 10 >= offset) {
            offset *= 10;
        }

        int next = process(arr, limit, offset);
        if (next != -1) {
            return next;
        } else {
            int right = (limit / offset) % 10;
            int mostRight = findRight(arr, right - 1);

            int max = arr[arr.length - 1];
            return rest(offset / 10, max) + (mostRight != -1 ? mostRight * offset : 0);
        }

    }

    public static int process(int[] arr, int limit, int offset) {
        //之前的所有数和limit一样，直接返回limit
        if (offset == 0) {
            return limit;
        }


        int cur = (limit / offset) % 10; // 当前offset位置得数
        int mostRight = findRight(arr, cur); //在arr中最右边的数
        if (mostRight == -1) { //如果最右的数没有
            return -1;
        } else { //如果最右有数
            //如果 最右边的数字 < cur   ||
            if (arr[mostRight] < cur) {
                return (limit/(offset*10)) * offset * 10
                        + (arr[mostRight] * offset)
                        + rest(offset/10, arr[arr.length-1]);
            } else { //最右边的数 == cur
                int next = process(arr, limit, offset / 10);
                if(next  != -1){
                    return next;
                }else if ( mostRight > 0){
                    mostRight --;
                    return (limit/(offset*10)) * offset * 10
                            + (arr[mostRight] * offset)
                            + rest(offset/10, arr[arr.length-1]);
                }else {
                    return -1;
                }


            }
        }

    }

    public static int rest(int offset, int max) {
        int ans = 0;
        while (offset != 0) {
            ans += max * offset;
            offset /= 10;
        }
        return ans;
    }

    public static int findRight(int[] arr, int num) {
        int l = 0;
        int r = arr.length - 1;
        int m = 0;
        int ans = -1;
        while (l <= r) {
            m = (l + r) >> 1;
            if (arr[m] > num) {
                r = m - 1;
            } else {
                ans = m;
                l = m + 1;
            }
        }
        return ans;
    }


    public static int tmp = 0;

    // 暴力尝试的方法
    public static int maxNumber1(int[] arr, int limit) {
        tmp = 0;
        Arrays.sort(arr);
        limit--;
        int offset = 1;
        while (offset <= limit / 10) {
            offset *= 10;
        }
        process1(arr, 0, offset, limit);
        if (tmp == 0) {
            int rest = 0;
            offset /= 10;
            while (offset > 0) {
                rest += arr[arr.length - 1] * offset;
                offset /= 10;
            }
            return rest;
        }
        return tmp;
    }

    public static void process1(int[] arr, int num, int offset, int limit) {
        if (offset == 0) {
            if (num <= limit) {
                tmp = Math.max(tmp, num);
            }
        } else {
            for (int cur : arr) {
                process1(arr, num * 10 + cur, offset / 10, limit);
            }
        }
    }

    // 为了测试
    public static int[] randomArray() {
        int[] arr = new int[(int) (Math.random() * 10) + 1];
        boolean[] cnt = new boolean[10];
        for (int i = 0; i < arr.length; i++) {
            do {
                arr[i] = (int) (Math.random() * 10);
            } while (cnt[arr[i]]);
            cnt[arr[i]] = true;
        }
        return arr;
    }

    public static void main(String[] args) {
        int max = 3000;
        int testTime = 100;
        System.out.println("测试开始");
        for (int i = 0; i < max; i++) {
            int[] arr = randomArray();
            for (int j = 0; j < testTime; j++) {
                int ans1 = maxNumber1(arr, i);
                int ans2 = maxNumber2(arr, i);
                if (ans1 != ans2) {
                    System.out.println("出错了!");
                    System.out.println("数组为 ：");
                    for (int num : arr) {
                        System.out.print(num + " ");
                    }
                    System.out.println();
                    System.out.println("数字为 ：" + i);
                    System.out.println(ans1);
                    System.out.println(ans2);
                    break;
                }
            }
        }
        System.out.println("测试结束");

    }
}
