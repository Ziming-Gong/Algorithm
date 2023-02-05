package Questions.code_26;

import java.util.ArrayList;
import java.util.List;

public class LC282ExpressionAddOperators {
    public List<String> addOperators(String num, int target) {

        char[] number = num.toCharArray();

        List<String> ans = new ArrayList<>();
        char[] path = new char[number.length * 2];
        long n = 0;
        for (int i = 0; i < number.length; i++) {
            n = 10 * n + number[i] - '0';
            path[i] = number[i];
            dfs(number, path, i + 1, 0, n, i + 1, ans, target);
            if (n == '0') {
                break;
            }
        }
        return ans;

    }


    public void dfs(char[] num, char[] path, int len, long left, long cur, int index, List<String> ans, int target) {
        if (index == num.length) {
            if (cur + left == target) {
                ans.add(new String(path, 0, len));
            }
            return;
        }

        long n = 0;
        int j = len + 1;
        for (int i = index; i < num.length; i++) {
            n = n * 10 + (num[i] - '0');
            // +
            path[j++] = num[i];
            path[len] = '+';
            dfs(num, path, j, left + cur, n, i + 1, ans, target);
            // -
            path[len] = '-';
            dfs(num, path, j, left + cur, -n, i + 1, ans, target);

            // *
            path[len] = '*';
            dfs(num, path, j, left, cur * n, i + 1, ans, target);

            if (num[index] == '0') {
                break;
            }
        }
    }
}
