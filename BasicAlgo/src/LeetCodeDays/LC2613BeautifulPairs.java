package LeetCodeDays;

import java.util.TreeMap;

public class LC2613BeautifulPairs {


    public int[] beautifulPair(int[] nums1, int[] nums2) {
        int[] ans = new int[2];
        int gap = Integer.MAX_VALUE;
        TreeMap<Integer, Integer> addMap = new TreeMap<>();
        TreeMap<Integer, Integer> subMap = new TreeMap<>();
        addMap.put(nums1[0] + nums2[0], 0);
        subMap.put(nums1[0] - nums2[0], 0);
        for(int i = 0; i < nums1.length; i ++){
            int add = nums1[i] + nums2[i];
            int sub = nums1[i] - nums2[i];
            int curAns = Integer.MAX_VALUE;
            Integer ceiling, floor;
            int gap1;
            // for situation 1 & 4:
            ceiling = addMap.ceilingKey(add);
            floor = addMap.floorKey(add);
            int preIndex1;
            if(ceiling != null && floor != null){
                if(add - floor == ceiling - add){
                    preIndex1 = Math.min(addMap.get(ceiling), addMap.get(floor));
                    gap1 = add - floor;
                }else if(add - floor > ceiling - floor){
                    preIndex1 = addMap.get(floor);
                    gap1 = add - floor;
                }else{
                    preIndex1 = addMap.get(ceiling);
                    gap1 = ceiling - add;
                }
            }else if(ceiling == null){
                preIndex1 = addMap.get(floor);
                gap1 = add - floor;
            }else{
                preIndex1 = addMap.get(ceiling);
                gap1 = ceiling - add;
            }
            // for situation 2 & 3
            int gap2;
            int preIndex2;
            ceiling = subMap.ceilingKey(sub);
            floor = subMap.floorKey(sub);
            if(ceiling != null && floor != null){
                if(add - floor == ceiling - add){
                    preIndex2 = Math.min(subMap.get(ceiling), subMap.get(floor));
                    gap2 = add - floor;
                }else if(add - floor > ceiling - floor){
                    preIndex2 = subMap.get(floor);
                    gap2 = add - floor;
                }else{
                    preIndex2 = subMap.get(ceiling);
                    gap2 = ceiling - add;
                }
            }else if(ceiling == null){
                preIndex2 = subMap.get(floor);
                gap2 = add - floor;
            }else{
                preIndex2 = subMap.get(ceiling);
                gap2 = ceiling - add;
            }

            // to compare
            int curGap = gap1 > gap2 ? gap2 : gap1;
            int preIndex = gap1 > gap2 ? preIndex2 : preIndex1;
            if(gap == curGap){
                if(preIndex < ans[0]){
                    ans[0] = preIndex;
                    ans[1] = i;
                }
            }else if(gap > curGap){
                ans[0] = preIndex;
                ans[1] = i;
                gap = curGap;
            }

            // update map
            // for add map
            if(!addMap.containsKey(add)){
                addMap.put(add, i);
            }
            if(!subMap.containsKey(sub)){
                subMap.put(sub, i);
            }
        }
        return ans;
    }
}
