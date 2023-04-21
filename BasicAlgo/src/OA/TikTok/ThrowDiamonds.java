package OA.TikTok;

import Questions.code_03.DistanceKNodes;
import com.sun.media.sound.SoftTuning;

import java.time.DayOfWeek;
import java.util.*;

public class ThrowDiamonds {
    public static void main(String[] args) {
        int[] a = {5, 53, 35, 15, 21, 53};
        System.out.println(solution(a));


        int maxVal = 100;
        int len = 10;
        int testTime = 1000;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateArr(len, maxVal);
            int ans1 = right(arr);
            int ans2 = solution(arr);
            if (ans1 != ans2) {
                print(arr);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("fucking oops");
                System.out.println(i);
                break;
            }
        }
        System.out.println("test end");


    }

    public static int right(int[] diamonds) {
        LinkedList<Integer> list = new LinkedList<>();
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i : diamonds) {
            list.addLast(i);
            queue.add(i);
        }
        int ans = 0;
        while (list.size() != 0) {
            while (!list.get(0).equals(queue.peek())) {
                ans++;
                int cur = list.remove(0);
                list.addLast(cur);
            }
            list.remove(0);
            queue.poll();
            ans++;
        }
        return ans;
    }

    public static int solution(int[] diamonds) {
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> (a[0] - b[0]));
        int n = diamonds.length;
        for (int i = 0; i < diamonds.length; i++) {
            queue.add(new int[]{diamonds[i], i, i});
        }
        int res = 0;
        int right = n - 1;
        int index = 0;
        IndexTree it = new IndexTree(n + 1);
        PriorityQueue<int[]> nexts = new PriorityQueue<>((a, b) -> (a[2] - b[2]));
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (index > cur[1]) {
                cur[2] = cur[1] + n;
            } else {
                cur[2] = cur[1];
            }
            nexts.add(cur);

            while (!queue.isEmpty() && queue.peek()[0] == nexts.peek()[0]) {
                cur = queue.poll();
                if (index > cur[1]) {
                    cur[2] = cur[1] + n;
                } else {
                    cur[2] = cur[1];
                }

                nexts.add(cur);
            }
            while (!nexts.isEmpty()) {
                cur = nexts.poll();
                int targetIndex = it.sum(cur[1] + 1) - 1;
                if (targetIndex >= index) {
                    res += targetIndex - index;
                    index = right <= targetIndex + 1 ? 0 : targetIndex + 1;
                    right--;
                } else {
                    int back = right - index;
                    int front = it.sum(cur[1] + 1);
                    res += front + back;
                    index = front - 1;
                    right--;
                }
                it.add(cur[1] + 1, -1);
                res++;
            }
        }
        return res;

    }

    public static class IndexTree {
        int[] arr;
        int size;

        public IndexTree(int n) {
            this.size = n + 1;
            arr = new int[n + 1];
            for (int i = 1; i < size; i++) {
                add(i, 1);
            }

        }

        public void add(int index, int num) {
            while (index < size) {
                arr[index] += num;
                index += (index & -index);
            }
        }

        public int sum(int index) {
            int res = 0;
            while (index > 0) {
                res += arr[index];
                index -= (index & -index);
            }
            return res;
        }
    }


    // for test
    public static int[] generateArr(int size, int maxVal) {
        int n = (int) (Math.random() * size) + 1;
        HashSet<Integer> set = new HashSet<>();
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = getVal(set, maxVal);
        }
        return res;

    }

    public static int getVal(HashSet<Integer> set, int maxVal) {
        int num = (int) (Math.random() * maxVal) + 1;
        return num;
    }

    public static void print(int[] arr) {
        for (int i : arr) {
            System.out.printf(i + ", ");
        }
        System.out.println();
    }
}
