package WeeklyPractice.March012023;


import javax.security.auth.callback.CallbackHandler;
import java.io.*;
import java.util.Arrays;

// HH有一串由各种漂亮的贝壳组成的项链
// HH 相信不同的贝壳会带来好运，所以每次散步完后，他都会随意取出一段贝壳，
// 思考它们所表达的含义。HH 不断地收集新的贝壳，因此，他的项链变得越来越长。
// 有一天，他突然提出了一个问题：某一段贝壳中，包含了多少种不同的贝壳？
// 这个问题很难回答... 因为项链实在是太长了
// 于是，他只好求助睿智的你，来解决这个问题
// 测试链接 : https://www.luogu.com.cn/problem/P1972
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 提交以下所有代码，把主类名改成Main
// 洛谷对java太不友好了，大量时间不是消耗在算法本身上，而是耗在了IO上
// 多提交几次能全通过
public class Code03_DiffColors {

    static int maxN = 100001;

    static int n, m;

    static int[] chain = new int[maxN];

    static int[][] querys = new int[maxN][3];

    static int[] tree = new int[maxN];

    static int[] map = new int[maxN];

    static void add(int index, int c) {
        while (index < n) {
            tree[index] += c;
            index += (index & -index);
        }
    }

    static int sum(int l, int r) {
        return sum(r) - sum(l - 1);

    }

    static int sum(int index) {
        int res = 0;
        while (index > 0) {
            res += tree[index];
            index -= (index & -index);
        }
        return res;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) in.nval;
            for (int i = 1; i <= n; i++) {
                in.nextToken();
                chain[i] = (int) in.nval;
            }
            // arr[i] : i位置的颜色
            in.nextToken();
            m = (int) in.nval;
            for (int i = 1; i <= m; i++) {
                in.nextToken();
                // left
                querys[i][0] = (int) in.nval;
                in.nextToken();
                // right
                querys[i][1] = (int) in.nval;
                // 第i个问题
                querys[i][2] = i;
            }
        }
        Arrays.sort(querys, (a, b) -> (a[1] - b[1]));
//        Arrays.fill(map, 0, n, -1);
        int[] ans = new int[m];
        int index = 0;
        for (int i = 1; i <= n && index < m; i++) {
            if (map[chain[i]] != 0) {
                add(map[chain[i]], -1);
            }
            add(i, 1);
            map[chain[i]] = i;
            while (index < m && querys[index][1] + 1 == i) {
                int ansIndex = querys[index][2];
                int sum = sum(querys[index][0] + 1, querys[index][1] + 1);
                ans[ansIndex] = sum;
                index++;
            }
        }
        for (int i = 0; i < m; i++) {
            out.println(ans[i]);
        }

        out.flush();

    }
}
