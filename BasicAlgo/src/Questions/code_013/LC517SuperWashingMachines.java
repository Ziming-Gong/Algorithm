package Questions.code_013;

//https://leetcode.com/problems/super-washing-machines/
public class LC517SuperWashingMachines {
    public int findMinMoves(int[] arr) {
        if(arr.length == 1){
            return 0;
        }
        int N = arr.length;
        int sum = 0;
        for(int i : arr){
            sum += i;
        }
        if(sum % N != 0){
            return -1;
        }
        int per = sum / N;
        int pre = 0;
        int ans = -1;
        for(int i = 0; i < N; i++){
            int leftRest = pre - i * per;
            int rightRest = (sum - pre - arr[i]) - per * (N - i - 1) ;
            if(leftRest < 0 && rightRest < 0){
                ans = Math.max(ans, - leftRest - rightRest);
            }else{
                ans = Math.max(ans, Math.max(Math.abs(rightRest), Math.abs(leftRest)));
            }
            pre += arr[i];
        }
        return ans;

    }
}
