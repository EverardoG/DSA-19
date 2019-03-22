package range_finding;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AVLRangeTree extends BinarySearchTree<Integer> {

    /**
     * Delete a key from the tree rooted at the given node.
     */
    @Override
    RangeNode<Integer> delete(RangeNode<Integer> n, Integer key) {
        n = super.delete(n, key);
        if (n != null) {
            calcChildren(n);
            n.height = 1 + Math.max(height(n.leftChild), height(n.rightChild));
            return balance(n);
        }
        return null;
    }

    /**
     * Insert a key into the tree rooted at the given node.
     */
    @Override
    RangeNode<Integer> insert(RangeNode<Integer> n, Integer key) {
        n = super.insert(n, key);
        if (n != null) {
            calcChildren(n);
            n.height = 1 + Math.max(height(n.leftChild), height(n.rightChild));
            return balance(n);
        }
        return null;
    }

    /**
     * Delete the minimum descendant of the given node.
     */
    @Override
    RangeNode<Integer> deleteMin(RangeNode<Integer> n) {
        n = super.deleteMin(n);
        if (n != null) {
            n.height = 1 + Math.max(height(n.leftChild), height(n.rightChild));
            return balance(n);
        }
        return null;
    }

    // calculate and set the number of children this node has
    public int calcChildren(RangeNode n){
        if (n == null){
            return -1;
        }
        else{
            n.numChildren = (2 + calcChildren(n.leftChild) + calcChildren(n.rightChild));
            return n.numChildren;
        }
    }

    // returns number of keys equal to or less than k
    public int rankHi(RangeNode n, int k){
        // Case 1: hit the end of the branch
        if (n == null) {
            return 0;
        }

        // Case 2: key is less than or equal to k, collect left children and traverse right
        else if ((int) n.key <= k){
            if (n.leftChild != null){
                return 2 + n.leftChild.numChildren + rankHi(n.rightChild, k);
            }
            else{ // n.leftChild is null
                return 1 + rankHi(n.rightChild, k);
            }
        }

        // Case 3: key is greater than k, traverse left
        else{
            return rankHi(n.leftChild, k);
        }
    }

    // returns number of keys greater than or equal to k
    public int rankLo(RangeNode n, int k){
        // Case 1: hit the end of the branch
        if (n == null) {
            return 0;
        }

        // Case 2: key is greater than or equal to k, collect left children and traverse right
        else if ((int) n.key >= k){
            if (n.rightChild != null){
                return 2 + n.rightChild.numChildren + rankLo(n.leftChild, k);
            }
            else{ // n.rightChild is null
                return 1 + rankLo(n.leftChild, k);
            }
        }

        // Case 3: key is less than k, traverse right
        else{
            return rankLo(n.rightChild, k);
        }
    }

    // returns number of keys in range lo to hi, inclusive
    public int rankBoth(RangeNode n, int lo, int hi){
        // Case 0: n is null
        if (n == null){
            return 0;
        }
        // Case 1: key is in range, attack with rankLo and rankHi
        if ( (int) n.key >= lo && (int) n.key <= hi){
            if (n.rightChild != null && n.leftChild != null){
                return 1 + rankLo(n.leftChild, lo) + rankHi(n.rightChild, hi);
            }
            else if (n.leftChild != null){
                return 1 + rankLo(n.leftChild, lo);
            }
            else{ // n.rightChild != null
                return 1 + rankHi(n.rightChild, hi);
            }
        }

        // Case 2: key is too large, go left
        else if ( (int) n.key > hi){
            return rankBoth(n.leftChild, lo, hi);
        }

        // Case 3: key is too small, go right
        else{ // n.key < lo
            return rankBoth(n.rightChild, lo, hi);
        }
    }

//    // returns number of keys equal to or less than k
//    public int antiRank(RangeNode n, int k){
//        if (n == null) {
//            return -1;
//        }
//
//        else if ((int) n.key >= k){
//            if (n.rightChild != null){
//                return 2 + n.rightChild.numChildren + antiRank(n.leftChild, k);
//            }
//            else{
//                return 1 + antiRank(n.leftChild, k);
//            }
//        }
//
//        else{
//            return antiRank(n.rightChild, k);
//        }
//    }
//
//    // return the number of keys within a give range from lo to hi inclusive from the given node
//    public int rankBoth(RangeNode n, int lo, int hi){
//
//        if (n == null){
//            return 0;
//        }
//
//        else if ( (int) n.key >= lo && (int) n.key <= hi){
//            if (n.leftChild != null && n.rightChild != null){
//                return 1 + rank(n.leftChild, hi) + antiRank(n.rightChild, lo);
//            }
//
//            else if (n.leftChild == null && n.rightChild == null){
//                return 1;
//            }
//
//            else if (n.rightChild != null){
//                return 1 + antiRank(n.rightChild, lo);
//
//            }
//            else { //n.leftChild != null
//                return 1 + rank(n.leftChild, hi);
//            }
//        }
//
//        else if ( (int) n.key > hi ){
//            return rankBoth(n.leftChild, lo, hi);
//        }
//
//        else{// n.key < lo
//            return rankBoth(n.rightChild, lo, hi);
//        }
//
//    }

    // Return the height of the given node. Return -1 if null.
    private int height(RangeNode x) {
        if (x == null) return -1;
        return x.height;
    }

    public int height() {
        return Math.max(height(root), 0);
    }

    // Restores the AVL tree property of the subtree.
    RangeNode<Integer> balance(RangeNode<Integer> x) {
        if (balanceFactor(x) > 1) {
            if (balanceFactor(x.rightChild) < 0) {
                x.rightChild = rotateRight(x.rightChild);
            }
            x = rotateLeft(x);
        } else if (balanceFactor(x) < -1) {
            if (balanceFactor(x.leftChild) > 0) {
                x.leftChild = rotateLeft(x.leftChild);
            }
            x = rotateRight(x);
        }
        return x;
    }

    // Return all keys that are between [lo, hi] (inclusive) for root node
    // TODO: runtime = O(?)
    public List<Integer> rangeIndex(int lo, int hi) {
        // TODO
        List<Integer> l = new LinkedList<>();
        rangeIndexRec(root, l, lo, hi);
        return l;
    }

    // Return all keys that are between [lo, hi] (inclusive) for given node
    public void rangeIndexRec(RangeNode<Integer> node, List<Integer> list, int lo, int hi) {
        if (node != null) {
            rangeIndexRec(node.leftChild, list, lo, hi);
            if (node.key >= lo && node.key <= hi){
                list.add(node.key);
            }
            rangeIndexRec(node.rightChild, list, lo, hi);
        }
    }

    // return the number of keys between [lo, hi], inclusive
    // TODO: runtime = O(?)
    public int rangeCount(int lo, int hi) {
        // TODO
        return rankBoth(root, lo, hi);
    }

    /**
     * Returns the balance factor of the subtree. The balance factor is defined
     * as the difference in height of the left subtree and right subtree, in
     * this order. Therefore, a subtree with a balance factor of -1, 0 or 1 has
     * the AVL property since the heights of the two child subtrees differ by at
     * most one.
     */
    private int balanceFactor(RangeNode x) {
        return height(x.rightChild) - height(x.leftChild);
    }

    /**
     * Perform a right rotation on node `n`. Return the head of the rotated tree.
     */
    private RangeNode<Integer> rotateRight(RangeNode<Integer> x) {
        RangeNode<Integer> y = x.leftChild;
        x.leftChild = y.rightChild;
        y.rightChild = x;
        x.height = 1 + Math.max(height(x.leftChild), height(x.rightChild));
        y.height = 1 + Math.max(height(y.leftChild), height(y.rightChild));
        return y;
    }

    /**
     * Perform a left rotation on node `n`. Return the head of the rotated tree.
     */
    private RangeNode<Integer> rotateLeft(RangeNode<Integer> x) {
        RangeNode<Integer> y = x.rightChild;
        x.rightChild = y.leftChild;
        y.leftChild = x;
        x.height = 1 + Math.max(height(x.leftChild), height(x.rightChild));
        y.height = 1 + Math.max(height(y.leftChild), height(y.rightChild));
        return y;
    }
}
