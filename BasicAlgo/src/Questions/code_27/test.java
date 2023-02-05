package Questions.code_27;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class test {
    public static int minCost(int[][] programs, int nums) {
        if (nums == 0 || programs == null || programs.length == 0) {
            return 0;
        }
        int size = clean(programs);
        int[] map1 = init(1 << (nums << 1));
        int[] map2 = null;
        if ((nums & 1) == 0) {
            // nums = 8 , 4
            f(programs, size, 0, 0, 0, nums >> 1, map1);
            map2 = map1;
        } else {
            // nums == 7 4 -> map1 3 -> map2
            f(programs, size, 0, 0, 0, nums >> 1, map1);
            map2 = init(1 << (nums << 1));
            f(programs, size, 0, 0, 0, nums - (nums >> 1), map2);
        }
        // 16 mask : 0..00 1111.1111(16个)
        // 12 mask : 0..00 1111.1111(12个)
        int mask = (1 << (nums << 1)) - 1;
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < map1.length; i++) {
            if (map1[i] != Integer.MAX_VALUE && map2[mask & (~i)] != Integer.MAX_VALUE) {
                ans = Math.min(ans, map1[i] + map2[mask & (~i)]);
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    // [
    // [9, 1, 100]
    // [2, 9 , 50]
    // ...
    // ]
    public static int clean(int[][] programs) {
        int x = 0;
        int y = 0;
        for (int[] p : programs) {
            x = Math.min(p[0], p[1]);
            y = Math.max(p[0], p[1]);
            p[0] = x;
            p[1] = y;
        }
        Arrays.sort(programs, (a, b) -> a[0] != b[0] ? (a[0] - b[0]) : (a[1] != b[1] ? (a[1] - b[1]) : (a[2] - b[2])));
        x = programs[0][0];
        y = programs[0][1];
        int n = programs.length;
        // (0, 1, ? )
        for (int i = 1; i < n; i++) {
            if (programs[i][0] == x && programs[i][1] == y) {
                programs[i] = null;
            } else {
                x = programs[i][0];
                y = programs[i][1];
            }
        }
        int size = 1;
        for (int i = 1; i < n; i++) {
            if (programs[i] != null) {
                programs[size++] = programs[i];
            }
        }
        // programs[0...size-1]
        return size;
    }

    public static int[] init(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = Integer.MAX_VALUE;
        }
        return arr;
    }

    public static void f(int[][] programs, int size, int index, int status, int cost, int rest, int[] map) {
        if (rest == 0) {
            map[status] = Math.min(map[status], cost);
        } else {
            if (index != size) {
                f(programs, size, index + 1, status, cost, rest, map);
                int pick = 0 | (1 << programs[index][0]) | (1 << programs[index][1]);
                if ((pick & status) == 0) {
                    f(programs, size, index + 1, status | pick, cost + programs[index][2], rest - 1, map);
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println((int) 1e9 + 7);
    }
}
