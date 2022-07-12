package basicAlgo.bitmap;

public class BitAddMinusMultiDiv {
    //https://leetcode.com/problems/divide-two-integers/
    //sum
    public static int sum(int a, int b){
        int sum = a;
        while ( b != 0){
            sum = a ^ b;
            b = ( a & b )<<1;
            a = sum;
        }
        return sum;
    }
    //minus
    public static int minus(int a, int b){
        return  sum(a,sum(~b,1));
    }


    //multi
    public static int multi1(int a, int b){
        int sum = 0;
        for( int n = 0; n <= 31; n ++){
            if( (b & (1 << n)) !=  0 ){
                sum = sum( sum, a << n);
            }
        }
        return sum;
    }
    public static int multi2(int a, int b){
        int ans = 0;
        while( b != 0){
            if( (b & 1) != 0){
                ans = sum(ans, a);
            }
            a <<= 1;
            b >>>= 1;
        }
        return ans;
    }

    //div
    public static boolean isNeg( int a ){
        return a < 0;
    }
    public static int abVal( int a ){
        return sum(~a,1);
    }
    public static int div( int dividend, int divisor){
        int a = isNeg(dividend)? abVal(dividend) : dividend;
        int b = isNeg(divisor) ? abVal(divisor) : divisor;
        int ans = 0;
        for( int i = 30; i >=0; i--){
            if( ( a >> i ) >= b ){
                //ans = sum( 1 << i,ans);
                ans |= 1 << i;
                a = minus(a, b<< i);
            }
        }
        ans = isNeg(dividend) ^ isNeg(divisor) ? abVal(ans) : ans;

        return ans;
    }
    public static int divider(int a, int b){
        if(a == Integer.MIN_VALUE && b == Integer.MIN_VALUE ){
            return 1;
        }else if ( b  == Integer.MIN_VALUE){
            return 0;
        }else if( a == Integer.MIN_VALUE){
            if( b == abVal(1)){
                return Integer.MAX_VALUE;
            }else {
                int c = div(sum(a,1),b);
                return sum(c,div(minus(a, multi1(c,b)),b));
                //return add(c, div(minus(OA.MaxNumDinstinctNum.a, multi(c, b)), b));
            }
        } else {
            return div( a, b);
        }
    }





    //for test;
    public static int generalRandom(int maxVal){
        int num = (int)(Math.random() * maxVal) - (int) (Math.random() * maxVal);
        return num;
    }

    public static void main(String[] args) {
        int a = 1;
        int b = Integer.MIN_VALUE;
        System.out.println(a / b);
        System.out.println(divider(a, b));


        int testTimes = 10000;
        int maxvalue = Integer.MIN_VALUE;
        for( int i = 1; i <= testTimes; i ++){
            int num1 = generalRandom(maxvalue);
            int num2 = generalRandom(maxvalue);
            if( divider( num1, num2) !=  (num1 / num2)){
                System.out.println("fxxking hard");
//                System.out.println(num1);
//                System.out.println(num2);
//                System.out.println((num1, num2));
//                System.out.println(num1 / num2);
                break;
            }
        }
        System.out.println("end");

    }
}
