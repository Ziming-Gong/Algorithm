package LeetCodeDays;

import apple.laf.JRSUIUtils;

import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

public class LC632SmallestRangeCoveringElementsfromKLists {
    public class Node {
        public int arrIndex;
        public int curIndex;
        public int value;

        public Node(int a, int c, int v) {
            arrIndex = a;
            curIndex = c;
            value = v;
        }
    }

    public class nodeComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o1.value - o2.value;
        }
    }


    public int[] smallestRange(List<List<Integer>> nums) {
        TreeSet<Node> map = new TreeSet<>(new nodeComparator());
        for (int i = 0; i < nums.size(); i++) {
            map.add(new Node(i, 0, nums.get(i).get(0)));
        }
        int length = map.last().value - map.first().value;
        int[] ans = new int[]{map.first().value, map.last().value};
        Node cur = map.first();
        while (cur.curIndex + 1 < nums.get(cur.arrIndex).size()) {
            map.remove(cur);
            cur.curIndex += 1;
            cur.value = nums.get(cur.arrIndex).get(cur.arrIndex);
            map.add(cur);
            if (length > map.last().value - map.first().value) {
                ans[0] = map.first().value;
                ans[1] = map.last().value;
                length = map.last().value - map.first().value;
            }
            cur = map.first();
        }

        return ans;
    }
    /*
    [
    1[-38,15,17,18],
    2[-34,46,58,59,61],
    3[-55,-31,-13,64,82,82,83,84,85],
    4[-3,63,70,90],
    5[2,6,10,28,28,32,32,32,33],
    6[-23,82,88,88,88,89],
    7[33,60,72,74,75],
    8[-5,44,44,57,58,58,60],
    9[-29,-22,-4,-4,17,18,19,19,19,20],
    10[22,57,82,89,93,94],[24,38,45]
    11[-100,-56,41,49,50,53,53,54],
    12[-76,-69,-66,-53,-27,-1,9,29,31,32,32,32,34],
    13[22,47,56],
    14[-34,-28,7,44]]
     */

    public int[] smallestRange1(List<List<Integer>> nums) {
        TreeSet<int[]> map = new TreeSet<>((a, b) -> (a[0] - b[0] < 0 ? -1 : 1));
        for (int i = 0; i < nums.size(); i++) {
            map.add(new int[]{nums.get(i).get(0), 0, i});
        }
        int length = map.last()[0] - map.first()[0];
        int[] ans = new int[]{map.last()[0], map.last()[0]};
        int[] cur = map.first();
        while (cur[1] + 1 < nums.get(cur[3]).size()) {
            map.remove(cur);
            cur[1] += 1;
            cur[0] = nums.get(cur[1]).get(cur[1]);
            map.add(cur);
            if (length > map.last()[0] - map.first()[0]) {
                ans[0] = map.first()[0];
                ans[1] = map.last()[0];
                length = map.last()[0] - map.first()[0];
            }
            cur = map.first();
        }

        return ans;
    }
}
