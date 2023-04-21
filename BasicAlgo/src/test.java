import basicAlgo.DPAdvanced.StrangerPrinter;
import com.sun.source.util.Trees;
import sun.security.provider.certpath.SunCertPathBuilder;

import java.util.*;

public class test {
    public static int minimizeMax(int[] nums, int p) {
        int ans = 0;
        Arrays.sort(nums);
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> (a[0] - b[0]));
        TreeSet<Integer> treeSet = new TreeSet<>();
        for (int i = 1; i < nums.length; i++) {
            queue.add(new int[]{nums[i] - nums[i - 1], i - 1, i});
            treeSet.add(i);
        }
        treeSet.add(0);
        HashSet<Integer> set = new HashSet<>();
        TreeMap<Integer, Integer> map = new TreeMap<>();
        while (p > 0) {
            int[] cur = queue.poll();
            while (set.contains(cur[1]) || set.contains(cur[2])) {
                cur = queue.poll();
            }
            ans = Math.max(ans, cur[0]);
            set.add(cur[1]);
            set.add(cur[2]);
            Integer ceiling = treeSet.ceiling(cur[2] + 1);
            Integer floor = treeSet.floor(cur[1] - 1);
            treeSet.remove(cur[1]);
            treeSet.remove(cur[2]);
            if (ceiling != null && floor != null) {
                queue.add(new int[]{nums[ceiling] - nums[floor], floor, ceiling});
            }
            p--;
        }
        return ans;


    }

    public static void main(String[] args) {
        int[] nums = {2, 6, 2, 4, 2, 2, 0, 2};
        int p = 4;
        System.out.println(minimizeMax(nums, p));
        ArrayList<Integer> list = new ArrayList<>();
        System.out.println(list.size());


        //[2,6,2,4,2,2,0,2]
        //4
    }

    public long[] distance(int[] nums) {
        int n = nums.length;
        long[] ans = new long[n];
        HashMap<Integer, long[]> backMap = new HashMap<>();
        HashMap<Integer, long[]> frontMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (backMap.containsKey(nums[i])) {
                long[] cur = backMap.get(nums[i]);
                cur[0] += 1;
                cur[1] += i;
            } else {
                backMap.put(nums[i], new long[]{1, i});
                frontMap.put(nums[i], new long[2]);
            }
        }
        for (int i = 0; i < n; i++) {
            long[] back = backMap.get(nums[i]);
            long[] front = frontMap.get(nums[i]);
            if(back[0] + front[0] == 1){
                ans[i] = 0;
            }
            back[0] --;
            back[1] -= i;
            long fromBack = back[1] - (back[0] * i);
            long fromFront = front[0] * i - front[1];
            ans[i] = fromBack + fromFront;
            front[0] ++;
            front[1] += i;
        }
        return ans;

    }
    public int minimumVisitedCells(int[][] grid) {
        PriorityQueue<int[]> queue = new PriorityQueue<>();
        int n = grid.length;
        int m = grid[0].length;
        boolean[][] visited = new boolean[n][m];
        LinkedList<int[]> list = new LinkedList<>();
        LinkedList<int[]> next = new LinkedList<>();
        int ans = -1;
        int step = 0;
        int[] xVisited = new int[m];
        int[] yVisited = new int[n];
        list.add(new int[]{0, 0});
        while(!list.isEmpty()){
            step ++;
            next = new LinkedList<>();
            while(!list.isEmpty()){
                int[] cur = list.pollFirst();
                if(cur[0] == n - 1 && cur[1] == m - 1){
                    ans = step;
                    break;
                }
                int xLimit = Math.min(n - 1, cur[0] + grid[cur[0]][cur[1]]);
                int yLimit = Math.min(m - 1, cur[1] + grid[cur[0]][cur[1]]);
                for(int x = Math.max(cur[0], xVisited[cur[1]]) + 1; x <= xLimit; x ++){
                    int[] add = new int[]{x, cur[1]};
                    xVisited[cur[1]] = x;
                    next.add(add);
                }
                for(int y = Math.max(cur[1], yVisited[cur[0]]) + 1; y <= yLimit; y ++){
                    int[] add = new int[]{cur[0], y};
                    yVisited[cur[0]] = y;
                    next.add(add);
                }
            }
            list = next;
            if(ans != -1){
                break;
            }
        }

        return ans;
    }

    public void process(int x, int y, int[][] dp){
        if(x >= dp.length || y >= dp[0].length){
            return;
        }
        String str = "ab";
        StringBuilder sb = new StringBuilder();
        sb.append(5);

    }
}









