package utility;

import java.util.ArrayList;
import java.util.List;

public class Covariance {

	private static ArrayList<Double> A;
	private static ArrayList<Double> B;
	private static double sumA;
	private static double Average_A;
	private static double sumB;
	private static double Average_B;
	private static double total;
	
	
	
	public static double correlation(List<List<String>> DB1, int a, int b)
	{
		
		double x;
		A=new ArrayList<Double>();
		B=new ArrayList<Double>();
		
		for(int i=1;i<DB1.size();i++)
		{
		//	System.out.println(DB1.get(i).get(a));
			A.add((Double.parseDouble(DB1.get(i).get(a))));
			B.add((Double.parseDouble(DB1.get(i).get(b))));
//			if(DB1.get(i).get(b).equals("<=50K")){
//				B.add((double) 0);
//			}
//			else
//				B.add((double) 1);
		}
		
		
		sumA=0;
		sumB=0;
		total=0;
		for(int i=0;i<A.size();i++){
			sumA= (A.get(i)+sumA);
			sumB=(B.get(i)+sumB);
			total=total+A.get(i)*B.get(i);
		}
		total=total/A.size();
		Average_A=sumA/A.size();
		Average_B=sumB/B.size();
		
		System.out.println(total-Average_A*Average_B);
		
		return 0;
	}
	
	
	
	
}
