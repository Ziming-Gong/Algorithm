package WeeklyPractice.FebSe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;

//leetCode :https://leetcode.com/problems/cheapest-flights-within-k-stops/
public class CheapestFlightsWithinKStops {
    public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        ArrayList<ArrayList<int[]>> graph = new ArrayList<>(n);
        for(int i = 0; i < n; i ++){
            graph.add(new ArrayList<>());
        }
        for(int[] line : flights){
            graph.get(line[0]).add(new int[]{line[1],line[2]});
        }
        int[] cost = new int[n];
        Arrays.fill(cost,Integer.MAX_VALUE);
        cost[src] = 0;
        HashMap<Integer, Integer> curMap = new HashMap<>();
        curMap.put(src,0);
        for(int i = 0; i <= k; i ++){
            HashMap<Integer, Integer> nextMap = new HashMap<>();
            for(Entry<Integer, Integer> cur : curMap.entrySet()){
                int start = cur.getKey();
                int preCost = cur.getValue();
                for(int[] end : graph.get(start)){
                    int to = end[0];
                    int price = end[1];
                    cost[to] =  Math.min(cost[to], preCost + price);
                    nextMap.put(to, Math.min(nextMap.getOrDefault(to,Integer.MAX_VALUE), preCost + price));
                }
            }
            curMap= nextMap;
        }
        return cost[dst] == Integer.MAX_VALUE ? -1 : cost[dst];
    }
    public static int findCheapestPrice1(int n, int[][] flights, int src, int dst, int k) {
        int[] cost = new int[n];
        Arrays.fill(cost, Integer.MAX_VALUE);
        cost[src] = 0;
        for( int i = 0; i <= k; i ++){
            int[] copy = Arrays.copyOf(cost, n);
            for(int[] line : flights){
                int from = line[0];
                int to = line[1];
                int lineCost = line[2];
                if( cost[from] != Integer.MAX_VALUE){
                    copy[to] = Math.min(copy[to], lineCost + cost[from]);
                }
            }
            cost = copy;
        }
        return cost[dst] == Integer.MAX_VALUE ? -1 : cost[dst];
    }
}
