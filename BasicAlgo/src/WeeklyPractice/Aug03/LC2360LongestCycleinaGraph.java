package WeeklyPractice.Aug03;

public class LC2360LongestCycleinaGraph {
    public int longestCycle(int[] edges) {
        int N = edges.length;
        int[] dfn = new int[N];
        int ans = -1;
        int cnt = 1;
        for (int i = 0; i < N; i++) {
            if (edges[i] != -1) {
                int startP = cnt;
                if (dfn[i] == 0) {
                    for (int j = i; j < N; j = edges[j]) {
                        if (j == -1) {
                            break;
                        }
                        if (dfn[j] != 0) {
                            if (dfn[j] >= startP) {
                                ans = Math.max(ans, cnt - dfn[j]);
                            }
                            break;
                        }
                        dfn[j] = cnt++;
                    }
                }
            }

        }
        return ans;
    }
}
