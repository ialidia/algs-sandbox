/* Hidden stub code will pass a root argument to the function below. Complete the function to solve the challenge. Hint: you may want to write one or more helper functions.

The Node class is defined as follows:*/

/**
 * Hackerrank: Trees: Is This a Binary Search Tree?
 *
 * @see <a href="https://www.hackerrank.com/challenges/ctci-is-binary-search-tree">Trees: Is This a Binary Search Tree?</a>
 */
class Node {
    int data;
    Node left;
    Node right;


    /**
     * Check whether the current binary tree is a binary search tree
     *
     * @param root Root node of the binary tree
     * @return true if it is a binary search tree and false otherwise
     */
    boolean checkBST(Node root) {
        if (root == null) {
            return true;
        }
        if ((root.left != null) && (root.left.data >= root.data)) {
            return false;
        }
        if ((root.right != null) && (root.right.data <= root.data)) {
            return false;
        }
        return checkBST(root.right) && checkBST(root.left) && checkLeft(root.data, root.left) && checkRight(root.data, root.right);
    }

    /**
     * Checking the left subtree if there are no values greater than the root value max
     *
     * @param max  value of the root of the current subtree
     * @param node current node
     * @return true if the values in the subtree are smaller than the max value and false otherwise
     */
    boolean checkLeft(int max, Node node) {
        if (node == null) {
            return true;
        }
        if (node.data < max) {
            return checkLeft(max, node.left) && checkLeft(max, node.right);
        } else {
            return false;
        }
    }

    /**
     * Checking the right subtree if there are no values smaller than the root value min
     *
     * @param min  value of the root of the current subtree
     * @param node current node
     * @return true if the values in the subtree are greater than the min value and false otherwise
     */
    boolean checkRight(int min, Node node) {
        if (node == null) {
            return true;
        }
        if (node.data > min) {
            return checkRight(min, node.left) && checkRight(min, node.right);
        } else {
            return false;
        }
    }


}