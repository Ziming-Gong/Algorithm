package WeeklyPractice.April02;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class SumOfValuesAboutPrimes {
    public static ArrayList<Long> primes(long num){
        ArrayList<Long> ans = new ArrayList<>();
        for(long i = 2; num > 1&& i * i <= num; i++){
            if( num % i == 0){
                ans.add(i);
                while(num%i == 0){
                    num /= i;
                }
            }
        }
        if( num > 1){
            ans.add(num);
        }
        return ans;
    }

    public static long sumOfValues1(int[] arr) {
        return process1(arr, 0, 1);
    }

    public static long process1(int[] arr, int index, long pre) {
        if (index == arr.length) {
            return (long) primes(pre).size();
        }
        long p1 = process1(arr, index + 1, pre);
        long p2 = process1(arr, index + 1, pre * (long) arr[index]);
        return p1 + p2;
    }

    public static long sumOfValues2(int[] arr){
        if(arr == null || arr.length < 1){
            return 0;
        }
        HashMap<Long, Long> map = new HashMap<>();
        for(int i = 0; i < arr.length; i ++){
            for(Long num : primes(arr[i])){
                if(!map.containsKey(num)){
                    map.put(num, 0L);
                }
                map.put(num, map.get(num) + 1L);
            }
        }
        long res = 0;
        long n = arr.length;
        for(Long index : map.keySet()){
            long others = n - map.get(index);
            res += (power(2, map.get(index)) - 1) * power(2, others);

        }
        return res;
    }

    public static long power(long num, long n){
        if( n == 0L){
            return 1L;
        }
        long res = 1L;
        while( n > 0){
            if((n & 1) != 0){
                res *= num;
            }
            num *= num;
            n >>= 1;
        }
        return res;
    }



    //for test
    public static int[] randomArray(int len, int value){
        int[] arr = new int[len];
        for(int i = 0; i < len; i ++){
            arr[i] = (int) (Math.random() * value) + 1;
        }
        return arr;
    }
    // for test
    public static void main(String[] args) {
        int n = 10;
        int v = 50;
        int testTime = 5000;
        System.out.println("begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(n, v);
            long ans1 = sumOfValues1(arr);
            long ans2 = sumOfValues2(arr);
            if (ans1 != ans2) {
                System.out.println("fuck！");
                BigInteger all = new BigInteger("1");
                for (int num : arr) {
                    all = all.multiply(new BigInteger(String.valueOf(num)));
                }
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("multi of nums : " + all.toString());
                System.out.println("Long range : " + Long.MAX_VALUE);
                System.out.println("overflow???");
                break;
            }
        }
        System.out.println("end");
    }

}
