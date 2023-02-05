package WeeklyPractice.Nov2;
// 来自华为
// 做甜点需要购买配料，目前共有n种基料和m种配料可供选购
// 制作甜点需要遵循以下几条规则：
// 必须选择1种基料；可以添加0种、1种或多种配料，每种类型的配料最多添加2份
// 给定长度为n的数组base, base[i]表示第i种基料的价格
// 给定长度为m的数组topping, topping[j]表示第j种配料的价格
// 给定一个正数target，表示你做的甜点最终的价格要尽量接近这个数值
// 返回最接近这个数值的价格是多少
// 如果有多个方案，都最接近target，返回价格最小的那个答案
// 1 <= n,m <= 10
// 1 <= base[i], topping[j] <= 10 ^ 4
// 1 <= target <= 10 ^ 4


import java.util.Arrays;
import java.util.TreeSet;

public class DessertPriceClosedTarget_HuaWei {

    public static int closedTarget1(int[] base, int[] topping, int target) {
        TreeSet<Integer> set = new TreeSet<>();
        initSet(topping, 0, 0, set);

        int gap = Integer.MAX_VALUE;
        int cost;
        int ans = -1;
        for (int i = 0; i < base.length; i++) {
            cost = base[i];
            if (cost < target) {
                int rest = target - cost;
                Integer ceiling = set.ceiling(rest);
                Integer floor = set.floor(rest);
                if (ceiling == null || floor == null) {
                    cost += ceiling == null ? floor : ceiling;
                } else {
                    cost += cost + ceiling - target - (target - cost - floor) >= 0 ? floor : ceiling;
                }
            }

            if (gap >= Math.abs(target - cost)) {
                if (gap == Math.abs(target - cost)) {
                    ans = Math.min(ans, cost);
                } else {
                    ans = cost;
                }
                gap = Math.abs(target - ans);
            }
        }
        return ans;

    }

    public static void initSet(int[] topping, int index, int cost, TreeSet<Integer> set) {
        if (index == topping.length) {
            set.add(cost);
            return;
        }
        initSet(topping, index + 1, cost, set);
        initSet(topping, index + 1, cost + topping[index], set);
        initSet(topping, index + 1, cost + topping[index] * 2, set);
    }


    // 方法2，用数组排序+二分的方法

    public static int[] collect = new int[14348907];

    public static int size = 0;

    public static int closedTarget2(int[] base, int[] topping, int target) {
        size = 0;
        initArray(topping, 0, 0);
        Arrays.sort(collect, 0, size);
        int gap = Integer.MAX_VALUE;
        int ans = Integer.MAX_VALUE;
        for (int i : base) {
            int cur = i;
            if (cur < target) {
                int rest = target - cur;
                int ceiling = ceiling(rest);
                int floor = floor(rest);
                if (ceiling == -1 || floor == -1) {
                    cur += ceiling == -1 ? floor : ceiling;
                } else {
                    cur += ceiling - rest >= rest - floor ? floor : ceiling;
                }
            }
            if (Math.abs(cur - target) < Math.abs(ans - target)
                    || (Math.abs(cur - target) == Math.abs(ans - target) && cur < ans)) {
                ans = cur;
            }
        }
        return ans;

    }

    public static int floor(int num) {
        int l = 0;
        int r = size - 1;
        int m = 0;
        int ans = -1;
        while (l <= r) {
            m = (l + r) / 2;
            if (collect[m] <= num) {
                ans = collect[m];
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return ans;
    }

    public static int ceiling(int num) {
        int l = 0;
        int r = size - 1;
        int m = 0;
        int ans = -1;
        while (l <= r) {
            m = (l + r) / 2;
            if (collect[m] >= num) {
                ans = collect[m];
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return ans;
    }

    public static void initArray(int[] topping, int index, int cost) {
        if (index == topping.length) {
            collect[size++] = cost;
        } else {
            initArray(topping, index + 1, cost);
            initArray(topping, index + 1, cost + topping[index]);
            initArray(topping, index + 1, cost + topping[index] * 2);
        }
    }


    // 为了验证
    public static int[] randomArray(int n, int v) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = (int) (Math.random() * v) + 1;
        }
        return arr;
    }

    // 为了验证
    public static void main(String[] args) {
        int N = 8;
        int V = 10000;
        int testTime = 5000;
        System.out.println("功能测试开始");
        for (int i = 0; i < testTime; i++) {
            int n = (int) (Math.random() * N) + 1;
            int m = (int) (Math.random() * N) + 1;
            int[] base = randomArray(n, V);
            int[] topping = randomArray(m, V);
            int target = (int) (Math.random() * V) + 1;
            int ans1 = closedTarget1(base, topping, target);
            int ans2 = closedTarget2(base, topping, target);
            if (ans1 != ans2) {
                System.out.println("出错了!");
            }
        }
        System.out.println("功能测试结束");

        System.out.println("性能测试开始");
        int n = 15;
        int m = 15;
        int[] base = randomArray(n, V);
        int[] topping = randomArray(m, V);
        int target = (int) (Math.random() * V) + 1;
        System.out.println("base数组长度 : " + n);
        System.out.println("topping数组长度 : " + m);
        System.out.println("数值范围 : " + V);
        long start = System.currentTimeMillis();
        closedTarget2(base, topping, target);
        long end = System.currentTimeMillis();
        System.out.println("运行时间 : " + (end - start) + " 毫秒");
        System.out.println("性能测试结束");
    }
}
