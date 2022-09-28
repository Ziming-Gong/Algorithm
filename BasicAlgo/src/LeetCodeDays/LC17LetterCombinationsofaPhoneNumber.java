package LeetCodeDays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LC17LetterCombinationsofaPhoneNumber {
    public List<String> letterCombinations(String digits) {
        if(digits.length() == 0){
            return null;
        }
        char[] str = digits.toCharArray();
        HashMap<Character, String[]> map = buildMap();

        List<String> ans = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        process(ans, map, str, sb, 0);
        return ans;
    }

    public void process(List<String> ans, HashMap<Character, String[]> map, char[] str, StringBuilder sb, int index){
        if(index == str.length){
            ans.add(sb.toString());
            return;
        }
        String[] cur = map.get(str[index]);
        for(int i = 0; i < cur.length; i ++){
            sb.append(cur[i]);
            process(ans, map, str, sb, index + 1);
            sb.deleteCharAt(sb.length() - 1);
        }

    }



    public HashMap<Character, String[]> buildMap(){
        HashMap<Character, String[]> map = new HashMap<>();

        map.put('2',new String[]{"a", "b", "c"});
        map.put('3',new String[]{"d", "e", "f"});
        map.put('4',new String[]{"g", "h", "i"});
        map.put('5',new String[]{"j", "k", "l"});
        map.put('6',new String[]{"m", "n", "o"});
        map.put('7',new String[]{"p", "q", "r", "s"});
        map.put('8',new String[]{"t", "u", "v"});
        map.put('9',new String[]{"w", "x", "y", "z"});

        return map;
    }
}
