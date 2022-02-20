package basicAlgo.Graph;


import java.util.*;

public class Kruskal {
    public static class UnionFind{
        public HashMap<Node,Node> fatherMap;
        public HashMap<Node,Integer> sizeMap;

        public UnionFind() {
            this.fatherMap = new HashMap<>();
            this.sizeMap = new HashMap<>();
        }

        public void makeSets(Collection<Node> nodes){
            if( nodes == null){
                return;
            }
            fatherMap.clear();
            sizeMap.clear();
            for( Node node : nodes){
                fatherMap.put(node, node);
                sizeMap.put(node,1);
            }
        }

        public  Node findFather(Node node){
            if(node == null){
                return null;
            }
            Stack<Node> stack = new Stack<>();
            while( node != fatherMap.get(node)){
                stack.add(node);
                node = fatherMap.get(node);
            }
            while(!stack.isEmpty()){
                Node cur = stack.pop();
                fatherMap.put(cur, node);
            }
            return node;
        }
        public  void Union(Node a, Node b){
            Node fatherA = findFather(a);
            Node fatherB = findFather(b);
            int sizeA = sizeMap.get(fatherA);
            int sizeB = sizeMap.get(fatherB);
            if(fatherA != fatherB){
                Node big = sizeA >= sizeB ? fatherA : fatherB;
                Node small = big == fatherA ? fatherB : fatherB;
                fatherMap.put(small, big);
                sizeMap.put(big, sizeA + sizeB);
                sizeMap.remove(small);
            }
        }
        public boolean isSameSet(Node a, Node b){
            return findFather(a) == findFather(b);
        }
    }

    public static class edgeComparator implements Comparator<Edge>{
        @Override
        public int compare(Edge o1, Edge o2){
            return o1.weight - o2.weight;
        }
    }

    public static Set<Edge> kruskalMST(Graph graph){
        UnionFind uf = new UnionFind();
        uf.makeSets(graph.nodes.values());

        PriorityQueue<Edge> edges = new PriorityQueue<>(new edgeComparator());
        for( Edge edge : graph.edges){
            edges.add(edge);
        }
        Set<Edge> ans = new HashSet<>();
        while( !edges.isEmpty()){
            Edge cur = edges.poll();
            if(!uf.isSameSet(cur.from, cur.to)){
                uf.Union(cur.from,cur.to);
                ans.add(cur);
            }
        }
        return ans;
    }

















}
