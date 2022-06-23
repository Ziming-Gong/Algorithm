package Questions.code_8;

public class LC11ContainerWithMostWater {
    public int maxArea(int[] height) {
        int L = 0;
        int R = height.length - 1;
        int max = Integer.MIN_VALUE;
        int area = 0;
        while(L < R){
            if(height[L] > height[R]){
                area = height[R] * (R - L);
                R--;
            }else{
                area = height[L] *(R-L);
                L ++;
            }
            max = Math.max(area, max);
        }
        return max;
    }
}
