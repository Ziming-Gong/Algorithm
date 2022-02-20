package WeeklyPractice.FebSe;

//leetcode:https://leetcode.com/problems/robot-bounded-in-circle/
public class RobotBoundedInCircle {
    public boolean isRobotBounded(String instructions) {
        int row = 0;
        int col = 0;
        int direction = 0;
        for( char c : instructions.toCharArray()){
            if(c == 'L'){
                direction = left(direction);
            }else if(c == 'R'){
                direction = right(direction);
            }else{
                row = row(direction,row);
                col = col(direction,col);
            }
        }
        return (row == 0 && col == 0) || direction != 0;
    }
    public static int left(int direction){
        return direction == 0 ? 3 : direction -1;
    }
    public static int right(int direction){
        return direction == 3 ? 0 : direction + 1;
    }
    public static int row( int direction, int row){
        return (direction == 1 || direction == 3) ? row : (row + (direction == 0 ? 1 : -1));
    }
    public static int col (int direction, int col){
        return (direction == 2 || direction == 0) ? col : (col + (direction == 1 ? 1 : -1));
    }
}
