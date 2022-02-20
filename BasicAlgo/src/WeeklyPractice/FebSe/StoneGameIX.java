package WeeklyPractice.FebSe;


//https://leetcode.com/problems/stone-game-ix/
public class StoneGameIX {
    public boolean stoneGameIX(int[] stones) {
        int[] count = new int[3];
        for(int num : stones){
            count[num%3] ++;
        }
        return count[0] % 2 == 0 ? count[1] != 0 && count[2] != 0 : Math.abs(count[1] - count[2]) > 2;
    }
}
