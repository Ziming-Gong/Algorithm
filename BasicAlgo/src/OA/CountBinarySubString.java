package OA;

import java.util.HashSet;

public class CountBinarySubString {


    public static int right(String s){
        HashSet<String> set = new HashSet<>();
        int ans = 0;
        for(int i = 0; i < s.length(); i ++){
            int cnt = 0;
            for(int j = i; j < s.length(); j ++){
                if(s.charAt(j) == '0'){
                    cnt --;
                }else{
                    cnt ++;
                }
                if(cnt == 0 && !set.contains(s.substring(i, j + 1))){
                    set.add(s.substring(i, j + 1));
                    ans ++;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String str = "001100011";
        System.out.println(right(str));
    }
}
