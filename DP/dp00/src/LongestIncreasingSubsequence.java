public class LongestIncreasingSubsequence {

    // Runtime: TODO
    // Space: TODO
    public static int LIS(int[] A) {
        // TODO
        // handle edge cases
        if (A.length == 0){
            return 0;
        }

        else if (A.length == 1){
            return 1;
        }

        // initialize DP array and recursively find longest substring for each array index
        int[] DP = new int[A.length];
        DP[DP.length-1] = 1;
        for (int i = 0; i < A.length; i++) {
            helperLIS(A, i, DP);
        }

        // find the maximum value in the DP array and return it
        int maxLength = 0;
        for (int length: DP){
            if (length > maxLength){
                maxLength = length;
            }
        }

        return maxLength;
    }

    // A - 1D array of some sequence
    // i - current index
    // DP - memoized solutions to subproblems
    private static int helperLIS(int[] A, int i, int[] DP){

        // first case - value already memoized, return memoized value
        if (DP[i] != 0){
            return DP[i];
        }

        // second case - search for the longest increasing subarray
        // initialize the longest increasing subarray to 1 in case we don't find anything
        int maxLength = 1;
        for (int j = i+1; j < A.length; j++){
            if (A[j]>A[i]){
                // always check if that's the longest increasing subarray for that given array index
                int newLength = helperLIS(A,j,DP) + 1;
                if (newLength > maxLength){
                    maxLength = newLength;
                }

            }
        }

        // save the 1 if you didn't find anything, or save the longest increasing subarray
        DP[i] = maxLength;
        return DP[i];
    }
}