package WeeklyPractice.July20;


import java.util.Arrays;

// 一个整数区间 [a, b]  ( a < b ) 代表着从 a 到 b 的所有连续整数，包括 a 和 b。
// 给你一组整数区间intervals，请找到一个最小的集合 S，
// 使得 S 里的元素与区间intervals中的每一个整数区间都至少有2个元素相交。
// 输出这个最小集合S的大小。
// 测试链接 : https://leetcode.cn/problems/set-intersection-size-at-least-two/
public class LC757SetIntersectionSizeAtLeastTwo {
    public int intersectionSizeTwo(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[1] == b[1] ? b[0] - a[0] : a[1] - b[1]);
        int post = intervals[0][1];
        int pre = post - 1;
        int ans = 2;
        for(int i = 1; i < intervals.length; i ++){
            int[] cur = intervals[i];
            if(pre < cur[0]){
                if(post < cur[0]){
                    ans += 2;
                    pre = cur[1] - 1;
                }else{
                    pre = post;
                    ans ++;
                }
                post = cur[1];
            }
        }
        return ans;

    }
}
