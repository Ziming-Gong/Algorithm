package basicAlgo.Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;

public class LeetCode743 {
    // pass the test;
    public int networkDelayTime1(int[][] times, int n, int k) {
        ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] line : times) {
            graph.get(line[0]).add(new int[]{line[1], line[2]});
        }
        int[] distance = new int[n + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[k] = 0;
        HashMap<Integer, Integer> curMap = new HashMap<>();
        curMap.put(k, 0);
        while (!curMap.isEmpty()) {
            HashMap<Integer, Integer> nextMap = new HashMap<>();
            for (Entry<Integer, Integer> cur : curMap.entrySet()) {
                int node = cur.getKey();
                int preDistance = cur.getValue();
                for (int[] line : graph.get(node)) {
                    int toNode = line[0];
                    int newDistance = line[1];
                    if (distance[toNode] > preDistance + newDistance) {
                        int small = preDistance + newDistance;
                        distance[toNode] = small;
                        nextMap.put(toNode, small);
                    }
                }
            }
            curMap = nextMap;
        }
        int ans = 0;
        for (int i = 1; i < distance.length; i++) {
            ans = Math.max(ans, distance[i]);
        }
        return ans != Integer.MAX_VALUE ? ans : -1;
    }

    public int networkDelayTime(int[][] times, int n, int k) {
        int size = times.length;
        int[] distance = new int[n + 1];
        boolean[] visit = new boolean[n + 1];
        int sum = 0;
        visit[k] = true;
        for (int i = 0; i < size; i++) {
            if (times[i][0] == k) {
                int index = times[i][1];
                distance[index] = times[i][2];
            }
        }
        int nextIndex = getUnselectandMinDistanceIndex(distance, visit);
        while (nextIndex != -1) {
            visit[nextIndex] = true;
            for (int i = 0; i < size; i++) {
                if (times[i][0] == nextIndex + 1 && !visit[times[i][1] - 1]) {
                    int index = times[i][1] - 1;
                    distance[index] = distance[index] == 0 ? times[i][2] + distance[nextIndex] : Math.min(distance[index], times[i][2] + distance[nextIndex]);
                }
            }
            nextIndex = getUnselectandMinDistanceIndex(distance, visit);
        }
        for (int i = 0; i < n; i++) {
            sum = Math.max(sum, distance[i]);
        }
        for (int i = 0; i < visit.length; i++) {
            System.out.print(visit[i]);
            if (visit[i] == false) {
                return -1;
            }
        }
        return sum;
    }

    public static int getUnselectandMinDistanceIndex(int[] distance, boolean[] visit) {
        int index = -1;
        int minDistance = Integer.MAX_VALUE;
        for (int i = 0; i < distance.length; i++) {
            if (!visit[i] && distance[i] > 0 && distance[i] < minDistance) {
                index = i;
                minDistance = distance[i];
            }
        }
        return index;
    }
}
