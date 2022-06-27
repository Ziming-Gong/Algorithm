package Contest.June4;

public class XMatrix {
    public static boolean checkXMatrix(int[][] grid) {
        int N = grid.length;
        for(int i = 0; i < N; i ++){
            for(int j = 0; j < N; j ++){
                if(j == i && grid[i][j] == 0){
                    return false;
                }else if (j == N - i - 1 && grid[i][j] == 0){
                    return false;
                }else if(j != i && j != N - i - 1 &&grid[i][j] != 0){
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] arr = {{2, 0, 0, 1}, {0, 3, 1, 0}, {0, 5, 2, 0}, {4, 0, 0, 2}};
        System.out.println(checkXMatrix(arr));
    }
}
