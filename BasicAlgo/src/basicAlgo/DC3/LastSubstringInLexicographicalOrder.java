package basicAlgo.DC3;

import basicAlgo.DynamicProgramming.KillerMonster;

import java.io.PipedWriter;

// 测试链接: https://leetcode.com/problems/last-substring-in-lexicographical-order/
public class LastSubstringInLexicographicalOrder {
    public String lastSubstring(String s) {
        char[] str = s.toCharArray();
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (char ch : str) {
            min = Math.min(ch, min);
            max = Math.max(ch, max);
        }
        int[] arr = new int[str.length];
        for (int i = 0; i < str.length; i++) {
            arr[i] = str[i] - min + 1;
        }
        DC3 d = new DC3(arr, max - min + 1);
        int N = s.length();
        return s.substring(d.SA[N - 1]);

    }

    public class DC3 {
        public int[] SA;

        public DC3(int[] arr, int max) {
            SA = sa(arr, max);
        }

        public int[] sa(int[] str, int max) {
            int n = str.length;
            int[] arr = new int[n + 3];
            for (int i = 0; i < n; i++) {
                arr[i] = str[i];
            }
            return skew(arr, n, max);
        }

        public int[] skew(int[] arr, int n, int max) {
            int n0 = (n + 2) / 3, n1 = (n + 1) / 3, n2 = n / 3, n02 = n0 + n2;
            int[] s12 = new int[n02 + 3], sa12 = new int[n02 + 3];
            for (int i = 0, j = 0; i < n + (n0 - n1); ++i) {
                if (0 != i % 3) {
                    s12[j++] = i;
                }
            }
            radixPass(arr, s12, sa12, 2, n02, max);
            radixPass(arr, sa12, s12, 1, n02, max);
            radixPass(arr, s12, sa12, 0, n02, max);
            int name = 0, c0 = -1, c1 = -1, c2 = -1;
            for (int i = 0; i < n02; ++i) {
                if (c0 != arr[sa12[i]] || c1 != arr[sa12[i] + 1] || c2 != arr[sa12[i] + 2]) {
                    name++;
                    c0 = arr[sa12[i]];
                    c1 = arr[sa12[i] + 1];
                    c2 = arr[sa12[i] + 2];
                }
                if (sa12[i] % 3 == 1) {
                    s12[sa12[i] / 3] = name;
                } else {
                    s12[sa12[i] / 3 + n0] = name;
                }
            }
            if (name < n02) {
                sa12 = skew(s12, n02, name);
                for (int i = 0; i < n02; i++) {
                    s12[sa12[i]] = i + 1;
                }
            } else {
                for (int i = 0, j = 0; i < n02; i++) {
                    sa12[s12[i] - 1] = i;
                }
            }
            int[] s0 = new int[n0], sa0 = new int[n0];
            for (int i = 0, j = 0; i < n02; i++) {
                if (sa12[i] < n0) {
                    s0[j++] = 3 * sa12[i];
                }
            }
            radixPass(arr, s0, sa0, 0, n0, max);
            int[] sa = new int[n];
            for (int p = 0, t = n0 - n1, k = 0; k < n; k++) {
                int i = sa12[t] < n0 ? sa12[t] * 3 + 1 : (sa12[t] - n0) * 3 + 2;
                int j = sa0[p];
                if (sa12[t] < n0 ? leq(arr[i], s12[sa12[t] + n0], arr[j], s12[j / 3]) :
                        leq(arr[i], arr[i + 1], s12[sa12[t] - n0 + 1], arr[j], arr[j + 1], s12[j / 3 + n0])) {
                    sa[k] = i;
                    t++;
                    if (t == n02) {
                        for (k++; p < n0; p++, k++) {
                            sa[k] = sa0[p];
                        }
                    }
                } else {
                    sa[k] = j;
                    p++;
                    if (p == n0) {
                        for (k++; t < n02; t++, k++) {
                            sa[k] = sa12[t] < n0 ? sa12[t] * 3 + 1 : (sa12[t] - n0) * 3 + 2;
                        }
                    }
                }
            }
            return sa;

        }

        public void radixPass(int[] arr, int[] input, int[] output, int offset, int n, int max) {
            int[] cnt = new int[max + 1];
            for (int i = 0; i < n; i++) {
                cnt[arr[input[i] + offset]]++;
            }
            for (int i = 0, sum = 0; i < cnt.length; ++i) {
                int t = cnt[i];
                cnt[i] = sum;
                sum += t;
            }
            for (int i = 0; i < n; ++i) {
                output[cnt[arr[input[i] + offset]]++] = input[i];
            }
        }

        private boolean leq(int a1, int a2, int b1, int b2) {
            return a1 < b1 || (a1 == b1 && a2 <= b2);
        }

        private boolean leq(int a1, int a2, int a3, int b1, int b2, int b3) {
            return a1 < b1 || (a1 == b1 && leq(a2, a3, b2, b3));
        }
    }
}


































