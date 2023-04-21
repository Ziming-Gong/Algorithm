import basicAlgo.DynamicProgramming.KillerMonster;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class an {
    public int findMinimumTime(int[][] tasks) {
        boolean[] used = new boolean[2001];
        Arrays.sort(tasks, (a, b) -> (a[1] - b[1]));

        int n = tasks.length;
        for (int[] task : tasks) {
            int count = 0;
            for (int i = task[1]; i >= task[0]; i--) {
                if (used[i]) count++;
                if (count == task[2]) break;
            }

            int remain = task[2] - count;
            for (int i = task[1]; i >= task[0]; i--) {
                if (remain == 0) break;
                if (!used[i]) {
                    used[i] = true;
                    remain--;
                }
            }
        }

        int res = 0;
        for (boolean b : used) {
            if (b) res++;
        }
        return res;
    }
}



