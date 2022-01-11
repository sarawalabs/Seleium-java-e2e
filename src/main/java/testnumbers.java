/* IMPORTANT: Multiple classes and nested static classes are supported */

//  uncomment this if you want to read input.
//imports for BufferedReader
import java.io.InputStreamReader;

//import for Scanner and other utility classes
import java.util.*;

// Warning: Printing unwanted or ill-formatted data to output will cause the test cases to fail

class testnumbers {
	public static void main(String args[]) throws Exception {

		ArrayList<Integer> dividers = new ArrayList<Integer>();
		int count = 0;
		// Scanner
		Scanner s = new Scanner(System.in);
		String name = s.nextLine();

		String[] Inputs = name.split(" ");

		List<String> toList = Arrays.asList(Inputs);

		// get the number in the array list list with the increasing order.
		ArrayList<Integer> numbers = new ArrayList<Integer>();

		for (String stringValue : toList) {

			// Convert String to Integer, and store it into integer array list.
			numbers.add(Integer.parseInt(stringValue));
		}

		// Get the size of the numbers
		int arraysize = numbers.size();

		System.out.println("the array size is: " + arraysize);

		int maxNum = numbers.get(0);

		int minNum = numbers.get(0);

		for (int i = 0; i < arraysize; i++) {

			if (numbers.get(i) > maxNum) {

				maxNum = numbers.get(i);
			}

			else {
				continue;
			}

			System.out.println("the maximum number is: " + maxNum);
		}

		numbers.remove(maxNum);

		for (int i = 1; i <= arraysize; i++) {

			for (int summers : numbers) {

				summers += summers;

				if (summers > maxNum) {

					System.out.println(summers);;
				}

				else {

					
					continue;

				}

		

			}

			ArrayList<Integer> FinalList = removeDuplicates(dividers);

			System.out.println("the final count: " + FinalList.size());

		}

	}

	// Now remove duplicate integers from an ArrayList
	public static <Integer> ArrayList<Integer> removeDuplicates(ArrayList<Integer> list) {
		ArrayList<Integer> newList = new ArrayList<Integer>();

		for (Integer element : list) {

			if (!newList.contains(element)) {

				newList.add(element);
			}
		}

		return newList;
	}

}
