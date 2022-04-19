package WeeklyPractice.April02;

import java.util.Scanner;
import java.util.HashMap;
//https://www.nowcoder.com/practice/f5a3b5ab02ed4202a8b54dfb76ad035e
public class PerfectPairNumber {
    public static void main (String[] args){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            int n = sc.nextInt();
            int m = sc.nextInt();
            int[][] matrix = new int[n][m];
            for(int i = 0; i < n; i ++){
                for(int j = 0; j < m; j ++){
                    matrix[i][j] = sc.nextInt();
                }
            }
            int pairs = perfectPairs(matrix);
            System.out.println(pairs);
        }
        sc.close();
    }
    public static int perfectPairs(int[][] matrix){
        HashMap<String, Integer> pairs = new HashMap<>();
        int ans = 0;
        int n = matrix[0].length;
        for(int[] cur : matrix){
            StringBuilder self = new StringBuilder();
            StringBuilder minus = new StringBuilder();
            for(int i = 1; i < cur.length; i ++){
//                 int minus = cur[i] - cur[i-1];
//                 int compare = ~minus+1;
                self.append("_"+(cur[i] - cur[i-1]));
                minus.append("_"+(cur[i-1] - cur[i]));

            }
            ans += pairs.getOrDefault(minus.toString(),0);
            pairs.put(self.toString(), pairs.getOrDefault(self.toString(),0) + 1);
        }
        return ans;
    }
}
