import com.sun.source.tree.Tree;

import java.net.Inet4Address;
import java.util.LinkedList;
import java.util.List;

public class BinarySearchTree<T extends Comparable<T>> {
    TreeNode<T> root;
    private int size;

    public int size() {
        return size;
    }

    public boolean contains(T key) {
        return find(root, key) != null;
    }

    /**
     * Add a node to the BST. Internally calls insert to recursively find the new node's place
     */
    public boolean add(T key) {
        if (find(root, key) != null) return false;
        root = insert(root, key);
        size++;
        return true;
    }

    public void addAll(T[] keys) {
        for (T k : keys)
            add(k);
    }

    private void inOrderTraverse(TreeNode<T> n, List<T> orderedList){
        // if left child exists -> recurse
        if (n.hasLeftChild()){
            inOrderTraverse(n.leftChild, orderedList);
        }

        // add self if no left child exists
        orderedList.add(n.key);

        // if right child exists -> recurse
        if (n.hasRightChild()){
            inOrderTraverse(n.rightChild, orderedList);
        }

    }

    public List<T> inOrderTraversal() {
        // TODO
        // Worst Case Runtime: O(N) - always goes through each element

        // create a linked list to place ordered keys
        List<T> orderedList = new LinkedList<>();

        // traverse the tree
        if (root == null){
            return orderedList;
        }
        inOrderTraverse(root,orderedList);

        // return the final ordered list of nodes
        return orderedList;
    }

    /**
     * Deletes a node from the BST using the following logic:
     * 1. If the node has a left child, replace it with its predecessor
     * 2. Else if it has a right child, replace it with its successor
     * 3. If it has no children, simply its parent's pointer to it
     */
    public boolean delete(T key) {
        TreeNode<T> toDelete = find(root, key);
        if (toDelete == null) {
            System.out.println("Key does not exist");
            return false;
        }
        TreeNode<T> deleted = delete(toDelete);
        if (toDelete == root) {
            root = deleted;
        }
        size--;
        return true;
    }

    private TreeNode<T> delete(TreeNode<T> n) {
        // Recursive base case
        if (n == null) return null;

        TreeNode<T> replacement;

        if (n.isLeaf())
            // Case 1: no children
            replacement = null;
        else if (n.hasRightChild() != n.hasLeftChild())
            // Case 2: one child
            replacement = (n.hasRightChild()) ? n.rightChild : n.leftChild; // replacement is the non-null child
        else {
            // Case 3: two children
            // TODO

            replacement = findPredecessor(n);
            delete(replacement);
            replacement.moveChildrenFrom(n);
        }

        // Put the replacement in its correct place, and set the parent.
        n.replaceWith(replacement);
        return replacement;
    }

    public T findPredecessor(T key) {
        // finds and returns the TreeNode with key = key if such a TreeNode exists in the tree
        TreeNode<T> n = find(root, key);
        if (n != null) {
            // get the predecessor TreeNode by calling the function you will implement below
            TreeNode<T> predecessor = findPredecessor(n);
            // return the key of predecessor TreeNode
            if (predecessor != null)
                return predecessor.key;
        }
        return null;
    }

    public T findSuccessor(T key) {

        // finds and returns the TreeNode with key = key if such a TreeNode exists in the tree
        TreeNode<T> n = find(root, key);
        if (n != null) {
            // get the successor TreeNode by calling the function you will implement below
            TreeNode<T> successor = findSuccessor(n);
            // return the key of successor TreeNode
            if (successor != null)
                return successor.key;
        }
        return null;
    }

    private TreeNode<T> findPredecessor(TreeNode<T> n) {
        // TODO

        // Worst Case: O(N): go one left for child, then go all the way right, and traverse through all elements that way

        // Case 1: if you have a left child
        //         go one left
        //         go as right as possible, that's the predecessor
        if (n.hasLeftChild()){
            TreeNode<T> currentNode = n.leftChild;
            currentNode = n.leftChild;
            while (currentNode.hasRightChild()){
                currentNode = currentNode.rightChild;
            }
            return currentNode;
        }

        // Case 2: no left child, you are right child
        //         parent is predecessor
        else if (n.isRightChild()){
            return n.parent;
        }

        // Case 3: if you are left child, go up all the way right
        //         once you are the right child, parent is predecessor
        else if (n.isLeftChild()){
            TreeNode<T> currentNode = n;
            while (currentNode.isLeftChild()){
                currentNode = currentNode.parent;
            }
            if (currentNode.isRightChild()){
                return currentNode.parent;
            }
            // never was right child, there is no predecessor
            return null;
        }

        // there just is no predecessor
        return null;
    }

    private TreeNode<T> findSuccessor(TreeNode<T> n) {
        // TODO
        // worst case: O(N) - go down one to the right, then all the way left, and that's the whole tree

        TreeNode<T> successor;

        // Case 1: successor is one of the descendants
        // go right child
        // keep going left until you run out
        if (n.hasRightChild()){
            successor = n.rightChild;
            while(successor.hasLeftChild()){
                successor = successor.leftChild;
            }
            return successor;
        }

        // Case 2: successor is a parent
        // while you are the right child go up
        // once you are the left child, go up and you're done
        successor = n;
        while (successor.isRightChild()){
            successor = successor.parent;
        }
        if (successor.isLeftChild()){
            successor = successor.parent;
            return successor;
        }

        // Case 3: there is no successor because you are not the left child
        else{
            return null;
        }

    }

    /**
     * Returns a node with the given key in the BST, or null if it doesn't exist.
     */
    private TreeNode<T> find(TreeNode<T> currentNode, T key) {
        if (currentNode == null)
            return null;
        int cmp = key.compareTo(currentNode.key);
        if (cmp < 0)
            return find(currentNode.leftChild, key);
        else if (cmp > 0)
            return find(currentNode.rightChild, key);
        return currentNode;
    }

    /**
     * Recursively insert a new node into the BST
     */
    private TreeNode<T> insert(TreeNode<T> node, T key) {
        if (node == null) return new TreeNode<>(key);

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.leftChild = insert(node.leftChild, key);
            node.leftChild.parent = node;
        } else {
            node.rightChild = insert(node.rightChild, key);
            node.rightChild.parent = node;
        }
        return node;
    }
}
