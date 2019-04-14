import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Permutations {

    public static List<List<Integer>> permutations(List<Integer> A) {
        // TODO
        // create a list of all the numbers we're working with
        LinkedList<Integer> unused = new LinkedList<Integer>();
        for (Integer num: A){unused.add(num);}

        // create an empty list to put all permutations in
        List<List<Integer>> permutations = new LinkedList<>();

        // call our helper function to recursively find all permutations
        LinkedList<Integer> emptyList = new LinkedList<Integer>();
        permutationsHelper(emptyList, unused, permutations);
        return permutations;
    }

    // helper function
    public static void permutationsHelper(LinkedList<Integer> current, LinkedList<Integer> unused, List<List<Integer>> permutations){
        // base case (all numbers are added)
        if (unused.isEmpty()){
            permutations.add(new LinkedList<>(current));
        }

        // recursive (add more numbers to the solution)
        // explore this branch completely and recursively before moving on to the next one
        for (Integer num: new LinkedList<>(unused)){

            // transfer current number from unused to working with it in current
            // this is going down one branch of hte permutations tree
            current.add(num);
            unused.remove(num);

            // recursively call this function on this branch
            permutationsHelper(current, unused, permutations);

            // transfer current number back into the unused space
            // this backs us up out of a branch
            current.remove(num);
            unused.add(num);
        }
    }
}
;