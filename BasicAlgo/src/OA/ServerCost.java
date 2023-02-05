//package OA;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.LinkedList;
//import java.util.List;
//
//public class ServerCost {
//    public static int minCost(int k, int n, List<Integer> from, List<Integer> to, List<Integer> weight) {
//        // 来到一个点
//        List<List<int[]>> graph = new ArrayList<>();
//        boolean[] visited = new boolean[n];
//        for (int i = 0; i < n; i++) {
//            graph.add(new ArrayList<>());
//        }
//        HashMap<Integer, Integer> edgesCntMap = new HashMap<>();
//        for (int i = 0; i < from.size(); i++) {
//            graph.get(from.get(i)).add(new int[]{to.get(i), i});
//            graph.get(to.get(i)).add(new int[]{from.get(i), i});
//            edgesCntMap.put(from.get(i), edgesCntMap.getOrDefault(from.get(i), 0));
//            edgesCntMap.put(to.get(i), edgesCntMap.getOrDefault(to.get(i), 0));
//        }
//        int sum = 0;
//        for (int i : weight) {
//            sum += i;
//        }
//        return sum;
//    }
//
//    // 返回 这个点只有K条边时候的最小值
//    public static int gong(List<List<int[]>> graph, int[] visited, int curIndex, int k, HashMap<Integer, Integer> edgesCntMap) {
//
//    }
//}
//
//
//
//
//
//
//
//
//
//
//
//
