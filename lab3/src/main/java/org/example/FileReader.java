package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileReader {
  /**
   * Reads a text file containing a list of names, one per line, and returns an array of Strings
   * @param fileName: text file containing a list of names, one per line
   * @return
   * @throws IOException
   */
  public static String[] readStrings(String fileName) throws IOException {
    // read the strings as an ArrayList
    List<String> listOfStrings = Files.readAllLines(Paths.get(fileName));
    // convert the ArrayList to a plain Array of Strings
    return listOfStrings.toArray(new String[0]);
  }
}
