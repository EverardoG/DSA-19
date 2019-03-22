package divide_and_conquer;

public class PeakFinding {

    // Return -1 if left is higher, 1 if right is higher, 0 if peak
    private static int peakOneD(int i, int[] nums) {
        if (i > 0 && nums[i] < nums[i - 1])
            return -1;
        if (i < nums.length - 1 && nums[i] < nums[i + 1])
            return 1;
        return 0;
    }

    // Return -1 if left is higher, 1 if right is higher, 0 if peak
    private static int peakX(int x, int y, int[][] nums) {
        if (x > 0 && nums[y][x] < nums[y][x - 1])
            return -1;
        if (x < nums[0].length - 1 && nums[y][x] < nums[y][x + 1])
            return 1;
        return 0;
    }

    // Return -1 if up is higher, 1 if down is higher, 0 if peak
    private static int peakY(int x, int y, int[][] nums) {
        if (y > 0 && nums[y][x] < nums[y - 1][x])
            return -1;
        if (y < nums.length - 1 && nums[y][x] < nums[y + 1][x])
            return 1;
        return 0;
    }

    // These two functions return the index of the highest value along the X or Y axis, with the given
    // value for the other axis. Searches between hi (exclusive) and lo (inclusive)
    private static int maxXIndex(int y, int lo, int hi, int[][] nums) {
        int maxIndex = -1;
        for (int x = lo; x < hi; x++) {
            if (maxIndex == -1 || nums[y][x] > nums[y][maxIndex])
                maxIndex = x;
        }
        return maxIndex;
    }

    private static int maxYIndex(int x, int lo, int hi, int[][] nums) {
        int maxIndex = -1;
        for (int y = lo; y < hi; y++) {
            if (maxIndex == -1 || nums[y][x] > nums[maxIndex][x])
                maxIndex = y;
        }
        return maxIndex;
    }

    // start - starting index to recurse through
    // end - ending index to recurse through
    private static int OneDRecurse(int[] nums, int start, int end){
        // Conquer: Determine whether or not the middle is a peak
        int midInd = (end-start)/2+start;
        int peakCase = peakOneD(midInd, nums);

        // Base Case: found peak, return it
        if (peakCase == 0){
            return midInd;
        }

        // Decrease Case 1: not the peak, try the left
        else if (peakCase == -1){
            return OneDRecurse(nums, start, midInd);
        }

        // Decrease Case 2: not the peak, try the right
        else{
            return OneDRecurse(nums, midInd+1, end);
        }


    }

    public static int findOneDPeak(int[] nums) {
        // TODO
        // runtime: O(lgN)
        return OneDRecurse(nums, 0, nums.length-1);
    }

    private static int[] TwoDRecurse(int[][] nums, int x, int y){
        // Conquer: Determine whether you are at a peak
        //          return the indicies of the peak if you're there
        //          decrease the problem space by following the gradient if not

        int val = nums[y][x];
        // Base Case: You're at a peak
        if ((peakY(x,y,nums)==0) && (peakX(x,y,nums)==0)){
            int[] answer = {y, x};
            return answer;
        }

        // Decrease Case: Not at a peak - Follow the gradient
        return TwoDRecurse(nums, x + peakX(x,y,nums), y + peakY(x,y,nums));

    }

    public static int[] findTwoDPeak(int[][] nums) {
        // TODO

        // find a 2D peak in the array
        return TwoDRecurse(nums,  0, 0);
    }

}
