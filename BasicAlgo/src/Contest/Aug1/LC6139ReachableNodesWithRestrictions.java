package Contest.Aug1;

import basicAlgo.Graph.TopologicalSort;
import com.sun.tools.corba.se.idl.InterfaceGen;
import sun.plugin2.jvm.CircularByteBuffer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

public class LC6139ReachableNodesWithRestrictions {
    public static int reachableNodes(int n, int[][] edges, int[] restricted) {
        HashSet<Integer> set = new HashSet<>();
        HashSet<Integer> visited = new HashSet<>();

        for (int i : restricted) {
            set.add(i);
        }
        int ans = 1;
        HashMap<Integer, HashSet<Integer>> map = new HashMap<>();
        for (int[] cur : edges) {
            if (!map.containsKey(cur[0])) {
                map.put(cur[0], new HashSet<>());
            }
            if (!map.containsKey(cur[1])) {
                map.put(cur[1], new HashSet<>());
            }
            map.get(cur[1]).add(cur[0]);
            map.get(cur[0]).add(cur[1]);
        }
        Stack<HashSet<Integer>> stack = new Stack<>();
        stack.push(map.get(0));
        while (!stack.isEmpty()) {
            HashSet<Integer> cur = stack.pop();
            for(int i : cur){
                if(!set.contains(i)){
                    ans ++;
                    if(!visited.contains(i)){
                        visited.add(i);
                        stack.push(map.get(i));
                    }
                }
            }
        }
        return visited.size();
    }

    public static void main(String[] args) {
        int[][] arr = new int[][]{{0, 1}, {0, 2}, {0, 5}, {0, 4}, {3, 2}, {6, 5}};
        int[] r = {4, 2, 1};
        int a = reachableNodes(7, arr, r);
        System.out.println(a);
    }
}
