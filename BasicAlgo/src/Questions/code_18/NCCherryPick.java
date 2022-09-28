package Questions.code_18;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class NCCherryPick {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            int N = (int) in.nval;
            in.nextToken();
            int M = (int) in.nval;
            int[][] matrix = new int[N][M];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    in.nextToken();
                    matrix[i][j] = (int) in.nval;
                }
            }
            out.println(pick(matrix));
            out.flush();
        }
    }

    public static int pick(int[][] matrix){
        int n = matrix.length;
        int m = matrix[0].length;
        int[][][] dp = new int[n][m][n];
        for(int i = 0; i < n; i ++){
            for(int j = 0; j < m; j ++){
                for(int k = 0; k < n; k ++){
                    dp[i][j][k] = -1;
                }
            }
        }
        dp[n - 1][m - 1][n - 1] = matrix[n - 1][m - 1];
        return process(matrix, 0, 0, 0, dp);
    }

    public static int process(int[][] matrix, int a, int b, int c, int[][][] dp){

        if(a == matrix.length - 1 && b == matrix[0].length - 1){
            return matrix[a][b];
        }
        if(dp[a][b][c] != -1){
            return dp[a][b][c];
        }

        int d = a + b - c;
        int next = 0;
        if(a + 1 < matrix.length){
            if(c + 1 < matrix.length){
                next = Math.max(next, process(matrix, a + 1, b, c + 1, dp));
            }
            if(d + 1 < matrix[0].length){
                next = Math.max(next, process(matrix, a + 1, b, c, dp));
            }
        }
        if(b + 1 < matrix[0].length){
            if(c + 1 < matrix.length){
                next = Math.max(next, process(matrix, a, b + 1, c + 1, dp));
            }
            if(d + 1 < matrix[0].length){
                next = Math.max(next, process(matrix, a, b + 1, c, dp));
            }
        }
        int cur = 0;
        if(a == c){
            cur = matrix[a][b];
        }else{
            cur = matrix[a][b] + matrix[c][d];
        }
        dp[a][b][c] = cur + next;
        return cur + next;

    }
}