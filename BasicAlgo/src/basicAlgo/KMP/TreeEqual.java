package basicAlgo.KMP;

import basicAlgo.mergesorted.ans;
import sun.reflect.annotation.AnnotationSupport;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class TreeEqual {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static boolean containsTree1(Node big, Node small) {
        if (small == null) {
            return true;
        }
        if (big == null) {
            return false;
        }

        if (isSame(big, small)) {
            return true;
        }
        return containsTree1(big.left, small) || containsTree1(big.right, small);
    }

    public static boolean isSame(Node head1, Node head2) {
        if (head1 == null && head2 != null) {
            return false;
        }
        if (head1 != null && head2 == null) {
            return false;
        }
        if (head1 == null && head2 == null) {
            return true;
        }
        if (head1.value != head2.value) {
            return false;
        }
        return isSame(head1.left, head2.left) && isSame(head1.right, head2.right);
    }

    public static boolean containsTree2(Node big, Node small) {
        if (small == null) {
            return true;
        }
        if (big == null) {
            return false;
        }
        ArrayList<String> bigStr = preSerial(big);
        ArrayList<String> smallStr = preSerial(small);
        String[] str = new String[bigStr.size()];
        String[] match = new String[smallStr.size()];
        for (int i = 0; i < str.length; i++) {
            str[i] = bigStr.get(i);
        }
        for(int i = 0; i < match.length; i ++){
            match[i] = smallStr.get(i);
        }
        return getIndexOf(str, match) != -1;
    }

    public static int getIndexOf(String[] str1, String[] str2){
        if (str1 == null || str2 == null || str1.length < 1 || str1.length < str2.length) {
            return -1;
        }
        int[] next = getNext(str2);
        int x = 0;
        int y = 0;
        while(x < str1.length && y < str2.length){
            if(isEqual(str1[x],str2[y])){
                x ++;
                y ++;
            }else if(next[y] == -1){
                x ++;
            }else{
                y = next[y];
            }
        }
        return y == str2.length? x - y : -1;
    }

    public static int[] getNext(String[] str){
        if(str.length == 1){
            return new int[]{-1};
        }
        int[] next = new int[str.length];
        next[0] = -1;
        next[1] = 0;
        int index = 2;
        int cn  = 0;
        while (index < next.length){
            if(isEqual(str[index -1], str[cn])){
                next[index++] = ++cn;
            }else if(cn == 0){
                 next[index++] = cn;
            }else{
                cn = next[cn];
            }
        }
        return next;
    }

    public static boolean isEqual(String a, String b){
        if(a == null && b == null){
            return true;
        }else{
            if(a == null ||b == null){
                return false;
            }else{
                return a.equals(b);
            }

        }
    }
    public static ArrayList<String> preSerial(Node node) {
        ArrayList<String> ans = new ArrayList<>();
        pre(node, ans);
        return ans;
    }

    public static void pre(Node head, ArrayList<String> ans) {
        if (head == null) {
            ans.add(null);
        } else {
            ans.add(String.valueOf(head.value));
            pre(head.left, ans);
            pre(head.right, ans);
        }
    }

    //for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        int bigTreeLevel = 7;
        int smallTreeLevel = 4;
        int nodeMaxValue = 5;
        int testTimes = 100000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            Node big = generateRandomBST(bigTreeLevel, nodeMaxValue);
            Node small = generateRandomBST(smallTreeLevel, nodeMaxValue);
            boolean ans1 = containsTree1(big, small);
            boolean ans2 = containsTree2(big, small);
            if (ans1 != ans2) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish!");

    }


}
