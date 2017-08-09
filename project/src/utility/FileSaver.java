package utility;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import interfaces.SaveFile;



public class FileSaver {
	
	private File file;
	
	public void print(SaveFile ss,String name) throws IOException {
		PrintStream console = System.out;
		
		file = new File(name);
		
		FileOutputStream fos = new FileOutputStream(file);
		
		PrintStream ps = new PrintStream(fos);
		
		System.setOut(ps);
		
		ss.printIntoFile();
		
		System.setOut(console);
		
		ps.close();
		
		fos.close();
	
	}
	
}
