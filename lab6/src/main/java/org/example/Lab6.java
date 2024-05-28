package org.example;

import java.io.FileWriter;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * Lab6, class that calculates the number of routes to get from the top-left corner
 * to the bottom-right corner of a grid by only being able to move down or right.
 * This class compares the running time of the recursive and dynamic programming
 * solutions to the problem.
 *
 * @author Towa Quimbayo, A01086002, Set U
 */
public class Lab6 {

    /**
     * Calculates and returns SW(m, n) by using the recursive formula:
     * SW(m, n) = SW(m - 1, n) + SW(m, n - 1).
     * @param m number of blocks down the grid path
     * @param n number of blocks right the grid path
     * @return SW(m, n)
     */
    public long SW_Recursive(int m, int n) {
        if (m == 0 || n == 0) return 1;
        return SW_Recursive(m - 1, n) + SW_Recursive(m, n - 1);
    }

    /**
     * Runs SW_Recursive in a loop using values of m and n
     * that are equal to each other. Measure the running time of
     * each call to SW_Recursive and print the results.
     * @param first starting value of m and n
     * @param last ending value of m and n
     */
    public void RunRecursive(int first, int last) {
        for (int i = first; i <= last; i++) {
            long startTime = System.currentTimeMillis();
            long result = SW_Recursive(i, i);
            long endTime = System.currentTimeMillis();
            System.out.println("SW_Recursive(" + i + ", " + i + ") = " + result + ", time is " + (endTime - startTime) + " ms");
        }
    }

    /**
     * Calculates SW(m, n) by using the dynamic programming.
     * @param m number of blocks down the grid path
     * @param n number of blocks right the grid path
     * @return SW(m, n)
     */
    public long SW_DynamicProg(int m, int n) {
        long[][] SW = new long[m + 1][n + 1];
        for (int i = 0; i <= m; i++) SW[i][0] = 1;
        for (int j = 0; j <= n; j++) SW[0][j] = 1;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                SW[i][j] = SW[i - 1][j] + SW[i][j - 1];
            }
        }
        return SW[m][n];
    }

    /**
     * Runs SW_DynamicProg in a loop using values of m and n
     * that are equal to each other. Measure the running time of
     * each call to SW_DynamicProg and print the results.
     * @param first starting value of m and n
     * @param last ending value of m and n
     */
    public void RunDynamicProg(int first, int last) {
        for (int i = first; i <= last; i++) {
            long startTime = System.currentTimeMillis();
            long result = SW_DynamicProg(i, i);
            long endTime = System.currentTimeMillis();
            System.out.println("SW_DynamicProg(" + i + ", " + i + ") = " + result + ", time is " + (endTime - startTime) + " ms");
        }
    }

    // Memoization map, used for Virtual Donut Problems
    private static final Map<String, BigInteger> memo = new HashMap<>();

    /**
     * Calculates the number of lattice paths from the top-left corner to the
     * bottom-right corner of a grid by only being able to move down or right.
     * This method uses the formula C(m+n, m) to calculate the number of paths.
     * The result is memoized to reduce the number of computations.
     * @param m number of blocks down the grid path
     * @param n number of blocks right the grid path
     * @return C(m+n, m)
     */
    public static BigInteger countLatticePaths(int m, int n) {
        // Use symmetry to reduce computations
        if (m < n) {
            int temp = m;
            m = n;
            n = temp;
        }

        // Check if the result is already memoized
        String key = m + "," + n;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        // Calculate C(m+n, m)
        BigInteger result = BigInteger.ONE;
        for (int i = 1; i <= n; i++) {
            result = result
                    .multiply(BigInteger.valueOf(m + i))
                    .divide(BigInteger.valueOf(i));
        }

        // Memoize the result
        memo.put(key, result);
        return result;
    }


    /**
     * Runs countLatticePaths in a loop using values of m and n
     * that are equal to each other. Measure the running time of
     * each call to countLatticePaths and print the results.
     * @param first starting value of m and n
     * @param last ending value of m and n
     */
    public void RunCountLatticePaths(int first, int last) {
        try(FileWriter fw = new FileWriter("vd2.txt", true)) {
            for (int i = first; i <= last; i++) {
                long startTime = System.currentTimeMillis();
                BigInteger result = countLatticePaths(i, i);
                long endTime = System.currentTimeMillis();
                fw.write("RunCountLatticePaths(" + first + ", " + last + ")\n");
                fw.write("countLatticePaths(" + i + ", " + i + ") = " + result + "\n\ntime is " + (endTime - startTime) + " ms\n");
                System.out.println("countPaths(" + i + ", " + i + ") = " + result + ", time is " + (endTime - startTime) + " ms");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
