package WeeklyPractice.July28;

import sun.font.TrueTypeGlyphMapper;

// 一个数组如果满足 :
// 升降升降升降... 或者 降升降升...都是满足的
// 给定一个数组，
// 1，看有几种方法能够剔除一个元素，达成上述的要求
// 2，数组天然符合要求返回0
// 3，剔除1个元素达成不了要求，返回-1，
// 比如：
// 给定[3, 4, 5, 3, 7]，返回3
// 移除0元素，4 5 3 7 符合
// 移除1元素，3 5 3 7 符合
// 移除2元素，3 4 3 7 符合
// 再比如：给定[1, 2, 3, 4] 返回-1
// 因为达成不了要求
public class WaysWiggle {
    public static int ways1(int[] arr){
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int N = arr.length;
        boolean[] rightDown = new boolean[N];
        boolean[] rightUp = new boolean[N];
        rightDown[N - 1] = true;
        rightUp[N - 1] = true;
        for(int i = N - 2; i >= 0; i --){
            rightDown[i] = (arr[i + 1] - arr[i] < 0) && rightUp[i + 1];
            rightUp[i] = (arr[i + 1] - arr[i] > 0) && rightDown[i + 1];
        }
        if(rightDown[0] || rightUp[0]){
            return 0;
        }
        boolean leftDown = true;
        boolean leftUp = true;
        int ans = (rightDown[1] || rightUp[1]) ? 1 : 0;
        for(int i = 1; i < N - 1; i ++){
            if(arr[i - 1] > arr[i + 1]){
                ans += (leftDown && rightUp[i + 1]) ? 1 : 0;
            }else if(arr[i - 1] < arr[i + 1]){
                ans += (leftUp && rightDown[i + 1]) ? 1 : 0;
            }
            boolean temp = leftDown;
            leftDown = (leftUp && (arr[i] > arr[i - 1]));
            leftUp = (temp && (arr[i] < arr[i - 1]));
        }
        ans += leftUp || leftDown ? 1 : 0;
        return ans == 0 ? -1 : ans;

    }
    // 时间复杂度O(N)
    public static int ways2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int n = arr.length;
        boolean[] rightUp = new boolean[n];
        boolean[] rightDown = new boolean[n];
        rightUp[n - 1] = true;
        rightDown[n - 1] = true;
        for (int i = n - 2; i >= 0; i--) {
            rightUp[i] = arr[i] < arr[i + 1] && rightDown[i + 1];
            rightDown[i] = arr[i] > arr[i + 1] && rightUp[i + 1];
        }
        // 数组是不是天然符合！
        if (rightUp[0] || rightDown[0]) {
            return 0;
        }
        // 删掉0位置的数，数组达标还是不达标！
        // 1 升
        // 1 降
        int ans = (rightUp[1] || rightDown[1]) ? 1 : 0;
        // ...[0]
        boolean leftUp = true;
        boolean leftDown = true;
        boolean tmp;
        for (int i = 1, l = 0, r = 2; i < n - 1; i++, l++, r++) {
            ans += (arr[l] > arr[r] && rightUp[r] && leftDown) || (arr[l] < arr[r] && rightDown[r] && leftUp) ? 1 : 0;
            // i（两个信息） i+1
            // 变量复用
//			boolean curLeftUp = arr[l] > arr[i] && leftDown;
//			boolean curLeftDown =  arr[l] < arr[i] && leftUp;
//			leftUp = curLeftUp;
//			leftDown = curLeftDown;
            tmp = leftUp;
            // 7 4
            leftUp = arr[l] > arr[i] && leftDown;
            leftDown = arr[l] < arr[i] && tmp;
        }
        // 单独算一下 删掉n-1位置数的时候
        ans += leftUp || leftDown ? 1 : 0;
        return ans == 0 ? -1 : ans;
    }

    // 为了验证
    public static int[] randomArray(int len, int maxValue) {
        int[] ans = new int[len];
        for (int i = 0; i < len; i++) {
            ans[i] = (int) (Math.random() * maxValue) + 1;
        }
        return ans;
    }

    // 为了验证
    public static void main(String[] args) {
        int maxLen = 10;
        int maxValue = 100;
        int testTime = 30000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * maxLen) + 1;
            int[] arr = randomArray(len, maxValue);
            int ans1 = ways1(arr);
            int ans2 = ways2(arr);
            if (ans1 != ans2) {
                System.out.println("出错了！");
                break;
            }
        }
        System.out.println("测试结束");
    }
}
