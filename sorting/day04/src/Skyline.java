import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Skyline {

    static class Point {
        int x, y;
        private Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Building {
        private int l, r, h;
        Building(int l, int r, int h) {
            this.l = l;
            this.r = r;
            this.h = h;
        }
    }

    public static Building[] merge(Building[] leftSkyline, Building[] rightSkyLine){
        // this function merges together two skylines to form a skyline

        // easily merge them together if there's no overlap
        // merge them the hard way otherwise
        int leftSkyInd = leftSkyline.length-1;
        int rightSkyInd = 0;
        if (leftSkyline[leftSkyInd].r == rightSkyLine[rightSkyInd].l){
            Building[] newSkyline = new Building[leftSkyline.length + rightSkyLine.length];
            System.arraycopy(leftSkyline, 0, newSkyline, 0, leftSkyline.length);
            System.arraycopy(rightSkyLine,0,newSkyline, leftSkyline.length, rightSkyLine.length);
            return newSkyline;
        }

        else if (leftSkyline[leftSkyInd].r < rightSkyLine[rightSkyInd].l){
            Building[] newSkyline = new Building[leftSkyline.length + rightSkyLine.length+1];
            System.arraycopy(leftSkyline, 0, newSkyline, 0, leftSkyline.length);
            newSkyline[leftSkyline.length] = new Building(leftSkyline[leftSkyInd].r, rightSkyLine[rightSkyInd].l, 0);
            System.arraycopy(rightSkyLine,0,newSkyline, leftSkyline.length+1, rightSkyLine.length);
            return newSkyline;
        }

        // this runs if there's overlap
        // create an array of heights
        // iterate through array of heights, filling with building heights
        // only if they're greater than what's already there

        int heightsLen = Math.max(rightSkyLine[rightSkyLine.length-1].r, leftSkyline[leftSkyline.length-1].r)+1;
//        if (rightSkyLine[rightSkyInd].r != 0){
//            heightsLen++;
//        }

        int[] heights = new int[heightsLen];
        for (Building building: leftSkyline){
            for (int i = building.l ; i < building.r; i++){
                if (building.h > heights[i]){
                    heights[i] = building.h;
                }
            }
        }
        for (Building building: rightSkyLine){
            for (int i = building.l ; i < building.r; i++){
                if (building.h > heights[i]){
                    heights[i] = building.h;
                }
            }
        }

        // now put this new skyline into building format

        // calculate number of buildings
        // create array for buildings
        // iterate through heights to define buildings

        // if the current value is different from the last value or its the first value
        // create a new building object
        // the right and left indices are equal to the current index
        // the height is equal to the value at the current index

        int numBuildings = 0;
        for (int i = 0; i < heights.length; i++){
            if (i == 0 || heights[i] != heights[i-1]){
                numBuildings += 1;
            }
        }

        Building[] mergedSkyLine = new Building[numBuildings];
        int buildingInd = -1;
        for (int i = 0; i < heights.length; i++){
            if (i == 0 || heights[i] != heights[i-1]){
                buildingInd++;
                mergedSkyLine[buildingInd] = new Building(i,i+1,heights[i]);
            }
            else{
                mergedSkyLine[buildingInd].r=i+1;
            }
        }

        return mergedSkyLine;
    }

    public static Building[] conquer(Building[] B){
        // base case
        if (B.length == 1){
            return B;
        }

        // recursive case, split into left and right
        Building[] left = new Building[B.length/2 + B.length%2];
        Building[] right = new Building[B.length/2];
        System.arraycopy(B, 0, left, 0, left.length);
        System.arraycopy(B, left.length, right, 0, right.length);

        // recurse!
        Building[] sortedLeft = conquer(left);
        Building[] sortedRight = conquer(right);

        // merge both sides together
        return merge(sortedLeft, sortedRight);
    }

    // Given an array of buildings, return a list of points representing the skyline
    public static List<Point> skyline(Building[] B) {
        // TODO

        // run the actual skyline algorithm
        Building[] skyLine = conquer(B);

        // turn skyLine array of building objects into arrayList of point objects
        // that represent the same information
        ArrayList<Point> points = new ArrayList<>();
        for (Building building: skyLine){
            points.add(new Point(building.l, building.h));
        }

        // get rid of the first point if it's just 0,0
        if (points.get(0).x == 0 && points.get(0).y == 0){
            points.remove(0);
        }


        return points;
    }
}
