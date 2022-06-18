package Questions.code_4;
//https://leetcode.com/problems/house-robber/
public class HouseRobber {
    public int rob(int[] arr) {
        if(arr.length == 1){
            return arr[0];
        }
        if(arr.length == 2){
            return Math.max(arr[0], arr[1]);
        }
        int n = arr.length;
        int pre = arr[0];
        int last = Math.max(arr[0], arr[1]);
        int ans = Math.max(arr[0], arr[1]);
        int temp = 0;
        for(int i = 2; i < n; i ++){
            temp = last;
            last = Math.max(arr[i] ,Math.max(arr[i] + pre ,last));
            pre = temp;
            ans = Math.max(ans, last);
        }
        return ans;
    }
}
