package LeetCodeDays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class LC1472DesignBrowserHistory {
    class BrowserHistory{
            Stack<String> preStack;
            Stack<String> postStack;
            String cur;

        public BrowserHistory(String homepage) {
            preStack = new Stack<>();
            postStack = new Stack<>();
            cur = homepage;
        }

        public void visit(String url) {
            preStack.add(cur);
            postStack = new Stack<>();
            cur = url;
        }

        public String back(int steps) {
            int index = 0;
            for(; index < steps && !preStack.isEmpty(); index ++){
                postStack.add(cur);
                cur = preStack.pop();
            }
            return cur;
        }

        public String forward(int steps) {
            int index = 0;
            for(; index < steps && !postStack.isEmpty(); index ++){
                preStack.add(cur);
                cur = postStack.pop();
            }
            return cur;

        }
    }

}
