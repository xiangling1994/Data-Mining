package pre_processes;



import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class FindBinary {
	
	// Read target attribute name
	public static String[] readTargetAttributeName(List<List<String>> DbMap,List<String> title) {
		Scanner scan = new Scanner(System.in);
		
		List<List<String>> tem = new ArrayList<List<String>>();
		
//		tem = retrieveBinaryAttributes(DbMap,title);
//		
//		// Input target attribute name
//		int index = 0;
//		if (tem.size() <= 1)
//			System.out.println("The following is binary attribute: ");
//		else
//			System.out.println("The following are binary attributes: ");
//		for (int i = 0; i<tem.size(); i++)
//			System.out.println("\t" + (i+1) + ". " + tem.get(i).get(0));
//		System.out.print("\nPlease select one attribute shown above (by index): ");
		
		tem = retrieveAllAttributes(DbMap,title);
		
		// Input target attribute name
		int index = 0;
		if (tem.size() <= 1)
			System.out.println("The following is available attribute: ");
		else
			System.out.println("The following are available attributes: ");
		for (int i = 0; i<tem.size(); i++)
			System.out.println("\t" + (i+1) + ". " + tem.get(i).get(0));
		System.out.print("\nPlease select one attribute shown above (by index): ");
				
		// Get input
		try {
			index = scan.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("Incorrect index format.");
			System.exit(1);
		}
		
		if (index<1 || index>tem.size()) {
			System.out.println("Index out of range.");
			System.exit(1);
		}
		
		// 
		String[] ta = {tem.get(index-1).get(0),tem.get(index-1).get(1)};
		return ta;
	}
	
	// Retrieve all binary attributes
	private static List<List<String>> retrieveBinaryAttributes(List<List<String>> DbMap,List<String> title) {
		List<List<String>> tem = new ArrayList<List<String>>();
		
		for (int i = 0; i<DbMap.size(); i++) 
			if (DbMap.get(i).size() == 2) {
				List<String> s = new ArrayList<String>();
				s.add(title.get(i));
				s.add(Integer.toString(i));
				tem.add(s);
			}
		
		return tem;
	}
	
	// Retrieve all attributes
	private static List<List<String>> retrieveAllAttributes(List<List<String>> DbMap,List<String> title) {
		List<List<String>> tem = new ArrayList<List<String>>();
		
		for (int i = 0; i<DbMap.size(); i++) {
				List<String> s = new ArrayList<String>();
				s.add(title.get(i));
				s.add(Integer.toString(i));
				tem.add(s);
			}
		
		return tem;
	}
}
