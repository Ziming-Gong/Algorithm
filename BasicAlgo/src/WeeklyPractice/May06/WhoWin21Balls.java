package WeeklyPractice.May06;

public class WhoWin21Balls {
    public static String win(int balls) {
        return process(0, balls, 0, 0);
    }

    public static String process(int turn, int rest, int A, int B) {
        if (rest == 0) {
            return (A & 1) == 0 ? "A" : "B";
        }
        if (turn == 0) {
            for (int i = 1; i <= Math.min(rest, 3); i++) {
                if(process(1, rest - i, A + i, B).equals("A")){
                    return "A";
                }
            }
            return "B";
        } else {
            for (int i = 1; i <= Math.min(rest, 3); i++) {
                if(process(0, rest - i, A, B + 1).equals("B")){
                    return "B";
                }
            }
            return "A";
        }
    }
    public static String win1(int rest, int cur, int first, int second) {
        if (rest == 0) {
            if (first == 0 && second == 1) {
                return "甲";
            }
            if (first == 1 && second == 0) {
                return "乙";
            }
            return "平";
        }
        if (cur == 0) { // 甲行动
            String bestAns = "乙";
            for (int pick = 1; pick <= Math.min(3, rest); pick++) {
                String curAns = win1(rest - pick, 1, first ^ (pick & 1), second);
                if (curAns.equals("甲")) {
                    bestAns = "甲";
                    break;
                }
                if (curAns.equals("平")) {
                    bestAns = "平";
                }
            }
            return bestAns;
        } else { // 乙行动
            String bestAns = "甲";
            for (int pick = 1; pick <= Math.min(3, rest); pick++) {
                String curAns = win1(rest - pick, 0, first, second ^ (pick & 1));
                if (curAns.equals("乙")) {
                    bestAns = "乙";
                    break;
                }
                if (curAns.equals("平")) {
                    bestAns = "平";
                }
            }
            return bestAns;
        }
    }

    // 下面的win2方法，仅仅是把win1方法，做了记忆化搜索
    // 变成了动态规划
    public static String[][][][] dp = new String[5000][2][2][2];

    public static String win2(int rest, int cur, int first, int second) {
        if (rest == 0) {
            if (first == 0 && second == 1) {
                return "甲";
            }
            if (first == 1 && second == 0) {
                return "乙";
            }
            return "平";
        }
        if (dp[rest][cur][first][second] != null) {
            return dp[rest][cur][first][second];
        }
        if (cur == 0) { // 甲行动
            String bestAns = "乙";
            for (int pick = 1; pick <= Math.min(3, rest); pick++) {
                String curAns = win2(rest - pick, 1, first ^ (pick & 1), second);
                if (curAns.equals("甲")) {
                    bestAns = "甲";
                    break;
                }
                if (curAns.equals("平")) {
                    bestAns = "平";
                }
            }
            dp[rest][cur][first][second] = bestAns;
            return bestAns;
        } else { // 乙行动
            String bestAns = "甲";
            for (int pick = 1; pick <= Math.min(3, rest); pick++) {
                String curAns = win2(rest - pick, 0, first, second ^ (pick & 1));
                if (curAns.equals("乙")) {
                    bestAns = "乙";
                    break;
                }
                if (curAns.equals("平")) {
                    bestAns = "平";
                }
            }
            dp[rest][cur][first][second] = bestAns;
            return bestAns;
        }
    }

    // 为了测试
    public static void main(String[] args) {
        for (int balls = 1; balls <= 500; balls += 2) {
            System.out.println("球数为 " + balls + " 时 , 赢的是 " + win(balls));
        }
    }
}
