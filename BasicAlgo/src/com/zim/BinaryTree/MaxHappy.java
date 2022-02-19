package com.zim.BinaryTree;

import java.util.ArrayList;
import java.util.List;

public class MaxHappy {
    public static class Employee{
        public int happy;
        public List<Employee> next;

        public Employee(int happy) {
            this.happy = happy;
            this.next = new ArrayList<>();
        }
    }

    public static int maxHappy1(Employee head){
        if (head == null) {
            return 0;
        }
        return process1(head,false);
    }
    public static int process1(Employee cur, boolean up){
        if (up) {
            int ans = 0;
            for( Employee next : cur.next){
                ans += process1(next, false);
            }
            return ans;
        }else {
            int p1 = cur.happy;
            int p2 = 0;
            for(Employee next : cur.next){
                p1 += process1(next, true);
                p2 += process1(next, false);
            }
            return Math.max(p1,p2);
        }
    }

    public static int maxHappy(Employee boss){
        if (boss == null) {
            return 0;
        }
        Info info = process(boss);
        return Math.max(info.no, info.yes);
    }

    public static class Info{
        public int yes;
        public int no;

        public Info(int yes, int no) {
            this.yes = yes;
            this.no = no;
        }
    }
    public static Info process(Employee head){
        if (head == null) {
            return new Info(0,0);
        }

        int no = 0;
        int yes = head.happy;
        for( Employee nexts : head.next){
            Info next = process(nexts);
            no += Math.max(next.no, next.yes);
            yes += next.no;
        }
        return new Info(yes,no);
    }

    // for test
    public static Employee genarateBoss(int maxLevel, int maxNexts, int maxHappy) {
        if (Math.random() < 0.02) {
            return null;
        }
        Employee boss = new Employee((int) (Math.random() * (maxHappy + 1)));
        genarateNexts(boss, 1, maxLevel, maxNexts, maxHappy);
        return boss;
    }

    // for test
    public static void genarateNexts(Employee e, int level, int maxLevel, int maxNexts, int maxHappy) {
        if (level > maxLevel) {
            return;
        }
        int nextsSize = (int) (Math.random() * (maxNexts + 1));
        for (int i = 0; i < nextsSize; i++) {
            Employee next = new Employee((int) (Math.random() * (maxHappy + 1)));
            e.next.add(next);
            genarateNexts(next, level + 1, maxLevel, maxNexts, maxHappy);
        }
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxNexts = 7;
        int maxHappy = 100;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            Employee boss = genarateBoss(maxLevel, maxNexts, maxHappy);
            if (maxHappy1(boss) != maxHappy(boss)) {
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("finish!");
    }


















}
