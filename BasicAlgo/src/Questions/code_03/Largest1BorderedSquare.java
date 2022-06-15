package Questions.code_03;

public class Largest1BorderedSquare {
    public int largest1BorderedSquare1(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] right = generateRight(grid);
        int[][] down = generateDown(grid);
        // print(right);
        // print(down);
        int edge = 0;
        for(int size = Math.min(n , m );size >  0; size --){
            if(has(size, right, down)){
                return size * size;
            }
        }
        return 0;
    }

    public boolean has(int size, int[][] right, int[][] down){
        for(int i = 0; i < right.length - size + 1; i ++){
            for(int j = 0; j < right[0].length - size + 1; j ++){
                if(right[i][j] >= size && down[i][j] >= size && right[i + size - 1][j] >= size && down[i][j + size - 1] >= size){
                    return true;
                }
            }
        }
        return false;
    }

    public int largest1BorderedSquare2(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] right = generateRight(grid);
        int[][] down = generateDown(grid);
        // print(right);
        // print(down);
        int edge = 0;
        for(int i = 0; i < n; i ++){
            for(int j = 0; j < m; j ++){
                for(int border = 1; border <= Math.min(n - i, m - j); border ++){
                    if(right[i][j] >= border && down[i][j] >= border && right[i+border-1][j] >= border && down[i][j+border-1] >= border){
                        edge = Math.max(edge, border);
                    }
                }
            }
        }
        return edge * edge;
    }

    public int[][] generateRight(int[][] grid){
        int n = grid.length;
        int m = grid[0].length;
        int[][] res = new int[n][m];
        for(int i = 0; i < n; i ++){
            int sum = 0;
            for(int j = m - 1; j >= 0; j --){
                if(grid[i][j] == 1){
                    res[i][j] = ++ sum;
                }else{
                    sum = 0;
                    res[i][j] = 0;
                }
            }
        }
        return res;
    }

    public int[][] generateDown(int[][] grid){
        int n = grid.length;
        int m = grid[0].length;
        int[][] res = new int[n][m];
        for(int j = 0; j < m; j ++){
            int sum = 0;
            for(int i = n-1; i >= 0; i --){
                if(grid[i][j] == 1){
                    res[i][j] = ++ sum;
                }else{
                    res[i][j] = 0;
                    sum = 0;
                }
            }
        }
        return res;
    }
    public void print(int[][] matrix){
        for(int i = 0; i < matrix.length; i ++){
            for(int j = 0; j < matrix[0].length; j ++){
                System.out.print(matrix[i][j] + ", ");
            }
            System.out.println();
        }
    }
}
