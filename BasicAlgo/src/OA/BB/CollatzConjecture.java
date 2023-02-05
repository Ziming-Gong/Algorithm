package OA.BB;

public class CollatzConjecture {
    public static int getSteps(int N) {
        int step = 0;
        while(N != 1){
            if(N % 2 == 0){
                N /= 2;
            }else{
                N = N * 3 + 1;
            }
            step ++;
        }
        return step;
    }

    public static void main(String[] args) {
        int maxN = 100;
        for(int i = 2; i <= maxN; i ++){
            System.out.println(i + " : " + getSteps(i));
        }
    }
}
