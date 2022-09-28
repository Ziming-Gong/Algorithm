package LeetCodeDays;

import Questions.code_013.GoogleCard;
import com.sun.tools.javah.Gen;
import sun.tools.attach.HotSpotVirtualMachine;
import sun.tools.tree.LessExpression;

public class LC130SurroundedRegions {
    public static void solve(char[][] board) {
        int N = board.length;
        int M = board[0].length;
        UnionFind uf = new UnionFind(board);

        for (int i = 0; i < N; i++) {
            if (board[i][0] == 'O') {
                uf.figure(i, 0);
            }
            if (board[i][M - 1] == 'O') {
                uf.figure(i, M - 1);
            }
        }
        for (int j = 0; j < M; j++) {
            if (board[0][j] == 'O') {
                uf.figure(0, j);
            }
            if (board[N - 1][j] == 'O') {
                uf.figure(N - 1, j);
            }
        }
        uf.make(board);
    }

    public static class UnionFind {
        public int[] arr;
        public int[] father;
        public int[] size;
        public int[] help;
        public int row;
        public int col;

        public UnionFind(char[][] matrix) {
            row = matrix.length;
            col = matrix[0].length;
            arr = new int[row * col];
            father = new int[row * col];
            size = new int[row * col];
            help = new int[row * col];
            initalArr(matrix);
            initalUnion();
        }

        public void initalUnion() {
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (arr[i * col + j] == 1) { // && father[i * col + j] == i * col + j
                        if (i + 1 < row && arr[(i + 1) * col + j] == 1) {
                            union(i, j, i + 1, j);
                        }
                        if (j + 1 < col && arr[i * col + j + 1] == 1) {
                            union(i, j, i, j + 1);
                        }
                    }
                }
            }
        }

        public void initalArr(char[][] matrix) {
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    arr[i * col + j] = matrix[i][j] == 'X' ? 0 : 1;
                    father[i * col + j] = i * col + j;
                    size[i * col + j] = 1;
                }
            }
        }

        public void union(int r1, int c1, int r2, int c2) {
            int fatherA = find(r1, c1);
            int fatherB = find(r2, c2);
            if (size[fatherA] > size[fatherB]) {
                father[fatherB] = fatherA;
                size[fatherA] += size[fatherB];
            } else {
                father[fatherA] = fatherB;
                size[fatherB] += size[fatherA];

            }
        }

        public int find(int r, int c) {
            int index = r * col + c;
            int size = 0;
            while (father[index] != index) {
                help[size++] = index;
                index = father[index];
            }
            while (size > 0) {
                father[help[--size]] = index;
            }
            return index;
        }

        public void figure(int r, int c) {
            int father = find(r, c);
            arr[father] = 3;
        }

        public void make(char[][] ans) {
            // char[][] ans = new char[row][col];
            for (int i = 0; i < row; i++) {
                for(int j = 0; j < col; j ++){
                    if(arr[i * col + j] == 0){
                        ans[i][j] = 'X';
                    }else{
                        int father = find(i, j);
                        if(arr[father] == 3){
                            ans[i][j] = 'O';
                        }else{
                            ans[i][j] = 'X';
                        }
                    }

                }
            }
            // return ans
        }

    }

    public static char[][] generate(int N, int M){
        char[][] ans = new char[N][M];
        for(int i = 0; i < N; i ++){
            for(int j = 0; j < M; j ++){
                ans[i][j] = Math.random() < 0.3 ? 'X' : 'O';
            }
        }
        return ans;
    }

    public static char[][] copy(char[][] m){
        char[][] ans = new char[m.length][m[0].length];
        for(int i = 0; i < m.length; i ++){
            for(int j = 0; j < m[0].length; j ++){
                ans[i][j] = m[i][j];
            }
        }
        return ans;
    }

    public static void solve1(char[][] board) {
        for(int row = 0; row < board.length; row++) {
            for(int col = 0; col < board[0].length; col++) {
                // only consider those 'O's who are the the edge of the board, because only 'O's
                // that are somehow connected to another 'O' that is at the edge of the board,
                // can be saved from being flipped
                if(board[row][col] == 'O' &&
                        (row == 0 || col == 0 || row == board.length - 1 || col == board[0].length - 1)) {
                    board = markAdjecentCells(board, row, col);
                }
            }
        }

        // all the temporary 'o' are save, everything else gets flipped
        for(int row = 0; row < board.length; row++) {
            for(int col = 0; col < board[0].length; col++) {
                if(board[row][col] == 'o') board[row][col] = 'O';
                else board[row][col] = 'X';
            }
        }
    }

    private static char[][] markAdjecentCells(char[][] board, int row, int col) {
        // out of bounds
        if(row < 0 || col < 0 || row >= board.length || col >= board[0].length || board[row][col] != 'O') {
            return board;
        } else {
            board[row][col] = 'o'; // temporary mark
            board = markAdjecentCells(board, row + 1, col);
            board = markAdjecentCells(board, row - 1, col);
            board = markAdjecentCells(board, row, col + 1);
            board = markAdjecentCells(board, row, col - 1);
            return board;
        }
    }

    public static boolean isSame(char[][] a, char[][] b){
        for(int i = 0; i < a.length; i ++){
            for(int j = 0; j < a[0].length; j ++){
                if(a[i][j] != b[i][j]){
                    return false;
                }
            }
        }
        return true;
    }
    public static void print(char[][] a){
        for(int i = 0; i < a.length; i ++){
            for(int j = 0; j < a[0].length; j ++){
                System.out.printf(a[i][j] + ", ");
            }
            System.out.println();
        }
    }
    public static void print1(char[][] a){
        for(int i = 0; i < a.length; i ++){
//            System.out.print("{");
            for(int j = 0; j < a[0].length; j ++){
                System.out.printf("'" + a[i][j]+ "'" + ", ");
            }
            System.out.print("},{");
        }
    }

    public static void main(String[] args) {
        char[][] arr = {{'O', 'O', 'O', },{'O', 'O', 'O', },{'O', 'O', 'X', },{'O', 'X', 'O', }};
        solve(arr);
        int N =  4;
        int M = 3;
        int testTime = 1000000;
        for(int i = 0; i <= testTime; i ++){
            char[][] a= generate(N, M);
            char[][] b = copy(a);
            char[][] c = copy(a);
//            print(a);
            solve(a);
            solve1(b);
//            System.out.println();
            if(!isSame(a,b)){
                print1(c);
                System.out.println();
                print(c);
                System.out.println();
                print(a);
                System.out.println("oops");
                break;
            }

        }
        System.out.println("test end");
    }
}
