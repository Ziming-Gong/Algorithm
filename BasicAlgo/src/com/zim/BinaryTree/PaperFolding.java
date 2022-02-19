package com.zim.BinaryTree;

public class PaperFolding {
    public static void printAllFolds(int n){
        process(1,n,true);
        System.out.println();
    }
    //凹 down  = true
    //凸 down = false
    public static void process(int i, int n, boolean down){
        if (i > n) {
            return;
        }
        process(i+1, n, true);
        System.out.println(down ? "凹" : "凸");
        process(i+1, n, false);
    }

    public static void main(String[] args) {
        printAllFolds(3);
    }
}
