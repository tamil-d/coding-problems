package in.tamil.coding.problems.general;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Assumption:
 * StartTime And EndTime come in double format
 * eg: 08:30 --> 830
 * 10:45 --> 1045
 * 15:20 --> 1520
 */
public class FreeTimeSlot {

    public static void main(String[] args) {

        //LocalDateTime localDateTime = LocalDate.now().atStartOfDay();


        List<TimeInterval> cal1 = new ArrayList<>();
        List<TimeInterval> cal2 = new ArrayList<>();
        cal1.add(new TimeInterval(LocalDateTime.of(LocalDate.now(), LocalTime.of(9, 0, 0)), LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 0, 0))));

        cal1.add(new TimeInterval(LocalDateTime.of(LocalDate.now(), LocalTime.of(11, 0, 0)), LocalDateTime.of(LocalDate.now(), LocalTime.of(14, 0, 0))));
        cal1.add(new TimeInterval(LocalDateTime.of(LocalDate.now(), LocalTime.of(21, 0, 0)), LocalDateTime.of(LocalDate.now(), LocalTime.of(23, 0, 0))));
        cal1.add(new TimeInterval(LocalDateTime.of(LocalDate.now(), LocalTime.of(12, 0, 0)), LocalDateTime.of(LocalDate.now(), LocalTime.of(17, 0, 0))));

        cal2.add(new TimeInterval(LocalDateTime.of(LocalDate.now(), LocalTime.of(19, 0, 0)), LocalDateTime.of(LocalDate.now(), LocalTime.of(20, 0, 0))));
        cal2.add(new TimeInterval(LocalDateTime.of(LocalDate.now(), LocalTime.of(9, 30, 0)), LocalDateTime.of(LocalDate.now(), LocalTime.of(12, 0, 0))));


        TimeInterval workingHrs = new TimeInterval(LocalDateTime.of(LocalDate.now(), LocalTime.of(8, 0, 0)), LocalDateTime.of(LocalDate.now(), LocalTime.of(18, 0, 0)));

        List<TimeInterval> result = (new FreeTimeSlot()).getFreeTime(cal1, cal2, 30, workingHrs);

        cal1 = new ArrayList<>();
        cal2 = new ArrayList<>();
        result = (new FreeTimeSlot()).getFreeTime(cal1, cal2, 30, workingHrs);
    }

    public List<TimeInterval> getFreeTime(List<TimeInterval> cal1, List<TimeInterval> cal2, int expectedTimeDurationInMins, TimeInterval workingHours) {
        List<TimeInterval> freeSlot = new ArrayList<>();
        List<TimeInterval> consolidatedTimeInterval = new ArrayList<>(); // we can even use of the existing list to add other one. but if that list used by caller outside, i dont want to disturb that

        getConcolidatedTimeIntervalSortedByStartTime(cal1, cal2, consolidatedTimeInterval);
        populateFreeTimeIntervalSlots(freeSlot, consolidatedTimeInterval, workingHours, expectedTimeDurationInMins);
        System.out.println(freeSlot);


        return freeSlot;
    }

    private void populateFreeTimeIntervalSlots(List<TimeInterval> freeSlot, List<TimeInterval> consolidatedTimeInterval, TimeInterval workingHours, int expectedTimeDurationInMins) {
        if (consolidatedTimeInterval.isEmpty()) {
            splitAndPopulateFreeSlotByExpectedTimeDuration(freeSlot, workingHours.startTime, workingHours.endTime, expectedTimeDurationInMins);
            return;
        }
        LinkedList<TimeInterval> blockedIntervals = new LinkedList<>();
        for (TimeInterval currInterval : consolidatedTimeInterval) {
            if (blockedIntervals.isEmpty()) {
                blockedIntervals.add(currInterval);
                if (workingHours.startTime.compareTo(currInterval.startTime) < 0) {
                    //freeSlot.add(new TimeInterval(workingHours.startTime, Math.min(currInterval.startTime, workingHours.endTime)));
                    splitAndPopulateFreeSlotByExpectedTimeDuration(freeSlot, workingHours.startTime, getMinimumOfDateTime(currInterval.startTime, workingHours.endTime), expectedTimeDurationInMins);
                }
                continue;
            }
            TimeInterval lastVisitedTimeInterval = blockedIntervals.getLast();
            if (currInterval.startTime.compareTo(workingHours.endTime) >= 0) {
                if (lastVisitedTimeInterval.endTime.compareTo(workingHours.endTime) < 0) {
                    //freeSlot.add(new TimeInterval(lastVisitedTimeInterval.endTime, workingHours.endTime));
                    splitAndPopulateFreeSlotByExpectedTimeDuration(freeSlot, lastVisitedTimeInterval.endTime, workingHours.endTime, expectedTimeDurationInMins);
                }
                break;
            }
            if (lastVisitedTimeInterval.endTime.compareTo(currInterval.startTime) >= 0) {
                lastVisitedTimeInterval.endTime = getMaximumOfDateTime(lastVisitedTimeInterval.endTime, currInterval.endTime);
            } else {
                //freeSlot.add(new TimeInterval(lastVisitedTimeInterval.endTime,currInterval.startTime));// splitbyDuration
                splitAndPopulateFreeSlotByExpectedTimeDuration(freeSlot, lastVisitedTimeInterval.endTime, currInterval.startTime, expectedTimeDurationInMins);
                blockedIntervals.add(currInterval);
            }
        }
    }

    private void splitAndPopulateFreeSlotByExpectedTimeDuration(List<TimeInterval> freeSlot, LocalDateTime startTime, LocalDateTime endTime, int expectedTimeDurationInMins) {
        LocalDateTime temp = startTime.plusMinutes(expectedTimeDurationInMins);
        while (temp.compareTo(endTime) <= 0) {
            freeSlot.add(new TimeInterval(startTime, temp));
            startTime = temp;
            temp = temp.plusMinutes(expectedTimeDurationInMins);
        }
    }

    private void getConcolidatedTimeIntervalSortedByStartTime(List<TimeInterval> cal1, List<TimeInterval> cal2, List<TimeInterval> consolidatedTimeInterval) {
        consolidatedTimeInterval.addAll(cal1);
        consolidatedTimeInterval.addAll(cal2);
        consolidatedTimeInterval.sort(Comparator.comparing(o -> o.startTime));
    }

    private LocalDateTime getMinimumOfDateTime(LocalDateTime value1, LocalDateTime value2) {
        return value1.compareTo(value2) < 0 ? value1 : value2;
    }

    private LocalDateTime getMaximumOfDateTime(LocalDateTime value1, LocalDateTime value2) {
        return value1.compareTo(value2) >= 0 ? value1 : value2;
    }

}
