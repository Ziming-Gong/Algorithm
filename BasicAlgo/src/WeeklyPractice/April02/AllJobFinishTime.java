package WeeklyPractice.April02;

import basicAlgo.mergesorted.ans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class AllJobFinishTime {
    // {come, from}
    public static int[] finishTime1(int n, int[] time, int[][] matrix) {
        HashMap<Integer, ArrayList<Integer>> nexts = new HashMap<>();
        int[] in = new int[n];
        for (int[] arr : matrix) {
            if (!nexts.containsKey(arr[1])) {
                nexts.put(arr[1], new ArrayList<>());
            }
            ArrayList<Integer> list = nexts.get(arr[1]);
            list.add(arr[0]);
            nexts.put(arr[1], list);
            in[arr[0]]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (in[i] == 0) {
                queue.add(i);
            }
        }
        int[] ans = new int[n];
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            ans[cur] += time[cur];
            //if(nexts.containsKey(cur)){
                for (int next : nexts.getOrDefault(cur,new ArrayList<>())) {
                    ans[next] = Math.max(ans[cur], ans[next]);
                    if(--in[next] == 0){
                        queue.add(next);
                    }
                }
            //}

        }
        return ans;
    }

    public static int[] finishTime2(int n, int[] time, int[][] matrix) {
        ArrayList<ArrayList<Integer>> nexts = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            nexts.add(new ArrayList<>());
        }
        int[] in = new int[n];
        for (int[] line : matrix) {
            nexts.get(line[1]).add(line[0]);
            in[line[0]]++;
//            print(in);
//            System.out.println();
        }
        Queue<Integer> zeroInQueue = new LinkedList<>();
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            if (in[i] == 0) {
                zeroInQueue.add(i);
            }
        }
        while (!zeroInQueue.isEmpty()) {
            int cur = zeroInQueue.poll();
            ans[cur] += time[cur];
            for (int next : nexts.get(cur)) {
                ans[next] = Math.max(ans[next], ans[cur]);
                if (--in[next] == 0) {
                    zeroInQueue.add(next);
                }
            }
        }
        return ans;
    }

    public static void print(int[] arr){
        for(int i : arr){
            System.out.printf(i+", ");
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{4,3},{3,2},{2,1},{1,0}};
        int[] time = {3,5,4,5,2};
        int n = 5;
        print(finishTime1(n,time,matrix));
        System.out.println();
        print(finishTime2(n,time,matrix));
    }
}
