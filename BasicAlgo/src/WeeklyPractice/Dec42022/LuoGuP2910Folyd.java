package WeeklyPractice.Dec42022;

import java.io.*;

public class LuoGuP2910Folyd {
    public static int N = 100;
    public static int M = 10000;
    public static int[] path = new int[M];
    public static int[][] distance = new int[N][N];
    public static int n, m, ans;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(bf);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) in.nval;
            in.nextToken();
            m = (int) in.nval;
            for (int i = 0; i < m; i++) {
                in.nextToken();
                path[i] = (int) in.nval - 1;
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    in.nextToken();
                    distance[i][j] = (int) in.nval;
                }
            }
            floyd();
            ans = 0;
            for (int i = 1; i < m; i++) {
                ans += distance[path[i - 1]][path[i]];
            }
            out.println(ans);
            out.flush();

        }

    }

    public static void floyd() {
        for (int jump = 0; jump < n; jump++) {
            for (int from = 0; from < n; from++) {
                for (int to = 0; to < n; to++) {
                    if (distance[from][jump] != Integer.MAX_VALUE && distance[jump][to] != Integer.MAX_VALUE && distance[from][to] > distance[from][jump] + distance[jump][to]) {
                        distance[from][to] = distance[from][jump] + distance[jump][to];
                    }
                }
            }
        }
    }
}
