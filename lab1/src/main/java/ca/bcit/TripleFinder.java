package ca.bcit;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/*
 * Starter code for Lab 1 - finding a "mult37-triple" in a data file by
 * two different algorithms and comparing the running times.
 *
 * Towa Quimbayo
 * A01086002
 */
public class TripleFinder {

  /*
   * RESULTS STORAGE ZONE Here is where you can declare private members of your class
   * used to save results and other data so that they can be requested from the
   * calling program at any time needed. Keep them private! Their values will be
   * accessed via getter methods.
   */
  private Integer[] theTriple = new Integer[3];
  private long bruteForceStartTime = 0;
  private long bruteForceEndTime = 0;
  private long cleverStartTime = 0;
  private long cleverEndTime = 0;
  private long bruteForceOperations = 0;
  private long cleverOperations = 0;

  /*
   * Brute Force algorithm for finding the mult37-triple.
   */
  public void findTripleBruteForce(Integer[] listOfIntegers) {
    bruteForceStartTime = System.currentTimeMillis();
    for (int i = 0; i < listOfIntegers.length - 2; i++) {
      for (int j = i + 1; j < listOfIntegers.length - 1; j++) {
        for (int k = j + 1; k < listOfIntegers.length; k++) {
          bruteForceOperations++;
          if ((listOfIntegers[i] + listOfIntegers[j] + listOfIntegers[k]) % 37 == 0) {
            theTriple[0] = listOfIntegers[i];
            theTriple[1] = listOfIntegers[j];
            theTriple[2] = listOfIntegers[k];
            bruteForceEndTime = System.currentTimeMillis();
            return;
          }
        }
      }
    }
    bruteForceEndTime = System.currentTimeMillis();
  }

  /*
   * "Clever" algorithm for finding the mult37-triple.
   */
  public void findTripleClever(Integer[] listOfIntegers) {
    cleverStartTime = System.currentTimeMillis();
    int[] arr = new int[37];
    Arrays.fill(arr, -1);

    for (Integer listOfInteger : listOfIntegers) {
      arr[listOfInteger % 37] = listOfInteger;
      cleverOperations++;
    }

    for (int i = 0; i < arr.length - 2; i++) {
      if (arr[i] == -1) continue;
      for (int j = i + 1; j < arr.length - 1; j++) {
        if (arr[j] == -1) continue;
        for (int k = j + 1; k < arr.length; k++) {
          if (arr[k] != -1) {
            cleverOperations++;
            if ((arr[i] + arr[j] + arr[k]) % 37 == 0) {
              theTriple[0] = arr[i];
              theTriple[1] = arr[j];
              theTriple[2] = arr[k];
              cleverEndTime = System.currentTimeMillis();
              return;
            }
          }
        }
      }
    }
    cleverEndTime = System.currentTimeMillis();
  }

  /*
   * Getter for the triple, returns the last triple that was found by this
   * instance of the class (either algorithm).
   */
  public Integer[] getTheTriple() {
    return this.theTriple;
  }

  /*
   * Getter that returns the number of operations performed the last time the
   * Brute Force algorithm was used. An operation should be counted every time
   * the "mod" operator (%) is used for any purpose.
   */
  public long getBruteForceOperations() {
    return bruteForceOperations;
  }

  /*
   * Getter that returns the running time, in milliseconds, of the last time the
   * Brute Force algorithm was used. This running time should include the entire
   * contents of the findTripleBruteForce() method.
   */
  public long getBruteForceRuntime() {
    return bruteForceEndTime - bruteForceStartTime;
  }

  /*
   * Getter that returns the number of operations performed the last time the
   * Clever algorithm was used. An operation should be counted every time
   * the "mod" operator (%) is used fr any purpose.
   */
  public long getCleverOperations() {
    return cleverOperations;
  }

  /*
   * Getter that returns the running time, in milliseconds, of the last time the
   * Clever algorithm was used. This running time should include the entire
   * contents of the findTripleClever() method.
   */
  public long getCleverRuntime() {
    return cleverEndTime - cleverStartTime;
  }

}
