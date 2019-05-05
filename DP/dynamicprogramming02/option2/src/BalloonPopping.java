import java.util.Arrays;

public class BalloonPopping {
    public static int[] balloons;
    public static int[][] DP;

    public static int maxPoints(int[] B){

        // pad the balloons array with 1s
        balloons = new int[B.length+2];
        balloons[0] = 1; balloons[B.length+1] = 1;
        System.arraycopy(B,0,balloons,1,B.length);

        // create memo
        DP = new int[B.length+2][B.length+2];
        for (int j = 0; j < DP.length; j++){
            for (int i = 0; i < DP.length; i++){
                DP[j][i] = -1;
            }
        }

        // call recursive function to fill DP array
        maxPointsHelper(1,balloons.length-2);

        return DP[DP.length-2][1];
    }

    // start - start of substring
    // end - end of substring
    private static int maxPointsHelper(int i, int j){
        // check if memoized
        if (DP[j][i] != -1){
            return DP[j][i];
        }

        // check if impossible
        if (j < i) {// ends before it starts
            DP[j][i] = 0;
            return DP[j][i];
        }

        // check if base case -> i = j
        if (i == j){
            DP[j][i] = balloons[i];
        }

        // iterate through last balloon popped
        // take the max of all iterations
        // store that in the DP array and return it
        int max = 0;
        for (int k = i; k <= j; k++){
            int option = maxPointsHelper(i,k-1) + balloons[i-1] * balloons[k] * balloons[j+1] + maxPointsHelper(k+1,j);
            if (option > max) max = option;
        }

        DP[j][i] = max;
        return DP[j][i];
    }

//    public static int maxPointsC(int[] B){
//        // initialize memo
//        int[][] DP = new int[B.length][];
//        for (int i = 0; i < B.length; i++){
//            DP[i] = new int[i+1];
//        }
//
//        // populate it with -1s
//        for (int j = 0; j < B.length; j++){
//            for (int i = 0; i < DP[j].length; i++){
//                DP[j][i] = -1;
//            }
//        }
//
//        // store base case - diagonal
//        for (int i = 0; i < B.length; i++) DP[i][i] = B[i];
//
//        maxPointsRecurse(B, DP, 0, B.length-1);
//
//        return DP[B.length-1][0];
//    }

//    private static int maxPointsRecurseC(int[] B, int[][] DP ,int i, int j){
//        // check if memoized
//        if (DP[j][i] != -1){
//            return DP[j][i];
//        }
//
//        // choose best option
//        int max = 0;
//        int option;
//        for (int k = i; k <= j; k++){
//            // check edges
//            if (k == i){
//                option = maxPointsRecurse(B,DP,k,k) * getBalloon(B,k-1) + maxPointsRecurse(B,DP,i+1,j);
//            }
//            else if (k == j) {
//                option = maxPointsRecurse(B, DP, i, j - 1) + maxPointsRecurse(B, DP, k, k) * getBalloon(B,k+1);
//            }
//
//            // general case
//            else {
//                option = maxPointsRecurse(B, DP, i, k - 1) + getBalloon(B,i-1) * maxPointsRecurse(B, DP, k, k) * getBalloon(B,j+1) + maxPointsRecurse(B, DP, k + 1, j);
//            }
//
//            if (option > max){
//                max = option;
//            }
//
//        }
//
//        DP[j][i] = max;
//        return DP[j][i];
//    }

//    private static int maxPointsRecurseB(int[] B, int[][] DP ,int i, int j){
//        // check if memoized
//        if (DP[j][i] != -1){
//            return DP[j][i];
//        }
//
//        // choose best option
//        int max = 0;
//        int option;
//        for (int k = i; k <= j; k++){
//
//            // check edges
//            if (k == i){
//                option = maxPointsRecurse(B,DP,i,i) + maxPointsRecurse(B,DP,i+1,j);
//            }
//            else if (k == j){
//                option = maxPointsRecurse(B,DP,i,j-1) + maxPointsRecurse(B,DP,j,j);
//            }
//
//            // general case
//            else {
//                option = maxPointsRecurse(B, DP, i, k - 1) + maxPointsRecurse(B, DP, k, k) + maxPointsRecurse(B, DP, k + 1, j);
//            }
//            if (option > max){
//                max = option;
//            }
//        }
//
//        DP[j][i] = max;
//        return DP[j][i];
//    }

    public static int maxPointsB(int[] B) {
        // TODO

        // initialize memo - beautiful triangular array
        int[][] DP = new int[B.length][];
        for (int i = 0; i < B.length; i++){
            DP[i] = new int[i+1];
        }
        // store base case - diagonal
        DP[0][0] = B[0]*B[1]; DP[B.length-1][B.length-1] = B[B.length-2]*B[B.length-1];
        for (int i = 1; i < B.length-1; i++) DP[i][i] = B[i-1] * B[i] * B[i+1];

        // iterate over subproblems
        for (int j = 0; j < B.length; j++){
            for (int i = j-1; i >= 0; i--){
                if (j == 2 && i == 0){
                    int debug = 0;
                }

                // solve subproblems
                // initialize options
                int option1 = getVal(DP,j-1,i) + getVal(DP,j,j)/getBalloon(B,j-1) * getBalloon(B,i-1);
                int option2 = getVal(DP,j,i+1) + getVal(DP,i,i)/getBalloon(B,i+1) * getBalloon(B, j+1);

                // store the answer
                DP[j][i] = Math.max(option1, option2);
            }
        }

        // return the og answer
        return DP[B.length-1][0];
    }

    private static int getVal(int[][] DP, int j, int i){
        if (j >= 0 && j < DP.length && i >= 0 && i < DP[j].length){
            return DP[j][i];
        }
        return 1;
    }

    private static int getBalloon(int[] B, int i){
        if (i >= 0 && i < B.length){
            return B[i];
        }
        return 1;
    }

}
