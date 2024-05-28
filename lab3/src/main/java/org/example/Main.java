package org.example;

import java.util.Arrays;

public class Main {
  public static void main(String[] args) {
    try {
      String[] keys = FileReader.readStrings("src/main/java/org/example/5575names.txt");
      HashSimulator hashSimulator = new HashSimulator();
      int n = 5575;
      int[] multiples = {1, 2, 5, 10, 100};
      for (int multiple : multiples) {
        int[] results = hashSimulator.runHashSimulation(keys, n * multiple);
        System.out.println("Hash table size " + n * multiple + " " + Arrays.toString(results));
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}