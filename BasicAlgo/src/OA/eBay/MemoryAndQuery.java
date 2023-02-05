package OA.eBay;

import javax.swing.plaf.basic.BasicTreeUI;
import java.util.*;

public class MemoryAndQuery {

    public static HashMap<Integer, Integer> headMap = new HashMap<>();
    public static HashMap<Integer, Integer> tailMap = new HashMap<>();
    public static HashMap<Integer, int[]> idMap = new HashMap<>();

    /*
    给一个数组memory，0代表空1代表占用，和一个二维数组query，query里有两个操作，
    一个是allocate，参数是x，在memory里找连续的x个空位存放数据，id就是存放数据的序数（第一次存进去就是1）
    如果存放失败返回一个-1；第二个操作是delete，参数是id，如果memory里有的话删除并返回删除的长度，
    如果没有返回-1，最后整个函数返回一个数组，数组的元素就是每次query的返回值。
     */
    public static List<Integer> solve(List<Integer> memory, List<List<Integer>> queries) {
        TreeSet<int[]> tree = new TreeSet<>((a, b) -> (a[1] == b[1] ? a[0] - b[0] : a[1] - b[1]));
        int begin = 0;
        int emptyCnt = 0;
        for (int i = 0; i < memory.size(); i++) {
            if (memory.get(i) == 1) {
                if (emptyCnt == 0) {
                    continue;
                }
                tree.add(new int[]{begin, emptyCnt});
                headMap.put(begin, emptyCnt);
                tailMap.put(i - 1, emptyCnt);
                emptyCnt = 0;
            } else {
                if (emptyCnt == 0) {
                    begin = i;
                    emptyCnt++;
                } else {
                    emptyCnt++;
                }
            }
        }
        if (emptyCnt != 0) {
            tree.add(new int[]{begin, emptyCnt});
            headMap.put(begin, emptyCnt);
            tailMap.put(memory.size() - 1, emptyCnt);
        }
        List<Integer> ans = new ArrayList<>();
        int id = 1;
        for (List<Integer> query : queries) {
            if (query.get(0) == 1) { // 1 : allocate
                int needSpace = query.get(1);
                int[] cur = tree.ceiling(new int[]{-1, needSpace});
                if (cur == null) {
                    ans.add(-1);
                    continue;
                }
                int rest = cur[1] - needSpace;
                int start = cur[0];
                int end = cur[0] + cur[1] - 1;
                headMap.remove(start);
                tailMap.remove(end);
                tree.remove(cur);
                if (rest != 0) {
                    tree.add(new int[]{start + needSpace, rest});
                    headMap.put(start + needSpace, rest);
                    tailMap.put(end, rest);
                }
                ans.add(id);
                idMap.put(id++, new int[]{start, needSpace});
            } else { // 2 : delete
                int targetId = query.get(1);
                if (!idMap.containsKey(targetId)) {
                    ans.add(-1);
                    continue;
                }
                int[] cur = idMap.get(targetId);
                int start = cur[0];
                int releaseSpace = cur[1];
                ans.add(releaseSpace);
                idMap.remove(targetId);
                int ceiling = start + releaseSpace + 1;
                int floor = start - 1;
                if (headMap.containsKey(ceiling) && tailMap.containsKey(floor)) {
                    // first section
                    int headLen = tailMap.get(floor);
                    tailMap.remove(floor);
                    headMap.remove(floor - headLen + 1);
                    tree.remove(tree.ceiling(new int[]{floor - headLen + 1, headLen}));
                    // third section
                    int tailLen = headMap.get(ceiling);
                    headMap.remove(ceiling);
                    tailMap.remove(ceiling + tailLen - 1);
                    tree.remove(tree.ceiling(new int[]{ceiling, tailLen}));
                    // merge
                    int totalLen = releaseSpace + headLen + tailLen;
                    int newStart = floor - headLen + 1;
                    tree.add(new int[]{newStart, totalLen});
                    headMap.put(newStart, totalLen);
                    tailMap.put(newStart + totalLen - 1, totalLen);
                } else if (headMap.containsKey(ceiling)) {
                    // have third section
                    int tailLen = headMap.get(ceiling);
                    headMap.remove(ceiling);
                    tailMap.remove(ceiling + tailLen - 1);
                    tree.remove(tree.ceiling(new int[]{ceiling, tailLen}));
                    // merge
                    int totalLen = releaseSpace + tailLen;
                    int newStart = start;
                    tree.add(new int[]{newStart, totalLen});
                    headMap.put(newStart, totalLen);
                    tailMap.put(newStart + totalLen - 1, totalLen);
                } else if (tailMap.containsKey(floor)) {
                    // first Section
                    int headLen = tailMap.get(floor);
                    tailMap.remove(floor);
                    headMap.remove(floor - headLen + 1);
                    tree.remove(tree.ceiling(new int[]{floor - headLen + 1, headLen}));
                    // merge
                    int totalLen = releaseSpace + headLen;
                    int newStart = floor - headLen + 1;
                    tree.add(new int[]{newStart, totalLen});
                    headMap.put(newStart, totalLen);
                    tailMap.put(newStart + totalLen - 1, totalLen);
                } else {
                    headMap.put(start, releaseSpace);
                    tailMap.put(start + releaseSpace - 1, releaseSpace);
                    tree.add(new int[]{start, releaseSpace});
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        List<Integer> memory = new ArrayList<>();
        memory.add(0);
        memory.add(0);
        memory.add(0);
        memory.add(0);
        memory.add(0);
        memory.add(1);
        memory.add(0);
        memory.add(0);
        memory.add(1);
        memory.add(0);
        List<List<Integer>> queries = new ArrayList<>();
        List<Integer> cur1 = new ArrayList<>();
        cur1.add(1);
        cur1.add(1);
        queries.add(cur1);
        List<Integer> cur2 = new ArrayList<>();
        cur2.add(1);
        cur2.add(2);
        queries.add(cur2);
        List<Integer> cur3 = new ArrayList<>();
        cur3.add(1);
        cur3.add(5);
        queries.add(cur3);
        List<Integer> cur4 = new ArrayList<>();
        cur4.add(1);
        cur4.add(5);
        queries.add(cur4);
        List<Integer> cur5 = new ArrayList<>();
        cur5.add(2);
        cur5.add(3);
        queries.add(cur5);
        List<Integer> cur6 = new ArrayList<>();
        cur6.add(1);
        cur6.add(3);
        queries.add(cur6);
        List<Integer> cur7 = new ArrayList<>();
        cur7.add(1);
        cur7.add(2);
        queries.add(cur7);
        List<Integer> cur8 = new ArrayList<>();
        cur8.add(1);
        cur8.add(3);
        queries.add(cur8);
        List<Integer> cur9 = new ArrayList<>();
        cur9.add(2);
        cur9.add(4);
        queries.add(cur9);
        List<Integer> cur10 = new ArrayList<>();
        cur10.add(1);
        cur10.add(4);
        queries.add(cur10);
        List<Integer> cur11 = new ArrayList<>();
        cur11.add(2);
        cur11.add(5);
        queries.add(cur11);
        List<Integer> cur12 = new ArrayList<>();
        cur12.add(1);
        cur12.add(6);
        queries.add(cur12);







        List<Integer> ans = solve(memory, queries);
        for (int i : ans) {
            System.out.printf(i + ", ");
        }


    }


}
