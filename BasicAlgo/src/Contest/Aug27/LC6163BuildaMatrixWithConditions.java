package Contest.Aug27;

import WeeklyPractice.Aug03.LC716MaxStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.function.Predicate;
import java.util.jar.JarEntry;

public class LC6163BuildaMatrixWithConditions {
    public static int[][] buildMatrix(int k, int[][] rowConditions, int[][] colConditions) {
        int[][] ans = new int[k][k];
        HashMap<Integer, ArrayList<Integer>> rowMap = new HashMap<>();
        HashMap<Integer, Integer> rowFaMap = new HashMap<>();
        HashMap<Integer, Integer> colFaMap = new HashMap<>();
        HashMap<Integer, ArrayList<Integer>> colMap = new HashMap<>();
        HashSet<Integer> rowSet = new HashSet<>();
        HashSet<Integer> colSet = new HashSet<>();
        HashSet<Integer> set = new HashSet<>();
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 1; i <= k; i++) {
            set.add(i);
            rowSet.add(i);
            colSet.add(i);
        }
        for (int[] cur : rowConditions) {
            if (!rowMap.containsKey(cur[0])) {
                rowMap.put(cur[0], new ArrayList<>());
            }
            rowMap.get(cur[0]).add(cur[1]);
            rowFaMap.put(cur[1], rowFaMap.getOrDefault(cur[1], 0) + 1);
            set.remove(cur[1]);
            rowSet.remove(cur[1]);
        }
        HashMap<Integer, Integer> indexMap = new HashMap<>();
        int index = 0;
        while (index < k) {
            HashSet<Integer> cur = new HashSet<>();
            int temp = index;
            for (int i : rowSet) {
                indexMap.put(i, index);
                map.put(index, i);
                ans[index][index++] = i;
                for (int next : rowMap.getOrDefault(i, new ArrayList<>())) {
                    rowFaMap.put(next, rowFaMap.get(next) - 1);
                    if (rowFaMap.get(next) == 0) {
                        cur.add(next);
                    }
                }
                rowSet = cur;
                if (index == temp) {
                    return new int[0][0];
                }
            }
        }

        for (int[] cur : colConditions) {
            if (!colMap.containsKey(cur[0])) {
                colMap.put(cur[0], new ArrayList<>());
            }
            colMap.get(cur[0]).add(cur[1]);
            colFaMap.put(cur[1], colFaMap.getOrDefault(cur[1], 0) + 1);
            set.remove(cur[1]);
            colSet.remove(cur[1]);
        }
        if (set.isEmpty()) {
            return new int[0][0];
        }
        index = 0;
        while (!colSet.isEmpty()) {
            HashSet<Integer> cur = new HashSet<>();
            for (int i : colSet) {
                for (int j : colMap.getOrDefault(i, new ArrayList<>())) {
//                    int sonIndex = indexMap.get(i);
//                    int faIndex = indexMap.get(j);

                    colFaMap.put(j, colFaMap.get(j) - 1);
                    if (colFaMap.get(j) == 0) {
                        cur.add(j);
                    }
                }
                if (indexMap.get(i) > index ) {
                    swap(ans, indexMap.get(i), index);
                    int lastIndex = map.get(index);
                    indexMap.put(lastIndex, indexMap.get(i));
                    map.put(indexMap.get(i), lastIndex);
                    index ++;
                }
            }
            colSet = cur;
        }
        return ans;
    }

    public static void swap(int[][] grid, int i, int j) {
        int[] temp = grid[i];
        grid[i] = grid[j];
        grid[j] = temp;
    }

    public static void main(String[] args) {
        int k = 3;
        int[][] row = {{1, 2}, {3, 2}};
        int[][] col = {{2, 1}, {3, 2}};
        System.out.println(buildMatrix(k, row, col));
    }
}
