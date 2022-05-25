package basicAlgo.Print;

public class RotateMatrix {
    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void rotate(int[][] matrix) {
        int x = 0;
        int y = 0;
        int m = matrix.length - 1;
        int n = matrix[0].length - 1;
        while (x < m) {
            rotateEdge(matrix, x++, y++, m--, n--);
        }
    }

    public static void rotateEdge(int[][] matrix, int x, int y, int m, int n) {
        for (int i = 0; i < m - x; i++) {
            int temp = matrix[x][y + i];
            matrix[x][y + i] = matrix[m - i][y];
            matrix[m - i][y] = matrix[m][n - i];
            matrix[m][n - i] = matrix[x + i][n];
            matrix[x + i][n] = temp;
        }

    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        printMatrix(matrix);
        rotate(matrix);
        System.out.println("=========");
        printMatrix(matrix);

    }
}
