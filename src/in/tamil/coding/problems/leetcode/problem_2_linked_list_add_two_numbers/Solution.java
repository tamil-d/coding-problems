package in.tamil.coding.problems.leetcode.problem_2_linked_list_add_two_numbers;

public class Solution {
    public ListNode addTwoNumbers(ListNode list1, ListNode list2) {
        ListNode resultListHead = new ListNode(-1);
        ListNode currNodeInResultList = resultListHead;
        int carryOverValue = 0;
        while (list1 != null || list2 != null || carryOverValue > 0) {
            int list1Value = 0;
            int list2Value = 0;
            if (list1 != null) {
                list1Value = list1.val;
                list1 = list1.next;
            }
            if (list2 != null) {
                list2Value = list2.val;
                list2 = list2.next;
            }
            int sum = list1Value + list2Value + carryOverValue;
            carryOverValue = sum / 10;
            currNodeInResultList.next = new ListNode(sum % 10);
            currNodeInResultList = currNodeInResultList.next;
        }
        return resultListHead.next;
    }
}
