package Questions.code_03;


// 测试链接 : https://leetcode.com/problems/boats-to-save-people/

import java.util.Arrays;

public class BoatsToSavePeople {
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int lessR = 0;
        int N = people.length;
        for(int i = N-1; i >= 0; i --){
            if(people[i] <= limit/2){
                lessR = i;
                break;
            }
        }
        int l = lessR;
        int r = lessR+1;
        int ans = 0;
        // int solved = 0;
        int unUsed = 0;
        while(l >= 0){
            int solved = 0;
            while(r < N && people[l] + people[r] <= limit){
                solved ++;
                r ++;
            }
            if(solved == 0){
                l --;
                unUsed ++;
            }else{
                l = Math.max(-1 , l - solved);
            }
        }
        int all = lessR+1;
        int used = all - unUsed;
        return used + ((unUsed + 1)/2 )+ (N - all - used);
    }

    public int findMid(int[] people , int target){
        int l = 0;
        int r = people.length-1;
        int mid = 0;
        int ans = 0;
        while(l <= r){
            mid = (l+r)/2;
            if(people[mid] >= target){
                r = mid - 1;
            }else{
                l = mid + 1;
                ans = mid;

            }
        }
        return ans;
    }



}
