package LeetCodeDays;

import Questions.code_2.ChooseWork;

public class LC60PermutationSequence {

    public String getPermutation(int n, int k) {
        return func(n, k, new int[n + 1]);

    }

    public String func(int n, int k, int[] used) {
        if (n == 1) {
            return getNMAax(used, k);
        }
        int first = 1;
        int temp = k;
        int one = NSum(n - 1);
        while (one < temp) {
            temp -= one;
            first++;
        }
        return getNMAax(used, first) + func(n - 1, k - (first - 1) * (n - 1), used);


    }

    public String getNMAax(int[] used, int k) {
        int ans = 0;
        for (int i = 1; i < used.length; i++) {
            if (used[i] == 0) {
                k--;
                if (k == 0) {
                    ans = i;
                    used[i] = 1;
                    break;
                }
            }
        }
        return String.valueOf(ans);
    }

    public int NSum(int N) {
        int sum = 1;
        while (N != 1) {
            sum *= N;
            N--;
        }
        return sum;
    }


}
