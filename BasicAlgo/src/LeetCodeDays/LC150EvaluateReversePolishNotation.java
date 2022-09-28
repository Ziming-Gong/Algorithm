package LeetCodeDays;

public class LC150EvaluateReversePolishNotation {
    public int evalRPN(String[] strs) {
        int N = strs.length;
        int[] stack = new int[N];
        int index = 0;
        for(String cur : strs){
            if(isNumber(cur)){
                stack[index ++] = Integer.valueOf(cur);
            }else{
                int p2 = stack[--index];
                int p1 = stack[--index];
                char c = cur.toCharArray()[0];
                int res;
                if(c == '+'){
                    res = p1 + p2;
                }else if(c == '-'){
                    res = p1 - p2;
                }else if (c == '*'){
                    res = p1 * p2;
                }else{
                    res = p1 / p2;
                }
                stack[index ++] = res;
            }
        }
        return stack[0];


    }
    public boolean isNumber(String str){
        if(str.length() > 1){
            return true;
        }
        char c = str.charAt(0);
        return  c != '-' || c != '+' || c != '*' || c != '/';
    }
}
