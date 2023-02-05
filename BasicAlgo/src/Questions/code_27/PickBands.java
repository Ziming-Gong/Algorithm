package Questions.code_27;

// 每一个项目都有三个数，[a,b,c]表示这个项目a和b乐队参演，花费为c
// 每一个乐队可能在多个项目里都出现了，但是只能挑一次
// nums是可以挑选的项目数量，所以一定会有nums*2只乐队被挑选出来
// 乐队的全部数量一定是nums*2，且标号一定是0 ~ nums*2-1
// 返回一共挑nums轮(也就意味着一定请到所有的乐队)，最少花费是多少？

import java.awt.color.ProfileDataException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.CompletionStage;

public class PickBands {
    public static int minCost(int[][] programs, int nums) {
        // clean the data
        int size = clean(programs);

        int[] map1 = init(1 << (nums << 1));
        int[] map2 = null;
        if (nums % 2 == 0) {
            process(programs, size, 0, 0, nums / 2, 0, map1);
            map2 = map1;
        } else {
            process(programs, size, 0, 0, (nums + 1) / 2, 0, map1);
            map2 = init(1 << (nums << 1));
            process(programs, size, 0, 0, nums / 2, 0, map2);
        }

        final int offset = (1 << ((nums << 1))) - 1;
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < map1.length; i++) {
            if (map1[i] != Integer.MAX_VALUE) {
                if (map2[offset ^ i] != Integer.MAX_VALUE) {
                    ans = Math.min(ans, map1[i] + map2[offset ^ i]);
                }
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public static void process(int[][] programs, int size, int index, int pick, int rest, int cost, int[] map) {
        if (rest == 0) {
            map[pick] = Math.min(cost, map[pick]);
        } else {
            if (index != size) {
                process(programs, size, index + 1, pick, rest, cost, map);
                int x = programs[index][0];
                int y = programs[index][1];
                int cur = (1 << x) | (1 << y);
                if ((cur & pick) == 0) {
                    process(programs, size, index + 1, pick | cur, rest - 1, cost + programs[index][2], map);
                }
            }
        }
    }

    public static int[] init(int size) {
        int[] res = new int[size];
        for (int i = 0; i < size; i++) {
            res[i] = Integer.MAX_VALUE;
        }
        return res;
    }

    public static int clean(int[][] programs) {
        int max;
        int min;
        for (int i = 0; i < programs.length; i++) {
            max = Math.max(programs[i][0], programs[i][1]);
            min = Math.min(programs[i][0], programs[i][1]);
            programs[i][0] = min;
            programs[i][1] = max;
        }

        Arrays.sort(programs, (a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] != b[1] ? a[1] - b[1] : a[2] - b[2]);
        int x = programs[0][0];
        int y = programs[0][1];

        for (int i = 1; i < programs.length; i++) {
            if (programs[i][0] == x && programs[i][1] == y) {
                programs[i] = null;
            } else {
                x = programs[i][0];
                y = programs[i][1];
            }
        }
        int size = 1;
        for (int i = 1; i < programs.length; i++) {
            if (programs[i] != null) {
                programs[size++] = programs[i];
            }
        }
        return size;
    }

    // 为了测试
    public static int right(int[][] programs, int nums) {
        min = Integer.MAX_VALUE;
        r(programs, 0, nums, 0, 0);
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    public static int min = Integer.MAX_VALUE;

    public static void r(int[][] programs, int index, int rest, int pick, int cost) {
        if (rest == 0) {
            min = Math.min(min, cost);
        } else {
            if (index < programs.length) {
                r(programs, index + 1, rest, pick, cost);
                int cur = (1 << programs[index][0]) | (1 << programs[index][1]);
                if ((pick & cur) == 0) {
                    r(programs, index + 1, rest - 1, pick | cur, cost + programs[index][2]);
                }
            }
        }
    }

    // 为了测试
    public static int[][] randomPrograms(int N, int V) {
        int nums = N << 1;
        int n = nums * (nums - 1);
        int[][] programs = new int[n][3];
        for (int i = 0; i < n; i++) {
            int a = (int) (Math.random() * nums);
            int b = 0;
            do {
                b = (int) (Math.random() * nums);
            } while (b == a);
            programs[i][0] = a;
            programs[i][1] = b;
            programs[i][2] = (int) (Math.random() * V) + 1;
        }
        return programs;
    }

    // 为了测试
    public static void main(String[] args) {
        int N = 4;
        int V = 100;
        int T = 10000;
        System.out.println("测试开始");
        for (int i = 0; i < T; i++) {
            int nums = (int) (Math.random() * N) + 1;
            int[][] programs = randomPrograms(nums, V);
            int ans1 = right(programs, nums);
            int ans2 = minCost(programs, nums);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");

        long start;
        long end;
        int[][] programs;

        programs = randomPrograms(7, V);
        start = System.currentTimeMillis();
        right(programs, 7);
        end = System.currentTimeMillis();
        System.out.println("right方法，在nums=7时候的运行时间(毫秒) : " + (end - start));

        programs = randomPrograms(10, V);
        start = System.currentTimeMillis();
        minCost(programs, 10);
        end = System.currentTimeMillis();
        System.out.println("minCost方法，在nums=10时候的运行时间(毫秒) : " + (end - start));

    }


}
