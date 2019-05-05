public class Regex {

    private static boolean[][] DP;
    private static char[] S;
    private static char[] P;


    public static boolean isMatch(String s, String p) {
        // p - expression
        // s - string

        // initialize our DP array
        DP = new boolean[p.length()+1][s.length()+1];

        // make our strings useful
        S = new char[s.length()+1];
        P = new char[p.length()+1];
        System.arraycopy(s.toCharArray(),0,S,1,s.length());
        System.arraycopy(p.toCharArray(),0,P,1,p.length());

        // store base case (null elements)
        // put true for appropriate stars in first row
        DP[0][0] = true;
        for (int j = 2; j < P.length; j++) {
            if (P[j] == '*' && DP[j-2][0]) {
                DP[j][0] = true;
            }
        }


        // loop through all elements
        // use recurrence relation to fill it all out
        for (int j = 1; j < DP.length; j++){ // expression
            for (int i = 1; i < DP[0].length; i++){ // string
                DP[j][i] = isMatchHelper(i,j);
            }
        }

        // return the index that matters

//        printDP();
        return DP[p.length()][s.length()];
    }

    private static boolean isMatchHelper(int i, int j){
        // i - string
        // j - expression

        // characters match
        if (S[i] == P[j] && DP[j-1][i-1]) {
            return true;
        }

        // dot case where anything goes
        if (P[j] == '.' && DP[j-1][i-1]) {
            return true;
        }

        // star case - ie: hell
        if (P[j] == '*'){
            if (DP[j-1][i]) return true;

            else if (DP[j-2][i]) return true;

            else if (DP[j-1][i-1]) return true;

            else if (S[i] == S[i-1] || P[j-1] == '.'){
                return DP[j][i-1];
            }
        }


        return false;
    }

    private static void printDP(){
        for (int j = 0; j < DP.length; j++){
            System.out.print("[ ");
            for (int i = 0; i < DP[0].length; i++){
                System.out.printf("%s ", btc(DP[j][i]));
            }
            System.out.print("]\n");
        }
        System.out.print("\n");
    }

    private static char btc(boolean bool){
        if (bool) return 'O';
        else return 'X';
    }

}
