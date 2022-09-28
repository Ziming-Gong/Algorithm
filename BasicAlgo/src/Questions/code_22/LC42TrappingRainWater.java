package Questions.code_22;

public class LC42TrappingRainWater {

    //方法1 单调栈
    public int trap1(int[] height) {
        if(height.length < 2){
            return 0;
        }
        int ans = 0;
        int N = height.length;
        int[] stack = new int[N];
        int index = 1;
        stack[0] = 0;
        int h = 0;
        for(int i = 1; i < N; i ++){
            while(index != 0 && height[stack[index - 1]] <= height[i]){
                ans += cal(stack[index - 1], i, height[stack[index - 1]] - h);
                h = height[stack[--index]];
            }
            if(index != 0){
                ans += cal(stack[index - 1], i, height[i] - h);
                h = height[i];
            }
            h = Math.min(height[i], h);
            if(index == 0){
                h = 0;
            }
            stack[index ++] = i;
        }
        return ans;
    }

    public int cal(int l, int r, int h){
        return (r - l - 1) * h;
    }
}
