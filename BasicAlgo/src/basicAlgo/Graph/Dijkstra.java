package basicAlgo.Graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

public class Dijkstra {
    public static HashMap<Node, Integer> dijkstra1(Node from) {
        HashMap<Node, Integer> distanceMap = new HashMap<>();
        distanceMap.put(from,0);

        HashSet<Node> set = new HashSet<>();
        Node minNode = getMinDistanceAndUnselectedNode(distanceMap,set);
        while(minNode != null){
            int distance = distanceMap.get(minNode);
            for(Edge edge : minNode.edges){
                Node toNode = edge.to;
                if( !distanceMap.containsKey(toNode)){
                    distanceMap.put(toNode,distance + edge.weight);
                }else {
                    distanceMap.put(toNode, Math.min(distance + edge.weight, distanceMap.get(toNode)));
                }
            }
            set.add(minNode);
            minNode = getMinDistanceAndUnselectedNode(distanceMap, set);
        }
        return distanceMap;
    }
    public static Node getMinDistanceAndUnselectedNode(HashMap<Node,Integer> distanceMap, HashSet<Node> set){
        Node ans = null;
        int minDistance = Integer.MAX_VALUE;
        for(Entry<Node, Integer> entry : distanceMap.entrySet()){
            Node node = entry.getKey();
            int distance = entry.getValue();
            if (!set.contains(node) && distance < minDistance) {
                minDistance = distance;
                ans = node;
            }
        }
        return ans;
    }

    public static class NodeHeap{
        public  Node[] nodes;
        public  HashMap<Node,Integer> indexMap;//反向索引
        public  HashMap<Node,Integer> distanceMap;
        public int size;

        public NodeHeap(int size){
            nodes = new Node[size];
            indexMap = new HashMap<>(size);
            distanceMap = new HashMap<>(size);
            this.size = size;
        }

        public void swap(int index1, int index2){
            indexMap.put(nodes[index1], index2);
            indexMap.put(nodes[index2], index1);
            Node temp = nodes[index1];
            nodes[index1] = nodes[index2];
            nodes[index1] = temp;

        }

        public void heapInsert(Node node,int index){
            while (distanceMap.get(nodes[index]) < distanceMap.get(nodes[(index-1)/2])) {
                swap((index-1)/2, index);
                index = (index-1)/2;
            }
        }

        public boolean isEntered(Node node){
            return indexMap.containsKey(node);
        }

        public boolean inHeap(Node node){
            return isEntered(node) && indexMap.get(node) != -1;
        }

        public void heapify( int index,int size){
            int left = 2 * index -1;
            while (left < size) {
                int small = left + 1 < size && distanceMap.get(nodes[left+1]) < distanceMap.get(nodes[left]) ? left + 1: left;
                int big = small == left? left + 1 : left;
                if (small == index) {
                    break;
                }
                swap(small, big);
                index = small;
                left = 2 * index -1;
            }
        }

        public void addOrUpdateOrIgnore(Node node,int distance){
            // add
            if(!isEntered(node)){
                nodes[size] = node;
                distanceMap.put(node, distance);
                indexMap.put(node, size);
                heapInsert(node, size ++);
            }
            if(inHeap(node)){
                distanceMap.put(node,Math.min(distanceMap.get(node), distance));
                heapInsert(node, indexMap.get(node));
            }
        }
        public Record pop(){
            Record record = new Record(nodes[0],distanceMap.get(nodes[0]));
            swap(0,size-1);
            indexMap.put(nodes[size-1],-1);
            distanceMap.remove(nodes[size-1]);
            nodes[size-1] = null;
            heapify(0,--size);
            return record;
        }
        public boolean isEmpty(){
            return size == 0;
        }
    }

    public static class Record{
        public Node node;
        public int distance;

        public Record(Node node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    public static HashMap<Node, Integer> dijkstra12(Node head, int size) {
        NodeHeap nodeHeap = new NodeHeap(size);
        nodeHeap.addOrUpdateOrIgnore(head,0);
        HashMap<Node, Integer> map = new HashMap<>();
        while (!nodeHeap.isEmpty()) {
            Record record = nodeHeap.pop();
            Node cur = record.node;
            int distance = record.distance;
            for( Edge edge : cur.edges){
                nodeHeap.addOrUpdateOrIgnore(edge.to, edge.weight + distance);
            }
            map.put(cur,distance);
        }
        return map;
    }


















}
