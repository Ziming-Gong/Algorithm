package basicAlgo.IndexTree;

// https://leetcode.com/problems/range-sum-query-2d-mutable/submissions/

public class IndexTree2D {
    public static class NumMatrix{
        private int[][] tree;
        private int[][] nums;
        private int COL;
        private int ROW;

        public NumMatrix(int[][] matrix) {
            COL = matrix[0].length;
            ROW = matrix.length;
            tree = new int[ROW + 1][COL + 1];
            nums = new int[ROW + 1][COL + 1];
            for(int i = 0; i < ROW; i ++){
                for(int j = 0; j < COL; j ++){
                    update(i,j,matrix[i][j]);
                }
            }

        }

        public void update(int row, int col, int val) {
            int r = row + 1;
            int c = col + 1;
            int add = val - nums[row][col];
            nums[row][col] = val;
            for(int i = r; i <= ROW; i += i & (-i)){
                for(int j = c; j <= COL; j += j & (-j)){
                    tree[i][j] += add;
                }
            }
        }

        private int sum(int row, int col){
            int sum = 0;
            for(int i = row + 1; i > 0; i -= i & (-i)){
                for( int j = col + 1; j > 0; j -= j & (-j)){
                    sum += tree[i][j];
                }
            }
            return sum;
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            return sum(row2, col2) - sum(row2, col1 -1 ) - sum(row1-1, col2) + sum( row1-1, col1-1);
        }
    }
}
