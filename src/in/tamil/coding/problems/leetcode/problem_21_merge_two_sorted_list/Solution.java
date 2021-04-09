package in.tamil.coding.problems.leetcode.problem_21_merge_two_sorted_list;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
public class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(-1);
        ListNode previousNode = dummyHead;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                previousNode.next = l1;
                l1 = l1.next;
            } else {
                previousNode.next = l2;
                l2 = l2.next;
            }
            previousNode = previousNode.next;
        }

        if (l1 == null) {
            previousNode.next = l2;
        } else {
            previousNode.next = l1;
        }
        return dummyHead.next;
    }
}