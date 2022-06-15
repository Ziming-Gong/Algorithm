package Questions.code_03;

//https://leetcode.com/problems/longest-substring-without-repeating-characters/submissions/
public class LongestSubStringWithoutRepeat {
    // public int lengthOfLongestSubstring(String s) {
    //     HashMap<Character, Integer> map = new HashMap<>();
    //     char[] ch = s.toCharArray();
    //     int pre = 0;
    //     int ans = 0;
    //     for(int i = 0; i < ch.length; i ++){
    //         char cur = ch[i];
    //         if(!map.containsKey(cur) || map.get(cur) < pre){
    //             map.put(cur, map.getOrDefault(i, i));
    //             ans = Math.max(ans, i - pre + 1);
    //         }else{
    //             // if(pre == i){
    //             //     break;
    //             // }
    //             pre = map.get(cur) + 1;
    //             map.put(cur, i);
    //         }
    //     }
    //     return ans;
    // }
    public int lengthOfLongestSubstring(String s) {
        if(s == null || s.length() == 0){
            return 0;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int[] visited = new int[256];
        for(int i = 0; i < 256; i ++){
            visited[i] = -1;
        }
        visited[str[0]] = 0;
        int pre = 1;
        int ans = 1;
        for(int i = 1; i < N; i ++){
            pre = Math.min(pre + 1,i - visited[str[i]]);
            ans = Math.max(ans, pre);
            visited[str[i]] = i;
        }
        return ans;
    }

}
