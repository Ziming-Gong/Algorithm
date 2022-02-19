package com.zim.heapgreater;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class ShowBoss {
    public static class Customer{
        public int buy;
        public int enterTime;
        public int id;

        public Customer(int id, int b){
            this.id = id;
            this.enterTime = 0;
            buy = b;
        }
    }
    public  static class  bossComparator implements Comparator<Customer>{
        @Override
        public int compare(Customer o1, Customer o2){
            return o1.buy == o2.buy ? o1.enterTime - o2.enterTime : o2.buy - o1.buy;
        }
    }

    public static class CandidateComparator implements Comparator<Customer>{
        @Override
        public int compare(Customer c1, Customer c2){
            return c1.buy != c2.buy ? c2.buy - c1.buy : c1.enterTime - c2.enterTime;

        }
    }



    //普通方法
    public static List<List<Integer>> findBoss(int[] arr, boolean[] op, int k){
        HashMap<Integer, Customer> map = new HashMap<>();
        ArrayList<Customer> candidateList = new ArrayList<>();
        ArrayList<Customer> bossList = new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();
        for( int i = 0; i < arr.length ; i++){
            int id = arr[i];
            boolean action = op[i];
            //如果没有购买记录，退货
            if(!action && !map.containsKey(id) ){
                ans.add(getCurArr(bossList));
                continue;
            }
            //如果有没有记录，买货
            if( !map.containsKey(id) ){
                map.put(id,new Customer(id, 0));
            }
            Customer c = map.get(id);
            //买、卖
            if (action) {
                c.buy++;
            }else {
                c.buy --;
            }
            if (c.buy == 0) {
                map.remove(id);
            }
            if( !candidateList.contains(c) && !bossList.contains(c)){
                if (bossList.size() < k) {
                    c.enterTime = i;
                    bossList.add(c);
                }else {
                    c.enterTime = i;
                    candidateList.add(c);
                }
            }
            cleanZeroBuy(candidateList);
            cleanZeroBuy(bossList);

            bossList.sort(new bossComparator());
            candidateList.sort(new CandidateComparator());
            move(candidateList,bossList,k,i);
            ans.add(getCurArr(bossList));
        }
        return ans;
    }
    public static void move(ArrayList<Customer> cands, ArrayList<Customer> daddy, int k, int time){
        if( cands.isEmpty()){
            return;
        }

        if( daddy.size() < k){
            Customer c = cands.get(0);
            c.enterTime = time;
            daddy.add(c);
            cands.remove(0);
        }else {
            if( cands.get(0).buy > daddy.get(0).buy){
                Customer oldBoss = daddy.get(0);
                daddy.remove(0);
                Customer newBoss = cands.get(0);
                cands.remove(0);


                newBoss.enterTime = time;
                oldBoss.enterTime = time;
                cands.add(oldBoss);
                daddy.add(newBoss);
            }
        }
    }

    public static void cleanZeroBuy(ArrayList<Customer> arr){
        //---------question: 为什么要加一个List
        List<Customer> noZero = new ArrayList<Customer>();
        for (Customer c : arr) {
            if (c.buy != 0) {
                noZero.add(c);
            }
        }
        arr.clear();
        for (Customer c : noZero) {
            arr.add(c);
        }

//        for(Customer c : arr){
//            if( c.buy == 0){
//                arr.remove(c);
//            }
//        }
    }

    public static List<Integer> getCurArr(ArrayList<Customer> arrayList){
        List<Integer> ans = new ArrayList<>();
        for( Customer c : arrayList){
            ans.add(c.id);
        }
        return ans;
    }

    public static class BossHeapComparator implements Comparator<Customer>{
        @Override
        public int compare( Customer c1, Customer c2){
            return c1.buy != c2.buy ? c1.buy - c2.buy : c2.enterTime - c1.enterTime;
        }
    }

    //利用加强堆
    public static class whoIsBoss{
        private HashMap<Integer, Customer> map;
        private HeapGreater<Customer> candidateHeap;
        private HeapGreater<Customer> bossHeap;
        private  final int limit;

        public whoIsBoss(int limit){
            map = new HashMap<>();
            candidateHeap = new HeapGreater<>(new CandidateComparator());
            bossHeap = new HeapGreater<>(new bossComparator());
            this.limit = limit;
        }

        public void operate(int id, boolean action, int time){
            //退货，没买过
            if( !action && !map.containsKey(id)){
                return;
            }
            if(!map.containsKey(id)){
                map.put(id, new Customer(id,0));
            }
            Customer c = map.get(id);
            if( action ){
                c.buy++;
            }else {
                c.buy --;
            }
            if( c.buy == 0){
                map.remove(id);
            }
            if( !candidateHeap.contains(c) && !bossHeap.contains(c)){
                if( bossHeap.size() < limit){
                    c.enterTime = time;
                    bossHeap.push(c);
                }else{
                    c.enterTime = time;
                    candidateHeap.push(c);
                }
            }else if (candidateHeap.contains(c)){
                if(c.buy == 0){
                    candidateHeap.remove(c);
                }else{
                    candidateHeap.resign(c);
                }
            }else {
                if( c.buy == 0){
                    bossHeap.remove(c);
                }else{
                    bossHeap.resign(c);
                }
            }
            heapMove(time);
        }
        public List<Integer> getAllBoss(){
            List<Integer> list = new ArrayList<>();
            for( Customer c : bossHeap.getAllElements()){
                list.add(c.id);
            }
            return list;
        }
        private void heapMove( int time){
            if( candidateHeap.isEmpty()){
                return;
            }
            if( bossHeap.size() < limit ){
                Customer newBoss = candidateHeap.pop();
                newBoss.enterTime = time;
                bossHeap.push(newBoss);
            }else{
                if (candidateHeap.peek().buy > bossHeap.peek().buy) {
                    Customer newBoss = candidateHeap.pop();
                    Customer oldBoss = bossHeap.pop();
                    newBoss.enterTime = time;
                    oldBoss.enterTime = time;
                    candidateHeap.push(oldBoss);
                    bossHeap.push(newBoss);
                }
            }
        }
    }

    public static List<List<Integer>> topK(int[] arr, boolean[] op,int k){
        List<List<Integer>> ans = new ArrayList<>();
        whoIsBoss whoIsBoss = new whoIsBoss(k);
        for(int i = 0; i < arr.length; i ++ ){
            whoIsBoss.operate(arr[i], op[i], i);
            ans.add(whoIsBoss.getAllBoss());
        }
        return ans;
    }
    // 为了测试
    public static class Data {
        public int[] arr;
        public boolean[] op;

        public Data(int[] a, boolean[] o) {
            arr = a;
            op = o;
        }
    }

    // 为了测试
    public static test.Data randomData(int maxValue, int maxLen) {
        int len = (int) (Math.random() * maxLen) + 1;
        int[] arr = new int[len];
        boolean[] op = new boolean[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * maxValue);
            op[i] = Math.random() < 0.5 ? true : false;
        }
        return new test.Data(arr, op);
    }

    // 为了测试
    public static boolean sameAnswer(List<List<Integer>> ans1, List<List<Integer>> ans2) {
        if (ans1.size() != ans2.size()) {
            return false;
        }
        for (int i = 0; i < ans1.size(); i++) {
            List<Integer> cur1 = ans1.get(i);
            List<Integer> cur2 = ans2.get(i);
            if (cur1.size() != cur2.size()) {
                return false;
            }
            cur1.sort((a, b) -> a - b);
            cur2.sort((a, b) -> a - b);
            for (int j = 0; j < cur1.size(); j++) {
                if (!cur1.get(j).equals(cur2.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int maxValue = 10;
        int maxLen = 10;
        int maxK = 6;
        int testTimes = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            test.Data testData = randomData(maxValue, maxLen);
            int k = (int) (Math.random() * maxK) + 1;
            int[] arr = testData.arr;
            boolean[] op = testData.op;
            List<List<Integer>> ans1 = topK(arr, op, k);
            List<List<Integer>> ans2 = findBoss(arr, op, k);
            if (!sameAnswer(ans1, ans2)) {
                for (int j = 0; j < arr.length; j++) {
                    System.out.println(arr[j] + " , " + op[j]);
                }
                //System.out.println(i + "I");
                System.out.println("K等于"+k);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("出错了！");
                break;
            }
        }
        System.out.println("测试结束");
    }






}
