package WeeklyPractice.Auguest31;

// 给定一个由 '[' ，']'，'('，‘)’ 组成的字符串
// 请问最少插入多少个括号就能使这个字符串的所有括号左右配对
// 例如当前串是 "([[])"，那么插入一个']'即可满足
// 输出最少插入多少个括号
// 测试链接 : https://www.nowcoder.com/practice/e391767d80d942d29e6095a935a5b96b
// 提交如下代码，把主类名改成Main，可以直接通过

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MidAddToMatch {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while((line = br.readLine()) != null){
            System.out.println(minAdd(line.toCharArray()));
        }
    }


    public static int minAdd(char[] str){
        int n = str.length;
        int[][] dp = new int[n][n];
        for(int i = 0; i < n; i ++){
            for(int j = 0; j < n; j ++){
                dp[i][j] = -1;
            }
        }
        return process(str, 0, n - 1, dp);


    }

    public static int process(char[] str, int L, int R, int[][] dp){
        if(L == R){
            return 1;
        }
        if(L == R - 1){
            return (str[L] == '(' && str[R] == ')') || (str[L] == '[' && str[R] == ']') ? 0 : 2;
        }

        if(dp[L][R] != -1){
            return dp[L][R];
        }
        // 两者是包含关系
        int p1 = 1 + process(str, L + 1, R, dp);
        int p2 = 1 + process(str, L, R - 1, dp);
        int p3 = Integer.MAX_VALUE;
        if((str[L] == '(' && str[R] == ')') || (str[L] == '[' && str[R] == ']')){
            p3 = process(str, L + 1, R - 1, dp);
        }
        int ans = Math.min(p1, Math.min(p2, p3));

        // 两者是并列关系
        for(int split = L; split < R; split ++){
            ans = Math.min(ans, process(str, L, split, dp) + process(str, split + 1, R, dp));
        }
        dp[L][R] = ans;

        return ans;



    }
}
