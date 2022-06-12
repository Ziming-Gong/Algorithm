package Questions.code_2;

import com.sun.org.apache.bcel.internal.generic.IUSHR;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeMap;

public class ChooseWork {
    public static class Job {
        public int hard;
        public int money;

        public Job(int hard, int money) {
            this.hard = hard;
            this.money = money;
        }
    }

    public static class JobComparator implements Comparator<Job> {
        @Override
        public int compare(Job j1, Job j2) {
            return j1.hard == j2.hard ? j2.money - j1.money : j1.hard - j2.hard;
        }
    }

    public static int[] bestMoney(int[] hard, int[] money, int[] ability) {
        int N = hard.length;
        int M = ability.length;
        Job[] jobs = new Job[N];
        for (int i = 1; i < N; i++) {
            jobs[i] = new Job(hard[i], money[i]);
        }
        Arrays.sort(jobs, new JobComparator());
        TreeMap<Integer, Integer> map = new TreeMap<>();
        Job pre = null;
        for (int i = 1; i < N; i++) {
            if (jobs[i].hard != jobs[i - 1].hard && jobs[i].money >= jobs[i-1].money) {
                pre = jobs[i];
                map.put(jobs[i].hard, jobs[i].money);
            }
        }
        int[] ans = new int[M];
        for(int i = 0; i < M; i ++){
            Integer key = map.floorKey(ability[i]);
            ans[i] = key != null ? map.get(key) : 0;
        }
        return ans;




    }
}
