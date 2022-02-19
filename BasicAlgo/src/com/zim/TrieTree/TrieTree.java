package com.zim.TrieTree;

import java.util.HashMap;

public class TrieTree {
    public static class Node1{
        public int pass;
        public int end;
        public Node1[] nexts;

        public Node1(){
            pass = 0;
            end = 0;
            nexts = new Node1[26];
        }
    }

    public static class TrieTree1{
        private Node1 root;

        public TrieTree1(){
            root = new Node1();
        }

        public void insert(String word) {
            if (word == null) {
                return;
            }
            char[] str = word.toCharArray();
            Node1 node = root;
            node.pass++;
            int path = 0;
            for (int i = 0; i < str.length; i++) { // 从左往右遍历字符
                path = str[i] - 'a'; // 由字符，对应成走向哪条路
                if (node.nexts[path] == null) {
                    node.nexts[path] = new Node1();
                }
                node = node.nexts[path];
                node.pass++;
            }
            node.end++;
        }
        public void delete(String word){
            if(search(word) != 0){
                Node1 node = root;
                char[] chars = word.toCharArray();
                int path = 0;
                node.pass--;
                for(int i = 0; i < chars.length; i++){
                    path = chars[i] - 'a';
                    if( -- node.nexts[path].pass == 0){
                        node.nexts[path] = null;
                        return;
                    }
                    node = node.nexts[path];
                }
                node.end--;
            }
        }
        public int search(String word){
            if( word == null){
                return 0;
            }
            Node1 node = root;
            char[] chars = word.toCharArray();
            int index = 0;
            for( int i = 0; i < chars.length; i++){
                index = chars[i] - 'a';
                if( node.nexts[index] == null){
                    return 0;
                }
                node = node.nexts[index];
            }
            return node.end;
        }
        public int prefixNumber(String word){
            if(word == null){
                return 0;
            }
            char[] chars = word.toCharArray();
            int index = 0;
            Node1 node = root;
            for( int i = 0; i < chars.length; i++){
                index = chars[i] -'a';
                if( node.nexts[index] == null){
                    return 0;
                }
                node = node.nexts[index];
            }
            return node.pass;
        }
    }
    public static class Node2{
        public int pass;
        public int end;
        public HashMap<Integer, Node2> map;

        public Node2(){
            pass = 0;
            end = 0;
            map = new HashMap<>();
        }
    }
    public static class TrieTree2{
        public Node2 root;

        public TrieTree2(){
            root = new Node2();
        }

        public void insert(String word){
            if(word == null){
                return;
            }
            int path = 0;
            char[] chars = word.toCharArray();
            Node2 node = root;
            node.pass ++;
            for( int i = 0; i < chars.length; i++){
                path = (int) chars[i];
                if(!node.map.containsKey(path)){
                    node.map.put(path, new Node2());
                }
                node = node.map.get(path);
                node.pass++;
            }
            node.end ++;
        }
        public void delete(String word){
            if(search(word) != 0){
                Node2 node = root;
                node.pass--;
                char[] chars = word.toCharArray();
                int index = 0;
                for( int i = 0; i < chars.length; i++){
                    index = (int) chars[i];
                    if(--node.map.get(index).pass == 0){
                       node.map.remove(index);
                       return;
                    }
                    node = node.map.get(index);
                }
                node.end --;
            }
        }

        public int search(String word){
            if(word == null){
                return 0;
            }
            Node2 node = root;
            char[] chars = word.toCharArray();
            int index = 0;
            for( int i = 0; i < chars.length; i++){
                index = (int)chars[i];
                if( !node.map.containsKey(index)){
                    return 0;
                }
                node = node.map.get(index);
            }
            return node.end;
        }

        public int prefixNumber(String pre){
            if(pre == null){
                return 0;
            }
            Node2 node = root;
            char[] chars = pre.toCharArray();
            int index = 0;
            for (int i = 0; i < chars.length; i++){
                index = (int) chars[i];
                if(!node.map.containsKey(index)){
                    return 0;
                }
                node = node.map.get(index);
            }
            return node.pass;
        }
    }
    public static class Right{
        public HashMap<String, Integer> box;
        public Right(){
            box = new HashMap<>();
        }

        public void insert(String word){
            if(! box.containsKey(word)){
                box.put(word, 1);
            } else{
                box.put(word, box.get(word) + 1);
            }
        }

        public void delete(String word){
            if( box.containsKey(word)){
                if(box.get(word) == 1){
                    box.remove(word);
                }else {
                    box.put(word, box.get(word) - 1);
                }
            }
        }

        public int search(String word){
            if(!box.containsKey(word)){
                return 0;
            }else {
                return box.get(word);
            }
        }
        public int prefixNumber(String pre){
            int count = 0;
            for( String str : box.keySet()){
                if(str.startsWith(pre)){
                    count += box.get(str);
                }
            }
            return count;
        }
    }
    // for test
    public static String generateRandomString(int strLen) {
        char[] ans = new char[(int) (Math.random() * strLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            int value = (int) (Math.random() * 6);
            ans[i] = (char) (97 + value);
        }
        return String.valueOf(ans);
    }

    // for test
    public static String[] generateRandomStringArray(int arrLen, int strLen) {
        String[] ans = new String[(int) (Math.random() * arrLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = generateRandomString(strLen);
        }
        return ans;
    }

    public static void main(String[] args) {
        int arrLen = 100;
        int strLen = 20;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            String[] arr = generateRandomStringArray(arrLen, strLen);
            TrieTree1 trie1 = new TrieTree1();
            TrieTree2 trie2 = new TrieTree2();
            Right right = new Right();
            for (int j = 0; j < arr.length; j++) {
                double decide = Math.random();
                if (decide < 0.25) {
                    trie1.insert(arr[j]);
                    trie2.insert(arr[j]);
                    right.insert(arr[j]);
                } else if (decide < 0.5) {
                    trie1.delete(arr[j]);
                    trie2.delete(arr[j]);
                    right.delete(arr[j]);
                } else if (decide < 0.75) {
                    int ans1 = trie1.search(arr[j]);
                    int ans2 = trie2.search(arr[j]);
                    int ans3 = right.search(arr[j]);
                    if (ans1 != ans2 || ans2 != ans3) {
                        System.out.println("Oops!");
                        System.out.println(arr);
                        System.out.println(ans1);
                        System.out.println(ans2);
                        System.out.println(ans3);
                        break;
                    }
                } else {
                    int ans1 = trie1.prefixNumber(arr[j]);
                    int ans2 = trie2.prefixNumber(arr[j]);
                    int ans3 = right.prefixNumber(arr[j]);
                    if (ans1 != ans2 || ans2 != ans3) {
                        System.out.println("Oops!");
                    }
                }
            }
        }
        System.out.println("finish!");

    }




















}
