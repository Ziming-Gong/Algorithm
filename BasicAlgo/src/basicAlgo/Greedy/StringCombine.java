package basicAlgo.Greedy;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.TreeSet;

public class StringCombine {
    public static String lowestString(String[] strs){
        if( strs == null || strs.length == 0){
            return "";
        }
        TreeSet<String> set = process(strs);
        for( String s : set){
            System.out.println(s);
        }
        return set.size() == 0 ? "":set.first();
    }
    public static TreeSet<String> process(String[] strs){
        TreeSet<String> ans = new TreeSet<>();
        if (strs.length == 0) {
            ans.add("");
            return ans;
        }
        for(int i = 0; i < strs.length ; i ++){
            String first = strs[i];
            String[] nexts = removeIndexString(strs, i);
            TreeSet<String> next = process(nexts);
            for( String s : next){
                ans.add(first + s);
//                System.out.printf(s+ ",");
            }
//            System.out.println();
        }
        return ans;
    }
    public static String[] removeIndexString(String[] strs, int index){
        String[] ans = new String[strs.length -1];
        int ansIndex = 0;
        for( int i = 0; i < strs.length; i ++){
            if (i != index) {
                ans[ansIndex++] = strs[i];
            }
        }
        return ans;
    }

    public static class myComparator implements Comparator<String>{
        @Override
        public int compare(String a, String b){
            return (a + b).compareTo(b + a);
        }
    }

    public static String lowestString2(String[] strs){
        if (strs == null || strs.length == 0) {
            return "";
        }
        Arrays.sort(strs, new myComparator());
        StringBuilder sb = new StringBuilder();
        for( int i = 0; i < strs.length; i ++){
            sb.append(strs[i]);
        }
        return sb.toString();
    }


    //test
    public static String generateRandomsString(int strLen){
        char[] ans = new char[(int) ((Math.random() * strLen) + 1)];
        for( int i = 0 ; i < ans.length ; i++){
            int val = (int) (Math.random() * 5);
            ans[i] = (Math.random() <= 0.5) ? (char) (65 + val) : (char) (97 + val);
        }
        return String.valueOf(ans);
    }

    public static String[] generateRandomStringArray( int arrLen, int strLen){
        String[] ans = new String[(int) (Math.random() * arrLen) + 1];
        for( int i = 0 ; i < ans.length; i ++){
            ans[i] = generateRandomsString(strLen);
        }
        return ans;
    }

    public static String[] copyStringArray(String[] arr){
        String[] ans = new String[arr.length];
        for(int i = 0; i < arr.length; i ++){
            ans[i] = String.valueOf(arr[i]);
        }
        return ans;
    }

    public static void main(String[] args) {

        String[] strs = {"OA.MaxNumDinstinctNum.a","b","c"};
        lowestString(strs);





//        int arrLen = 6;
//        int strLen = 5;
//        int testTimes = 10000;
//        System.out.println("test begin");
//        for( int i = 0; i < testTimes; i ++){
//            String[] arr1 = generateRandomStringArray(arrLen, strLen);
//            String[] arr2 = copyStringArray(arr1);
//            if( !lowestString(arr1).equals(lowestString2(arr2))){
//                for(String str : arr1){
//                    System.out.print(str +",");
//                }
//                System.out.println();
//                System.out.println("oops");
//                break;
//            }
//        }
//        System.out.println("finish");
    }

}
