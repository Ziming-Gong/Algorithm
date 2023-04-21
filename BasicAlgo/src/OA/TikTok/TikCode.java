package OA.TikTok;

import com.sun.tools.doclint.Env;
import sun.util.resources.in.CurrencyNames_in_ID;

import java.util.HashMap;
import java.util.Map.*;
import java.util.TreeMap;

public class TikCode {
    public static void main(String[] args) {
//        int[] c = {7, 6, 8, 7, 5, 4, 5};
//        int[][] e = {{1, 7, 0}, {2, 1, 4}, {1, 4, 0}, {2, 6, 8}, {2, 2, 9}, {2, 0, 1}, {2, 5, 1}, {2, 5, 3}};
//        int[] a1 = right(c, e);
//        int[] a2 = solution1(c, e);
//        printArr(a1);
//        System.out.println();
//        printArr(a2);

//        int[] c = {0, 9, 2};
//        int[][] e = {{2, 2, 6}, {1, 6, 0}, {2, 0, 8}, {1, 2, 0}, {1, 7, 0}};
//        int[] a1 = right(c, e);
//        int[] a2 = solution1(c, e);
//        printArr(a1);
//        System.out.println();
//        printArr(a2);


        //
        int maxVal = 10000;
        int testTime = 1000;
        int maxUserLen = 10000;
        int maxEventLen = 10000;
        for (int i = 0; i < testTime; i++) {
            int userLen = (int) (Math.random() * maxUserLen) + 1;
            int[] counts = generateCounts(userLen, maxVal);
            int[][] events = generateEvents(maxEventLen, userLen, maxVal);
            int[] ans1 = right(counts, events);
            int[] ans2 = solution1(counts, events);
            if (!compare(ans1, ans2)) {
                print(counts, events);
                System.out.println("OOPS");
                break;
            }
        }
        System.out.println("test end");

        System.out.println("功能测试: ");
        maxVal = 100000000;
        maxUserLen = 100000;
        maxEventLen = 100000;

        int[] counts = generateCounts(maxUserLen, maxVal);
        int[][] events = generateEvents(maxEventLen, maxUserLen, maxVal);
//        print(counts, events);
        System.out.println(counts.length);
        System.out.println(events.length);
        long start = System.currentTimeMillis();
        solution1(counts, events);
        long end = System.currentTimeMillis();
        long res = end - start;
        System.out.printf("优化结果总用时：" + res);
        System.out.println();
        start = System.currentTimeMillis();
        right(counts, events);
        end = System.currentTimeMillis();
        res = end - start;
        System.out.printf("未优化结果总用时：" + res);


    }


