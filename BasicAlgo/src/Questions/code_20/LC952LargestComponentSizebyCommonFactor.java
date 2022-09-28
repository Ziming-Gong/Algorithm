package Questions.code_20;

import java.util.HashMap;


//https://leetcode.com/problems/largest-component-size-by-common-factor/
public class LC952LargestComponentSizebyCommonFactor {
    public int largestComponentSize(int[] arr) {
        UnionFind uf = new UnionFind(arr);
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < arr.length; i ++){
            gcd(uf, map, i, arr[i]);
        }
        return uf.getMax();
    }

    public void gcd(UnionFind uf, HashMap<Integer, Integer> map, int index, int value){
        if(map.containsKey(value)){
            uf.union(index, map.get(value));
        }else{
            map.put(value, index);
        }
        for(int i = 2; i * i <= value; i ++){
            if(value % i == 0){
                if(map.containsKey(i)){
                    uf.union(index, map.get(i));
                }else {
                    map.put(i, index);
                }

                if(map.containsKey(value/i)){
                    uf.union(index, map.get(value/i));
                }else{
                    map.put(value/ i, index);
                }
            }
        }
    }

    public class UnionFind{
        public int[] arr;
        public int[] father;
        public int[] help;
        public int[] size;
        public int N;
        public int MAX;

        public UnionFind(int[] nums){
            N = nums.length;
            MAX = 1;
            father = new int[N];
            arr= new int[N];
            help = new int[N];
            size = new int[N];
            for(int i = 0; i < N; i ++){
                arr[i] = nums[i];
                father[i] = i;
                size[i] = 1;
            }
        }

        public void union(int p1, int p2){
            int fatherA = find(p1);
            int fatherB = find(p2);
            if(fatherA != fatherB){
                if(size[fatherA] > size[fatherB]){
                    size[fatherA] += size[fatherB];
                    father[fatherB] = fatherA;
                    MAX = Math.max(MAX, size[fatherA]);
                }else{
                    size[fatherB] += size[fatherA];
                    father[fatherA] = fatherB;
                    MAX = Math.max(MAX, size[fatherB]);
                }
                N --;

            }
        }

        public int find(int index){
            int size = 0;
            while(index != father[index]){
                help[size ++] = index;
                index = father[index];
            }
            while(size > 0){
                father[help[--size]] = index;
            }
            return index;
        }


        public int getMax(){
            return MAX;
        }
    }
}
