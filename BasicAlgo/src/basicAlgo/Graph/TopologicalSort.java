package basicAlgo.Graph;

import java.util.*;

public class TopologicalSort {
    public static List<Node> topologicalSort(Graph graph){
        HashMap<Node,Integer> inMap = new HashMap<>();
        Queue<Node> zeroQueue = new LinkedList<>();
        for(Node node : graph.nodes.values()){
            inMap.put(node, node.in);
            if (node.in == 0) {
                zeroQueue.add(node);
            }
        }

        List<Node> ans = new ArrayList<>();

        while (!zeroQueue.isEmpty()) {
            Node node = zeroQueue.poll();
            ans.add(node);
//            inMap.remove(node);
            for( Node cur : node.nexts){
                inMap.put(cur,inMap.get(cur) - 1);
                if (inMap.get(cur) == 0) {
                    zeroQueue.add(cur);
                }
            }
        }
        return ans;
    }
}
