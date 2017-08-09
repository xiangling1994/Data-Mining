package k_means;

import entities.Constrains;
import entities.Databases;
import interfaces.SaveFile;

 abstract class GeneralAlgorithm implements SaveFile {

	
	protected static Constrains cons;
	protected static Databases db;
	protected static boolean input = false;
	
	
	
	protected  static Constrains getCons() {
		if (cons == null)
			cons= new Constrains();

		return cons;
	}

	protected static Databases getDb() {
		if (db == null)
			 db = new Databases();
		
		return db;
	}

}
