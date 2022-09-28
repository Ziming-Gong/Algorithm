package Questions.code_9;

import java.util.Arrays;
import java.util.Comparator;

public class LC354RussianDollEnvelopes {
    public int maxEnvelopes(int[][] matrix) {
        int n = matrix.length;
        Envelope[] arr = new Envelope[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new Envelope(matrix[i][0], matrix[i][1]);
        }
        Arrays.sort(arr, new EComparator());
        int[] end = new int[n];
        int ans = 1;
        int L = 0;
        int R = 0;
        int size = 0;
        end[0] = arr[0].height;
        for (int i = 1; i < n; i++) {
            L = 0;
            R = size;
            while (L <= R) {
                int mid = (L + R) >> 1;
                if (arr[i].height < end[mid]) {
                    R = mid - 1;
                } else {
                    L = mid + 1;
                }

            }
            size = Math.min(size, L);
            end[L] = arr[i].height;
            ans = Math.max(ans, end[L]);
        }
        return ans;

    }

    public class EComparator implements Comparator<Envelope> {
        @Override
        public int compare(Envelope o1, Envelope o2) {
            return o1.weight == o2.weight ? o2.height - o1.height : o1.weight - o2.weight;
        }
    }

    public class Envelope {
        public int weight;
        public int height;

        public Envelope(int w, int h) {
            weight = w;
            height = h;
        }
    }
}
