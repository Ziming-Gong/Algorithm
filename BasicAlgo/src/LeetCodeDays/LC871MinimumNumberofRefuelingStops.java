package LeetCodeDays;

import com.sun.javafx.cursor.CursorFrame;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class LC871MinimumNumberofRefuelingStops {
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        if (stations == null || stations.length == 0) {
            return startFuel >= target ? 0 : -1;
        }

        return process(startFuel, 0, stations, target);
    }


    public int process(int curFuel, int index, int[][] stations, int rest) {
        if (index == stations.length) {
            return curFuel >= rest ? 0 : -1;
        }
        if (curFuel >= rest) {
            return 0;
        }
        if (curFuel < stations[index][0]) {
            return -1;
        }
        int ans = -1;
        int p1 = process(curFuel, index + 1, stations, rest);
        if (p1 != -1) {
            ans = p1;
        }
        int p2 = process(curFuel + stations[index][1], index + 1, stations, rest);
        if (p2 != -1) {
            ans = ans == -1 ? p2 + 1 : Math.min(p2 + 1, ans);
        }

        return ans;
    }

    public static int minRefuelStops1(int target, int startFuel, int[][] stations) {
        if (stations == null || stations.length == 0 || startFuel >= target) {
            return startFuel >= target ? 0 : -1;
        }
        int L = 1, R = stations.length, mid;
        int ans = -1;
        while (L <= R) {
            mid = (L + R) / 2;
            if (can(mid, startFuel, target, stations)) {
                ans = mid;
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return ans;

    }

    public static boolean can(int N, int fuel, long target, int[][] stations) {
        PriorityQueue<int[]> queue = new PriorityQueue<>(new intComparator());
        int index = 1;
        while (index < stations.length && fuel >= stations[index][0]) {
            queue.add(stations[index ++]);
        }
        while (!queue.isEmpty()) {
            fuel += queue.poll()[1];
            if (--N < 0) {
                return false;
            }
            while (index < stations.length && stations[index][0] <= fuel) {
                queue.add(stations[index++]);
            }
            if (fuel >= target) {
                return true;
            }

        }
        return false;

    }

    public static class intComparator implements Comparator<int[]> {
        @Override
        public int compare(int[] o1, int[] o2) {
            return o1[1] == o2[1] ? o1[0] - o2[0] : o2[1] - o1[1];
        }
    }


    public static int minRefuelStops2(int target, int startFuel, int[][] stations) {
        if (stations == null || stations.length == 0 || startFuel >= target) {
            return startFuel >= target ? 0 : -1;
        }
        PriorityQueue<int[]> queue = new PriorityQueue<>(new intComparator());
        int index = 0;
        while(index < stations.length && startFuel <= stations[index][0]){
            queue.add(stations[index ++]);
        }
        int ans = 0;
        while(!queue.isEmpty()){
            startFuel += queue.poll()[1];
            ans ++;
            while(index < stations.length && startFuel <= stations[index][0]){
                queue.add(stations[index ++]);
            }
            if(startFuel >= target){
                break;
            }
        }
        return startFuel >= target ? ans : -1;

    }


}
