package ca.bcit;

public class Main {
  public static void main(String[] args) {
    try {
      TripleFinder tripleFinder = new TripleFinder();
      String path = "src/main/java/ca/bcit/";
      Integer[] nums = ReadArray.readArray(path + "data4.txt");
      System.out.println("Numbers of items in the file: " + nums.length);

      // Brute force algorithm
      tripleFinder.findTripleBruteForce(nums);
      Integer[] bruteForceTriple = tripleFinder.getTheTriple();
      System.out.println("\nBrute force approach:");
      for (Integer integer : bruteForceTriple) {
        System.out.println(integer);
      }

      // Clever algorithm
      tripleFinder.findTripleClever(nums);
      Integer[] cleverTriple = tripleFinder.getTheTriple();
      System.out.println("\nClever approach:");
      for (Integer integer : cleverTriple) {
        System.out.println(integer);
      }

      // Print run time
      System.out.println("\nBrute force run time: " + tripleFinder.getBruteForceRuntime() + " ms");
      System.out.println("\nClever run time: " + tripleFinder.getCleverRuntime() + " ms");

      // Print number of operations
      System.out.println("\nBrute force operations count: " + tripleFinder.getBruteForceOperations());
      System.out.println("\nClever operations count: " + tripleFinder.getCleverOperations());
    } catch (Exception e) {
      System.out.println("Error: " + e.getMessage());
    }
  }
}