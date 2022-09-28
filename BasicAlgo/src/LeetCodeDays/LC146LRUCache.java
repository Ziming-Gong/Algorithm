package LeetCodeDays;

import Questions.code_5.Hash;
import sun.security.krb5.internal.crypto.KeyUsage;
import sun.util.resources.cldr.ta.CurrencyNames_ta;

import javax.swing.plaf.basic.BasicSplitPaneUI;
import java.util.*;

public class LC146LRUCache {

    public static class  LRUCache {

        Node head, tail;
        int size;
        HashMap<Integer, Node> map;


        public LRUCache(int capacity) {
            head = new Node();
            tail = new Node();
            map = new HashMap<>();
            size = capacity;
            head.next = tail;
            tail.pre = head;
        }

        public int get(int key) {
            if (!map.containsKey(key)) {
                return -1;
            }
            Node cur = map.get(key);
            remove(cur);
            add(cur,tail);
            return cur.value;
        }

        public  void put(int key, int value) {
            if (map.containsKey(key)) {
                Node cur = map.get(key);
                cur.value = value;
                remove(cur);
                add(cur, tail);
            } else if (map.size() < size) {
                Node cur = new Node(key, value);
                map.put(key, cur);
                add(cur, tail);
            } else {
                Node delete = head.next;
                remove(head.next);
                map.remove(delete.key);
                Node cur = new Node(key, value);
                map.put(key, cur);
                add(cur, tail);
            }

        }
    }

    public static class Node {
        public int key;
        public int value;
        public Node next, pre;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
            next = null;
            pre = null;
        }

        public Node() {

        }
    }

    public static void add(Node cur, Node tail) {
        Node pre = tail.pre;
        cur.pre = pre;
        pre.next = cur;
        tail.pre = cur;
        cur.next = tail;
    }

    public static void remove(Node node) {
        Node pre = node.pre;
        Node next = node.next;
        pre.next = next;
        next.pre = pre;
    }

    public static void main(String[] args) {
        LRUCache lc = new LRUCache(2);
        lc.put(1,1);
        lc.put(2,2);
        lc.get(1);
        lc.put(3,3);
        lc.get(2);
    }

}
