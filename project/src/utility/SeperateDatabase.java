package utility;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import k_means.Point;

public class SeperateDatabase {

final static int MAX_STACK=500;
final static int PROCESS_NUM=250;
	
private static int i;
private static List<String> title;
private static Random rn;

	public static void seperateDatabase(List<List<String>>DB1,List<List<String>> DB2,double p, boolean gotTitle){
		
		if(p>=1||p<=0){
			System.out.println("Input err.");
			System.exit(1);
		}
		if(gotTitle){
		title=DB1.get(0);
		DB1.remove(0);
		}
		int total=DB1.size();
		int NumOfDB1 = (int) (DB1.size()*p);
		int NumOfDB2 = DB1.size()-NumOfDB1;
		rn = new Random();
		
		
	/*this part is for prevent the stack overflow*/
		while(NumOfDB2>MAX_STACK){
			total=total-PROCESS_NUM;
			seperateDB(DB1,DB2,total);
			if(total<=(NumOfDB1+MAX_STACK))
				break;
		}
		
		seperateDB(DB1,DB2,NumOfDB1);
		if(gotTitle){
		DB1.add(0, title);
		DB2.add(0,title);
		}
	}
	

	public static void seperateDatabase(List<List<String>>DB1,List<List<String>> DB2,double p){
		seperateDatabase(DB1, DB2,p, true);
	}
	private static void seperateDB(List<List<String>>DB1,List<List<String>> DB2,int size)
	{
		i = rn.nextInt(DB1.size())+0;

		if(DB1.size()!=size){
			DB2.add(DB1.get(i));
			DB1.remove(i);
			seperateDB(DB1,DB2,size);
		}
	}
}
