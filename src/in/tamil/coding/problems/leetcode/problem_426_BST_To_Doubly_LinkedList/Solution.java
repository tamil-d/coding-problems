package in.tamil.coding.problems.leetcode.problem_426_BST_To_Doubly_LinkedList;/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
};
*/

public class Solution {

    // the smallest (first) and the largest (last) nodes
    Node first = null;
    Node last = null;

    public void inOrderParseTreeAndConvertToDoublyLinkedListRecursively(Node currNode) {
        if (currNode == null) {
            return;
        }
        // parse left sub tree
        inOrderParseTreeAndConvertToDoublyLinkedListRecursively(currNode.left);
        // do currNode logic
        convertBSTNodeToCircularDLLNode(currNode);
        // parse right sub tree
        inOrderParseTreeAndConvertToDoublyLinkedListRecursively(currNode.right);
    }

    private void convertBSTNodeToCircularDLLNode(Node currNode) {
        if (last != null) {
            // link the previous currNode (last)
            // with the current one (currNode)
            last.right = currNode;
            currNode.left = last;
        } else {
            // to keep the reference of the first element of DLL.
            first = currNode;
        }
        last = currNode;
    }

    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }

        inOrderParseTreeAndConvertToDoublyLinkedListRecursively(root);
        // to close the loop and convert DLL to Circular DLL
        last.right = first;
        first.left = last;

        return first;
    }
}