import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Problems {

    private static PriorityQueue<Integer> minPQ() {
        return new PriorityQueue<>(11);
    }

    private static PriorityQueue<Integer> maxPQ() {
        return new PriorityQueue<>(11, Collections.reverseOrder());
    }

    private static double getMedian(List<Integer> A) {
        double median = (double) A.get(A.size() / 2);
        if (A.size() % 2 == 0)
            median = (median + A.get(A.size() / 2 - 1)) / 2.0;
        return median;
    }

    // Runtime of this algorithm is O(N^2). Sad! We provide it here for testing purposes
    public static double[] runningMedianReallySlow(int[] A) {
        double[] out = new double[A.length];
        List<Integer> seen = new ArrayList<>();
        for (int i = 0; i < A.length; i++) {
            int j = 0;
            while (j < seen.size() && seen.get(j) < A[i])
                j++;
            seen.add(j, A[i]);
            out[i] = getMedian(seen);
        }
        return out;
    }


    /**
     *
     * @param inputStream an input stream of integers
     * @return the median of the stream, after each element has been added
     */

    public static double[] runningMedian(int[] inputStream) {
        double[] runningMedian = new double[inputStream.length];
        // TODO

        // create priority queues for first and second half of sequence
        PriorityQueue<Integer> firstHalf = maxPQ();
        PriorityQueue<Integer> secondHalf = minPQ();

        // iterate through new integers
        for (int i = 0; i < inputStream.length; i++){

            // first integer case
            if (i == 0){
                firstHalf.add(inputStream[i]);
                runningMedian[i] =firstHalf.peek();
            }

            // second integer case
            else if (i == 1){
                if (inputStream[i] >= firstHalf.peek()){
                    secondHalf.add(inputStream[i]);
                }
                else{
                    secondHalf.add(firstHalf.poll());
                    firstHalf.add(inputStream[i]);
                }
                runningMedian[i] = ((double) firstHalf.peek() + (double) secondHalf.peek())/2;
            }

            // general case
            // first insert the new integer into the appropriate queue
            // then make sure the queues are the right sizes
            else{
                if (inputStream[i] >= firstHalf.peek() && inputStream[i] <= secondHalf.peek()){
                    firstHalf.add(inputStream[i]);
                }
                else if(inputStream[i] <= firstHalf.peek()){
                    while (firstHalf.size() > 0 && inputStream[i] <= firstHalf.peek()){
                        secondHalf.add(firstHalf.poll());
                    }
                    firstHalf.add(inputStream[i]);
                }
                else if(inputStream[i] >= secondHalf.peek()){
                    while (secondHalf.size() > 0 && inputStream[i] >= secondHalf.peek()){
                        firstHalf.add(secondHalf.poll());
                    }
                    secondHalf.add(inputStream[i]);
                }

                if (Math.abs(firstHalf.size() - secondHalf.size()) > 1){

                    while(firstHalf.size() > secondHalf.size()){
                        secondHalf.add(firstHalf.poll());
                    }

                    while (secondHalf.size() > firstHalf.size()){
                        firstHalf.add(secondHalf.poll());
                    }

                }

                // figure out where to get running median from
                if (firstHalf.size() == secondHalf.size()){
                    runningMedian[i] = ((double) firstHalf.peek() + (double) secondHalf.peek())/2;
                }
                else if (firstHalf.size() > secondHalf.size()){
                    runningMedian[i] = (double) firstHalf.peek();
                }
                else{
                    runningMedian[i] = (double) secondHalf.peek();
                }
            }

        }


        return runningMedian;
    }

}
