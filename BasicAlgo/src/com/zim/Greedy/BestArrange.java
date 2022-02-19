package com.zim.Greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BestArrange {
    public static class Program{
        public int start;
        public int end;
        public Program(int s, int e){
            start = s;
            end = e;
        }
    }

    //暴力
    public static int bestArrange1(Program[] programs){
        if( programs == null || programs.length == 0){
            return 0;
        }
        return process(programs,0,0 );
    }
    public static int process(Program[] programs, int doneNum, int timeline){
        if (programs.length == 0) {
            return doneNum;
        }
        int max = doneNum;
        for( int i = 0; i < programs.length; i ++){
            if (timeline <= programs[i].start) {
                Program[] next = copyButExcept(programs, i);
                max = Math.max(max, process(next, doneNum + 1, programs[i].end));
            }
        }
        return max;
    }
    public static Program[] copyButExcept(Program[] programs, int index){
        Program[] ans = new Program[programs.length-1];
        int i = 0;
        for( int k = 0; k < programs.length; k++){
            if (k != index) {
                ans[i++] = programs[k];
            }
        }
        return ans;
    }


    //非暴力
    public static int bestArrange2(Program[] programs){
        Arrays.sort(programs, new myComparator());
        int max = 0;
        int timeline = 0;
        for(int i = 0; i < programs.length; i++){
            if (timeline <= programs[i].start) {
                max ++;
                timeline = programs[i].end;
            }
        }
        return max;
    }
    public static class myComparator implements Comparator<Program>{
        @Override
        public int compare(Program o1, Program o2){
            return o1.end - o2.end;
        }
    }
    // for test
    public static Program[] generatePrograms(int programSize, int timeMax) {
        Program[] ans = new Program[(int) (Math.random() * (programSize + 1))];
        for (int i = 0; i < ans.length; i++) {
            int r1 = (int) (Math.random() * (timeMax + 1));
            int r2 = (int) (Math.random() * (timeMax + 1));
            if (r1 == r2) {
                ans[i] = new Program(r1, r1 + 1);
            } else {
                ans[i] = new Program(Math.min(r1, r2), Math.max(r1, r2));
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int programSize = 12;
        int timeMax = 20;
        int timeTimes = 1000000;
        for (int i = 0; i < timeTimes; i++) {
            Program[] programs = generatePrograms(programSize, timeMax);
            if (bestArrange1(programs) != bestArrange2(programs)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }



































}
