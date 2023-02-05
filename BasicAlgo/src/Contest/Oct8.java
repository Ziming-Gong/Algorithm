package Contest;

import jdk.management.resource.internal.inst.SocketOutputStreamRMHooks;

import java.util.Stack;

public class Oct8 {
    public static void main(String[] args) {
        String str = "bydizfve";
        System.out.println(robotWithString(str));
    }

    public static String robotWithString(String s) {
        char[] str = s.toCharArray();
        int n = str.length;
        int[][] map = new int[n][26];
        for(int i = 0; i < 26; i ++){
            map[n - 1][i] = -1;
        }
        map[n - 1][str[n - 1] - 'a'] = n - 1;
        for(int i = n - 1; i >= 0; i --){
            for(int j = 0; j < 26; j ++){
                map[i][j] = map[i][j];
            }
            map[i][str[i] - 'a'] = i;
        }

        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        char a = 'a';
        int index = 0;
        while(index < n){
            while(!stack.isEmpty() && stack.peek() == a){
                sb.append(stack.pop());
            }
            while(index < n && map[index][a - 'a'] != -1){
                stack.push(str[index]);
                if(str[index] == a){
                    sb.append(stack.pop());
                }
                index ++;
            }
            a += 1;
        }
        while(!stack.isEmpty()){
            sb.append(stack.pop());
        }
        return sb.toString();


    }

}
