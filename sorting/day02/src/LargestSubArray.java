import java.util.HashMap;

public class LargestSubArray {
    static int[] largestSubarray(int[] nums) {
        // TODO
        int total = 0;
        int subLen;
        HashMap<Integer, int[]> subArrayDict = new HashMap<>();
        int longest = 0;
        int[] longestInds = {0,0};
        int[] initInds = {0,0};

        subArrayDict.put(0, initInds);

        int count = 0;
        for (int element : nums){
            if (element == 1){
                total += 1;
            }
            else{
                total -= 1;
            }

            if (!subArrayDict.containsKey(total)){
                int[] tempInds = {count+1,count+1};
                subArrayDict.put(total, tempInds);
            }
            else{
                int[] tempInds = subArrayDict.get(total);
                tempInds[1] = count;
                subArrayDict.put(total, tempInds);

                subLen = tempInds[1] - tempInds[0];
                if (subLen > longest) {
                    longest = subLen;
                    longestInds = tempInds;
                }
            }


            count++;
        }




        return longestInds;
    }
}
