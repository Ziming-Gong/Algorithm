package Contest.Sep3;

import basicAlgo.Print.RotateMatrix;
import sun.awt.image.ImageWatched;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class LC6170MeetingRoomsIII {
    public static int mostBooked(int n, int[][] meetings) {

        Arrays.sort(meetings, (a, b) -> (a[0] - b[0]));

        PriorityQueue<Room> valid = new PriorityQueue<>((a, b) -> (a.num - b.num));
        PriorityQueue<Room> using = new PriorityQueue<>((a, b) -> (a.endTime == b.endTime ? a.num - b.num : a.endTime - b.endTime));


        Room[] arr = new Room[n];

        for (int i = 0; i < n; i++) {
            Room cur = new Room(i, 0, 0);
            valid.add(cur);
            arr[i] = cur;
        }

        for (int[] cur : meetings) {
            Room v;
            while (cur[0] >= using.peek().endTime) {
                valid.add(using.poll());
            }
            if (!valid.isEmpty()) {
                v = valid.poll();
                v.endTime = cur[1];
            } else {
                v = using.poll();
                v.endTime = v.endTime <= cur[0] ? cur[0] : v.endTime + cur[1] - cur[0];
            }
            v.used++;
            using.add(v);
        }


        int ans = 0;
        int maxUsed = 0;
        for (int i = 0; i < n; i++) {
            if (maxUsed < arr[i].used) {
                ans = arr[i].num;
                maxUsed = arr[i].used;
            }
        }
        return ans;

    }


    public static class Room {
        public int num;
        public int endTime;
        public int used;

        public Room(int n, int e, int u) {
            num = n;
            endTime = e;
            used = u;
        }
    }

    public static void main(String[] args) {
        // [[18,19],[3,12],[17,19],[2,13],[7,10]]
        int[][] me = {{18, 19}, {3, 12}, {17, 19}, {2, 13}, {7, 10}};
        int n = 4;
        mostBooked(n, me);
    }
}
