package org.example;
import java.util.ArrayList;

public class Main {
  public static void main(String[] args) {
    for (int i = 1; i <= 20; i++) {
      ArrayList<String> list = new Lab2().generatePalindromeSequences(i);
      System.out.println("Length " + i + " produces " + list.size() + " sequences.");
    }
  }
}