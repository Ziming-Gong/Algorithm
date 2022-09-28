package WeeklyPractice.Aug03;

import java.util.*;

public class LC716MaxStack {
    public class MaxStack {

        public GreaterHeap heap;
        public Node head;
        public Node tail;
        public int cnt;


        public MaxStack() {
            heap = new GreaterHeap();
            head = new Node();
            tail = head;
            cnt = 0;

        }

        public void push(int x) {
            Node cur = new Node();
            cur.val = x;
            cur.cnt = cnt++;
            cur.last = tail;
            tail.next = cur;
            tail = cur;
            heap.add(cur);

        }

        public int pop() {
            if (head == tail) {
                return -1;
            }
            Node remove = tail;
            tail = tail.last;
            tail.next = null;
            heap.remove(remove);
            return remove.val;

        }

        public int top() {
            return head == tail ? -1 : tail.val;
        }

        public int peekMax() {
            return heap.peek().val;

        }

        public int popMax() {
            Node remove = heap.pop();
            Node pre = remove.last;
            Node next = remove.next;
            pre.next = next;
            if (next != null) {
                next.last = pre;
            } else {
                tail = pre;
            }
            return remove.val;
        }


        public class GreaterHeap {
            public List<Node> heap;
            public HashMap<Node, Integer> indexMap;
            public NodeCompare comp;
            public int heapSize;

            public GreaterHeap() {
                heap = new ArrayList<>();
                indexMap = new HashMap<>();
                comp = new NodeCompare();
                heapSize = 0;
            }

            public void add(Node cur) {
                indexMap.put(cur, heapSize);
                heap.add(cur);
                heapInsert(heapSize++);
            }

            public Node peek() {
                return heap.get(0);
            }

            public Node pop() {
                Node ans = heap.get(0);
                swap(0, heapSize - 1);
                indexMap.remove(ans);
                heap.remove(heapSize--);
                heapify(0);
                return ans;
            }

            public void remove(Node obj) {
                Node replace = heap.get(heapSize - 1);
                int index = indexMap.get(obj);
                indexMap.remove(obj);
                heap.remove(--heapSize);
                if (obj != replace) {
                    indexMap.put(replace, index);
                    heap.set(index, replace);
                    heapify(index);
                    heapInsert(index);

                }

            }


            public void heapInsert(int index) {
                while (comp.compare(heap.get(index), heap.get((index - 1) / 2)) < 0) {
                    swap(index, (index - 1) / 2);
                    index = (index - 1) / 2;
                }
            }

            public void heapify(int index) {
                int left = index * 2 + 1;
                while (left < heapSize) {
                    int right = left + 1;
                    int best = right < heapSize && comp.compare(heap.get(left), heap.get(right)) > 0 ? right : left;
                    best = comp.compare(heap.get(best), heap.get(index)) < 0 ? best : index;
                    if (best == index) {
                        break;
                    }
                    swap(index, best);
                    index = best;
                    left = best * 2 + 1;

                }
            }

            public void swap(int i, int j) {
                Node o1 = heap.get(i);
                Node o2 = heap.get(j);
                heap.set(i, o2);
                heap.set(j, o1);
                indexMap.put(o1, j);
                indexMap.put(o2, i);

            }


        }

        public class NodeCompare implements Comparator<Node> {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.val != o2.val ? o2.val - o1.val : o2.cnt - o1.cnt;
            }
        }

        public class Node {
            public int cnt;
            public int val;
            public Node last;
            public Node next;


            public Node(int c, int v, Node l, Node n) {
                cnt = c;
                val = v;
                last = l;
                next = n;
            }

            public Node() {
            }
        }

    }
}
