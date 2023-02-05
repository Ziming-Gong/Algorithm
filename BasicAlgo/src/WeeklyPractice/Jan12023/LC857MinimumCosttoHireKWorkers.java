package WeeklyPractice.Jan12023;

import java.util.Comparator;
import java.util.PriorityQueue;

public class LC857MinimumCosttoHireKWorkers {
    public double mincostToHireWorkers(int[] quality, int[] wage, int k) {
        PriorityQueue<Worker> queue = new PriorityQueue<>(new WorkerComparator());
        for (int i = 0; i < quality.length; i++) {
            Worker cur = new Worker(quality[i], wage[i], quality[i] / wage[i]);
            queue.add(cur);
        }
        double res = 0d;
        while (queue.size() > k) {
            queue.poll();
        }
        double radio = 0;
        Worker cur = queue.poll();
        radio = cur.radio;
        res += cur.wage;
        while (!queue.isEmpty()) {
            cur = queue.poll();
            res += cur.wage * radio;
        }
        return res;


    }


    public class Worker {
        private int quality;
        private int wage;
        private double radio;
        private int id;

        public Worker(int q, int w, double r) {
            quality = q;
            wage = w;
            radio = r;
        }
    }

    public class WorkerComparator implements Comparator<Worker> {
        @Override
        public int compare(Worker w1, Worker w2) {
            return w1.radio == w2.radio ? w2.wage - w1.wage : Double.doubleToLongBits(w1.radio) - Double.doubleToLongBits(w2.radio) > 0 ? 1 : -1;
        }
    }
}
