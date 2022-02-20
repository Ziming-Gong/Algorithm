package basicAlgo.UnionFind;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

//leetCode https://leetcode.com/problems/number-of-islands/
public class NumberOfIslands {
    //递归方法
    public int numIslands1(char[][] grid) {
        if( grid == null){
            return 0;
        }

        int ans = 0;;
        for( int i =0; i < grid.length; i ++ ){
            for( int j = 0; j < grid[0].length; j ++){
                if(grid[i][j] == '1'){
                    ans++;
                    process(grid,i,j);
                }
            }
        }
        return ans;
    }
    public static void process(char[][] grid, int i, int j){
        if(i < 0 || i == grid.length || j < 0 || j == grid[0].length || grid[i][j] !='1'){
            return;
        }

        grid[i][j] = 0;
        process(grid, i + 1, j);
        process(grid, i - 1, j);
        process(grid, i , j + 1);
        process(grid, i, j - 1);
        Stack<Integer> stack = new Stack<>();
    }

    //并查集HashMap形式
    public int numIslands2(char[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        Dot[][] dot= new Dot[row][col];
        ArrayList<Dot> dotList = new ArrayList<>();
        for( int i = 0; i < row; i ++){
            for(int j = 0; j < col; j ++){
                if(grid[i][j] == '1'){
                    dot[i][j] = new Dot();
                    dotList.add(dot[i][j]);
                }
            }
        }

        UnionFind uf = new UnionFind(dotList);

        for(int i = 1; i < col; i ++){
            if(grid[0][i-1] == '1' && grid[0][i] == '1'){
                uf.union(dot[0][i],dot[0][i-1]);
            }
        }

        for(int i = 1; i < row; i ++){
            if(grid[i-1][0] == '1' && grid[i][0] == '1'){
                uf.union(dot[i-1][0],dot[i][0]);
            }
        }

        for( int i = 1; i < row; i ++){
            for( int j = 1; j < col; j ++){
                if(grid[i][j] == '1' && grid[i-1][j] == '1'){
                    uf.union(dot[i][j],dot[i-1][j]);
                }
                if(grid[i][j] == '1' && grid[i][j-1] == '1'){
                    uf.union(dot[i][j],dot[i][j-1]);
                }
            }
        }
        return uf.sets();
    }
    public static class Dot{

    }
    public static class Node<V>{
        public V val;
        public Node(V val){
            this.val = val;
        }
    }
    public static class UnionFind<V>{
        public HashMap<V,Node<V>> nodes;
        public HashMap<Node<V>,Node<V>> parents;
        public HashMap<Node<V>,Integer> sizeMap;


        public UnionFind(List<V> values) {
            nodes = new HashMap<>();
            parents = new HashMap<>();
            sizeMap = new HashMap<>();
            for (V cur : values) {
                Node node = new Node(cur);
                nodes.put(cur, node);
                parents.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        public Node<V> findFather(V a){
            Node<V> cur = nodes.get(a);
            Stack<Node> stack = new Stack<>();
            while( cur != parents.get(cur)){
                stack.add(cur);
                cur = parents.get(cur);
            }
            while(!stack.isEmpty()){
                parents.put(stack.pop(), cur);
            }
            return cur;
        }
        public void union(V a, V b){
            Node<V> fatherA = findFather(a);
            Node<V> fatherB = findFather(b);
            if( fatherA != fatherB){
                int sizeA = sizeMap.get(fatherA);
                int sizeB = sizeMap.get(fatherB);
                Node<V> big = sizeA >= sizeB ? fatherA : fatherB;
                Node<V> small = big == fatherA ? fatherB : fatherA;
                parents.put(small, big);
                sizeMap.put(big, sizeA + sizeB);
                sizeMap.remove(small);
            }
            // if( sizeMap.get(fatherA) <= sizeMap.get(fatherB)){
            //     parents.push(fatherA, fatherB);
            //     sizeMap.push(fatherB, sizeMap.get(fatherA) + sizeMap.get(fatherB));
            //     sizeMap.remove(fatherA);
            // } else {
            //     parents.push(fatherB, fatherA);
            //     sizeMap.push(fatherA, sizeMap.get(fatherB) + sizeMap.get(fatherA));
            //     sizeMap.remove(fatherB);
            // }
        }
        public int sets(){
            return sizeMap.size();
        }
    }

    public int numIslands(char[][] grid) {
        int row = grid.length;
        int col = grid[0].length;

        UnionFind2 uf = new UnionFind2(grid);
        System.out.print(uf.parents);
        for( int i = 1; i < row; i ++){
            if( grid[i-1][0] == '1' && grid[i][0] == '1'){
                uf.union(i-1,0,i,0);
            }
        }
        for( int j = 1; j < col; j ++){
            if(grid[0][j-1] == '1' && grid[0][j] == '1'){
                uf.union(0,j-1,0,j);
            }
        }
        for(int i = 1; i < row; i ++){
            for(int j = 1; j < col; j ++){
                if(grid[i][j] == '1' && grid[i-1][j] == '1'){
                    uf.union(i,j,i-1,j);
                }
                if(grid[i][j] == '1' && grid[i][j-1] == '1'){
                    uf.union(i,j,i,j-1);
                }
            }
        }
        return uf.sets();


    }
    public static class UnionFind2{
        public int[] parents;
        public int[] size;
        public int[] help;
        public int sets;
        public int col;

        public UnionFind2(char[][] grid){
            col = grid[0].length;
            int row = grid.length;
            int listSize = col * row;
            int sets = 0;
            parents = new int[listSize];
            size = new int[listSize];
            help = new int[listSize];

            for(int i = 0; i < row; i ++){
                for(int j = 0; j < col; j ++ ){
                    if(grid[i][j] == '1'){
                        int index = getIndex(i,j);
                        parents[index] = index;
                        size[index] = 1;
                        sets++;
                    }
                }
            }
        }
        public int getIndex(int i, int j){
            return i * col + j;
        }

        private int findFather(int index){
            int cur = index;
            int i = 0;
            while( cur != parents[cur]){
                help[i++] = cur;
                cur = parents[cur];
            }
            for( i-- ; i > 0; i --){
                int j = help[i];
                parents[j] = cur;
            }
            // while( i > 0){
            //     int j = help[--i];
            //     parents[j] = cur;
            // }
            return cur;
        }

        public  void union (int ai, int aj, int bi,int bj){
            //int[ai][bi] and int[bi][bj]
            int indexA = getIndex(ai,aj);
            int indexB = getIndex(bi,bj);
            int fatherA = findFather(indexA);
            int fatherB = findFather(indexB);
            if( fatherA != fatherB){
                if(size[fatherA] >= size[fatherB]){
                    size[fatherA] += size[fatherB];
                    parents[fatherB] = fatherA;
                }else{
                    size[fatherB] += size[fatherA];
                    parents[fatherA] = fatherB;
                }
                // int sizeA = size[fatherA];
                // int sizeB = size[fatherB];
                // int big = sizeA >= sizeB ? fatherA : fatherB;
                // int small = big == fatherA ? fatherB : fatherA;
                // parents[small] = big;
                // size[big] = small + big;
                sets --;
            }
        }

        public int sets(){
            return sets;
        }










    }
}
