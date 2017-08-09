package k_means;

import java.util.ArrayList;
import java.util.Random;

public class Point {

	private ArrayList<Double> points;
	private int cluster_num;
	private double distanceToCen;
	
	//create a new pint array
	public Point(){
		points = new ArrayList<Double>();
		cluster_num = 0;
	}
	
	//
	public Point(int i){
		
		double asdd = 1;
		points=new ArrayList<Double>();
		for(int l = 0; i < i; i++){
			points.add(asdd);
		}
	}
	
	//add the number into this point
	public void addP(double p){

		points.add(p);
	}
	
	 public int getCluster() {
	        return this.cluster_num;
	    }
	 
	 public void setCluster(int n) {
	        this.cluster_num = n;
	    }
	 
	 public int size(){
		 return points.size();
	 }
	 
	 public double get(int i){
		 return points.get(i);
	 }
	 
	//Calculates the distance between two points.
	    public static double distance(Point p, Point centroid) {
	    	double differentOfSquare = 0;
	    	double sum = 0;
	    	
	    	for(int i = 0; i < p.size(); i++){
	    		sum = Math.pow(p.get(i)-centroid.get(i),2) + sum;
	    	}
	    	
	    	
	        return Math.sqrt(sum);
	    }
	    
	  public void set(int i, double e){
		  points.set(i, e);
	  }
	    
	  public String toString(){
		  String point = "(";
		  for(int i = 0;i<size();i++){
			  
			  point = point+points.get(i) + ",";
			  
		  }
		 point = point.substring(0, point.length()-1);
		 point = point + ")";
		 return point;
	  }
	
	  
	  public ArrayList<Double> getPoint(){
		  return points;
	  }
	  
	  //return a new point
	  public Point copyPoint(){
		  Point  p = new Point();
		  for(int i = 0; i < points.size(); i++)
			  p.addP(points.get(i));
		  return p;
			  
	  }
	  
	  //
	public void setNew(double[] sumOfPoints) {
		// TODO Auto-generated method stub
		points.clear();
		for(int i = 0;i < sumOfPoints.length;i++){
			points.add(i,sumOfPoints[i]);
		}
	}
//	public void clear() {
//		// TODO Auto-generated method stub
//		for( int i=0; i<points.size();i++){
//			points.set(i, (double) (0));
//		}
//	}
}
