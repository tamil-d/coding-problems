package in.tamil.coding.problems.leetcode.problem_33_search_in_rotated_sorted_array;

public class Solution {
    public int search(int[] nums, int target) {
        int startIndex = 0;
        int endIndex = nums.length - 1;
        while (startIndex <= endIndex) {
            if (nums[startIndex] == target) {
                return startIndex;
            }
            if (nums[endIndex] == target) {
                return endIndex;
            }
            int midIndex = startIndex + (endIndex - startIndex) / 2;
            if (nums[midIndex] == target) {
                return midIndex;
            } else {
                if (nums[startIndex] <= nums[midIndex]) {
                    if (target >= nums[startIndex] && target < nums[midIndex]) {
                        endIndex = midIndex - 1;
                    } else {
                        startIndex = midIndex + 1;
                    }
                } else {
                    if (target > nums[midIndex] && target <= nums[endIndex]) {
                        startIndex = midIndex + 1;
                    } else {
                        endIndex = midIndex - 1;
                    }
                }
            }

        }
        return -1;
    }
}
