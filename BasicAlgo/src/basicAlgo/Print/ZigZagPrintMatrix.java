package basicAlgo.Print;

public class ZigZagPrintMatrix {
    public static void printMatrixZigZag(int[][] matrix) {
        int x = 0;
        int y = 0;
        int m = 0;
        int n = 0;
        int endR = matrix.length - 1;
        int endC = matrix[0].length - 1;
        boolean up = false;
        while (x != endR + 1) {
            print(matrix, x, y, m, n, up);
            x = y != endC ? x : x + 1;
            y = y != endC ? y + 1 : y;
            n = m != endR ? n : n + 1;
            m = m != endR ? m + 1 : m;
            up = !up;
        }

    }

    public static void print(int[][] matrix, int x, int y, int m, int n, boolean f) {
        if(f){
            while(x != m + 1){
                System.out.print(matrix[x++][y--] + " ");
            }
        }else {
            while(m != x - 1){
                System.out.print(matrix[m--][n++]+" ");
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        printMatrixZigZag(matrix);

    }
}
