package WeeklyPractice.July20;

public class LC678ValidParenthesisString {
    //最优解
    public boolean checkValidString1(String s) {
        char[] str = s.toCharArray();
        int min = 0, max = 0;
        for(char c : str){
            if(c == ')'){
                min = min > 0 ? min - 1 : min;
                max --;
            }else if (c == '('){
                min ++;
                max ++;
            }else{
                min = min > 0 ? min - 1 : min;
                max ++;
            }
            if(max < 0){
                return false;
            }
        }
        return min == 0;

    }

    public boolean checkValidString(String s) {
        char[] str = s.toCharArray();
        int n = str.length;
        int[][] dp = new int[n][n + 1];
        return process(str, 0, 0, dp);
    }

    public boolean process(char[] str, int index, int count, int[][] dp){
        if(index == str.length){
            return count == 0;
        }
        if(count < 0){
            return false;
        }

        if(dp[index][count] != 0){
            return dp[index][count] == 1;
        }
        boolean ans = false;
        if(str[index] == ')'){
            ans = process(str, index + 1, count - 1, dp);
        }else if(str[index] == '('){
            ans = process(str, index + 1, count + 1, dp);
        }else{
            ans = process(str, index + 1, count, dp) || process(str, index + 1, count + 1, dp) || process(str, index + 1, count - 1, dp);
        }
        dp[index][count] = ans ? 1 : -1;
        return ans;
    }


}
