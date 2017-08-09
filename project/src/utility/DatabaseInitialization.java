package utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DatabaseInitialization {

	private List<List<String>> database;
	
	public DatabaseInitialization() {
		this.database = new ArrayList<List<String>>();
	}
	
	public List<List<String>> analyzeOd(List<List<String>> DB) {
				
		buildOdMap(DB);
		return database;
	}
	
	private void oDMapInitialization(List<List<String>> db) {
		List<String> lineOne = db.get(1);
		
		for (int i = 0; i<lineOne.size(); i++) {
			List<String> l = new ArrayList<String>();
			l.add(lineOne.get(i));
			database.add(l);
		}
	}


	// Check every cell in od to build odTrainMap
	private void buildOdMap(List<List<String>> db) {
		// Initialize the odMap first
		oDMapInitialization(db);
		for (int i = 1; i<db.size(); i++) 
			for (int j = 0; j<db.get(i).size(); j++) 			
				// Check every cell in od to build odMap
				checkIndex(j, db.get(i).get(j));
	}
	
	// Check the index in the original database mapping Arraylist
	// If the current String already exist, simply return;
	// Otherwise, add it into the mapping Arraylist, then return.
	private void checkIndex(int column, String s) {
		for (int i = 0; i<database.get(column).size(); i++) 
			if (database.get(column).get(i).equals(s))
				return;
		
		// If the current String is a new one
		// Add this String into mapping Arraylist
		database.get(column).add(s);
	}
	

//	public static String[ ] findTarget(List<String> title)
//	{
//		
//		int index = 0;
//		if (title.size() <= 1)
//			System.out.println("The following is available attribute: ");
//		else
//			System.out.println("The following are available attributes: ");
//		for (int i = 0; i<title.size(); i++)
//			System.out.println("\t" + (i+1) + ". " + title.get(i));
//		System.out.print("\nPlease select one attribute shown above (by index): ");
//				
//		// Get input
//		try {
//			index = scan.nextInt();
//		} catch (InputMismatchException e) {
//			System.out.println("Incorrect index format.");
//			System.exit(1);
//		}
//		
//		if (index<1 || index>title.size()) {
//			System.out.println("Index out of range.");
//			System.exit(1);
//		}
//		String[] o={title.get((index-1)),Integer.toString(index)};
//		return o;
//		
//	}
}
