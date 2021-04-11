package in.tamil.coding.problems.leetcode.problem_138_linkedList_deep_copy_randompointer;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    public Node copyRandomList(Node head) {
        Map<Node,Node> oldListToNewListMapper = new HashMap<>();
        return getLinkToTheDeepCopiedList(head, oldListToNewListMapper);
    }

    private Node getLinkToTheDeepCopiedList(Node currNode, Map<Node, Node> oldListToNewListMapper) {
        if(currNode == null) {
            return null;
        }
        if(oldListToNewListMapper.containsKey(currNode)) {
            return oldListToNewListMapper.get(currNode);
        }
        Node newNode = new Node(currNode.val);
        oldListToNewListMapper.put(currNode,newNode);
        newNode.next = getLinkToTheDeepCopiedList(currNode.next, oldListToNewListMapper);
        newNode.random = getLinkToTheDeepCopiedList(currNode.random, oldListToNewListMapper);
        return newNode;
    }
}
