package basicAlgo.Recursive;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class PrintAllSubsequences {
    //打印字符串的全部子序列
    public static List<String> subs(String s){
        String str = "";
        List<String> ans = new ArrayList<>();
        char[] chars = s.toCharArray();
        process(chars, 0, ans, str);
        return ans;
    }
    public static void process(char[] chars, int index, List<String> ans, String path){
        if (index == chars.length) {
            ans.add(path);
            return;
        }
        String no = path;
        process(chars, index + 1 , ans, no);
        String yes = path + String.valueOf(chars[index]);
        process(chars, index + 1, ans, yes);


    }

    public static List<String> subsNoRepeat(String s){
        String path = "";
        char[] chars = s.toCharArray();
        HashSet<String> set = new HashSet<>();
        process1(chars, 0, set, path);
        List<String> ans = new ArrayList<>();
        for( String str: set){
            ans.add(str);
        }
        return ans;
    }
    public static void process1(char[] chars, int index, HashSet<String> set,String path){
        if (index == chars.length) {
            set.add(path);
            return;
        }
        process1(chars, index + 1, set, path);
        process1(chars, index+1,set,path+String.valueOf(chars[index]));

    }

    public static void print(List<String> str){
        for(String string : str){
            System.out.println(string);
        }
    }
    public static void main(String[] args) {
        String s = "acc";
        List<String> ans = subs(s);
        print(ans);
        System.out.println("===================");
        List<String> ans2 = subsNoRepeat(s);
        print(ans2);
    }

}
