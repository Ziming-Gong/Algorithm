package basicAlgo.Print;

public class PrintMatrixSpiralOrder {
    public static void spiralOrderPrint(int[][] matrix) {
        int x = 0;
        int y = 0;
        int m = matrix.length - 1;
        int n = matrix[0].length - 1;
        while (x <= m && y <= n) {
            printEdge(matrix, x++, y++, m--, n--);
        }
    }

    public static void printEdge(int[][] matrix, int x, int y, int m, int n) {
        if (x == m) {
            for (int i = y; i <= n; i++) {
                System.out.print(matrix[x][i] + " ");
            }
        } else if (y == n) {
            for (int i = x; i <= m; i++) {
                System.out.print(matrix[i][y] + " ");
            }
        } else {
            for (int i = y; i < n; i++) {
                System.out.print(matrix[x][i] + " ");
            }
            for (int i = x; i < m; i++) {
                System.out.print(matrix[i][n] + " ");
            }
            for (int i = n; i > x; i--) {
                System.out.print(matrix[m][i] + " ");
            }
            for (int i = m; i > y; i--) {
                System.out.print(matrix[i][y] + " ");
            }
        }
    }


    public static void main(String[] args) {
//        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12},
//                {13, 14, 15, 16}};
        int[][] matrix = {{1, 2, 3, 4, 5, 6, 7, 8}, {9, 10, 11, 12, 13, 14, 15, 16},
                {17, 18, 19, 20, 21, 22, 23, 24}};
        spiralOrderPrint(matrix);

    }
}
