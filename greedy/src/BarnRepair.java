import java.util.*;

public class BarnRepair {
    public static int solve(int M, int[] occupied) {
        // TODO

        Arrays.sort(occupied);

        // check for edge case where M is 1 (you just block all the stalls)
        if (M == 1){
            return (occupied[occupied.length-1] - occupied[0]+1);
        }

        //create a priority queue of costs associated with covering empty stalls
        // count up how many ranges there are, and compare that to max boards (M)
        // also count up how many stalls are blocked
        PriorityQueue<Integer> costs = new PriorityQueue<>();
        int boardsNeeded = 1;
        for (int i = 1; i < occupied.length; i++){
            if (occupied[i] != occupied[i-1]+1){
                boardsNeeded++;
                costs.add(occupied[i] - occupied[i-1]-1);
            }
        }

        // get how many stalls are blocked
        int stallsBlocked = occupied.length;

        // take away boards and block more stalls until we hit M (max boards)
        while (boardsNeeded > M) {
            System.out.println(costs.peek());
            stallsBlocked += costs.poll();
            boardsNeeded--;
        }
//        // make a linked list where each entry is an array of a range
//        LinkedList<LinkedList<Integer>> ranges = new LinkedList<>();
//        LinkedList<Integer> currentRange = new LinkedList<>();
//        currentRange.add(occupied[0]);
//
//        // build up the linked list from the occupied array
//        for (int i = 1; i < occupied.length -1; i++){
//            if (occupied[i] != occupied[i-1] + 1){
//                ranges.add(currentRange);
//                currentRange = new LinkedList<>();
//                currentRange.add(occupied[i]);
//            }
//            else {
//                if (currentRange.size() >= 2){
//                    currentRange.removeLast();
//                }
//                currentRange.add(occupied[i]);
//            }
//        }

        return stallsBlocked;
    }
}