package basicAlgo.DynamicProgramming;

import javax.crypto.Mac;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class CoffeeMachine {
    public static class Machine {
        public int availableTimeZone;
        public int useTime;

        public Machine(int a, int u) {
            availableTimeZone = a;
            useTime = u;
        }
    }

    public static class myComparator implements Comparator<Machine> {
        @Override
        public int compare(Machine o1, Machine o2) {
            return o1.availableTimeZone + o1.useTime - o2.availableTimeZone - o2.useTime;
        }
    }

    public static int[] drink(int N, int[] arr) {
        int[] ans = new int[N];
        PriorityQueue<Machine> heap = new PriorityQueue<>(new myComparator());
        for (int i = 0; i < arr.length; i++) {
            heap.add(new Machine(0, arr[i]));
        }
        for (int i = 0; i < N; i++) {
            Machine cur = heap.poll();
            cur.availableTimeZone += cur.useTime;
            ans[i] = cur.availableTimeZone;
            heap.add(cur);
        }
        return ans;
    }

    public static int minTime1(int[] arr, int n, int a, int b) {
        int[] drinks = drink(n, arr);
        return process(drinks, 0, a, b, 0);
    }

    public static int process(int[] drinks, int index, int a, int b, int free) {
        if(index == drinks.length){
            return 0;
        }
        //if choose a
        int timeA = Math.max(free, drinks[index]) + a;
        int restA = process(drinks, index + 1, a, b, timeA);
        int resultA = Math.max(timeA, restA);

        //if choose b
        int timeB = drinks[index] + b;
        int restB = process(drinks, index + 1, a, b, free);
        int resultB = Math.max(timeB, restB);

        return Math.min(resultA, resultB);
    }

    public static int minTime2(int[] arr, int n, int wash, int air){
        int[] drinks = drink(n, arr);
        int maxFree = 0;
        int N = drinks.length;
        for(int i = 0; i < N; i++){
            maxFree = Math.max(drinks[i], maxFree) + wash;
        }
        int[][] dp = new int[N+1][maxFree+1];
        for(int i = N - 1; i >=0; i --){
            for(int free = 0; free < maxFree; free ++){
                int curAir = drinks[i] + air;

                int restA = dp[i+1][free];
                int resultA = Math.max(restA, curAir);

                int curWash = Math.max(free, drinks[i])+wash;
                if(curWash > maxFree){
                    continue;
                }
                int restB = dp[i+1][curWash];
                int resultB = Math.max(restB, curWash);
                dp[i][free] = Math.min(resultA,resultB);
            }
        }


        return dp[0][0];
    }

    public static int right(int[] arr, int n, int a, int b) {
        int[] times = new int[arr.length];
        int[] drink = new int[n];
        return forceMake(arr, times, 0, drink, n, a, b);
    }

    // 每个人暴力尝试用每一个咖啡机给自己做咖啡
    public static int forceMake(int[] arr, int[] times, int kth, int[] drink, int n, int a, int b) {
        if (kth == n) {
            int[] drinkSorted = Arrays.copyOf(drink, kth);
            Arrays.sort(drinkSorted);
            return forceWash(drinkSorted, a, b, 0, 0, 0);
        }
        int time = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            int work = arr[i];
            int pre = times[i];
            drink[kth] = pre + work;
            times[i] = pre + work;
            time = Math.min(time, forceMake(arr, times, kth + 1, drink, n, a, b));
            drink[kth] = 0;
            times[i] = pre;
        }
        return time;
    }
    public static int forceWash(int[] drinks, int a, int b, int index, int washLine, int time) {
        if (index == drinks.length) {
            return time;
        }
        // 选择一：当前index号咖啡杯，选择用洗咖啡机刷干净
        int wash = Math.max(drinks[index], washLine) + a;
        int ans1 = forceWash(drinks, a, b, index + 1, wash, Math.max(wash, time));

        // 选择二：当前index号咖啡杯，选择自然挥发
        int dry = drinks[index] + b;
        int ans2 = forceWash(drinks, a, b, index + 1, washLine, Math.max(dry, time));
        return Math.min(ans1, ans2);
    }


    // for test
    public static int[] randomArray(int len, int max) {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * max) + 1;
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        System.out.print("arr : ");
        for (int j = 0; j < arr.length; j++) {
            System.out.print(arr[j] + ", ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int len = 10;
        int max = 10;
        int testTime = 10;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(len, max);
            int n = (int) (Math.random() * 7) + 1;
            int a = (int) (Math.random() * 7) + 1;
            int b = (int) (Math.random() * 10) + 1;
            int ans1 = right(arr, n, a, b);
            int ans2 = minTime1(arr, n, a, b);
            int ans3 = minTime2(arr, n, a, b);
            if (ans1 != ans2 || ans2 != ans3) {
                printArray(arr);
                System.out.println("n : " + n);
                System.out.println("a : " + a);
                System.out.println("b : " + b);
                System.out.println(ans1 + " , " + ans2 + " , " + ans3);
                System.out.println("===============");
                break;
            }
        }
        System.out.println("测试结束");

    }
}
