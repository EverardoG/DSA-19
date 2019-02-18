
public class MergeSort extends SortAlgorithm {

    private static final int INSERTION_THRESHOLD = 10;

    /**
     * This is the recursive step in which you split the array up into
     * a left and a right portion, sort them, and then merge them together.
     * Use Insertion Sort if the length of the array is <= INSERTION_THRESHOLD
     *
     * TODO
     * Best-case runtime: O(NlgN)
     * Worst-case runtime: O(NlgN))
     * Average-case runtime: O(NlgN)
     *
     * Space-complexity: O(NlgN)
     */
    @Override
    public int[] sort(int[] array) {
        // TODO: Implement this boi

        // new base case! use insertion sort if under 10
        if (array.length <= 10){
            InsertionSort sorter = new InsertionSort();
            return sorter.sort(array);
        }

        // base case, return the single element array
//        if (array.length <= 1){
//            return array;
//        }

        // split the array into a left and right
        int[] left = new int[array.length/2 + array.length%2];
        int[] right = new int[array.length/2];
        System.arraycopy(array, 0, left, 0, left.length);
        System.arraycopy(array, left.length, right, 0, right.length);

        // merge sort the left, merge sort the right
        int[] sortedLeft = sort(left);
        int[] sortedRight = sort(right);

        // merge the array
        int[] mergedArray = merge(sortedLeft, sortedRight);

        return mergedArray;
    }

    /**
     * Given two sorted arrays a and b, return a new sorted array containing
     * all elements in a and b. A test for this method is provided in `SortTest.java`
     */
    public int[] merge(int[] a, int[] b) {
        // initialize array for merging
        // and pointers to iterate through each array
        int[] newArr = new int[a.length+b.length];
        int bInd = 0;
        int count = 0;

        // while you haven't gotten to the end of a
        // compare the a element to the b element
        // add the appropriate element to newArr and increment things
        for (int aVal : a){
            while( bInd < b.length && b[bInd] < aVal){
                newArr[count] = b[bInd];
                bInd++;
                count++;
            }
            newArr[count] = aVal;
            count++;
        }

        // hit this if you finish going through a, and b still has elements
        // finish going through b
        while (count < newArr.length){
            newArr[count] = b[bInd];
            bInd++;
            count++;
        }

        return newArr;


        // IGNORE THIS STUFF DOWN HERE

        // while the new array isn't filled
        // put the greatest value in and move appropriate pointers forward
//        while (count < newArr.length){
//            if (a[p1] < b[p2]){
//                newArr[count] = a[p1];
//                if (p1 < a.length-1){
//                    p1++;
//                }
//            }
//            else if (a[p1] > b[p2]){
//                newArr[count] = b[p2];
//                if (p2 < b.length-1){
//                    p2++;
//                }
//            }
//            else{
//                newArr[count] = a[p1];
//                count++;
//                newArr[count] = b[p2];
//                if (p1 < a.length-1){
//                    p1++;
//                }
//                if (p2 < b.length-1){
//                    p2++;
//                }
//
//            }
//                count++;
//        }

    }

}
