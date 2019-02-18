import java.util.HashMap;

public class Boomerang {

    public static int numberOfBoomerangs(int[][] points) {
        // TODO

        // create a dictionary to hold distances between points
        // where key = point distance, value = how many points are that far away

        // for each point:
        // iterate through the other elements
        // update dictionary with appropriate k:v pair
        // add 1 to the value for each element that distance away
        // if the value is >= 2, then add 2 to the numBangs counter

        // clear the dictionary at the end, and do this for the next point

        HashMap<Float, Integer> distMap = new HashMap<>();
        float distance;
        int numBangs=0;

        for (int[] point:points){

            for (int[] otherPoint: points){

                // increment the number of boomerangs this distance away if this key exists
                distance = (float) Math.sqrt ((float) Math.pow(((float) otherPoint[0] - point[0]), 2) + (float) Math.pow( ((float) otherPoint[1] - point[1]), 2 ));
                if (distMap.containsKey(distance)) {
                    distMap.put(distance, distMap.get(distance) + 1);

                    // increment the numBangs counter appropriately
                    numBangs = numBangs + (distMap.get(distance) - 1) * 2;
                }

                // create this key value pair if it didn't already exist
                else{
                    distMap.put(distance, 1);
                }
            }

            // clearing the dictionary for the next point
            distMap.clear();
        }

        return numBangs;
    }
}

