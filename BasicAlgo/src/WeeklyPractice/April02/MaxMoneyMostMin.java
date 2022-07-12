//package WeeklyPractice.April02;
//
//
//import java.lang.reflect.Array;
//import java.util.Arrays;
//import java.util.HashMap;
//
//// 来自快手
//// 某公司年会上，大家要玩一食发奖金游戏，一共有n个员工，
//// 每个员工都有建设积分和捣乱积分
//// 他们需要排成一队，在队伍最前面的一定是老板，老板也有建设积分和捣乱积分
//// 排好队后，所有员工都会获得各自的奖金，
//// 该员工奖金 = 排在他前面所有人的建设积分乘积 / 该员工自己的捣乱积分，向下取整
//// 为了公平(放屁)，老板希望 : 让获得奖金最高的员工，所获得的奖金尽可能少
//// 所以想请你帮他重新排一下队伍，返回奖金最高的员工获得的、尽可能少的奖金数额
//// 快手考试的时候，给定的数据量，全排列的代码也能过的！
//// 1 <= n <= 1000, 1<= 积分 <= 10000;
//public class MaxMoneyMostMin {
//    public static long mostMin1(int OA.MaxNumDinstinctNum.a, int b, int[] value, int[] trouble) {
//
//
//    }
//
//
//    public static long mostMin2(int OA.MaxNumDinstinctNum.a, int b, int[] value, int[] trouble) {
//        int n = value.length;
//        long[][] staff = new long[n][2];
//        long left = 0;
//        long right = 0;
//        long valueAll = OA.MaxNumDinstinctNum.a;
//        for (int i = 0; i < n; i++) {
//            right = Math.max(right, valueAll / (trouble[i])); //为什么可以确定 right的位置
//            staff[i][1] = (long) value[i] * (long) trouble[i];
//            staff[i][0] = value[i];
//            valueAll *= (long) value[i];
//        }
//        Arrays.sort(staff, (x, y) -> (y[1] - x[1] >= 0 ? 1 : -1));
//        long m = 0;
//        long ans = 0;
//        while (left < right) {
//            m = left + (right - left) >> 1;
//            if (f(valueAll, staff, m)) {
//                ans = m;
//                right = m - 1;
//            } else {
//                left = m + 1;
//
//            }
//        }
//        return ans;
//    }
//
//    public static boolean f(long valueAll, long[][] staff, long limit) {
//
//        HashMap<Long, Long> map = new HashMap<>();
//
//
//
//    }
//
//    public static
//
//
//    // test
//    public static int[] randomArray(int len, int value) {
//        int[] arr = new int[len];
//        for (int i = 0; i < len; i++) {
//            arr[i] = (int) (Math.random() * value) + 1;
//        }
//        return arr;
//    }
//
//    // test
//    public static void main(String[] args) {
//        int n = 9;
//        int v = 50;
//        int testTime = 5000;
//        System.out.println("begin");
//        for (int i = 0; i < testTime; i++) {
//            int OA.MaxNumDinstinctNum.a = (int) (Math.random() * v) + 1;
//            int b = (int) (Math.random() * v) + 1;
//            int len = (int) (Math.random() * n);
//            int[] value = randomArray(len, v);
//            int[] trouble = randomArray(len, v);
//            long ans1 = mostMin1(OA.MaxNumDinstinctNum.a, b, value, trouble);
//            long ans2 = mostMin2(OA.MaxNumDinstinctNum.a, b, value, trouble);
//            if (ans1 != ans2) {
//                System.out.println("fuck！");
//                break;
//            }
//        }
//        System.out.println("end");
//    }
//}
