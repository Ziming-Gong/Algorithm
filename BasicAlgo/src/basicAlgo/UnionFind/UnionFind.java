package basicAlgo.UnionFind;

import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class UnionFind {
    public static class Node<V>{
        public V val;
        public Node(V val){
            this.val = val;
        }
    }

    public static class UnionFind1<V>{
        public HashMap<V, Node> nodes;
        public HashMap<Node, Node> parents;
        public HashMap<Node,Integer> sizeMap;

        public UnionFind1(List<V> values){
            nodes = new HashMap<>();
            parents = new HashMap<>();
            sizeMap = new HashMap<>();

            for( V cur : values){
                Node<V> node = new Node(cur);
                nodes.put(cur,node);
                parents.put(node,node);
                sizeMap.put(node,1);
            }
        }
        public Node<V> findFather(Node<V> cur){
            Stack<Node> stack = new Stack<>();
            while(cur != parents.get(cur)){
                stack.push(cur);
                cur = parents.get(cur);
            }
            while (!stack.isEmpty()) {
                Node node = stack.pop();
                parents.put(node, cur);
            }
            return cur;
        }

        public boolean isSameSet(V a, V b){
            return findFather(nodes.get(a)) == findFather(nodes.get(b));
        }

        public void union(V a, V b){
            Node aHead = findFather(nodes.get(a));
            Node bHead = findFather(nodes.get(b));
            if (aHead != bHead) {
                int aSize = sizeMap.get(aHead);
                int bSize = sizeMap.get(bHead);
                Node<V> big = aSize > bSize ? aHead : bHead;
                Node<V> small = big == aHead ? bHead : aHead;
                parents.put(small,big);
                sizeMap.remove(small);
                sizeMap.put(big, aSize + bSize);
            }
        }

        public int sets(){
            return sizeMap.size();

        }

    }
}
