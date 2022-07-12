package Questions.code_8;


import java.awt.geom.FlatteningPathIterator;
import java.net.PortUnreachableException;

/*
 * 给定一个char[][] matrix，也就是char类型的二维数组，再给定一个字符串word，
 * 可以从任何一个某个位置出发，可以走上下左右，能不能找到word？
 * 比如：
 * char[][] m = {
 *     { 'OA.MaxNumDinstinctNum.a', 'b', 'z' },
 *     { 'c', 'd', 'o' },
 *     { 'f', 'e', 'o' },
 * };
 * word = "zooe"
 * 是可以找到的
 *
 * 设定1：可以走重复路的情况下，返回能不能找到
 * 比如，word = "zoooz"，是可以找到的，z -> o -> o -> o -> z，因为允许走一条路径中已经走过的字符
 *
 * 设定2：不可以走重复路的情况下，返回能不能找到
 * 比如，word = "zoooz"，是不可以找到的，因为允许走一条路径中已经走过的字符不能重复走
 *
 * 写出两种设定下的code
 *
 * */
public class FindWordMatrix {
    /**
     * 设定1： 可以有重复
     */
    public static boolean findWord1(char[][] m, String word) {
        boolean ans = false;
        char[] w = word.toCharArray();
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                if (process(m, w, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean process(char[][] m, char[] word, int i, int j, int k) {
        if (k == word.length) {
            return true;
        }
        if (i < 0 || i >= m.length || j < 0 || j >= m[0].length || m[i][j] != word[k]) {
            return false;
        }
        if (process(m, word, i + 1, j, k + 1) || process(m, word, i - 1, j, k + 1) ||
                process(m, word, i, j + 1, k + 1) || process(m, word, i, j - 1, k + 1)) {
            return true;
        }
        return false;
    }

    public static boolean findWord2(char[][] matrix, String word) {
        if (word == null || word.equals("")) {
            return true;
        }
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return false;
        }
        char[] w = word.toCharArray();
        int N = matrix.length;
        int M = matrix[0].length;
        int len = w.length;
        boolean[][][] dp = new boolean[N][M][len];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dp[i][j][0] = matrix[i][j] == w[0];
            }
        }
        for (int p = 1; p < len; p++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    dp[i][j][p] = (check(dp, i, j, p) && matrix[i][j] == w[p]);
                }
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(dp[i][j][len - 1]){
                    return true ;
                }
            }
        }
        return false;
    }

    public static boolean check(boolean[][][] dp, int i, int j, int k) {
        boolean up = i < dp.length - 1 ? dp[i + 1][j][k - 1] : false;
        boolean down = i > 0 ? dp[i - 1][j][k - 1] : false;
        boolean left = j > 0 ? dp[i][j - 1][k - 1] : false;
        boolean right = j < dp[0].length - 1 ? dp[i][j + 1][k - 1] : false;
        return up || down || left || right;
    }


    public static char[][] randomMatrix(int maxN, int maxM) {
        int n = (int) (Math.random() * maxN) + 1;
        int m = (int) (Math.random() * maxM) + 1;
        char[][] ans = new char[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                ans[i][j] = (char) ('a' + (int) (Math.random() * 26));
            }
        }
        return ans;
    }

    public static String randomString(int maxLen) {
        int n = (int) (Math.random() * maxLen) + 1;
        char[] ans = new char[n];
        for (int i = 0; i < n; i++) {
            ans[i] = (char) ('a' + (int) (Math.random() * 26));
        }
        return String.valueOf(ans);
    }

    public static void main(String[] args) {
//        char[][] m = {{'OA.MaxNumDinstinctNum.a', 'b', 'z'}, {'c', 'd', 'o'}, {'f', 'e', 'o'},};
//        String word1 = "zoooz";
//        String word2 = "zood";
//        // 可以走重复路的设定
//        System.out.println(findWord1(m, word1));
//        System.out.println(findWord1(m, word2));
        // 不可以走重复路的设定
//        System.out.println(findWord2(m, word1));
//        System.out.println(findWord2(m, word2));


        int maxLen = 10;
        int maxN = 100;
        int maxM = 100;
        int testTime = 100000;
        System.out.println("testBegin");
        for (int i = 1; i <= testTime; i++) {
            char[][] m = randomMatrix(maxN, maxM);
            String word = randomString(maxLen);
            boolean ans1 = findWord1(m, word);
            boolean ans2 = findWord2(m, word);
            if (ans1 != ans2) {
                System.out.println("oops!");
                break;
            }

        }
        System.out.println("test end!");


    }


}

















