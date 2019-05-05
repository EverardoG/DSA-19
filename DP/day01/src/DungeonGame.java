public class DungeonGame {

    // runtime - time of subproblem O(1), num sub problems O(N^2)
    // space - O(N^2)
    public static int minInitialHealth(int[][] map) {
        // TODO: Your code here

        // initialize memo - same size as map + 1 on each side
        int[][][] DP = new int[map.length+1][map.length+1][2];
        for (int i = 0; i < map.length+1; i++){
            for (int j = 0; j < map.length+1; j++){
                DP[j][i][0] = -1;
            }
        }

        // store ridiculous value for edges (so the algorithm won't choose them)
        for (int i = 0; i < map.length+1; i++){
            DP[i][0][1] = -1000; DP[0][i][1] = -1000;
        }

        // iterate through sub problems
        for (int j = 1; j < map.length+1; j++){
            for (int i = 1; i < map.length+1; i++) {

                // base case - starting square
                if (j == 1 && i == 1) {
                    DP[j][i][0] = map[j - 1][i - 1];
                    DP[j][i][1] = map[j - 1][i - 1];
                }

                // Decide which way to go - down or right
                else {
                    // set values to compare
                    int minDown = DP[j - 1][i][1];
                    int minRight = DP[j][i - 1][1];
                    if (DP[j - 1][i][0] + map[j - 1][i - 1] < minDown) {
                        minDown = DP[j - 1][i][0] + map[j - 1][i - 1];
                    }
                    if (DP[j][i - 1][0] + map[j - 1][i - 1] < minRight) {
                        minRight = DP[j][i - 1][0] + map[j - 1][i - 1];
                    }


                    // pick down or right
                    if (minDown > minRight) {
                        DP[j][i][1] = minDown;
                        DP[j][i][0] = DP[j - 1][i][0] + map[j - 1][i - 1];
                    } else {
                        DP[j][i][1] = minRight;
                        DP[j][i][0] = DP[j][i - 1][0] + map[j - 1][i - 1];
                    }
                }
            }
        }

        return 1 - DP[map.length][map.length][1];
    }

    public static int minInitialHealth2(int[][] map) {
        // TODO: Your code here

        // initialize memo - same size as map + 1 on each side
        int[][][] DP = new int[map.length+1][map.length+1][2];
        for (int i = 0; i < map.length+1; i++){
            for (int j = 0; j < map.length+1; j++){
                DP[j][i][0] = -1;
            }
        }

        // store base cases
        for (int i = 0; i < map.length+1; i++){
            DP[i][0][0] = -1000; DP[0][i][0] = -1000;
            DP[i][0][1] = -1000; DP[0][i][1] = -1000;
        }

        // iterate through subproblems
        for (int j = 1; j < map.length+1; j++){
            for (int i = 1; i < map.length+1; i++){

                if (j == 1 && i == 1) {
                    DP[j][i][0] = map[j-1][i-1];
                    DP[j][i][1] = map[j-1][i-1];
                }

                // move down
                else if (DP[j-1][i][1] > DP[j][i-1][1]){
                    // store current health at that point
                    DP[j][i][0] = DP[j - 1][i][0] + map[j - 1][i - 1];

                    // store lowest health along that path
                    if (DP[j][i][0] < DP[j - 1][i][1]) DP[j][i][1] = DP[j][i][0];
                    else DP[j][i][1] = DP[j - 1][i][1];

                }

                // need more info before choosing
                else if (DP[j-1][i][1] == DP[j][i-1][1]){
                    // move down
                    if (DP[j-1][i][0] >= DP[j][i-1][0]){
                        // store current health at that point
                        DP[j][i][0] = DP[j-1][i][0] + map[j-1][i-1];

                        // store lowest health along that path
                        if (DP[j][i][0] <  DP[j-1][i][1]) DP[j][i][1] = DP[j][i][0];
                        else DP[j][i][1] = DP[j-1][i][1];
                    }

                    // move right
                    else {
                        // store current health at that point
                        DP[j][i][0] = DP[j][i-1][0] + map[j-1][i-1];

                        // store lowest health along that path
                        if (DP[j][i][0] <  DP[j][i-1][1]) DP[j][i][1] = DP[j][i][0];
                        else DP[j][i][1] = DP[j][i-1][1];
                    }

                }

                // move right
                else{
                    // store current health at that point
                    DP[j][i][0] = DP[j][i-1][0] + map[j-1][i-1];

                    // store lowest health along that path
                    if (DP[j][i][0] <  DP[j][i-1][1]) DP[j][i][1] = DP[j][i][0]; // min health went down, store new min health
                    else DP[j][i][1] = DP[j][i-1][1]; // min health stayed the same, carry on

                }
            }
        }

        return 1 - DP[map.length][map.length][1];
    }

    public static int minInitialHealthbackup(int[][] map) {
        // TODO: Your code here

        // initialize memo - same size as map + 1 on each side
        int[][] DP = new int[map.length+1][map.length+1];
        for (int i = 0; i < map.length+1; i++){
            for (int j = 0; j < map.length+1; j++){
                DP[j][i] = -1;
            }
        }

        // store base cases
        for (int i = 0; i < map.length+1; i++){
            DP[i][0] = -1000; DP[0][i] = -1000;
        }

        // iterate through subproblems
        for (int j = 1; j < map.length+1; j++){
            for (int i = 1; i < map.length+1; i++){
                if (j == 1 && i == 1) DP[j][i] = map[j-1][i-1];

                else DP[j][i] = Math.max(DP[j-1][i], DP[j][i-1]) + map[j-1][i-1];
            }
        }

        return 0;
    }
}
