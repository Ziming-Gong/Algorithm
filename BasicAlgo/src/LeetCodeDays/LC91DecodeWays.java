package LeetCodeDays;

public class LC91DecodeWays {
    public int numDecodings(String s) {
        char[] str = s.toCharArray();
        if(str[0] == '0'){
            return 0;
        }
        if(s.length() == 1){
            return 1;
        }
        int N = str.length;
        int[] dp = new int[N];
        dp[0] =  1;
        if(str[1] == '0' && str[0] > '2'){
            return 0;
        }
        dp[1] = str[1] == '0' ? 1 : ((((str[0] == '1') || (str[0] == '2' && str[1] <= '6')) && str[0] != '0') ? 2 : 1);
        for(int i = 2; i < N; i ++){
            if(str[i] == '0'){
                if(str[i - 1] != '1' && str[i - 1] != '2'){
                    return 0;
                }
                dp[i] = dp[i - 2];
            }else{
                dp[i] = dp[i - 1];
                dp[i] += (((str[i - 1] == '1') || (str[i - 1] == '2' && str[i] <= '6')) && str[i - 1] != '0') ? dp[i - 2] : 0;
            }

        }
        return dp[N - 1];
    }
}
