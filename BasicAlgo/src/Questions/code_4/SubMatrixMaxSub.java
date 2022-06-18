package Questions.code_4;

//https://leetcode.cn/problems/max-submatrix-lcci/comments/
public class SubMatrixMaxSub {
    public int[] getMaxMatrix(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int max = Integer.MIN_VALUE;
        int a = 0;
        int b = 0;
        int c = 0;
        int d = 0;
        int cur = 0;
        for(int i = 0; i < n; i ++){
            int[] help = new int[m];
            for(int j = i; j < n; j ++){
                cur = 0;
                int begin = 0;
                for(int k = 0; k < m; k ++){
                    help[k] += matrix[j][k];
                    cur += help[k];
                    if(cur > max){
                        a = i;
                        b = begin;
                        c = j;
                        d = k;
                        max = cur;
                    }
                    if(cur < 0){
                        cur = 0;
                        begin = k + 1;
                    }
                }
            }
        }
        return new int[]{a,b,c,d};
    }
}
