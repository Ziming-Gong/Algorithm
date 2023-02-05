package LeetCodeDays;

import java.util.LinkedList;

public class LC818RaceCar {
    public int racecar(int target) {
        int[] h = help(target);
        int mostSpeed = h[0];
        int mostDistance = h[1];
        boolean[][] positiveMap = new boolean[mostSpeed + 1][mostDistance + 1];
        boolean[][] negativeMap = new boolean[mostSpeed + 1][mostDistance + 1];
        LinkedList<int[]> queue = new LinkedList<>();
        // visited[0] = true;
        queue.add(new int[]{0, 1}); // {position ,speed, action}
        int res = 0;
        while (!queue.isEmpty()) {
            LinkedList<int[]> nexts = new LinkedList<>();
            while (!queue.isEmpty()) {
                int[] cur = queue.poll();
                int position = cur[0];
                int speed = cur[1];
                if (position == target) {
                    return res;
                }
                if (speed > 0) {
                    if (!positiveMap[speed][position]) {
                        positiveMap[speed][position] = true;
                        if (position + (1 << speed) <= mostDistance && (speed + 1) <= mostSpeed) {
                            nexts.add(new int[]{position + (1 << speed), speed + 1});
                        }
                        nexts.add(new int[]{position, -1});
                    }
                } else {
                    speed = -speed;
                    if (!negativeMap[speed][position]) {
                        negativeMap[speed][position] = true;
                        if (position - (1 << speed) > 0) {
                            nexts.add(new int[]{position - (1 << speed), -speed - 1});
                        }
                        nexts.add(new int[]{position, 1});
                    }
                }
            }
            res++;
            queue = nexts;
        }
        return -1;


    }

    // calculate the far point
    public int[] help(int target) {
        int maxp = 0;
        int maxs = 1;
        while (maxp <= target) {
            maxp += 1 << (maxs - 1);
            maxs++;
        }
        return new int[]{maxs, maxp};
    }

}
