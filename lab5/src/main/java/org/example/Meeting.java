package org.example;

import java.util.ArrayList;

/**
 * Very simple class for use in the task-scheduling problem.
 * A task includes a name, a start time, and an end time.
 * In Lab 5 this class is being used to represent "meeting requests".
 * <p></p>
 * Summary of changes made by Towa Quimbayo:
 * <ul>
 * <li>Added sortByStartTime, sortByLength, and sortByEndTime methods
 * to sort meetings by start time, length, and end time respectively.</li>
 * <li>Added scheduleMeetings helper method to schedule meetings.</li>
 * <li>Added printScheduledMeetings helper method to print the names of the meetings
 * in the given list and the number of meetings scheduled.</li>
 * </ul>
 *
 * @author Towa Quimbayo, A01086002, Set U
 */
public class Meeting {
    private final String name;
    private final Integer start;
    private final Integer end;

    /**
     * Simple constructor to set all three member values.
     * @param n name
     * @param s start time
     * @param e end time
     */
    public Meeting(String n, Integer s, Integer e) {
        name = n;
        start = s;
        end = e;
    }

    /**
     * Getter for the name.
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for the start time.
     * @return the start time
     */
    public Integer getStart() {
        return start;
    }

    /**
     * Getter for the end time.
     * @return the end time
     */
    public Integer getEnd() {
        return end;
    }

    /**
     * A useful helper function to return the length of a meeting, defined as end
     * time minus start time.
     * @return the length of the meeting
     */
    public Integer getLength() {
        return end - start;
    }

    /**
     * This was helpful while I was testing/debugging. It's not needed for any of
     * the official output requirements of the lab.
     * @return a string representation of the meeting
     */
    public String toString() {
        return name + "[" + start + "," + end + "]";
    }

    /**
     * Check whether this meeting conflicts with another one.
     * @param otherMeeting the other meeting to compare with
     */
    public boolean overlapsWith(Meeting otherMeeting) {
        return !((start >= otherMeeting.getEnd()) || (otherMeeting.getStart() >= end));
    }

    /**
     * Greedy algorithm sorting a list of meetings by start time.
     * Earliest start time chosen first.
     * @param meetings list of meetings
     */
    public static void sortByStartTime(Meeting[] meetings) {
        Meeting[] sortedMeetings = meetings.clone();
        // sort meetings by start time
        for (int i = 0; i < sortedMeetings.length; i++) {
            for (int j = 0; j < sortedMeetings.length - 1; j++) {
                if (sortedMeetings[j].getStart() > sortedMeetings[j + 1].getStart()) {
                    Meeting temp = sortedMeetings[j];
                    sortedMeetings[j] = sortedMeetings[j + 1];
                    sortedMeetings[j + 1] = temp;
                }
            }
        }
        printScheduledMeetings(scheduleMeetings(sortedMeetings), "start time");
    }

    /**
     * Greedy algorithm sorting a list of meetings by length.
     * Shortest meeting chosen first.
     * @param meetings list of meetings
     */
    public static void sortByLength(Meeting[] meetings) {
        Meeting[] sortedMeetings = meetings.clone();
        // sort meetings by length
        for (int i = 0; i < sortedMeetings.length; i++) {
            for (int j = 0; j < sortedMeetings.length - 1; j++) {
                if (sortedMeetings[j].getLength() > sortedMeetings[j + 1].getLength()) {
                    Meeting temp = sortedMeetings[j];
                    sortedMeetings[j] = sortedMeetings[j + 1];
                    sortedMeetings[j + 1] = temp;
                }
            }
        }
        printScheduledMeetings(scheduleMeetings(sortedMeetings), "length");
    }

    /**
     * Greedy algorithm sorting a list of meetings by end time.
     * Earliest end time chosen first.
     * @param meetings list of meetings
     */
    public static void sortByEndTime(Meeting[] meetings) {
        Meeting[] sortedMeetings = meetings.clone();
        // sort meetings by end time
        for (int i = 0; i < sortedMeetings.length; i++) {
            for (int j = 0; j < sortedMeetings.length - 1; j++) {
                if (sortedMeetings[j].getEnd() > sortedMeetings[j + 1].getEnd()) {
                    Meeting temp = sortedMeetings[j];
                    sortedMeetings[j] = sortedMeetings[j + 1];
                    sortedMeetings[j + 1] = temp;
                }
            }
        }
        printScheduledMeetings(scheduleMeetings(sortedMeetings), "end time");
    }

    /**
     * Greedy algorithm sorting a list of meetings by the number of conflicts (overlaps)
     * that it has with other meetings.
     * The meeting with the fewest conflicts is chosen first.
     * @param meetings list of meetings
     */
    public static void sortByConflicts(Meeting[] meetings) {
        Meeting[] sortedMeetings = meetings.clone();
        // sort meetings by number of conflicts
        for (int i = 0; i < sortedMeetings.length; i++) {
            for (int j = 0; j < sortedMeetings.length - 1; j++) {
                int conflicts1 = 0;
                int conflicts2 = 0;
                for (Meeting meeting : sortedMeetings) {
                    if (sortedMeetings[j].overlapsWith(meeting)) conflicts1++;
                    if (sortedMeetings[j + 1].overlapsWith(meeting)) conflicts2++;
                }
                if (conflicts1 > conflicts2) {
                    Meeting temp = sortedMeetings[j];
                    sortedMeetings[j] = sortedMeetings[j + 1];
                    sortedMeetings[j + 1] = temp;
                }
            }
        }
        printScheduledMeetings(scheduleMeetings(sortedMeetings), "conflicts");
    }

    /**
     * Helper function to schedule meetings.
     * If two meetings overlap, the "best" one is chosen where "best" is defined as
     * earliest start / end time / the shortest length / the fewest conflicts.
     * Else add to list of scheduled meetings.
     * @param sortedMeetings list of meetings sorted by start / end time / length / number of conflicts
     */
    private static ArrayList<Meeting> scheduleMeetings(Meeting[] sortedMeetings) {
        ArrayList<Meeting> scheduledMeetings = new ArrayList<>();
        for (Meeting meeting : sortedMeetings) {
            boolean overlap = false;
            for (Meeting scheduledMeeting : scheduledMeetings) {
                if (meeting.overlapsWith(scheduledMeeting)) {
                    overlap = true;
                    break;
                }
            }
            if (!overlap) scheduledMeetings.add(meeting);
        }
        return scheduledMeetings;
    }

    /**
     * Prints the names of the meetings in the given list and the number of meetings scheduled.
     * @param meetings list of scheduled meetings
     * @param sortBy the way the meetings were sorted
     */
    private static void printScheduledMeetings(ArrayList<Meeting> meetings, String sortBy) {
        System.out.print("{");
        for (int i = 0; i < meetings.size(); i++) {
            System.out.print(meetings.get(i).getName());
            if (i < meetings.size() - 1) System.out.print(", ");
        }
        System.out.println("}");
        System.out.println("Results of rank by " + sortBy + ": " + meetings.size() + " meetings scheduled");
    }
}
