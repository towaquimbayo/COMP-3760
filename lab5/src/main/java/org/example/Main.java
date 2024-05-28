package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Lab5, driver class.
 * This class reads a text file containing a list of meetings and its time interval (start / end),
 *
 * @author Towa Quimbayo, A01086002, Set U
 */
public class Main {

    /**
     * Reads a text file containing a list of meetings and its time interval (start / end),
     * one per 2 lines and returns an array of Meetings.
     *
     * @param filename text file containing a list of meetings, one per line
     * @return an array of Meetings
     * @throws IOException if the file cannot be read
     */
    public static Meeting[] getMeetings(String filename) throws IOException {
        String[] listOfStrings = Files.readAllLines(Paths.get(filename)).toArray(new String[0]);
        Meeting[] meetings = new Meeting[listOfStrings.length / 2];
        for (int i = 0; i < listOfStrings.length; i += 2) {
            String[] time = listOfStrings[i + 1].split(" ");
            meetings[i / 2] = new Meeting(listOfStrings[i], Integer.parseInt(time[0]), Integer.parseInt(time[1]));
        }
        return meetings;
    }

    public static void main(String[] args) {
        try {
            String path = "src/main/java/org/example/";
            Meeting[] meetings = getMeetings(path + "meeting4.txt");
            System.out.println("\nOutput of greedy algo #1 (ascending start-time):");
            Meeting.sortByStartTime(meetings);
            System.out.println("\nOutput of greedy algo #2 (ascending length):");
            Meeting.sortByLength(meetings);
            System.out.println("\nOutput of greedy algo #3 (ascending end-time):");
            Meeting.sortByEndTime(meetings);
            System.out.println("\nOutput of greedy algo #4 (ascending number of conflicts):");
            Meeting.sortByConflicts(meetings);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}