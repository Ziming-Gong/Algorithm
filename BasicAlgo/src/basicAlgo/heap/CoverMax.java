package basicAlgo.heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class CoverMax {
    public static int coverMax1(int[][] arr){
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for( int i = 0; i < arr.length; i ++){
            min = Math.min(min, arr[i][0]);
            max = Math.max(max, arr[i][1]);
        }

        int cover = 0;
        for( double i = min + 0.5; i < max; i += 1){
            int cur = 0;
            for( int k = 0; k < arr.length; k ++){
                if( arr[k][0] < i && arr[k][1] > i){
                    cur ++;
                }
            }
            cover = Math.max(cur, cover);
        }
        return cover;
    }
    public static class Line {
        public int start;
        public int end;
        public Line(int s, int e){
            start = s;
            end = e;
        }
    }

    public static class StartComparator implements Comparator<Line>{

        @Override
        public int compare(Line o1, Line o2){
            return o1.start - o2.start;
        }
    }



    public static int coverMax2(int[][] m){
        Line[] lines = new Line[m.length];
        for(int i = 0; i < m.length; i ++){
            lines[i] = new Line(m[i][0], m[i][1]);
        }

        Arrays.sort(lines, new StartComparator());
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int max = 0;
        for(int i = 0; i < lines.length; i ++){
            while (!heap.isEmpty() && heap.peek() <= lines[i].start) {
                heap.poll();
            }
            heap.add(lines[i].end);
            max = Math.max(max, heap.size());
        }
        return max;
    }

    public static int coverMax3(int[][] arr){
        Arrays.sort(arr, (a, b) -> (a[0] - b[0]));
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int max = 0;
        for( int[] line : arr){
            if( !heap.isEmpty() && heap.peek() <= line[0]){
                heap.poll();
            }
            heap.add(line[1]);
            max = Math.max(max, heap.size());
        }
        return max;
    }

    // for test
    public static int[][] generateLines(int N, int L, int R) {
        int size = (int) (Math.random() * N) + 1;
        int[][] ans = new int[size][2];
        for (int i = 0; i < size; i++) {
            int a = L + (int) (Math.random() * (R - L + 1));
            int b = L + (int) (Math.random() * (R - L + 1));
            if (a == b) {
                b = a + 1;
            }
            ans[i][0] = Math.min(a, b);
            ans[i][1] = Math.max(a, b);
        }
        return ans;
    }



    public static void main(String[] args) {

        Line l1 = new Line(4, 9);
        Line l2 = new Line(1, 4);
        Line l3 = new Line(7, 15);
        Line l4 = new Line(2, 4);
        Line l5 = new Line(4, 6);
        Line l6 = new Line(3, 7);

        // 底层堆结构，heap
        PriorityQueue<Line> heap = new PriorityQueue<>(new StartComparator());
        heap.add(l1);
        heap.add(l2);
        heap.add(l3);
        heap.add(l4);
        heap.add(l5);
        heap.add(l6);

        while (!heap.isEmpty()) {
            Line cur = heap.poll();
            System.out.println(cur.start + "," + cur.end);
        }

        System.out.println("test begin");
        int N = 100;
        int L = 0;
        int R = 200;
        int testTimes = 200000;
        for (int i = 0; i < testTimes; i++) {
            int[][] lines = generateLines(N, L, R);
            int ans1 = coverMax1(lines);
            //System.out.println("1");
            int ans2 = coverMax2(lines);
           //System.out.println("2");
            int ans3 = coverMax3(lines);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("Oops!");
                //System.out.println(lines);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans3);
                break;

            }
        }
        System.out.println("test end");
    }






















}
