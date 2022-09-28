package LeetCodeDays;

import sun.awt.image.ImageWatched;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class LC1249MinimumRemovetoMakeValidParentheses {
    public String minRemoveToMakeValid(String s) {
        char[] str = s.toCharArray();
        int left = 0;
        int start = 0;
        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < str.length; i++) {
            if (str[i] != '(' && str[i] != ')') {
                sb.append(str[i]);
                continue;
            }
            if (str[i] == '(') {
                stack.add(sb.length());
                sb.append('(');
                left++;
            }
            if (str[i] == ')') {
                if (left > 0) {
                    left--;
                    stack.pop();
                    sb.append(')');
                }
            }
        }
        while (left > 0) {
            left--;
            int index = stack.pop();
            sb.replace(index, index, "");
        }
        return sb.toString();

    }


    public static void main(String[] args) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
    }

}
