public class HeapSort extends SortAlgorithm {
    int size;
    int[] heap;

    private int parent(int i) {
        return (i-1) / 2;
    }

    private int leftChild(int i) {
        return 2*i + 1;
    }

    private int rightChild(int i) {
        return 2 * (i + 1);
    }

    // Check children, and swap with larger child if necessary.
    // Corrects the position of element indexed i by sinking it.
    // Use either recursion or a loop to then sink the child

    // O(lgN) because that's the depth of the heap
    public void sink(int i) {
        // TODO

        // figure out max child and index for swapping
        int maxInd;
        if (leftChild(i) < size && rightChild(i) < size){
            if (heap[leftChild(i)] > heap[rightChild(i)]){
                maxInd = leftChild(i);
            }
            else{
                maxInd = rightChild(i);

            }
        }
        else if (leftChild(i) < size){
            maxInd = leftChild(i);
        }
        else if (rightChild(i) < size){
            maxInd = rightChild(i);
        }
        else{
            return;
        }

        // sink if appropriate
        if (heap[i] < heap[maxInd]){
            swap(heap, i,maxInd);

            // try sinking further
            sink(maxInd);
        }

    }

    // Given the array, build a heap by correcting every non-leaf's position, starting from the bottom, then
    // progressing upward
    // run time is O(N) because depth of tree changes as you progress through it _NEED CLARIFICATION_
    public void heapify(int[] array) {
        this.heap = array;
        this.size = array.length;

        for (int i=this.size / 2 - 1; i>=0; i--) {
            // TODO
            // sink every non leaf
            sink(i);
        }
    }

    /**
     * Best-case runtime: O(NlgN)
     * Worst-case runtime: O(NlgN)
     * Average-case runtime: O(NlgN)
     * iterates through each element, and needs to sink each time, which is O(N) for loop, and O(lgN) for sink
     *
     * Space-complexity: O(1), uses existing array
     */
    @Override
    public int[] sort(int[] array) {
        heapify(array);

        for (int i=size-1; i>0; i--) {
            // TODO

            // swap last element with top element
            // decrease the size
            // sink top element

            swap(heap, 0, i);
            size--;
            sink(0);
        }

        return heap;
    }
}
