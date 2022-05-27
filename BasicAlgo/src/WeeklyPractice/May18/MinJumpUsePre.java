package WeeklyPractice.May18;

import jdk.nashorn.internal.IntDeque;

// 来自学员问题
// 为了给刷题的同学一些奖励，力扣团队引入了一个弹簧游戏机
// 游戏机由 N 个特殊弹簧排成一排，编号为 0 到 N-1
// 初始有一个小球在编号 0 的弹簧处。若小球在编号为 i 的弹簧处
// 通过按动弹簧，可以选择把小球向右弹射 jump[i] 的距离，或者向左弹射到任意左侧弹簧的位置
// 也就是说，在编号为 i 弹簧处按动弹簧，
// 小球可以弹向 0 到 i-1 中任意弹簧或者 i+jump[i] 的弹簧（若 i+jump[i]>=N ，则表示小球弹出了机器）
// 小球位于编号 0 处的弹簧时不能再向左弹。
// 为了获得奖励，你需要将小球弹出机器。
// 请求出最少需要按动多少次弹簧，可以将小球从编号 0 弹簧弹出整个机器，即向右越过编号 N-1 的弹簧。
// 测试链接 : https://leetcode-cn.com/problems/zui-xiao-tiao-yue-ci-shu/

public class MinJumpUsePre {
    public static int minJump(int[] jump) {
        int n = jump.length;
        int[] queue = new int[n];
        int l = 0;
        int r = 0;
        queue[r++] = 0;
        IndexTree tree = new IndexTree(n);
        for (int i = 1; i < n; i++) {
            tree.add(i, 1);
        }
        tree.add(1, -1);
        int ans = 0;
        while (r < n) {
            int temp = r;
            for (; l < temp; l++) {
                int cur = queue[l];
                int forward = cur + jump[l];
                if (forward >= n) {
                    return ans + 1;
                }
                if (tree.values(forward) != 0) {
                    queue[r++] = forward;
                    tree.add(forward, -1);
                }
                while (tree.sum(cur - 1) != 0) {
                    int find = find(tree, cur - 1);
                    queue[r++] = find;
                    tree.add(find, -1);
                }
            }
            ans ++;
        }
        return -1;
    }

    public static int find(IndexTree it, int r) {
        int l = 0;
        int ans = 0;
        int mid = 0;
        while (l <= r) {
            mid = (l + r) / 2;
            if (it.sum(mid) > 0) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return ans;
    }

    public static class IndexTree {
        private int[] arr;
        private int length;

        public IndexTree(int n) {
            arr = new int[n + 1];
            length = n;
        }

        public void add(int index, int value) {
            while (index <= length) {
                arr[index] += value;
                index += (index & -index);
            }
        }

        public int values(int index) {
            if (index == 0) {
                return arr[0];
            } else {
                return sum(index) - sum(index - 1);
            }
        }

        private int sum(int index) {
            index++;
            int ans = 0;
            while (index > 0) {
                ans += arr[index];
                index -= index & -index;
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        int[] jump = {2,5,1,1,1,1};
        System.out.println(minJump(jump));
    }
}
