package com.zim.Graph;

import com.zim.mergesorted.ans;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

public class LintCode127DFS1 {
    public static class DirectedGraphNode {
        public int label;
        public ArrayList<DirectedGraphNode> neighbors;

        public DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<DirectedGraphNode>();
        }
    }
    //通过统计点次来看他们的拓扑序
    public static class Record{
        public DirectedGraphNode node;
        public long nodes;

        public Record(DirectedGraphNode node, long nodes) {
            this.node = node;
            this.nodes = nodes;
        }
    }

    public static class recordComparator implements Comparator<Record>{
        @Override
        public int compare(Record r1, Record r2){
            return r1.nodes == r2.nodes ? 0 : (r1.nodes > r2.nodes ? -1 : 1);
        }
    }


    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        HashMap<DirectedGraphNode, Record> order = new HashMap<>();
        for(DirectedGraphNode node : graph){
            f(node,order);
        }
        ArrayList<Record> arr = new ArrayList<>();
        for(Record r : order.values()){
            arr.add(r);
        }
        arr.sort(new recordComparator());
        ArrayList<DirectedGraphNode> ans = new ArrayList<>();
        for(Record  r : arr){
            ans.add(r.node);
        }
        return ans;

    }

    public static Record f(DirectedGraphNode node, HashMap<DirectedGraphNode, Record> order){
        if(order.containsKey(node)){
            return order.get(node);
        }
        long nodes = 0;
        for(DirectedGraphNode next : node.neighbors){
            nodes += f(next,order).nodes;
        }
        Record ans = new Record(node, nodes+1);
        order.put(node,ans);
        return ans;
    }

















}
