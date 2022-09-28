package WeeklyPractice.Sep07;

// 来自美团
// 给定一个正数n, 表示从0位置到n-1位置每个位置放着1件衣服
// 从0位置到n-1位置不仅有衣服，每个位置还摆着1个机器人
// 给定两个长度为n的数组，powers和rates
// powers[i]表示i位置的机器人的启动电量
// rates[i]表示i位置的机器人收起1件衣服的时间
// 使用每个机器人只需要付出启动电量
// 当i位置的机器人收起i位置的衣服，它会继续尝试往右收起i+1位置衣服
// 如果i+1位置的衣服已经被其他机器人收了或者其他机器人正在收
// 这个机器人就会停机, 不再收衣服。
// 不过如果它不停机，它会同样以rates[i]的时间来收起这件i+1位置的衣服
// 也就是收衣服的时间为每个机器人的固定属性，当它收起i+1位置的衣服，
// 它会继续检查i+2位置...一直到它停机或者右边没有衣服可以收了
// 形象的来说，机器人会一直尝试往右边收衣服，收k件的话就耗费k * rates[i]的时间
// 但是当它遇见其他机器人工作的痕迹，就会认为后面的事情它不用管了，进入停机状态
// 你手里总共有电量b，准备在0时刻将所有想启动的机器人全部一起启动
// 过后不再启动新的机器人，并且启动机器人的电量之和不能大于b
// 返回在最佳选择下，假快多久能收完所有衣服
// 如果无论如何都收不完所有衣服，返回-1
// 给定数据: int n, int b, int[] powers, int[] rates
// 数据范围:
// powers长度 == rates长度 == n <= 1000
// 1 <= b <= 10^5
// 1 <= powers[i]、rates[i] <= 10^5
// 0号 : 10^5 * 10^3 -> 10^8
// log 10^8 * N^2  -> 27 * 10^6 -> 10^7
// 优化之后 : (log10^8) -> 27 * 1000 * 10

import Questions.code_2.ChooseWork;

import java.util.Arrays;

public class RobotAndClothes {

    public static int fast1(int n, int b, int[] powers, int[] rates) {
        int[][] dp = new int[n][b + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= b; j++) {
                dp[i][j] = -1;
            }
        }
        int ans = process(0, b, powers, rates, dp);

