package Contest.Aug27;

import java.util.Stack;

public class LC6161RemovingStarsFromaString {
    public String removeStars(String s) {
        char[] str = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (char c : str) {
            if (c != '*') {
                stack.push(c);
            } else {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();

    }
}
