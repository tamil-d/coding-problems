package in.tamil.coding.problems.leetcode.problem_98_valid_bst;

import java.util.LinkedList;

public class Solution {
    public boolean isValidBST(TreeNode root) {
        LinkedList<Integer> traversedValues = new LinkedList<>();
        // Idea is to do utilise the property of BST where Inorder traversal (Left - Curr - Right)
        // of the tree gives Values in the Sorted Order which can be captured in our traversedValues list
        return populateAndValidateTraversedListUsingTreeInOrderTraversal(root, traversedValues);
    }

    private boolean populateAndValidateTraversedListUsingTreeInOrderTraversal(TreeNode currNode, LinkedList<Integer> traversedValues) {
        if (currNode == null) {
            return true;
        }
        // Left Subtree Parsing
        if (!populateAndValidateTraversedListUsingTreeInOrderTraversal(currNode.left, traversedValues)) {
            return false;
        }

        // Current Node (a) Parent Node's logic against already traversed Nodes List
        if (!traversedValues.isEmpty()) {
            int lastTraversedVal = traversedValues.getLast();
            if (lastTraversedVal < currNode.val) {
                traversedValues.add(currNode.val);
            } else {
                return false;
            }
        } else {
            traversedValues.add(currNode.val);
        }

        // Right Subtree Parsing
        return populateAndValidateTraversedListUsingTreeInOrderTraversal(currNode.right, traversedValues);
    }
}
