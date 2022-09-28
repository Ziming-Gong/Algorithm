package Questions.code_19;


//给定一个证书N， 比如13
// 1，2，3，4，5，6，7，8，9，10，11，12，13
//求1 ～ N中 1 出现的次数

public class OneChar {
    public static int solve(int N) {
        return process(N);
    }

    public static int process(int N) {
        if (N < 1) {
            return 0;
        }
        int len = getLen(N);
        int temp = getPow(len - 1);
        int first = N / temp;
        int firstPart = first == 1 ? N % temp + 1 : temp;
        int otherPart = first * (len - 1) * (temp / 10);
        return firstPart + otherPart + process(N % temp);

    }

    public static int getPow(int len) {
        return (int) Math.pow(10, len);
    }

    public static int getLen(int N) {
        int ans = 0;
        while (N != 0) {
            ans++;
            N /= 10;
        }
        return ans;
    }

    public static int[] getHigh(int N) {
        int ans = 1;
        int help = 1;
        while ((N / (ans * 10)) > 0) {
            ans *= 10;
            help++;
        }
        return new int[]{ans, help};
    }


    public static int solution1(int num) {
        if (num < 1) {
            return 0;
        }
        int count = 0;
        for (int i = 1; i != num + 1; i++) {
            count += get1Nums(i);
        }
        return count;
    }

    public static int get1Nums(int num) {
        int res = 0;
        while (num != 0) {
            if (num % 10 == 1) {
                res++;
            }
            num /= 10;
        }
        return res;
    }

    public static void main(String[] args) {
        int num = 10000;
        for(int i = 0; i <= num; i ++){
            int ans1 = solution1(i);
            int ans2 = solve(i);
            if(ans1 != ans2){
                System.out.println("oops");
                break;
            }
        }
        System.out.println("test end");

    }


}
