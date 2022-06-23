package Questions.code_8;

import java.util.LinkedList;

public class LC772BasicCalculator {
    public static int calculate(String s) {
        char[] str = s.toCharArray();
        return f(str, 0).ans;
    }

    public static class Info {
        public int ans;
        public int end;

        public Info(int a, int e) {
            ans = a;
            end = e;
        }
    }

    public static Info f(char[] str, int index) {
        LinkedList<String> list = new LinkedList<>();
        int cur = 0;
        while (index < str.length && str[index] != ')') {
            if (str[index] - '0' < 10 && str[index] - '0' >= 0) {
                cur *= 10;
                cur += str[index++] - '0';
            } else if (str[index] == '(') {
                Info next = f(str, index + 1);
                index = next.end + 1;
                cur = next.ans;
            } else {
                put(list, str, index++, cur);
                cur = 0;
            }
        }
        put(list, str, -1, cur);
        return new Info(sum(list), index);
    }

    public static void put(LinkedList<String> list, char[] str, int index, int cur) {
        if (!list.isEmpty() && (list.peekLast().equals("*") || list.peekLast().equals("/"))) {
            String s = list.pollLast();
            if (s.equals("*")) {
                cur *= Integer.valueOf(list.pollLast());
            } else {
                cur = Integer.valueOf(list.pollLast()) / cur;
            }
        }
        list.add(String.valueOf(cur));
        if(index != -1){
            list.add(String.valueOf(str[index]));
        }
    }

    public static int sum(LinkedList<String> list) {
        int ans = 0;
        while (list.size() > 1) {
            int num = Integer.valueOf(list.pollLast());
            String s = list.pollLast();
            if (s.equals("+")) {
                ans += num;
            } else {
                ans -= num;
            }
        }
        ans += Integer.valueOf(list.pollLast());
        return ans;
    }
}












