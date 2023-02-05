package Contest.Oct1;

import basicAlgo.Graph.DFS;
import sun.tools.attach.HotSpotVirtualMachine;

public class LC1213324 {
    public static int minimizeXor(int num1, int num2) {
        int set1 = get(num1);
        int set2 = get(num2);
        if(set1 == set2){
            return num1;
        }
        int diff = set1 - set2;
        int ans;
        if(diff > 0){
            ans = num1;
            int cur = 1;
            int cnt = 0;
            while(diff  > 0){
                while(((cur << cnt) & num1) != (cur << cnt)){
                    cnt --;
                }
                diff--;
                ans -= 1 << cnt;
                cnt --;
            }
        }else{
            ans = num1;
            int cur = 1;
            int cnt = 0;
            while(diff < 0){
                while(((cur << cnt) & num1) == (cur << cnt)){
                    cnt ++;
                }
                diff ++;
                ans += 1 << cnt;
                cnt ++;
            }
        }
        return ans;
    }

    public static int get(int t){
        int cur = 1;
        int ans = 0;
        for(int i = 0; i < 32; i ++){
            if((t & cur) == cur){
                ans ++;
            }
            cur <<= 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        int a = 79;
        int b = 74;
        System.out.println(minimizeXor(a, b));
    }
}
