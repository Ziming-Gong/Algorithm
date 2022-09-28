package WeeklyPractice.Aug24;

// 来自网易
// 小红拿到了一个仅由r、e、d组成的字符串
// 她定义一个字符e为"好e" : 当且仅当这个e字符和r、d相邻
// 例如"reeder"只有一个"好e"，前两个e都不是"好e"，只有第三个e是"好e"
// 小红每次可以将任意字符修改为任意字符，即三种字符可以相互修改
// 她希望"好e"的数量尽可能多
// 小红想知道，自己最少要修改多少次
// 输入一个只有r、e、d三种字符的字符串
// 长度 <= 2 * 10^5
// 输出最小修改次数

public class MinCostMaxE {

    public static int solve(String str) {
        return process(str.toCharArray(), 0, ' ', ' ').cost;
    }

    public static Info process(char[] str, int index, char pre, char Prepre) {
        if (index == str.length) {
            return new Info(0, 0);
        }
        //可能性1 变r
        int p1Value = pre == 'e' && Prepre == 'd' ? 1 : 0;
        int p1Cost = str[index] == 'r' ? 0 : 1;
        Info info = process(str, index + 1, 'r', pre);
        p1Value += info.count;
        p1Cost += info.cost;

        //可能性2：变e
        int p2Value = 0;
        int p2Cost = str[index] == 'e' ? 0 : 1;
        info = process(str, index + 1, 'e', pre);
        p2Value = info.count;
        p2Cost += info.cost;

        //可能性3： 变d
        int p3Value = pre == 'e' && Prepre == 'r' ? 1 : 0;
        int p3Cost = str[index] == 'd' ? 0 : 1;
        info = process(str, index + 1, 'd', pre);
        p3Value += info.count;
        p3Cost += info.cost;

        int minCost = Integer.MAX_VALUE;
        int maxE = 0;
        if (p1Value > maxE) {
            maxE = p1Value;
            minCost = p1Cost;
        } else if (p1Value == maxE) {
            minCost = Math.min(p1Cost, minCost);
        }
        if (p2Value > maxE) {
            maxE = p2Value;
            minCost = p2Cost;
        } else if (p2Value == maxE) {
            minCost = Math.min(p2Cost, minCost);
        }

        if (p3Value > maxE) {
            maxE = p3Value;
            minCost = p3Cost;
        } else if (p3Value == maxE) {
            minCost = Math.min(minCost, p3Cost);
        }

        return new Info(minCost, maxE);

    }

    public static class Info {
        public int cost;
        public int count;

        public Info(int c, int co) {
            cost = c;
            count = co;

        }
    }


}
