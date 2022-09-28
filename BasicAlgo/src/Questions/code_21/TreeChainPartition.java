package Questions.code_21;

import java.lang.reflect.Method;

public class TreeChainPartition {

    public static class TreeChain {
        private int[][] tree;
        private int[] father;
        private int[] dep;
        private int[] son;
        private int[] size;
        private int[] top;
        private int[] dfn;
        private int[] tnw;
        private int[] val;

        private int timeStamp;
        private int cnt;
        private int head;

        private int n;


        public TreeChain(int[] father, int[] value) {

            initTree(father, value);


        }

        public void initTree(int[] fa, int[] value) {
            n = father.length + 1;
            cnt = 1;
            tree = new int[n][];
            father = new int[n];
            dep = new int[n];
            son = new int[n];
            size = new int[n];
            top = new int[n];
            dfn = new int[n];
            tnw = new int[n];
            val = new int[n];



        }


    }


}
