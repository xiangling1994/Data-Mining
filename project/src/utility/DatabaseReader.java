package utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DatabaseReader {
	private final CharSequence COMMA=",";
	private final CharSequence SEMICOLON=";";
	private final CharSequence SPACE=" ";
	private final String COMMASPACE=", ";
	private final int AGE=0;
	
	private ArrayList<Integer> dindex;
	private boolean StringInput;
	//private final char[] s={SEMICOLON,COMMA,SPACE};
	private CharSequence theSeperate;
    private List<List<String>> DB;		

	public DatabaseReader() {
		dindex=new ArrayList<Integer>();
//		dindex.add(2);
		

		StringInput=false;
		theSeperate=COMMA;
	}
	
	public void scan(String name) {
		DB = null;
		DB = new ArrayList<List<String>>();
		checkFileExistence(name);
		
	}
	
	private void checkFileExistence(String name) {
		File f = new File(name);
		if (! f.exists()) {
			System.out.println("File not found: " + name);
			System.exit(1);
		}
		
		readOd(name);
	}
	
	private void readOd(String dataBasename) {
		// Read database
		String line = null;
		int lc = 0;
		
		try {
			FileReader fileReader = new FileReader(dataBasename);
	        BufferedReader bufferedReader = new BufferedReader(fileReader);
	        while ((line = bufferedReader.readLine()) != null) {
	        	lc++;
	        	addRowIntoDB(lc-1, line);
	        }   
	        bufferedReader.close();
	        fileReader.close();
	        lc--;
	    } 
		catch (IOException e) {
	    	System.out.println("Reading file error: " + dataBasename);
	    	System.exit(1);
	    }
		
	}

	private void addRowIntoDB(int lc, String line) {
		List<String> rw = new ArrayList<String>();
		
		if(lc==0)
			separatrix(line);
		rw = retrieveRow(lc, line);
		
		if (rw.size()>0)
			DB.add(rw);
	}


	private List<String> retrieveRow(int lc, String line) {
		if(!StringInput)
			return retrieveRowSe(lc, line, theSeperate.charAt(0));
		else
			return retrieveRowSe(lc, line, COMMASPACE);
		
		
		
	}
	
	private List<String> retrieveRowSe(int lc, String line, String sepreate) {
		int j=0;
		int d=1;
		List<String> l = new ArrayList<String>();
		while (! line.isEmpty()) {
			if(line.contains(sepreate))
				j=line.indexOf(sepreate, 0);
			else{
				
					l.add(line);
			
				break;
			}
			
	
			String s = line.substring(0,j);
			l.add(s);

			j=j+sepreate.length();
			
			line = line.substring(j, line.length());
			
		
		}
//		if(!l.isEmpty()){
//			l.remove(l.size()-1);
//		}
//		if(!l.isEmpty()){
//			
//			l.remove(2);
//			if(lc!=0){
//			if(Double.parseDouble(l.get(0))>=90)
//				l.set(0, "1"+AGE);
////				else if(Double.parseDouble(l.get(0))>=80)
////					l.set(0, "2"+AGE);
//				else if(Double.parseDouble(l.get(0))>=70)
//					l.set(0, "3"+AGE);
////						else if(Double.parseDouble(l.get(0))>=60)
////							l.set(0, "4"+AGE);
//							else if(Double.parseDouble(l.get(0))>=50)
//								l.set(0, "5"+AGE);
////								else if(Double.parseDouble(l.get(0))>=40)
////									l.set(0, "6"+AGE);
////									else if(Double.parseDouble(l.get(0))>=30)
////										l.set(0, "7"+AGE);
////										else if(Double.parseDouble(l.get(0))>=20)
////											l.set(0, "8"+AGE);
////											else if(Double.parseDouble(l.get(0))>=10)
////												l.set(0, "9"+AGE);
////										
////			
////			if(l.get(13).contains("<="))
////				l.set(13, "<=50k");
////			else
////				l.set(13, ">50k");
				
//			}
//	}
//		if(lc==0)
//		System.err.println(l);
		
		return l;
	}
	
	private List<String> retrieveRowSe(int lc, String line, char sepreate) {
		int i;
		int d=1;
		List<String> l = new ArrayList<String>();
	
		while (! line.isEmpty()) {
			for (i = 0; i<line.length(); i++) 
				if (line.charAt(i) == sepreate)
					break;
			String s = line.substring(0, i);
			l.add(s);
			for (; i<line.length(); i++)
				if (line.charAt(i) != sepreate)
					break;
			line = line.substring(i, line.length());
		}
		if(!l.isEmpty()){
//			l.remove(0);
//			l.remove(0);
		}
		return l;
	}
	
	private void separatrix(String line){
		
		if(line.contains(COMMASPACE)){
			StringInput=true;
		}
		if(line.contains(SEMICOLON)){
			theSeperate=SEMICOLON;
		}
		else if(line.contains(COMMA)){
			theSeperate=COMMA;
		}
		else if(line.contains(SPACE)){
			theSeperate=SPACE;
		}
		
	}
	
	public void DeleteColumn(List<List<String>> DB)
	{
		System.out.println("Selete the column you want to delete");
		String o[]=AskTarget.findTarget(DB.get(0));
		dindex.add(Integer.parseInt((o[1])));
	}
	
	public List<List<String>> getDB() {
		return DB;
	}
}
