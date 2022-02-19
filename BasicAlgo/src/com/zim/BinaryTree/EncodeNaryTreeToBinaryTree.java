package com.zim.BinaryTree;

import java.util.List;

public class EncodeNaryTreeToBinaryTree {
    // 本题测试链接：https://leetcode.com/problems/encode-n-ary-tree-to-binary-tree

    public static class Node{
        public int val;
        public List<Node> children;
        public Node(){

        }
        public Node(int val){
            this.val = val;
        }
        public Node(int val, List<Node> children){
            this.val = val;
            this.children = children;
        }
    }


}
