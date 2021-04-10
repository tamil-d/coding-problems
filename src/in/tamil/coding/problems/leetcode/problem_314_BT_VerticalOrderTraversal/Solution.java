package in.tamil.coding.problems.leetcode.problem_314_BT_VerticalOrderTraversal;

import java.util.*;

public class Solution {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        SortedMap<Integer, SortedMap<Integer, List<Integer>>> columnIndexToNodeListSortedMap = new TreeMap<>();
        inOrderTraversal(root, 0, 0, columnIndexToNodeListSortedMap);
        populateResultFromTreeTraversalSortedMapOutput(result, columnIndexToNodeListSortedMap);
        return result;
    }

    private void populateResultFromTreeTraversalSortedMapOutput(List<List<Integer>> result, SortedMap<Integer, SortedMap<Integer, List<Integer>>> columnIndexToNodeListSortedMap) {
        for (Map.Entry<Integer, SortedMap<Integer, List<Integer>>> columnKeyEntrySet : columnIndexToNodeListSortedMap.entrySet()) {
            if (!columnKeyEntrySet.getValue().isEmpty()) {
                List<Integer> values = new ArrayList<>();
                for (Map.Entry<Integer, List<Integer>> rowKeyEntrySet : columnKeyEntrySet.getValue().entrySet()) {
                    values.addAll(rowKeyEntrySet.getValue());
                }
                if (!values.isEmpty()) {
                    result.add(values);
                }
            }
        }
    }

    private void inOrderTraversal(TreeNode currNode, int row, int column, SortedMap<Integer, SortedMap<Integer, List<Integer>>> columnIndexToNodeListSortedMap) {
        if (!columnIndexToNodeListSortedMap.containsKey(column)) {
            columnIndexToNodeListSortedMap.put(column, new TreeMap<>());
        }

        if (currNode == null) {
            return;
        }

        //left subtree traversal
        inOrderTraversal(currNode.left, row + 1, column - 1, columnIndexToNodeListSortedMap);

        //currNode logic
        SortedMap<Integer, List<Integer>> rowKeySortedMap = columnIndexToNodeListSortedMap.computeIfAbsent(column, k -> new TreeMap<>());
        if (!rowKeySortedMap.containsKey(row)) {
            rowKeySortedMap.put(row, new ArrayList<>());
        }
        rowKeySortedMap.get(row).add(currNode.val);

        //right subtree traversal
        inOrderTraversal(currNode.right, row + 1, column + 1, columnIndexToNodeListSortedMap);
    }
}
