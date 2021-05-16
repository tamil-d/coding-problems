package in.tamil.coding.problems.leetcode.problem_759_emp_free_time;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Solution {
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<Interval> result = new ArrayList<>();
        List<Interval> sortedSchedule = new ArrayList<>();
        for (List<Interval> list : schedule) {
            sortedSchedule.addAll(list);
        }
        if (sortedSchedule.isEmpty()) {
            return result;
        }
        sortedSchedule.sort(Comparator.comparingInt(anInt -> anInt.start));
        LinkedList<Interval> mergedIntervals = new LinkedList<>();
        for (Interval currSch : sortedSchedule) {
            if (mergedIntervals.isEmpty()) {
                mergedIntervals.add(currSch);
                continue;
            }
            Interval lastIntInList;
            lastIntInList = mergedIntervals.getLast();
            if (currSch.start <= mergedIntervals.getLast().end) {

                lastIntInList.start = Math.min(currSch.start, lastIntInList.start);
                lastIntInList.end = Math.max(currSch.end, lastIntInList.end);
            } else {
                result.add(new Interval(lastIntInList.end, currSch.start));
                mergedIntervals.add(currSch);
            }
        }
        return result;
    }

    private class Interval {
        public int start;
        public int end;

        public Interval() {
        }

        public Interval(int _start, int _end) {
            start = _start;
            end = _end;
        }
    }
}
