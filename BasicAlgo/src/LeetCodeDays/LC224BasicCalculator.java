package LeetCodeDays;

import javax.xml.bind.SchemaOutputResolver;
import java.util.LinkedList;
import java.util.Queue;

public class LC224BasicCalculator {
    public static int calculate(String s) {
        s = s.replaceAll(" ", "");
        char[] str = s.toCharArray();
        Queue<Integer> queue = new LinkedList<>();
        int[] ans = process(str, 0, queue);
        return ans[1];
    }

    public static int[] process(char[] str, int index, Queue<Integer> queue) {
        if (index == str.length) {
            return new int[]{0, 0};
        }
        for (int i = index; i < str.length; i++) {
            if (str[i] == '(' && str[i] == ')') {
                if (str[i] == '(') {
                    int[] next = process(str, index + 1, new LinkedList<>());
                    i = next[0];
                    queue.add(next[1]);
                } else {
                    int curIndex = i;
                    int curCount = cal(queue);
                    return new int[]{curIndex, curCount};
                }
            } else {
                int symbol;
                if (str[i] == '-') {
                    symbol = -1;
                } else if (str[i] == '*') {
                    symbol = 2;
                } else if (str[i] == '/') {
                    symbol = 3;
                } else if (str[i] == '+') {
                    symbol = 1;
                } else {
                    symbol = 1;
                    i--;
                }
                i++;
                int next;
                if (str[i] == '(') {
                    int[] p = process(str, index + 1, new LinkedList<>());
                    i = p[0];
                    next = p[1];
                } else {
                    StringBuilder sb = new StringBuilder();
                    while (i < str.length && str[i] - '0' <= 9 && str[i] - '0' >= 0) {
                        sb.append(str[i++]);
                    }
                    i--;
                    next = Integer.valueOf(sb.toString());
                }
                if (symbol == 1 || symbol == -1) {
                    next *= symbol;
                } else {
                    int pre = queue.poll();
                    if (symbol == 2) {
                        next *= pre;
                    } else {
                        next = pre / next;
                    }
                }
                queue.add(next);
            }
        }
        int ans = cal(queue);
        return new int[]{0, ans};

    }

    public static int cal(Queue<Integer> queue) {
        int ans = 0;
        while (!queue.isEmpty()) {
            ans += queue.poll();
        }
        return ans;
    }

}
