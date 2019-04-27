public class DiceRollSum {

    // Runtime: TODO
    // Space: TODO
    public static int diceRollSum(int N) {
        // TODO

        // edge cases:
        if (N == 0){
            return 1;
        }

        else if (N==1){
            return 1;
        }

        int[] DP = new int[N+1];
        for (int i = 0; i < DP.length; i++){
            DP[i] = -1;
        }
        DP[0] = 1;
        DP[1] = 1;
        diceRollSumHelper(N, DP);
        return DP[N];
    }

    // i - number of die rolls
    // DP - nice 1D array for memoizing
    private static int diceRollSumHelper(int i, int[] DP){
        // base case 1: less than zero, just return nothing (literally impossible to generate sequence for this)
        if (i<0){
            return 0;
        }

        // base case 2: its memoized, return the memoized info
        else if (DP[i] != -1) return DP[i];

        // recursive case: keep rolling dice
        else {
            int temp = 0;
            for (int n = 1; n <= 6; n++){
                temp += diceRollSumHelper(i-n, DP);
            }

            DP[i] = temp;
            return DP[i];
        }


    }
}
