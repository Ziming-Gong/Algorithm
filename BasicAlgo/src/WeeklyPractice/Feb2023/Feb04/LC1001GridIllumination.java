package WeeklyPractice.Feb2023.Feb04;

import com.sun.tools.corba.se.idl.constExpr.Or;

import java.util.HashMap;
import java.util.HashSet;

public class LC1001GridIllumination {
    public int[] gridIllumination(int n, int[][] lamps, int[][] queries) {
        HashMap<Integer, Integer> rows = new HashMap<>();
        HashMap<Integer, Integer> cols = new HashMap<>();
        HashMap<Integer, Integer> left = new HashMap<>();
        HashMap<Integer, Integer> right = new HashMap<>();
        HashSet<Long> set = new HashSet<>();
        int l, r, x, y;
        long p;
        for (int[] lamp : lamps) {
            x = lamp[0];
            y = lamp[1];
            l = getLeft(x, y, n);
            r = getRight(x, y, n);
            p = getPoint(x, y, n);
            if (!set.contains(p)) {
                rows.put(x, rows.getOrDefault(x, 0) + 1);
                cols.put(y, cols.getOrDefault(y, 0) + 1);
                left.put(l, left.getOrDefault(l, 0) + 1);
                right.put(r, right.getOrDefault(r, 0) + 1);
                set.add(p);
            }
        }
        int[] ans = new int[queries.length];
        int index = 0;
        for (int[] query : queries) {
            x = query[0];
            y = query[1];
            l = getLeft(x, y, n);
            r = getRight(x, y, n);
            if (!left.containsKey(l) && !right.containsKey(r) && !rows.containsKey(x) && !cols.containsKey(y)) {
                ans[index++] = 0;
            } else {
                ans[index++] = 1;
            }
            for (int i = Math.max(x - 1, 0); i <= Math.min(n - 1, x + 1); i++) {
                for (int j = Math.max(y - 1, 0); j <= Math.min(n - 1, y + 1); j++) {
                    l = getLeft(i, j, n);
                    r = getRight(i, j, n);
                    p = getPoint(i, j, n);
                    if (set.contains(p)) {
                        set.remove(p);
                        rows.put(i, rows.get(i) - 1);
                        cols.put(j, cols.get(j) - 1);
                        left.put(l, left.get(l) - 1);
                        right.put(r, right.get(r) - 1);
                        if (rows.get(i) == 0) {
                            rows.remove(i);
                        }
                        if (cols.get(j) == 0) {
                            cols.remove(j);
                        }
                        if (left.get(l) == 0) {
                            left.remove(l);
                        }
                        if (right.get(r) == 0) {
                            right.remove(r);
                        }
                    }
                }
            }

        }
        return ans;
    }

    public int getLeft(int x, int y, int n) {
        return x >= y ? x - y : n - 1 + y - x;
    }

    public int getRight(int x, int y, int n) {
        return x + y;
    }

    public long getPoint(int x, int y, int n) {
        return ((long) x) * (long) (n) + y;
    }
}
