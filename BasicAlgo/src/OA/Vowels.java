package OA;

import Method.C;
import Questions.code_5.Hash;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Vowels {
    public static List<Integer> hasVowels(List<String> strArr, List<String> query) {
        int n = strArr.size();
        int[] count = new int[n + 1];
        HashSet<Character> vowelsSet = new HashSet<>();
        vowelsSet.add('a');
        vowelsSet.add('e');
        vowelsSet.add('i');
        vowelsSet.add('o');
        vowelsSet.add('u');
        for (int i = 0; i < strArr.size(); i++) {
            count[i + 1] = count[i];
            if (vowelsSet.contains(strArr.get(i).charAt(0)) && vowelsSet.contains(strArr.get(i).charAt(strArr.get(i).length() - 1))) {
                count[i + 1]++;
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < query.size(); i++) {
            String q = query.get(i);
            String[] range = q.split("-");
            ans.add(count[Integer.valueOf(range[1])] - count[Integer.valueOf(range[0]) - 1]);
        }
        return ans;
    }

    public static void print(List<Integer> list){
        for(int i : list){
            System.out.printf(i + ", ");
        }
    }

    public static void main(String[] args) {
        List<String> strArr = new ArrayList<>();
        strArr.add("yy");
        strArr.add("u");
        strArr.add("oe");
//        strArr.add("aa");
//        strArr.add("e");
        List<String> query = new ArrayList<>();
        query.add("1-2");
        query.add("2-3");
//        query.add("2-2");

        print(hasVowels(strArr, query));

    }
}
