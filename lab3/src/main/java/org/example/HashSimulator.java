package org.example;

/**
 * Lab3, simulates inserting N items a hash table given a list of N input strings and
 * a size to use for the hash table. It also contains 3 hash functions to use for hashing the keys.
 * @author Towa Quimbayo, A01086002, Set U
 */
public class HashSimulator {
  /**
   * Simulates inserting N items a hash table given a list
   * of N input strings and a size to use for the hash table
   * @param keys array of strings, the keys to be hashed and inserted
   * @param size size of the hash table
   * @return an array of 6 ints representing:
   * number of collisions and probes that occur when hashing with each H1(), H2() and H3() hash function.
   */
  public int[] runHashSimulation(String[] keys, int size) {
    int[] results = new int[6];
    for (int i = 0; i < results.length; i += 2) {
      String[] hashTable = new String[size];
      for (String key : keys) {
        int index = i / 2 == 0 ? H1(key, size) : i / 2 == 1 ? H2(key, size) : H3(key, size);
        if (hashTable[index] != null) {
          results[i]++; // Increment collision
          while (hashTable[index] != null) {
            index = index + 1 == size ? 0 : index + 1; // Linear probing, reset to 0 if index is at the end of the array
            results[i + 1]++; // Increment probes
          }
        }
        hashTable[index] = key;
      }
    }
    return results;
  }

  /**
   * Hash function 1.
   * This function calculates the sum of the values of each character in the string (A=1, B=2, C=3, etc.)
   * and mods the sum by the size of the hash table which will be the hash value.
   * @param key the string key to hash
   * @param size the size of the hash table being used
   * @return the hash value of the key
   */
  public int H1(String key, int size) {
    char[] arr = key.toCharArray();
    int sum = 0;
    for (char c : arr) sum += c - 'A' + 1;
    return sum % size;
  }

  /**
   * Hash function 2.
   * This function calculates the sum of values from multiplying the values of each character in the string
   * (A=1, B=2, C=3, etc.) by 26^i where i is the index of the character in the string.
   * It then mods the sum by the size of the hash table which will be the hash value.
   * @param key the string key to hash
   * @param size the size of the hash table being used
   * @return the hash value of the key
   */
  public int H2(String key, int size) {
    char[] arr = key.toCharArray();
    long sum = 0;
    for (int i = 0; i < arr.length; i++) sum += (arr[i] - 'A' + 1) * (long) Math.pow(26, i);
    return (int) (sum % size);
  }

  /**
   * Hash function 3 (my own hash function).
   * This function calculates the sum of values from multiplying the ASCII values of each character in the string by 37
   * and adding the ASCII value of the character mod 37. It then mods the sum by the size of the hash table.
   * Source: This is my custom function I made on my own and did not use any outside sources to create it.
   * @param key the string key to hash
   * @param size the size of the hash table being used
   * @return the hash value of the key
   */
  public int H3(String key, int size) {
    char[] arr = key.toCharArray();
    int sum = 0;
    for (char c : arr) sum += c * 37 + c % 37;
    return sum % size;
  }
}
