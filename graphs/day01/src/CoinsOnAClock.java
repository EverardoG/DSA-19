import java.io.CharConversionException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CoinsOnAClock {

    public static List<char[]> coinsOnAClock(int pennies, int nickels, int dimes, int hoursInDay) {
        // TODO
        List<char[]> result = new ArrayList<>();
        char[] startClock = new char[hoursInDay];
        Arrays.fill(startClock, '.');

        coinHelper(pennies, nickels, dimes, 0, startClock, result);

        return result;
    }

    private static void coinHelper(int pCount, int nCount, int dCount, int nextInd, char[] currentClock, List<char[]> listClocks){
        // base case - filled a clock, add it to the list
        if (!(new String(currentClock).contains("."))){
            listClocks.add(currentClock.clone());
        }

        // recursive case
        // make a list of possible permutations and modify counters based on coins available
        List<Character> coinList = new LinkedList<>();
        if (pCount > 0) {coinList.add('p');}
        if (nCount > 0) {coinList.add('n');}
        if (dCount > 0) {coinList.add('d');}

        // iterate through permutations and recurse
        for (Character coin: coinList){
            // modify the clock but only if there's space
            if (currentClock[nextInd] == '.'){
                currentClock[nextInd] = coin;

                // decrement counter of coin chosen
                if      (coin=='p') pCount--;
                else if (coin=='n') nCount--;
                else if (coin=='d') dCount--;

                // update our index
                int oldInd = nextInd;
                if      (coin=='p') nextInd+= 1;
                else if (coin=='n') nextInd+= 5;
                else if (coin=='d') nextInd+= 10;
                // make it loop because it's a circle
                nextInd %= (currentClock.length);

                // keep going!
                coinHelper(pCount, nCount, dCount, nextInd, currentClock, listClocks);

                // set clock back to how it was
                currentClock[oldInd] = '.';

                // set index back to what it was
                nextInd = oldInd;

                // put the coin back where you got it from, you thief
                if      (coin=='p') pCount++;
                else if (coin=='n') nCount++;
                else if (coin=='d') dCount++;
            }

        }
    }
}
