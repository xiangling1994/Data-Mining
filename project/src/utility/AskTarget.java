package utility;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class AskTarget {
	
	private static Scanner scan ;
	
	
	public static String[ ] findTarget(List<String> title)
	{
		scan = new Scanner(System.in);
		int index = 0;
		if (title.size() <= 1)
			System.out.println("The following is available attribute: ");
		else
			System.out.println("The following are available attributes: ");
		for (int i = 0; i<title.size(); i++)
			System.out.println("\t" + (i+1) + ". " + title.get(i));
		System.out.print("\nPlease select one attribute shown above (by index): ");
				
		// Get input
		try {
			index = scan.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("Incorrect index format.");
			System.exit(1);
		}
		
		if (index<1 || index>title.size()) {
			System.out.println("Index out of range.");
			System.exit(1);
		}
		String[] o={title.get((index-1)),Integer.toString((index-1))};
		return o;
		
	}
}
