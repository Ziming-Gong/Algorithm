package Questions.code_013;

import java.text.DecimalFormat;

// 谷歌面试题
// 面值为1~10的牌组成一组，
// 每次你从组里等概率的抽出1~10中的一张
// 下次抽会换一个新的组，有无限组
// 当累加和<17时，你将一直抽牌
// 当累加和>=17且<21时，你将获胜
// 当累加和>=21时，你将失败
// 返回获胜的概率

public class GoogleCard {

    public static double f2(int N, int a, int b) {
        if (a >= b || a < 0 || N < 1) {
            return 0.0;
        }
        if (b - a >= N) {
            return 1.0;
        }
        return process(0, a, b, N);
    }

    public static double process(int cur, int a, int b, int N) {
        if (cur >= a && cur < b) {
            return 1.0;
        }
        if (cur >= b) {
            return 0.0;
        }
        double ans = 0;
        for (int i = 1; i <= N; i++) {
            ans += process(cur + i, a, b, N);
        }
        return ans / N;
    }

    public static double f3(int N, int a, int b) {
        if (a >= b || a < 0 || N < 1) {
            return 0.0;
        }
        if (b - a >= N) {
            return 1.0;
        }

        return process0(0, a, b, N);
    }

    public static double process0(int cur, int a, int b, int N) {
        if (cur >= a && cur < b) {
            return 1.0;
        }
        if (cur >= b) {
            return 0.0;
        }
        if (cur == a - 1) {
            return 1.0 * (b - a) / N;
        }
        double ans;
        if (cur >= b - N - 1) {
            ans = process0(cur + 1, a, b, N) * N + process0(cur + 1, a, b, N);
        } else {
            ans = process0(cur + 1, a, b, N) * N + process0(cur + 1, a, b, N) - process0(cur + N + 1, a, b, N);
        }
//        double w = process0(cur + 1, a, b, N) + process0(cur + 1, a, b, N) * N;
//        if (cur + 1 + N < b) {
//            w -= process0(cur + 1 + N, a, b, N);
//        }
        return ans / N;

    }

    public static double f4(int N, int a, int b) {
        if (a >= b || a < 0 || N < 1) {
            return 0.0;
        }
        if (b - a >= N) {
            return 1.0;
        }
        double[] dp = new double[b];
        int i = b - 1;
        for (; i >= a && i >= 0; i--) {
            dp[i] = 1;
        }
        if(i < 0){
            return dp[0];
        }
        dp[i] = 1.0 * (b - a) / N;
        i--;
        for (; i >= 0 && i >= b - N - 1; i--) {
            dp[i] = (dp[i + 1] + dp[i + 1] * N) / N;
        }
        for(; i >= 0; i --){
            dp[i] = dp[i + 1] + dp[i + 1] * N - dp[i + N + 1];
            dp[i] /= N;
        }
        return dp[0];
    }


    public static void main(String[] args) {
        int N = 10;
        int a = 17;
        int b = 21;
        System.out.println("N = " + N + ", a = " + a + ", b = " + b);
//        System.out.println(f1());
        System.out.println(f2(N, a, b));
        System.out.println(f3(N, a, b));
        System.out.println(f4(N, a, b));

        int maxN = 15;
        int maxM = 20;
        int testTime = 100000;
        System.out.println("测试开始");
        System.out.println("比对double类型答案可能会有精度对不准的问题");
        System.out.println("所以答案一律只保留小数点后四位进行比对");
        System.out.println("如果没有错误提示, 说明验证通过");
        DecimalFormat df = new DecimalFormat("#.####");
        for (int i = 0; i < testTime; i++) {

            N = (int) (Math.random() * maxN);
            a = (int) (Math.random() * maxM);
            b = (int) (Math.random() * maxM);
//            System.out.println(N + " , " + a + " , " + b);

            double ans2 = Double.valueOf(df.format(f2(N, a, b)));
            double ans3 = Double.valueOf(df.format(f3(N, a, b)));
            double ans4 = Double.valueOf(df.format(f4(N, a, b)));
            if (ans2 != ans3 || ans2 != ans4) {
                System.out.println("Oops!");
                System.out.println(N + " , " + a + " , " + b);
                System.out.println(ans2);
                System.out.println(ans3);
                System.out.println(ans4);
                break;
            }
        }
        System.out.println("测试结束");

        N = 10000;
        a = 67834;
        b = 72315;
        System.out.println("N = " + N + ", a = " + a + ", b = " + b + "时, 除了方法4外都超时");
        System.out.print("方法4答案: ");
        System.out.println(f4(N, a, b));
    }

}
