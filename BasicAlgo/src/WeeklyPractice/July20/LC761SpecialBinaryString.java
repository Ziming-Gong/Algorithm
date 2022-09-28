package WeeklyPractice.July20;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class LC761SpecialBinaryString {
    public String makeLargestSpecial(String s) {
        ArrayList<String> list = new ArrayList<>();
        char[] str = s.toCharArray();
        for (int i = 0; i < str.length; ) {
            Info next = process(str, i + 1);
            list.add(next.ans);
            i = next.end + 1;
        }
        list.sort((a, b) -> (b.compareTo(a)));
        StringBuilder sb = new StringBuilder();
        for (String string : list) {
            sb.append(string);
        }
        return sb.toString();

    }

    public Info process(char[] str, int index) {
        ArrayList<String> ans = new ArrayList<>();
        while (str[index] != '0') {
            Info next = process(str, index + 1);
            index = next.end + 1;
            ans.add(next.ans);
        }
        ans.sort((a, b) -> (b.compareTo(a)));
        StringBuilder sb = new StringBuilder();
        for(String cur : ans){
            sb.append(cur);
        }
        return new Info(index, "1" + sb.toString() + "0");
    }

    public class Info {
        int end;
        String ans;

        public Info(int e, String s) {
            end = e;
            ans = s;
        }

    }

//    public static void main(String[] args) {
//        String s1 = "100";
//        String s2 = "10";
//        System.out.println(s1.compareTo(s2));
//    }
}












