package Questions.code_13;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class LC759EmployeeFreeTime {
    class Interval {
        public int start;
        public int end;

        public Interval() {
        }

        public Interval(int _start, int _end) {
            start = _start;
            end = _end;
        }
    }

    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        int n = 0;
        for (List<Interval> list : schedule) {
            n += list.size();
        }
        int[][] events = new int[n << 1][3];
        int index = 0;
        for (List<Interval> list : schedule) {
            for (Interval cur : list) {
                events[index][0] = cur.start;
                events[index][1] = cur.end;
                events[index][2] = 1;
                events[index + n][0] = cur.end;
                events[index + n][2] = -1;
                index++;
            }
        }
        Arrays.sort(events, (a, b) -> (a[0] - b[0]));
        int pre = events[0][1];
        HashSet<Integer> set = new HashSet<>();
        set.add(events[0][1]);
        List<Interval> ans = new ArrayList<>();
        for (int i = 1; i < 2 * n; i++) {
            if (events[i][2] == -1) {
                set.remove(events[i][0]);
            } else {
                if (set.isEmpty()) {
                    ans.add(new Interval(pre, events[i][0]));
                }
                set.add(events[i][1]);
                pre = events[i][1];
            }
        }
        return ans;

    }


}










