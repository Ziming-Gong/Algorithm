package LeetCodeDays;

import WeeklyPractice.April02.PerfectPairNumber;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class LC432AllOoneDataStructure {
    public class AllOne {

        //bucket数组
        ArrayList<Node> buckets;
        //Str - Buckets
        HashMap<String, Integer> timesMap;
        //Str - Node Map
        HashMap<String, Node> nodeMap;
        // Min
        int minIndex;
        // Max
        int maxIndex;


        public AllOne() {
            minIndex = 0;
            maxIndex = 0;
            nodeMap = new HashMap<>();
            timesMap = new HashMap<>();
            buckets = new ArrayList<>();
            buckets.add(null);
        }

        public void inc(String key) {
            Node cur;
            int index;
            if (!nodeMap.containsKey(key)) {
                cur = new Node(key);
                index = 1;
                nodeMap.put(key, cur);
            } else {
                index = timesMap.get(key) + 1;
                cur = breakOut(key);
            }

            add(cur, index);
            if (buckets.get(1) != null) {
                minIndex = 1;
            } else {
                minIndex++;
            }
            maxIndex = Math.max(maxIndex, index);

        }

        public void add(Node cur, int index) {
            timesMap.put(cur.context, index);
            if (index == buckets.size()) {
                buckets.add(cur);
                return;
            }
            Node head = buckets.get(index);
            if (head == null) {
                buckets.set(index, cur);
            } else {
                Node next = head.next;
                head.next = cur;
                cur.pre = head;
                cur.next = next;
                if (next != null) {
                    next.pre = cur;
                }
            }

        }

        public Node breakOut(String str) {
            Node cur = nodeMap.get(str);
            int index = timesMap.get(str);
            Node head = buckets.get(index);
            if (head == cur) {
                Node next = cur.next;
                if (next != null) {
                    next.pre = null;
                }

                cur.next = null;
                buckets.set(index, next);
            } else {
                Node pre = cur.pre;
                Node next = cur.next;
                cur.next = null;
                cur.pre = null;
                pre.next = next;
                if (next != null) {
                    next.pre = pre;
                }
            }
            return cur;
        }

        public void dec(String key) {
            int time = timesMap.get(key);
            Node cur = breakOut(key);
            if (time == 1) {
                nodeMap.remove(key);
                timesMap.remove(key);
                return;
            }
            add(cur, time - 1);
            if (buckets.get(maxIndex) == null) {
                maxIndex--;
            }
            if (time > 1) {
                minIndex = Math.min(minIndex, time - 1);
            } else {
                minIndex = -1;
                for (int i = 1; i < buckets.size(); i++) {
                    if (buckets.get(i) != null) {
                        minIndex = i;
                        break;
                    }
                }
                minIndex = minIndex == -1 ? 0 : minIndex;
            }


        }

        public String getMaxKey() {
            Node cur = buckets.get(maxIndex);

            return cur != null ? cur.context : "";

        }

        public String getMinKey() {
            Node cur = buckets.get(minIndex);
            return cur != null ? cur.context : "";

        }


        public class Node {
            public String context;
            public Node next;
            public Node pre;

            public Node(String str) {
                context = str;
                next = null;
                pre = null;
            }
        }


    }
}
