package org.example;
import java.util.ArrayList;

/**
 * Lab2, generating all palindrome sequences of length n that contain up to A, B, C.
 * Decrease and conquer, decrease by constant algorithm in recursive method.
 * @author Towa Quimbayo, A01086002, Set U
 */
public class Lab2 {
  /**
   * Generates all palindrome sequences of length n that contain up to A, B, C.
   * Recursively calls itself to generate palindrome sequences of length n - 2.
   * Base cases are n = 1 and n = 2.
   * @param n length of palindrome sequence
   * @return ArrayList of palindrome sequences
   */
  public ArrayList<String> generatePalindromeSequences(int n) {
    // Assuming n >= 1
    ArrayList<String> result = new ArrayList<>();
    if (n == 1) {
      result.add("A");
      result.add("B");
      result.add("C");
      return result;
    }
    if (n == 2) {
      result.add("AA");
      result.add("BB");
      result.add("CC");
      return result;
    }
    // Find the palindrome sequences of length n - 2 and
    // insert A, B, C at the beginning and end of each sequence.
    ArrayList<String> temp = generatePalindromeSequences(n - 2);
    for (String s : temp) {
      result.add("A" + s + "A");
      result.add("B" + s + "B");
      result.add("C" + s + "C");
    }
    return result;
  }
}
