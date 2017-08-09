package pre_processes;

import java.util.List;

import entities.Constrains;
import entities.Databases;
import utility.AskTarget;
import utility.DatabaseInitialization;

public class Initialization {

	public static void initialize(String NameOfDB1, List<List<String>> DB1, String NameOfDB2, List<List<String>> DB2, List<List<String>> mapTrain, Databases db, Constrains cons,boolean Asktarget) {
		
		
		DatabaseInitialization di = new DatabaseInitialization();
		
		mapTrain.addAll(di.analyzeOd(DB1));
		
		
		db.setTitleTrain(DB1.get(0));
		DB1.remove(0);
		db.setOdTrain(DB1);		
		
		db.setOdTrainMap(mapTrain);
//		db.setOdTestMap(mapTest);
		db.setRowCount((DB1.size()));
		//String o[]=FindBinary.readTargetAttributeName(db.getOdTrainMap(), db.getTitleTrain());
		cons.setFilenameTrain(NameOfDB1);
		
		if(Asktarget){
		String o[]=AskTarget.findTarget(DB1.get(0));
		cons.setTarget(o[0]);
		cons.setTargetIndex(Integer.parseInt(o[1]));
		}
		
		if(DB2!=null)
		{
			db.setTitleTest(DB2.get(0));
			DB2.remove(0);
			db.setOdTest(DB2);
			cons.setFilenameTest(NameOfDB2);
		}
	}
	
	public static void initialize(String NameOfDB1, List<List<String>> DB1,List<List<String>> mapTrain, Databases db, Constrains cons, boolean ask) {
		
		Initialization.initialize(NameOfDB1, DB1, null, null, mapTrain, db, cons,ask);
	
	}
}