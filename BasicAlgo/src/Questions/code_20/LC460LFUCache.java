package Questions.code_20;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class LC460LFUCache {
    public class LFUCache {
        public HashMap<Integer, Node> map;
        public int Capacity;
        public int MinBucket;
        public List<List<Node>> list;

        public LFUCache(int capacity) {
            Capacity = capacity;
            map = new HashMap<>();
            list = new ArrayList<>();
            MinBucket = 1;
            list.add(new LinkedList<>());
            list.add(new LinkedList<>());



        }

        public int get(int key) {
            if (!map.containsKey(key)) {
                return -1;
            }
            Node cur = map.get(key);
            list.get(cur.times).remove(cur);
            cur.times ++;
            if(cur.times > list.size() - 1){
                createBucket();
            }
            insert(cur, cur.times);
            while(list.get(MinBucket).isEmpty()){
                MinBucket ++;
            }
            return cur.value;
        }

        public void put(int key, int value) {
            Node cur = new Node(key, value, 1, 0);
            if (map.size() < Capacity) {
                map.put(key, cur);
                insert(cur, 1);
            } else {
                removeFirst();
                map.put(key, cur);
                insert(cur, 1);
            }

            MinBucket = 1;
        }

        public void removeFirst() {
            Node cur = list.get(MinBucket).remove(0);
            map.remove(cur.key);
            while(list.get(MinBucket).isEmpty()){
                MinBucket++;
            }
        }

        public void insert(Node cur, int bucketNum) {
            List<Node> l = list.get(bucketNum);
            l.add(cur);
        }

        public void createBucket() {
            List<Node> newArr = new LinkedList<>();
            list.add(newArr);
        }

        public class Node {
            public int key;
            public int value;
            public int times;
            public int clock;

            public Node(int k, int v, int t, int c) {
                key = k;
                value = v;
                times = t;
                clock = c;
            }

        }
    }
}
