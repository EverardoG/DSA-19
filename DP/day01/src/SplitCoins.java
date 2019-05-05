import java.util.ArrayList;
import java.util.*;

public class SplitCoins {

    // Time - O(S*N) sum*numCoins
    // Space - O(S*N) sum*numCoins
    public static int splitCoins(int[] coins) {
        // TODO
        // bottom up approach to solving split coins

        int sum = 0;
        for (int coin: coins){
            sum += coin;
        }

        // initialize our memo, an (S+1, N+1)
        boolean[][] DP = new boolean[sum+1][coins.length+1];

        // put in those good ol base cases
        // first row of coin 0 and all sums should be full of falses - all elements are intialized false
        // first column of all coins and sum - should be full of trues
        for (int n = 0; n < coins.length+1; n++) DP[0][n] = true;

        // fill that boi in
        for (int j = 1; j < sum+1; j++){
            for (int i = 1; i < coins.length+1; i++){
                // case where this coin isn't in bounds of current sum
                if ((j-coins[i-1]) < 0) DP[j][i] = DP[j][i-1];

                // general case
                else DP[j][i] = (DP[j][i-1] || DP[j - coins[i-1]][i-1]);
            }
        }

        // actually go back and find the answer
        // start at s/2, and radiate outwards from there
        int midPoint = sum/2;
        int colDiff = 0;
        boolean addTrue = false;

        // keep going until you hit all columns or until you return an answer
        for (int m = 0; m < sum+1; m++){

            // this path to go right
            if (addTrue){
                for (boolean bool: DP[midPoint + colDiff]){
                    if (bool) return Math.abs( (midPoint + colDiff)*2 - sum );
                }
                addTrue = false;
            }

            // this path to go left
            else { // addTrue == false
                for (boolean bool: DP[midPoint - colDiff]){
                    if (bool) return Math.abs( (midPoint - colDiff)*2 - sum );
                }
                addTrue = true;
                colDiff += 1;
            }
        }

        return -1000;

    }

    public static int splitCoinsBAD(int[] coins) {
        //         this boi right here is some useless stuff unless you want fast and non optimal, then it's your best bet
        //
        // sort coins in descending order
        ArrayList<Integer> coinList = new ArrayList<>(coins.length);
        for (int coin: coins) coinList.add(coin);
        Comparator c = Collections.reverseOrder();
        coinList.sort(c);

        // set up the DP array
        // populate it with answers to all sub problems and main problem
        int[] DP = new int[coins.length];
        DP[0] = coinList.get(0);
        for (int i = 1; i < coins.length; i++) DP[i] = Math.abs(DP[i-1] - coinList.get(i));

        // return the answer to the overall problem
        return DP[DP.length-1];
    }
}
