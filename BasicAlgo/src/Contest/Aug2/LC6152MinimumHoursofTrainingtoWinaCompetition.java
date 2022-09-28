package Contest.Aug2;

public class LC6152MinimumHoursofTrainingtoWinaCompetition {
    public static int minNumberOfHours(int initialEnergy, int initialExperience, int[] energy, int[] experience) {
        int sumE = 0;
        for (int i = 0; i < energy.length; i++) {
            sumE += energy[i];
        }
        int ans = sumE > initialEnergy ? sumE - initialEnergy + 1 : 0;
//        initialExperience += ans;
        for (int i = 0; i < experience.length; i++) {
            if (initialExperience < experience[i]) {
                ans += experience[i] - initialExperience + 1;
                initialExperience += experience[i] - initialExperience + 1;
            }
            initialExperience += experience[i];
        }
        return ans;

    }

    public static void main(String[] args) {
        int initialEnergy = 5;
        int initialExperience = 3;
        int[] energy = new int[]{1, 4, 3, 2};
        int[] experience = new int[]{2, 6, 3, 1};
        System.out.println(minNumberOfHours(initialEnergy, initialExperience, energy, experience));
    }
}
