public class EditDistance {

    // Time - subproblem: O(1), total: O(a.len * b.len)
    // Space - O(a.len * b.len)
    public static int minEditDist(String a, String b) {
        // TODO: Your code here

        // initialize memo
        int[][] DP = new int[a.length() + 1][b.length() + 1];
        for (int j = 0; j < a.length()+1; j++){
            for (int i = 0; i < b.length()+1; i++) DP[j][i] = -1;
        }

        // store base case
        for (int n = 0; n < a.length()+1; n++) DP[n][0] = n;
        for (int n = 0; n < b.length()+1; n++) DP[0][n] = n;

        // call private recursive function
        distRecurse(a, b, b.length(), a.length() ,DP);

        return DP[a.length()][b.length()];
    }

    private static int distRecurse(String a, String b, int i, int j, int[][] DP){
        if (DP[j][i] != -1) return DP[j][i];

        int option1 = distRecurse(a, b, i-1, j, DP) + 1;
        int option2 = distRecurse(a, b, i ,j-1, DP) + 1;
        int option3 = distRecurse(a,b, i-1, j-1, DP);
        if (a.charAt(j-1) != b.charAt(i-1)) option3 ++;

        DP[j][i] = Math.min(Math.min(option1, option2), option3);

        return DP[j][i];
    }

}
