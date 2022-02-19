package com.zim.Graph;

import java.util.*;

public class LintCode127BFS {
    public static class DirectedGraphNode {
        public int label;
        public ArrayList<DirectedGraphNode> neighbors;

        public DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<DirectedGraphNode>();
        }
    }

    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        HashMap<DirectedGraphNode, Integer> nodeNum = new HashMap<>();
        for(DirectedGraphNode node : graph){
            nodeNum.put(node,0);
        }
        for( DirectedGraphNode node : graph){
            for(DirectedGraphNode next : node.neighbors){
                nodeNum.put(next, nodeNum.get(next)+1);
            }
        }
        Queue<DirectedGraphNode> zeroQueue = new LinkedList<>();
        for( DirectedGraphNode node : nodeNum.keySet()){
            if(nodeNum.get(node) == 0){
                zeroQueue.add(node);
            }
        }
        ArrayList<DirectedGraphNode> ans = new ArrayList<>();
//        HashSet<DirectedGraphNode> set = new HashSet<>();

        while (!zeroQueue.isEmpty()) {
            DirectedGraphNode node = zeroQueue.poll();
            ans.add(node);
//            set.add(node);
            for( DirectedGraphNode next: node.neighbors){
                //if(!set.contains(next)){
                    nodeNum.put(next, nodeNum.get(next) -1 );
                    if (nodeNum.get(next) == 0) {
                        zeroQueue.offer(next);
                    }
                //}
            }

        }
        return ans;
    }
















}
