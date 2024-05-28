package ca.bcit;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

	//
	// Reads the input file given by "filename", assumed to contain a list of
	// integer numbers. Clones the numbers into a plain array and returns the array.
	//
public class ReadArray {
	public static Integer[] readArray(String filename) throws IOException {
		File file = new File(filename);
		Scanner sc = new Scanner(file);

		// Read the items initially into an ArrayList (for dynamic growth)
		ArrayList<Integer> input_list = new ArrayList<Integer>();
		while (sc.hasNext()) {
			int n = sc.nextInt();
			input_list.add(n);
		}
		sc.close();

		// Copy the ArrayList to an Integer[] array of the proper size
		Integer[] arr = new Integer[input_list.size()];
		arr = input_list.toArray(arr);
		return arr;
	}
}

