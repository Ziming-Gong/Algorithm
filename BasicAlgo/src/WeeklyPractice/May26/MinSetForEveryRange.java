package WeeklyPractice.May26;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// 给定区间的范围[xi,yi]，xi<=yi，且都是正整数
// 找出一个坐标集合set，set中有若干个数字
// set要和每个给定的区间，有交集
// 求set的最少需要几个数
// 比如给定区间 : [5, 8] [1, 7] [2, 4] [1, 9]
// set最小可以是: {2, 6}或者{2, 5}或者{4, 5}
public class MinSetForEveryRange {
    public static int minSize(int[][] m) {
        Set<Integer> set = new HashSet<>();
        Set<Integer> finalSet = new HashSet<>();
        int N = m.length;
        int[][] events = new int[N * 2][3];
        for (int i = 0; i < N; i++) {
            events[i][0] = m[i][0];
            events[i][1] = 0; //0 --> start
            events[i][2] = m[i][1];
            events[i + N][0] = m[i][1];
            events[i + N][1] = 1;
        }
        Arrays.sort(events, (a, b) -> (a[0] - b[0]));
        for (int i = 0; i < events.length; i++) {
            int[] event = events[i];
            if (event[1] == 0) {
                set.add(event[2]);
            } else if (event[1] == 1 && set.contains(event[0])) {
                finalSet.add(event[0]);
                set.clear();
            }
        }
        return finalSet.size();

    }

    public static int minSet(int[][] ranges) {
        int n = ranges.length;
        // events[i] = {a, b, c}
        // a == 0, 表示这是一个区间的开始事件，这个区间结束位置是b
        // a == 1, 表示这是一个区间的结束事件，b的值没有意义
        // c表示这个事件的时间点，不管是开始事件还是结束事件，都会有c这个值
        int[][] events = new int[n << 1][3];
        for (int i = 0; i < n; i++) {
            // [3, 7]
            // (0,7,3)
            // (1,X,7)
            events[i][0] = 0;
            events[i][1] = ranges[i][1];
            events[i][2] = ranges[i][0];
            events[i + n][0] = 1;
            events[i + n][2] = ranges[i][1];
        }
        Arrays.sort(events, (a, b) -> a[2] - b[2]);
        // 容器
        HashSet<Integer> tmp = new HashSet<>();
        int ans = 0;
        for (int[] event : events) {
            if (event[0] == 0) {
                tmp.add(event[1]);
            } else {
                if (tmp.contains(event[2])) {
                    ans++;
                    tmp.clear();
                }
            }
        }
        return ans;
    }

    public static int[][] generate(int maxVal, int maxLen){
        int N = (int) (Math.random() * maxLen)  +1;
        int[][] res = new int[N][2];
        for(int i = 0; i < N; i ++){
            res[i][0] = (int) (Math.random() * maxVal);
            res[i][1] = res[i][0] + (int) (Math.random() * maxVal);
        }
        return res;
    }

    public static void main(String[] args) {
//        int[][] arr = {{5, 8}, {1, 7}, {2, 4}, {1, 9}};
//        System.out.println(minSet(arr));
//        System.out.println(minSize(arr));
        int maxVal = 100;
        int maxLen = 100;
        int testTime = 100000;
        System.out.println("test begin");
        for(int i = 1; i <= testTime; i ++){
            int[][] m = generate(maxVal,maxLen);
            int ans1 = minSet(m);
            int ans2 = minSize(m);
            if(ans1 != ans2){
                System.out.println("oops");
            }
        }
        System.out.println("test end");
    }

}
