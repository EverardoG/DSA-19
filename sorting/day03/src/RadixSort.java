import java.sql.SQLIntegrityConstraintViolationException;
import java.util.LinkedList;
import java.util.Stack;

public class RadixSort {

    /**
     * @param n the digit number, 0 is least significant
     * @return
     */
    private static int getNthDigit(int number, int base, int n) {
        return number / ((int) Math.pow(base, n)) % base;
    }


    /**
     * Use counting sort to sort the integer array according to a digit
     *
     * @param b The base used in radix sort
     * @param n The digit number (where 0 is the least significant digit)
     */
    static void countingSortByDigit(int[] A, int b, int n) {
        // array of linked lists for holding counts
        LinkedList<Integer>[] L = new LinkedList[b];
        for (int i = 0; i < b; i++)
            L[i] = new LinkedList<>();

        for (int i : A) {
            // TODO: Extract the relevant digit from i, and add i to the corresponding Linked List.
            int digit = getNthDigit(i,b,n);
            L[digit].add(i);
            
        }
        int j = 0; // index in A to place numbers
        for (LinkedList<Integer> list : L) {
            // TODO: Put all numbers in the linked lists into A
            while (!list.isEmpty()){
                A[j] = list.pollFirst();
                j++;
            }
        }
    }

    /**
     * Runtime: TODO: Express your runtime in terms of n, b, and w
     *
     * n: length of array
     * w: word length of integers A in base b (equal to log base b of k (log_b k) )
     *
     * @param b The base to use for radix sort
     */
    static void radixSort(int[] A, int b) {
        // Calculate the upper-bound for numbers in A
        int k = A[0] + 1;
        for (int i = 1; i < A.length; i++)
            k = (A[i] + 1 > k) ? A[i] + 1 : k;
        // k is the max element of the array
        int w = (int) Math.ceil(Math.log(k) / Math.log(b)); // w = log base b of k, word length of numbers
        // w is how many digits the max element has

        // TODO: Perform radix sort

        for (int place= 0; place <= w; place++){
            countingSortByDigit(A, b, place);
        }


    }

    /**
     * Use counting sort to sort the integer array according to a digit
     *
     * @param b The base used in radix sort
     * @param n The digit number (where 0 is the least significant digit)
     */
    static void specialCountingSortByDigit(int[] A, int b, int n, int start, int end, boolean forwardSort) {
        // array of linked lists for holding counts
        LinkedList<Integer>[] L = new LinkedList[b];
        for (int i = 0; i < b; i++)
            L[i] = new LinkedList<>();


        if (forwardSort){
            // extract all relevant digits like normal
            for (int i = start; i < end; i++){
                // TODO: Extract the relevant digit from i, and add i to the corresponding Linked List.
                int digit = getNthDigit(A[i],b,n);
                L[digit].add(A[i]);
            }

            // put all numbers back into A in normal sorted order
            int j = start; // index in A to place numbers
            for (LinkedList<Integer> list : L) {
                // TODO: Put all numbers in the linked lists into A
                while (!list.isEmpty()){
                    A[j] = list.pollFirst();
                    j++;
                }
            }
        }

        // for negative values, do the same thing
        else{
            // extract all relevant digits like normal
            for (int i = start; i < end; i++){
                // TODO: Extract the relevant digit from i, and add i to the corresponding Linked List.
                int digit = Math.abs(getNthDigit(A[i],b,n));
                L[digit].add(A[i]);
            }

            // put all numbers back into A in normal sorted order
            int j = start; // index in A to place numbers
            for (LinkedList<Integer> list : L) {
                // TODO: Put all numbers in the linked lists into A
                while (!list.isEmpty()){
                    A[j] = list.pollFirst();
                    j++;
                }
            }
        }
    }


    static void specialRadixSort(int[] A, int b) {
        // Calculate the upper-bound for numbers in A
        int k = A[0] + 1;
        for (int i = 1; i < A.length; i++)
            k = (A[i] + 1 > k) ? A[i] + 1 : k;
        // k is the max element of the array
        int w = Math.abs((int) Math.ceil(Math.log(k) / Math.log(b))); // w = log base b of k, word length of numbers
        // w is how many digits the max element has

        // TODO: Perform radix sort

        // separate array into positive and negative parts
        Stack<Integer> postiveSide = new Stack<>();
        Stack<Integer> negativeSide = new Stack<>();
        for (int element: A){
            if (element >= 0){
                postiveSide.push(element);
            }
            else{
                negativeSide.push(element);
            }
        }

        // save the index of the first positive index
        int posInd = negativeSide.size();

        // place negatives back onto the first half, positives onto the second half
        int ind = 0;
        while (!negativeSide.isEmpty()){
            A[ind] = negativeSide.pop();
            ind++;
        }
        while (!postiveSide.isEmpty()){
            A[ind] = postiveSide.pop();
            ind++;
        }



        // perform radixSort on the negative part of the array
        for (int place= 0; place <= w; place++){
            specialCountingSortByDigit(A, b, place, 0, posInd, false);
        }

        // reverse the negative part of the array so that it's sorted properly
        for (int i = 0; i < posInd/2; i++){
            int secondInd = posInd-1-i;
            int temp = A[i];
            A[i] = A[secondInd];
            A[secondInd] = temp;
        }

        // perform normal radixSort on the positive part of the array
        for (int place= 0; place <= w; place++){
            specialCountingSortByDigit(A, b, place, posInd, A.length, true);
        }
    }

}
