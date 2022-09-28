package WeeklyPractice.Aug24;

// 来自美团
// 8.20笔试
// 小团生日收到妈妈送的两个一模一样的数列作为礼物！
// 他很开心的把玩，不过不小心没拿稳将数列摔坏了！
// 现在他手上的两个数列分别为A和B，长度分别为n和m。
// 小团很想再次让这两个数列变得一样。他现在能做两种操作：
// 操作一是将一个选定数列中的某一个数a改成数b，这会花费|b-a|的时间，
// 操作二是选择一个数列中某个数a，将它从数列中丢掉，花费|a|的时间。
// 小团想知道，他最少能以多少时间将这两个数列变得再次相同！
// 输入描述：
// 第一行两个空格隔开的正整数n和m，分别表示数列A和B的长度。
// 接下来一行n个整数，分别为A1 A2…An
// 接下来一行m个整数，分别为B1 B2…Bm
// 对于所有数据，1 ≤ n,m ≤ 2000， |Ai|,|Bi| ≤ 10000
// 输出一行一个整数，表示最少花费时间，来使得两个数列相同。

public class ChangeToSame {


    public static int F(int[] a, int[] b) {
        return f(a, b, 0, 0);
    }

    public static int f(int[] a, int[] b, int ai, int bi) {
        if (ai == a.length && bi == b.length) {
            return 0;
        }
        if (ai == a.length && bi != b.length) {
            return b[bi] + f(a, b, ai, bi + 1);
        }
        if (ai != a.length && bi == b.length) {
            return a[ai] + f(a, b, ai + 1, bi);
        }

        // 可能1：不要bi
        int p1 = b[bi] + f(a, b, ai, bi + 1);
        //可能性2： 不要ai
        int p2 = a[ai] + f(a, b, ai + 1, bi);
        //可能性3： a -> b
        int p3 = Math.abs(a[ai] - b[bi]) + f(a, b, ai + 1, bi + 1);

        return Math.min(p1, Math.min(p2, p3));
    }


    // 上面的暴力递归方法改动态规划
    public static int change(int[] A, int[] B, int indexA, int indexB, int[][] dp) {
        if (indexA == A.length && indexB == B.length) {
            return 0;
        }
        if (dp[indexA][indexB] != -1) {
            return dp[indexA][indexB];
        }
        int ans = 0;
        if (indexA == A.length && indexB != B.length) {
            ans = B[indexB] + change(A, B, indexA, indexB + 1, dp);
        } else if (indexA != A.length && indexB == B.length) {
            ans = A[indexA] + change(A, B, indexA + 1, indexB, dp);
        } else {
            int p1 = A[indexA] + change(A, B, indexA + 1, indexB, dp);
            int p2 = B[indexB] + change(A, B, indexA, indexB + 1, dp);
            int p45 = Math.abs(A[indexA] - B[indexB]) + change(A, B, indexA + 1, indexB + 1, dp);
            ans = Math.min(Math.min(p1, p2), p45);
        }
        dp[indexA][indexB] = ans;
        return ans;
    }


}
