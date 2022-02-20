package basicAlgo.Graph;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class LintCode127DFS2 {
    public static class DirectedGraphNode {
        public int label;
        public ArrayList<DirectedGraphNode> neighbors;

        public DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<DirectedGraphNode>();
        }
    }
    public static class Record{
        public DirectedGraphNode node;
        public long deep;

        public Record(DirectedGraphNode node, long deep) {
            this.node = node;
            this.deep = deep;
        }
    }
    public static class recordComparator implements Comparator<Record>{
        @Override
        public int compare(Record o1, Record o2){
            return o1.deep == o2.deep? 0 : (o1.deep > o2.deep ? -1 : 1);
        }
    }
    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        HashMap<DirectedGraphNode, Record> recordMap = new HashMap<>();
        for( DirectedGraphNode node : graph){
            f(node,recordMap);
        }
        ArrayList<Record> recordArr = new ArrayList<>();
        for(Record r : recordMap.values()){
            recordArr.add(r);
        }
        recordArr.sort(new recordComparator());
        ArrayList<DirectedGraphNode> ans = new ArrayList<>();
        for( Record record : recordArr){
            ans.add(record.node);
        }
        return ans;
    }
    public static Record f(DirectedGraphNode node, HashMap<DirectedGraphNode,Record> recordMap){
        if(recordMap.containsKey(node)){
            return recordMap.get(node);
        }
        long deep = 0;
        for( DirectedGraphNode next : node.neighbors){
            deep = Math.max(deep, f(next,recordMap).deep);
        }
        Record ans = new Record(node, deep+1);
        recordMap.put(node, ans);
        return ans;
    }


}
