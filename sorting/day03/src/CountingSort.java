public class CountingSort {

    /**
     * Use counting sort to sort non-negative integer array A.
     * Runtime: TODO
     * Best: O(N)
     * Average: O(N)
     * Worst: O(N)
     *
     * runtime is actually O(k+N) where k is max element
     *
     * Space: O(k)
     *
     * k: maximum element in array A
     */
    static void countingSort(int[] A) {
        // TODO

        // get max
        int k=0; // only non negative ints in A
        for (int element: A){
            if (element > k){
                k = element;
            }
        }

        // build up array with max number dictating size
        int[] counts = new int[k+1];

        // count up how many of each element are in A
        for (int e : A){
            counts[e]++;
        }


        // back fill original array with counts
        int i= 0;
        for (int j = 0; j < k+1; j++){
            while (counts[j] > 0){
                A[i] = j;
                counts[j]--;
                i++;
            }
        }

    }

}
