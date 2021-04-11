package in.tamil.coding.problems.leetcode.problem_143_reorder_list;

public class Solution {
    public void reorderList(ListNode head) {
        // Base Condition Check
        if (head == null || head.next == null) {
            return;
        }

        //Step 1: Find the Mid point of the list (node pointed by pointerToMiddleOfList)
        ListNode pointerToMiddleOfList = getMiddleOfList(head);

        //Step 2: Reverse the List from Middle to end of the List
        ListNode pointerToReversedList = getReferenceToTheReversedList(pointerToMiddleOfList);

        //Step 3: Merge first half and second half (reversed) of the list.
        mergeTwoListNodes(head, pointerToReversedList);

    }

    private void mergeTwoListNodes(ListNode list1, ListNode list2) {
        ListNode tempNodeForSwapping;
        while (list2.next != null) {
            tempNodeForSwapping = list1.next;
            list1.next = list2;
            list1 = tempNodeForSwapping;
            tempNodeForSwapping = list2.next;
            list2.next = list1;
            list2 = tempNodeForSwapping;
        }
    }

    private ListNode getReferenceToTheReversedList(ListNode currNode) {
        ListNode prevNode = null;
        ListNode tempNodeForSwapping;
        while (currNode != null) {
            tempNodeForSwapping = currNode.next;
            currNode.next = prevNode;
            prevNode = currNode;
            currNode = tempNodeForSwapping;
        }
        return prevNode;
    }

    private ListNode getMiddleOfList(ListNode head) {
        ListNode oneStepPointer = head;
        ListNode twoStepPointer = head;
        while (twoStepPointer != null && twoStepPointer.next != null) {
            oneStepPointer = oneStepPointer.next;
            twoStepPointer = twoStepPointer.next.next;
        }
        return oneStepPointer;
    }
}
