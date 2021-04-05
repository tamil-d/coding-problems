package in.tamil.coding.problems;

import java.util.Arrays;
import java.util.LinkedList;

public class Problem_56_MergeIntervals_Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a,b) -> Integer.compare(a[0],b[0]));
        LinkedList<int[]> mergedIntervals = new LinkedList<>();
        for(int[] currInterval : intervals) {
            if (mergedIntervals.isEmpty() || mergedIntervals.getLast()[1] < currInterval[0]) {
                mergedIntervals.add(currInterval);
            } else {
                mergedIntervals.getLast()[1] = Math.max(mergedIntervals.getLast()[1],currInterval[1]);
            }
        }
        return mergedIntervals.toArray(new int[mergedIntervals.size()][]);
    }
}