package WeeklyPractice.OCT5;

// 来自学员问题
// 商场中有一展柜A，其大小固定，现已被不同的商品摆满
// 商家提供了一些新商品B，需要对A中的部分商品进行更新替换
// B中的商品可以自由使用，也就是可以用B中的任何商品替换A中的任何商品
// A中的商品一旦被替换，就认为消失了！而不是回到了B中！
// 要求更新过后的展柜中，商品严格按照价格由低到高进行排列
// 不能有相邻商品价格相等的情况
// A[i]为展柜中第i个位置商品的价格，B[i]为各个新商品的价格
// 求能够满足A中商品价格严格递增的最小操作次数，若无法满足则返回-1

import javax.print.attribute.standard.PresentationDirection;
import java.util.Arrays;

public class MakeASortedMinSwaps {

    public static int minSwaps(int[] A, int[] B) {
        Arrays.sort(B);
        return process(A, B, 0, 0, 0);
    }

    public static int process(int[] A, int[] B, int ai, int bi, int pre) {
        if (ai == A.length) {
            return 0;
        }
        int preNum;
        if (ai == 0) {
            preNum = Integer.MIN_VALUE;
        } else {
            if (pre == 0) {
                preNum = A[ai - 1];
            } else {
                preNum = B[bi - 1];
            }
        }
        //如果不换：
        int p1 = preNum < A[ai] ? process(A, B, ai + 1, bi, 0) : Integer.MAX_VALUE;
        //如果换
        int nextNumber = getMinIndex(B, bi, preNum);
        int p2 = Integer.MAX_VALUE;
        if (nextNumber != -1) {
            int next = process(A, B, ai + 1, nextNumber + 1, 1);
            if (next != Integer.MAX_VALUE) {
                p2 = next + 1;
            }
        }
        return Math.min(p1, p2);
    }

    public static int getMinIndex(int[] b, int left, int target) {
        int right = b.length - 1;
        int mid = (right + left) / 2;
        int ans = -1;
        while (left <= right) {
            mid = (right + left) / 2;
            if (b[mid] > target) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        int[] A1 = { 1, 8, 3, 6, 9 };
        int[] B1 = { 1, 3, 2, 4 };
        System.out.println(minSwaps(A1, B1));
        int[] A2 = { 4, 8, 3, 10, 5 };
        int[] B2 = { 1, 3, 2, 4 };
        System.out.println(minSwaps(A2, B2));
        int[] A3 = { 1, 8, 3, 6, 9 };
        int[] B3 = { 4, 3, 1 };
        System.out.println(minSwaps(A3, B3));
    }

}
