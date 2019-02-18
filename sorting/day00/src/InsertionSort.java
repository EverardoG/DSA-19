
public class InsertionSort extends SortAlgorithm {
    /**
     * Use the insertion sort algorithm to sort the array
     *
     * TODO
     * Best-case runtime: O(N) - just iterates through the array that's already sorted
     * Worst-case runtime: O(N^2) - iterates through an array sorted in inverse order
     * Average-case runtime: O(N^2) - just normally what it do
     *
     * Space-complexity: O(1) - sorts in place, no extra storage
     */

    @Override
    public int[] sort(int[] array) {
        // TODO: Implement this!

        int p;
        for (int i = 1; i < array.length; i++) {
            p = i;
            while (p > 0 && array[p-1] > array[p]){
                swap(array, p-1, p);
                p--;
        }

    }

        return array;
    }
}