    // for test
    public static int[] right(int[] counts, int[][] events) {
        int n = counts.length;
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = counts[i];
        }
        for (int[] event : events) {
            if (event[0] == 1) {
                for (int i = 0; i < n; i++) {
                    res[i] = Math.max(res[i], event[1]);
                }
            } else {
                res[event[1]] = event[2];
            }
        }
        return res;
    }


    public static int[] solution1(int[] counts, int[][] events) {
        TreeMap<Integer, Node> treeMap = new TreeMap<>();
        HashMap<Integer, Node> userMap = new HashMap<>();
        HashMap<Integer, Node> tailMap = new HashMap<>();
        HashMap<Node, Integer> tailValMap = new HashMap<>();

        init(treeMap, counts, userMap, tailMap, tailValMap);

        for (int[] event : events) {
            if (event[0] == 1) {
                int lessNum = event[1];
                Node head = treeMap.get(lessNum);
                if (head == null) {
                    head = new Node(lessNum);
                }

                lessNum--;
                Integer floorKey = treeMap.floorKey(lessNum);
                Node tail = tailMap.get(floorKey);
                Node finalTail = tail;
//                tailMap.remove(floorKey);
                boolean shouldChange = false;
                if (tail != null && !tailMap.containsKey(lessNum + 1)) {
                    tailMap.put(lessNum + 1, tail);
                    shouldChange = true;
                }
                while (floorKey != null) {
                    Node front = treeMap.get(floorKey).next;
                    tail = tailMap.get(floorKey);
                    treeMap.remove(floorKey);
                    tailMap.remove(floorKey);
                    tailValMap.remove(tail);
                    Node next = head.next;
                    head.next = front;
                    front.front = head;
                    tail.next = next;
                    if (next != null) {
                        next.front = tail;
                    }
                    floorKey = treeMap.floorKey(lessNum);
                }
                if (shouldChange) {
                    tailValMap.put(finalTail, lessNum + 1);
                }
                if (head.next != null) {
                    treeMap.put(head.value, head);
                }
            } else {
                Node cur = userMap.get(event[1]);
                Node head = treeMap.get(event[2]);
                // remove cur from the chain
                if (head != null && head.next == cur) {
                    continue;
                }
                Node front = cur.front;
                Node next = cur.next;
                front.next = next;


                if (tailValMap.containsKey(cur)) {
                    int curVal = tailValMap.get(cur);
                    tailValMap.remove(cur);
                    Node newTail = cur.front;
                    if (newTail.index == -1) {
                        tailMap.remove(curVal);
                        treeMap.remove(curVal);
                    } else {
                        tailMap.put(curVal, newTail);
                        tailValMap.put(newTail, curVal);
                    }
                }

                if (next != null) {
                    next.front = front;
                }

                // add cur to the head
                if (head == null) {
                    head = new Node(event[2]);
                    tailMap.put(event[2], cur);
                    treeMap.put(event[2], head);
                    tailValMap.put(cur, event[2]);
                }
                next = head.next;
                head.next = cur;
                cur.front = head;
                cur.next = next;
                if (next != null) {
                    next.front = cur;
                }
            }
        }
        int[] res = new int[counts.length];
        for (Entry<Integer, Node> map : treeMap.entrySet()) {
            Node head = map.getValue();
            int val = head.value;
            head = head.next;
            while (head != null) {
                res[head.index] = val;
                head = head.next;
            }
        }
        return res;
    }

    public static void init(TreeMap<Integer, Node> treeMap, int[] counts, HashMap<Integer, Node> userMap, HashMap<Integer, Node> tailMap, HashMap<Node, Integer> tailValNode) {
        for (int i = 0; i < counts.length; i++) {
            Node cur = new Node(i, counts[i]);
            userMap.put(i, cur);
            Node front = treeMap.get(counts[i]);
            if (front == null) {
                front = new Node(counts[i]);
                front.next = cur;
                cur.front = front;
                treeMap.put(counts[i], front);
                tailMap.put(counts[i], cur);
                tailValNode.put(cur, counts[i]);
            } else {
                Node next = front.next;
                front.next = cur;
                cur.front = front;
                cur.next = next;
                next.front = cur;
            }
        }
    }


    public static class Node {
        int value;
        int index;
        Node next;
        Node front;

        public Node(int value) {
            this.value = value;
            index = -1;
            next = null;
            front = null;
        }

        public Node(int index, int value) {
            this.value = value;
            this.index = index;
            next = null;
            front = null;
        }


    }

    // for test
    public static boolean compare(int[] arr1, int[] arr2) {
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static int[][] generateEvents(int maxLen, int userLen, int maxVal) {
        int n = (int) (Math.random() * maxLen) + 1;
        int[][] res = new int[n][3];
        for (int i = 0; i < n; i++) {
            res[i][0] = (Math.random() < 0.5) ? 1 : 2;
            if (res[i][0] == 1) {
                res[i][1] = (int) (Math.random() * maxVal);
            } else {
                res[i][1] = (int) (Math.random() * userLen);
                res[i][2] = (int) (Math.random() * maxVal);
            }
        }
        return res;
    }

    public static int[] generateCounts(int len, int maxVal) {
        int[] res = new int[len];
        for (int i = 0; i < len; i++) {
            res[i] = (int) (Math.random() * maxVal);
        }
        return res;
    }

    public static void print(int[] counts, int[][] events) {
        System.out.println("counts:");
        for (int i = 0; i < counts.length; i++) {
            System.out.printf(counts[i] + ", ");
        }
        System.out.println();
        System.out.println("events");
        System.out.printf("{ ");
        for (int[] event : events) {
            System.out.printf("{ " + event[0] + ", " + event[1] + ", " + event[2] + " },");
        }
        System.out.println("}");
    }

    public static void printArr(int[] arr) {
        for (int i : arr) {
            System.out.printf(i + ", ");
        }
    }
}
