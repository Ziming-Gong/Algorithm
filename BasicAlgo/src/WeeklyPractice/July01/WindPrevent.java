package WeeklyPractice.July01;

import Questions.code_7.WordBreak;
import basicAlgo.Recursive.PrintAllPermutations;
import sun.awt.image.ImageWatched;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// 来自真实笔试
// 给定一个二维数组matrix，数组中的每个元素代表一棵树的高度。
// 你可以选定连续的若干行组成防风带，防风带每一列的防风高度为这一列的最大值
// 防风带整体的防风高度为，所有列防风高度的最小值。
// 比如，假设选定如下三行
// 1 5 4
// 7 2 6
// 2 3 4
// 1、7、2的列，防风高度为7
// 5、2、3的列，防风高度为5
// 4、6、4的列，防风高度为6
// 防风带整体的防风高度为5，是7、5、6中的最小值
// 给定一个正数k，k <= matrix的行数，表示可以取连续的k行，这k行一起防风。
// 求防风带整体的防风高度最大值
public class WindPrevent {

    public static int bestHeight1(int[][] matrix, int k) {
        int n = matrix.length;
        int m = matrix[0].length;
        int ans = 0;
        for (int startRow = 0; startRow < n; startRow++) {
            int bottleNeck = Integer.MAX_VALUE;
            for (int col = 0; col < m; col++) {
                int height = 0;
                for (int endRow = startRow; endRow < n && (endRow - startRow + 1 <= k); endRow++) {
                    height = Math.max(height, matrix[endRow][col]);
                }
                bottleNeck = Math.min(bottleNeck, height);
            }
            ans = Math.max(ans, bottleNeck);
        }
        return ans;
    }

    public static int bestHeight2(int[][] matrix, int k) {
        int n = matrix.length;
        int m = matrix[0].length;
        int ans = Integer.MAX_VALUE;
        int temp = 0;
        ArrayList<LinkedList<Integer>> windows = new ArrayList<>();
        for (int j = 0; j < m; j++) {
            windows.add(new LinkedList<>());
            windows.get(j).add(0);
            LinkedList<Integer> cur = windows.get(j);
            for (int i = 1; i < k; i++) {
                while (!cur.isEmpty() && matrix[cur.peekLast()][j] <= matrix[i][j]) {
                    cur.pollLast();
                }
                cur.addLast(i);
            }
            ans = Math.min(ans, matrix[cur.peekFirst()][j]);
        }

        for (int i = k; i < n; i++) {
            temp = Integer.MAX_VALUE;
            for (int j = 0; j < m; j++) {
                LinkedList<Integer> cur = windows.get(j);
                if (!cur.isEmpty() && ((cur.peekFirst() == i - k)  )) {
                    cur.pollFirst();
                }
                while (!cur.isEmpty() && ((matrix[cur.peekLast()][j] <= matrix[i][j]))){
                    cur.pollLast();
                }
                cur.addLast(i);
                temp = Math.min(temp, matrix[cur.peekFirst()][j]);
            }
            ans = Math.max(ans, temp);
        }

        return ans;
    }

    public static int[][] generateMatrix(int n, int m, int v) {
        int[][] matrix = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = (int) (Math.random() * v) + 1;
            }
        }
        return matrix;
    }

    public static void main(String[] args) {
        int[][] ma = {{29,50,44,10},{18,40,49,44},{17,42,2,42},{37,13,38,20},{50,26,33,21}};
        System.out.println(bestHeight2(ma,4));



        int nMax = 5;
        int mMax = 5;
        int vMax = 50;
        int testTimes = 1000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            int n = (int) (Math.random() * nMax) + 1;
            int m = (int) (Math.random() * mMax) + 1;
            int[][] matrix = generateMatrix(n, m, vMax);
            int k = (int) (Math.random() * n) + 1;
            int ans1 = bestHeight1(matrix, k);
            int ans2 = bestHeight2(matrix, k);
            if (ans1 != ans2) {
                System.out.println(k);
                System.out.println(ans1);
                System.out.println(ans2);
                print(matrix);
                System.out.println("出错了!");
                break;
            }
        }
        System.out.println("测试结束");
    }

    public static void print(int[][] arr){
        for(int i = 0; i < arr.length; i ++){
            for(int j = 0; j < arr[0].length; j ++){
                System.out.printf(arr[i][j] + ",");
            }
            System.out.println();
        }
    }


}
















