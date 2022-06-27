package basicAlgo.DynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class NQueens {
    public static List<List<String>> solveNQueens(int n) {
        int limit = (1 << n) - 1;
        List<List<String>> list = new ArrayList<>();
        int[] queen = new int[n];
        process(limit, 0, 0, 0, list, queen,0);
        return list;
    }

    public static void process(int limit, int col, int left, int right, List<List<String>> list, int[] queen, int n) {
        if (col == limit) {
            generateList(queen, list);
        } else {
            // pos 1 0 1 0 0 1
            int pos = limit & (~(col | left | right));
            int mostRight = 0;
            while (pos != 0) {
                mostRight = pos & (~pos + 1);
                pos -= mostRight;
                int index = getN(mostRight);
                queen[n] = index;
                process(limit, col | mostRight, left | (mostRight << 1), right | (mostRight >> 1), list, queen,n+1);
                queen[n] = -1;
            }
        }
    }

    public static int getN(int mostRight) {
        int ans = 0;
        while ((1 << ans) != mostRight) {
            ans++;
        }
        return ans;
    }
    // queen[i] = j row = i, col = j
    public static void  generateList(int[] queen, List<List<String>> list) {
        List<String> res = new ArrayList<>();
        for(int i = 0; i < queen.length; i ++){
            char[] str = new char[queen.length];
            Arrays.fill(str,0,queen[i] -  1,'.');
            Arrays.fill(str,queen[i],queen[i],'Q');
            Arrays.fill(str,queen[i]+1,queen.length,'.');
            res.add(str.toString());
        }
        list.add(res);
    }

    public static void main(String[] args) {
        int n = 4;
        List<List<String>> list = solveNQueens(n);
        System.out.println();
    }
}
