package LeetCodeDays;

public class LC727MinimumWindowSubsequence {
    public String minWindow(String s1, String s2) {
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int N = str1.length;
        int M = str2.length;
        int[][] map = new int[N][26];
        for (int j = 0; j < 26; j++) {
            map[N - 1][j] = Integer.MAX_VALUE;
        }
        map[N - 1][str1[N - 1] - 'a'] = N - 1;
        for (int i = N - 2; i >= 0; i--) {
            for (int j = 0; j < 26; j++) {
                map[i][j] = map[i + 1][j];
            }
            map[i][str1[i] - 'a'] = i;
        }

        int min = Integer.MAX_VALUE, s = -1, e = -1;
        for (int i = 0; i < N - M ; i++) {
            if(str1[i] == str2[0]){
                int end = i;
                int p2 = 1;
                while(p2 < M){
                    if(map[end + 1][str2[p2] -'a'] == Integer.MAX_VALUE){
                        end = Integer.MAX_VALUE;
                        break;
                    }
                    end = map[end + 1][str2[p2 ++] - 'a'];
                }
                if(end == Integer.MAX_VALUE){
                    continue;
                }
                if(min > end - i + 1){
                    s = i;
                    e = end;
                    min = end - i + 1;
                }
            }

        }

        return e == -1 ? "": s1.substring(s , e + 1 );

    }
//    public String minWindow1(String s1, String s2) {
//
//    }
}
