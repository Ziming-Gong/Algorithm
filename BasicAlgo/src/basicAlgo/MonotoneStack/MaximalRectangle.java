package basicAlgo.MonotoneStack;


//https://leetcode.com/problems/maximal-rectangle/submissions/
public class MaximalRectangle {
    public int maximalRectangle(char[][] matrix) {
        int result = 0;
        int n = matrix.length;
        int[] height = new int[matrix[0].length];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < matrix[0].length; j ++){
                height[j] = matrix[i][j] == '0' ? 0 : height[j] + 1;
            }
            result = Math.max(result, getMax(height));
        }
        return result;

    }
    public static int getMax(int[] arr){
        int n = arr.length;
        int[] stack = new int[n];
        int result = Integer.MIN_VALUE;
        int stackSize = 0;
        for(int i = 0; i < n; i ++){
            while(stackSize != 0 && arr[stack[stackSize-1]] >= arr[i]){
                int cur = stack[--stackSize];
                int k = stackSize == 0 ? -1 : stack[stackSize -1 ];
                int area = (i - k - 1) * arr[cur];
                result = Math.max(result, area);
            }
            stack[stackSize++] = i;
        }
        while( stackSize != 0){
            int cur = stack[--stackSize];
            int k = stackSize == 0? -1 : stack[stackSize - 1];
            int area = (n - k - 1) * arr[cur];
            result = Math.max(result, area);
        }
        return result;
    }
}
