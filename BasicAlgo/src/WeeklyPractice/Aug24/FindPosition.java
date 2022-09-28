package WeeklyPractice.Aug24;

// 来自美团
// 8.20笔试
// 题目1
// 小美将要期中考试，有n道题，对于第i道题，
// 小美有pi的几率做对，获得ai的分值，还有(1-pi)的概率做错，得0分。
// 小美总分是每道题获得的分数。
// 小美不甘于此，决定突击复习，因为时间有限，她最多复习m道题，复习后的试题正确率为100%。
// 如果以最佳方式复习，能获得期望最大总分是多少？
// 输入n、m
// 接下来输入n个整数，代表pi%，为了简单期间，将概率扩大了100倍。
// 接下来输入n个整数，代表ai，某道题的分值
// 输出最大期望分值，精确到小数点后2位
// 数据 1m<=n<=50000
// 简单题, 课上提一下解法即可
//


import java.util.*;

// 解法
// 题目2
// 小团在地图上放了3个定位装置，想依赖他们进行定位！
// 地图是一个n*n的棋盘，
// 有3个定位装置(x1,y1),(x2,y2),(x3,y3)，每个值均在[1,n]内。
// 小团在(a,b)位置放了一个信标，
// 每个定位装置会告诉小团它到信标的曼哈顿距离，也就是对于每个点，小团知道|xi-a|+|yi-b|
// 求信标位置，信标不唯一，输出字典序最小的。
// 输入n，然后是3个定位装置坐标，
// 最后是3个定位装置到信标的曼哈顿记录。
// 输出最小字典序的信标位置。
// 1 <= 所有数据值 <= 50000
public class FindPosition {
//
//    public static int find(int n, int[] a, int[] b, int[] c, int ad, int bd, int cd) {
//        int[] x1 = null;
//        int r1 = Integer.MAX_VALUE;
//        int[] x2 = null;
//        int r2 = 0;
//        int[] x3 = null;
//        int r3 = 0;
//        if (r1 > ad) {
//            x1 = a;
//            x2 = b;
//            x3 = c;
//            r1 = ad;
//            r2 = bd;
//            r3 = bd;
//        }
//        if (r1 > bd) {
//            r1 = bd;
//            r2 = ad;
//            r3 = cd;
//            x1 = b;
//            x2 = a;
//            x3 = c;
//        }
//        if (r1 > cd) {
//            r1 = cd;
//            x1 = c;
//            x2 = a;
//            r2 = ad;
//            x3 = b;
//            r3 = bd;
//        }
//
//        Queue<int[]> queue = new LinkedList<>();
//        HashSet<String> set = new HashSet<>();
//        ArrayList<int[]> ans = new ArrayList<>();
//        int[] cur = new int[]{x1[0], x1[1] + r1};
//        queue.add(cur);
//        while (!queue.isEmpty()) {
//            cur = queue.poll();
//            String curS = cur[0] + "_" + cur[1];
//            if (cur[1] > 0 && cur[0] > 0 && cur[0] < n && cur[1] < n && distance(cur, a, ad) && distance(cur, b, bd) && distance(cur, c, cd)) {
//                ans.add(cur);
//            }
//            if (ans.size() >= 2) {
//                break;
//            }
//
//
//        }
//
//    }
//
//    public static void add(PriorityQueue<int[]> queue, HashSet<String> visited, )
//
//    public static boolean distance(int[] from, int[] to, int distance) {
//        return Math.abs(from[0] - to[0]) + Math.abs(from[1] - to[1]) == distance;
//    }


}