        return ans == Integer.MAX_VALUE ? -1 : ans;


    }

    public static int process(int index, int rest, int[] power, int[] rates, int[][] dp) {
        if (index == power.length) {
            return 0;
        }
        if (power[index] > rest) {
            return Integer.MAX_VALUE;
        }
        if (dp[index][rest] != -1) {
            return dp[index][rest];
        }
        int ans = Integer.MAX_VALUE;

        for (int i = index; i < power.length; i++) {
            int curCost = rates[index] * (i - index + 1);
            int nextCost = process(i + 1, rest - power[index], power, rates, dp);
            ans = Math.min(ans, Math.max(curCost, nextCost));
        }
        dp[index][rest] = ans;
        return ans;
    }

    public static int fast2(int n, int b, int[] powers, int[] rates) {
        if (powers[0] > b) {
            return -1;
        }
        int L = 1;
        int R = rates[0] * n;
        int mid;
        int ans = -1;
        while (L <= R) {
            mid = (L + R) >> 1;
            if (minCost(mid, powers, rates) <= b) {
                ans = mid;
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return ans;

    }

    public static int minCost(int time, int[] powers, int[] rates) {
        int[] dp = new int[powers.length];
        Arrays.fill(dp, -1);
        return process1(0, time, powers, rates, dp);
    }

    public static int process1(int index, int time, int[] power, int[] rates, int[] dp) {
        if (index == power.length) {
            return 0;
        }
        if (dp[index] != -1) {
            return dp[index];
        }
        int ans = Integer.MAX_VALUE;
        int used = rates[index];
        for (int i = index; i < power.length && used <= time; i++) {
            ans = Math.min(ans, process1(i + 1, time, power, rates, dp));
            used += rates[index];
        }
        ans = ans == Integer.MAX_VALUE ? ans : (power[index] + ans);
        dp[index] = ans;
        return ans;
    }


    public static int fast3(int n, int b, int[] powers, int[] rates) {
        if (n == 0) {
            return 0;
        }
        if (b == 0 || powers[0] > b) {
            return -1;
        }
        int R = n * rates[0];
        int L = 1, mid;
        int ans = -1;
        while (L <= R) {
            mid = (L + R) >> 1;
            if (minCost1(powers, rates, mid) <= b) {
                ans = mid;
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return ans;
    }

    public static int minCost1(int[] power, int[] rates, int time) {
        int n = power.length;
        int[] dp = new int[n + 1];
        //      dp[n-1]  dp[n]
        //         n-1     n
        SegmentTree st = new SegmentTree(n + 1);
        st.update(n, 0);
        for (int i = n - 1; i >= 0; i--) {
            if (rates[i] > time) {
                dp[i] = Integer.MAX_VALUE;
            } else {
                int j = Math.min(i + (time / rates[i]) - 1, n - 1);
                // for.... logN
                int next = st.getMin(i + 1, j + 1);
                int ans = next == Integer.MAX_VALUE ? next : (power[i] + next);
                dp[i] = ans;
            }
            st.update(i, dp[i]);
        }
        return dp[0];
    }

    public static class SegmentTree {
        public int[] min;
        public int n;

        public SegmentTree(int n) {
            this.n = n + 1;
            min = new int[n << 2];
            Arrays.fill(min, Integer.MAX_VALUE);
        }

        public void update(int index, int c) {
            index++;
            update(index, index, c, 1, n, 1);
        }

        public int getMin(int l, int r) {
            l++;
            r++;
            return getMin(l, r, 1, n, 1);
        }

        private int getMin(int L, int R, int l, int r, int rt) {
            if (L <= l && r <= R) {
                return min[rt];
            }
            int mid = (l + r) >> 1;
            int ans = Integer.MAX_VALUE;
            if (L <= mid) {
                ans = Math.min(ans, getMin(L, R, l, mid, rt << 1));
            }
            if (R > mid) {
                ans = Math.min(ans, getMin(L, R, mid + 1, r, rt << 1 | 1));
            }
            return ans;
        }

        private void update(int L, int R, int c, int l, int r, int rt) {
            if (L <= l && r <= R) {
                min[rt] = Math.min(c, min[rt]);
                return;
            }
            int mid = (l + r) >> 1;
            if (L <= mid) {
                update(L, R, c, l, mid, rt << 1);
            }
            if (R > mid) {
                update(L, R, c, mid + 1, r, rt << 1 | 1);
            }
            pushUp(rt);
        }

        private void pushUp(int rt) {
            min[rt] = Math.min(min[rt << 1], min[rt << 1 | 1]);
        }


    }


    // 为了测试
    public static void main(String[] args) {
        int N = 200;
        int B = 100;
        int P = 20;
        int R = 10;
        int testTimes = 5000;
        System.out.println("功能测试开始");
        for (int i = 0; i < testTimes; i++) {
            int n = (int) (Math.random() * N) + 1;
            int b = (int) (Math.random() * B) + 1;
            int[] powers = randomArray(n, P);
            int[] rates = randomArray(n, R);
            int ans1 = fast1(n, b, powers, rates);
            int ans2 = fast2(n, b, powers, rates);
            int ans3 = fast3(n, b, powers, rates);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("出错了!");
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans3);
                break;
            }
        }
        System.out.println("功能测试结束");

        System.out.println("性能测试开始");
        int n = 10000;
        int b = 100000;
        int[] powers = randomArray(n, b);
        int[] rates = randomArray(n, b);
        System.out.println("衣服规模 : " + n);
        System.out.println("电量规模 : " + b);
        System.out.println("机器人启动费用取值规模 : " + b);
        System.out.println("机器人工作速度取值规模 : " + b);
        long start = System.currentTimeMillis();
        fast3(n, b, powers, rates);
        long end = System.currentTimeMillis();
        System.out.println("运行时间 : " + (end - start) + " 毫秒");
        System.out.println("性能测试结束");

    }

    // 为了测试
    public static int[] randomArray(int n, int v) {
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = (int) (Math.random() * v) + 1;
        }
        return ans;
    }

}
