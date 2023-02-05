package Contest;

import java.awt.color.CMMException;
import java.util.Comparator;
import java.util.PriorityQueue;

public class LC6306TimetoCrossaBridge {

    public static int findCrossingTime(int n, int k, int[][] time) {
        PriorityQueue<Worker> leftQueue = new PriorityQueue<>(new workerComparator());
        PriorityQueue<Worker> rightQueue = new PriorityQueue<>(new workerComparator());
        PriorityQueue<Worker> leftHouse = new PriorityQueue<>(new wareHouseComparator());
        PriorityQueue<Worker> rightHouse = new PriorityQueue<>(new wareHouseComparator());
        for (int i = 0; i < k; i++) {
            Worker w = new Worker(time[i][0], time[i][1], time[i][2], time[i][3], i);
            leftQueue.add(w);
        }

        int curTime = 0;
        int cnt = 0;
        while (cnt < n) {
            figure(leftQueue, leftHouse, curTime);
            figure(rightQueue, rightHouse, curTime);
            if (leftQueue.isEmpty() && rightQueue.isEmpty()) {

                curTime = Math.min(leftHouse.isEmpty() ? Integer.MAX_VALUE : leftHouse.peek().time, rightHouse.isEmpty() ? Integer.MAX_VALUE : rightHouse.peek().time);
                while (!leftHouse.isEmpty() && leftHouse.peek().time == curTime) {
                    leftQueue.add(leftHouse.poll());
                }
                while (!rightHouse.isEmpty() && rightHouse.peek().time == curTime) {
                    rightQueue.add(rightHouse.poll());
                }
            }
            if (rightQueue.isEmpty()) {
                Worker cur = leftQueue.poll();
                curTime += cur.leftToRight;
                cur.time = curTime + cur.pickOld;
                rightHouse.add(cur);
                cnt++;
            } else {
                Worker cur = rightQueue.poll();
                curTime += cur.rightToLeft;
                cur.time = curTime + cur.putNew;
                leftHouse.add(cur);
//                cnt++;
            }
        }
        int res = curTime;
        while (!rightHouse.isEmpty() || !rightQueue.isEmpty()) {
            figure(rightQueue, rightHouse, curTime);
            if (rightQueue.isEmpty()) {
                Worker cur = rightHouse.poll();
                curTime = cur.time;
                rightQueue.add(cur);
                while (!rightHouse.isEmpty() && rightHouse.peek().time == curTime) {
                    rightQueue.add(rightHouse.poll());
                }
            } else {
                Worker cur = rightQueue.poll();
                curTime += cur.rightToLeft;
                res = Math.max(res, curTime);
            }
        }

        return res;
    }

    public static void figure(PriorityQueue<Worker> queue, PriorityQueue<Worker> house, int curTime) {
        while (!house.isEmpty() && house.peek().time <= curTime) {
            queue.add(house.poll());
        }
    }


    public static class Worker {
        int leftToRight;
        int pickOld;
        int rightToLeft;
        int putNew;
        int num;
        int time;

        public Worker(int l, int p, int r, int putNewi, int n) {
            leftToRight = l;
            pickOld = p;
            rightToLeft = r;
            putNew = putNewi;
            num = n;
            time = 0;
        }
    }

    public static class workerComparator implements Comparator<Worker> {
        @Override
        public int compare(Worker w1, Worker w2) {
            return w1.leftToRight + w1.rightToLeft == w2.rightToLeft + w2.leftToRight ? w2.num - w1.num : w2.rightToLeft + w2.leftToRight - w1.leftToRight - w1.rightToLeft;
        }
    }

    public static class wareHouseComparator implements Comparator<Worker> {
        @Override
        public int compare(Worker w1, Worker w2) {
            return w1.time == w2.time ? w1.num - w2.num : w1.time - w2.time;
        }
    }

    public static void main(String[] args) {
        int[][] time = {{1, 9, 1, 8}, {10, 10, 10, 10}};
        int n = 3;
        int k = 2;
        System.out.println(findCrossingTime(n, k, time));
    }
}
