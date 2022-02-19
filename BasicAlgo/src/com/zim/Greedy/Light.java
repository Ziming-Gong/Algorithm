package com.zim.Greedy;

import java.util.HashSet;

public class Light {

    //非暴力
    public static int minLight1(String road){
        if( road == null || road ==""){
            return 0;
        }

        return process( road.toCharArray(), 0, new HashSet<>());
    }
    public static int process( char[] str, int index, HashSet<Integer> lights){
        if (index == str.length) {
            for( int i = 0; i < str.length; i++){
                if (str[i] != 'X') {
                    if(!lights.contains(i-1) && !lights.contains(i) && !lights.contains(i+1)){
                        return Integer.MAX_VALUE;
                    }
                }
            }
            return lights.size();
        }else{
            int no = process(str, index + 1, lights);
            int yes = Integer.MAX_VALUE;
            if(str[index] == '.'){
                lights.add(index);
                yes = process(str, index + 1,lights);
                lights.remove(index);
            }
            return Math.min(no,yes);
        }
    }



    //非暴力
    public static int minLight2(String road){
        char[] chars = road.toCharArray();
        int ans = 0;
        int index = 0;
        while( index < chars.length){
            if( chars[index] == 'X'){
                index++;
            }else{
                ans++;
                if (index + 1 == chars.length) {
                    break;
                }else {
                    if( chars[index + 1 ] == 'X' ){
                        index = index + 2;
                    }else{
                        index = index  + 3;
                    }
                }

            }
        }
        return ans;
    }
    // for test
    public static String randomString(int len) {
        char[] res = new char[(int) (Math.random() * len) + 1];
        for (int i = 0; i < res.length; i++) {
            res[i] = Math.random() < 0.5 ? 'X' : '.';
        }
        return String.valueOf(res);
    }

    public static void main(String[] args) {
        int len = 20;
        int testTime = 100000;
        for (int i = 0; i < testTime; i++) {
            String test = randomString(len);
            int ans1 = minLight1(test);
            int ans2 = minLight2(test);
            if (ans1 != ans2) {
                System.out.println("oops!");
            }
        }
        System.out.println("finish!");
    }
}
