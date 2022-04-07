package basicAlgo.MonotoneStack;

import java.util.PriorityQueue;
import java.util.Stack;

//https://leetcode.com/problems/largest-rectangle-in-histogram/submissions/
public class LargestRectangleInHistogram {
    public static int largestRectangleArea(int[] heights) {
        int result = Integer.MIN_VALUE;
        int n = heights.length;
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < n; i++){
            while(!stack.isEmpty() && heights[stack.peek()] >= heights[i]){
                int cur = stack.pop();
                int k = stack.isEmpty() ? -1 : stack.peek();
                int area = (i - k - 1)* heights[cur];
                result = Math.max(result, area);
            }
            stack.push(i);
        }
        while(!stack.isEmpty()){
            int cur = stack.pop();
            int k = stack.isEmpty() ? -1 : stack.peek();
            int area = (n - k - 1) * heights[cur];
            result = Math.max(result, area);
        }
        return result;
    }

    public static int largestRectangleArea1(int[] heights) {
        int result = Integer.MIN_VALUE;
        int n = heights.length;
        int[] stack = new int[n];
        int stackSize = 0;
        for(int i = 0; i < n; i++){
            while(stackSize != 0 && heights[stack[stackSize - 1]] >= heights[i]){
                int cur = stack[--stackSize];
                int k = stackSize == 0 ? -1 : stack[stackSize - 1];
                int area = (i - k - 1)* heights[cur];
                result = Math.max(result, area);
            }
            stack[stackSize ++] = i;
        }
        while(stackSize != 0){
            int cur = stack[--stackSize];
            int k = stackSize == 0 ? -1 : stack[stackSize - 1];
            int area = (n - k - 1) * heights[cur];
            result = Math.max(result, area);
        }
        return result;
    }
}
