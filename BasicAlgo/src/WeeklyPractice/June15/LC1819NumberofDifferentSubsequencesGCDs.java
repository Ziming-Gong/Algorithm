package WeeklyPractice.June15;

public class LC1819NumberofDifferentSubsequencesGCDs {
    public int countDifferentSubsequenceGCDs(int[] arr) {
        int max = Integer.MIN_VALUE;
        for(int i : arr){
            max= Math.max(i, max);
        }
        boolean[] check = new boolean[max + 1];
        for (int num : arr) {
            check[num] = true;
        }
        int ans = 0;
        for(int a = 1; a <= max; a ++){
            // if(check[i]){
            //     ans ++;
            //     continue;
            // }
            int g = a;
            for(; g <= max; g += a){
                if(check[g]){
                    break;
                }
            }
            for(int b = g; b <= max; b += a){
                if(check[b]){
                    g = GCD(g, b);
                    if(g == a){
                        ans ++;
                        break;
                    }
                }
            }


        }
        return ans;

    }

    public int GCD(int m, int n){
        return n == 0 ? m : GCD(n, m % n);
    }
}
