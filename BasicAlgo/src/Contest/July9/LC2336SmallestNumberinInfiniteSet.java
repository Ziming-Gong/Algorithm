package Contest.July9;

import java.util.PriorityQueue;

public class LC2336SmallestNumberinInfiniteSet {
    class SmallestInfiniteSet {
        private int point;
        public PriorityQueue<Integer> queue;

        public SmallestInfiniteSet() {
            point = 1;
            queue = new PriorityQueue<>();
        }

        public int popSmallest() {
            if (queue.isEmpty()) {
                return point++;
            } else {
                return queue.poll();
            }
        }

        public void addBack(int num) {
            if(num < point && !queue.contains(num)){
                queue.add(num);
            }
//            if (point <= num) {
//                return;
//            } else if (point == num - 1) {
//                point--;
//            } else {
//                queue.add(num);
//            }
//            while(!queue.isEmpty() && queue.peek() == point - 1){
//                point --;
//                queue.poll();
//            }
        }
    }

    public static void main(String[] args) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.add(4);
        queue.add(2);
        queue.add(3);
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
    }
}
