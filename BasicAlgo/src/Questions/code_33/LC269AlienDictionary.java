package Questions.code_33;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class LC269AlienDictionary {
    public static void main(String[] args) {
        String[] strs = {"z", "x"};
        System.out.println(alienOrder(strs));
    }
    public static String alienOrder(String[] words) {
        if(words.length <= 1){
            return "";
        }
        Topo tp = new Topo();
        HashSet<Integer> set = new HashSet<>();
        for(int i = 0, j = 1; j < words.length; i ++, j ++){
            int index = 0;
            for(; index < words[i].length() && index < words[j].length(); index ++){
                set.add(words[i].charAt(index) - 'a');
                set.add(words[j].charAt(index) - 'a');
                if(words[i].charAt(index) != words[j].charAt(index)){
                    tp.add((int) (words[i].charAt(index) - 'a'), (int) (words[j].charAt(index) - 'a'));
                    break;
                }
            }
            if(index < words[i].length() && index == words[j].length()){
                return "";
            }

        }
        System.out.println(1);
        return tp.finger(set);
    }

    public static class Topo{
        ArrayList<Integer> list;
        HashMap<Integer, ArrayList<Integer>> map;

        public Topo(){
            list = new ArrayList<>();
            map = new HashMap<>();
            for(int i = 0; i < 26; i ++){
                list.add(0);
                map.put(i, new ArrayList<>());
            }
        }

        public void add(int from, int to){
            list.set(to, list.get(to) + 1);
            map.get(from).add(to);
        }

        public String finger(HashSet<Integer> set){
            StringBuilder sb = new StringBuilder();
            int[] in = new int[26];
            int l = 0;
            int r = 0;
            for(int i = 0; i < 26; i ++){
                if(set.contains(i) && list.get(i) == 0){
                    in[r ++] = i;
                    set.remove(i);
                }
            }
            while(l != r){
                int temp = r;
                while(l != temp){
                    sb.append((char) (in[l] + 'a'));
                    for(int i : map.get(in[l])){
                        list.set(i, list.get(i) - 1);
                        if(list.get(i) == 0){
                            in[r ++] = i;
                        }
                        set.remove(i);
                    }
                    l ++;
                }
            }

            for(int i : set){
                sb.append((char) (i + 'a'));
            }
            return sb.toString();
        }
    }


}
