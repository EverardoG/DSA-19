import java.util.*;

public class Problems {

    private static void conquer(Integer[] valArray, BinarySearchTree<Integer> newTree){
        if (valArray.length > 0){
            newTree.add(valArray[valArray.length/2]);
        }


        if (valArray.length > 1){
        Integer[] left = new Integer[valArray.length/2 + valArray.length % 2];
        Integer[] right = new Integer[valArray.length/2-1];

        System.arraycopy(valArray, 0, left, 0, left.length);
        System.arraycopy(valArray, left.length+1, right, 0, right.length);

        conquer(left, newTree);
        conquer(right, newTree);

        }


//        // base case
//        if (valArray.length == 1){
//            newTree.add(valArray[0]);
//        }
//
//        // recursive case
//        else {
//            newTree.add(valArray[valArray.length/2]);
//
//            // cut into left and right
//            Integer[] left = new Integer[(valArray.length/2 + valArray.length % 2)-1];
//            Integer[] right = new Integer[valArray.length/2];
//            System.arraycopy(valArray, 0, left, 0, left.length);
//            System.arraycopy(valArray, left.length, right, 0, right.length);
//
//            // keep going through the left
//            // keep going through the right
//            conquer(left, newTree);
//            conquer(right, newTree);
//        }
    }



    public static BinarySearchTree<Integer> minimalHeight(List<Integer> values) {
        // TODO

        Integer[] valArray = new Integer[values.size()];
        for (int i = 0; i < values.size(); i++){
            valArray[i] = values.get(i);
        }

        Arrays.sort(valArray);
        BinarySearchTree<Integer> newTree = new BinarySearchTree<>();

        conquer(valArray, newTree);

        return newTree;
    }

    public static boolean isIsomorphic(TreeNode n1, TreeNode n2) {
        // TODO
        return false;
    }
}
