/*
 * This class implement the genernal k means
 */

package k_means;
import java.util.*;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import entities.Constrains;
import entities.Databases;

public class GenKmeans extends GeneralAlgorithm {

	private ArrayList<Point> points;
	private ArrayList<Cluster> clusters;
	private List<List<String>> database;
	private int NumOfCLusters = 2;
	private int pointL;
	
	public GenKmeans(){
		points = new ArrayList<Point>();
		clusters = new ArrayList<Cluster>();

		
	}
	
	
	public void getData(Databases db, Constrains cons) {
		this.db = db;
		this.cons = cons;
		database = db.getOdTrain();
		NumOfCLusters = cons.getNumOfCluster();
	}
	
	public void run() throws IOException {
		Kmeans();
		
	}
	/*
	 * convert the original database to numerical databse for k kmeans
	 * delete all the duplicate points
	 * find initial centroid
	 */
	private void Kmeans() throws IOException {
		MapDataBase();
		pointL = points.get(0).size();
		deleteRepeat();
		init();
		calculate();
		ArrayList<Integer> a=new ArrayList<Integer>();
	for(Cluster c:clusters){
		a.add(c.getPoints().size());
	}
	
	Collections.sort(a);
	for(int i=(a.size()-1);i>=0;i--){
		System.out.print(a.get(i)+"\t");
	}
	
	System.out.println();
	}
	/*
	 * random pick k point from the given data set as initial centroids.
	 */
	public void init() {
    	
		//Create Points
    	Random r = new Random();
    	//Create Clusters
    	//Set Random Centroids
    	for (int i = 0; i < NumOfCLusters; i++) {
    		Cluster cluster = new Cluster(i);
    		
    		Point np = points.get(r.nextInt(points.size()));
    		Point centroid = np.copyPoint();
    		cluster.setCentroid(centroid);
    		clusters.add(cluster);

    	}
    	
    }
	/*
	 * delete the repeat points
	 */
	public void deleteRepeat(){
		double distance = 0;
		
		for(int i = 0; i < points.size(); i++){
			for(int j = i+1; j< points.size(); j++){
				if(i != j){
				distance=Point.distance(points.get(i), points.get(j));
				if(distance == 0){
					points.remove(j);
					j--;
				}
				}
			}
		}
	}
	
	/*
	 * 
	 */
	 public void calculate() {
	        boolean finish = false;
	        int iteration = 0;
	        
	        // Add in new data, one at a time, recalculating centroids with each new one. 
	        while(!finish) {

	        	clearClusters();
	        
	        	ArrayList<Point> lastCentroids = getCentroids();

	        	//Assign points to the closer cluster
	        	assignCluster();
	            
	            //Calculate new centroids.
	        	calculateCentroids();
	        	
	        	iteration++;
	        	
	        	ArrayList<Point> currentCentroids = getCentroids();

	        	//Calculates total distance between new and old Centroids
	        	double distance = 0;
	        	for(int i = 0; i < lastCentroids.size(); i++) {
	        		distance += Point.distance(lastCentroids.get(i),currentCentroids.get(i));
	        	}
//	        	System.out.println("#################");
//	        	System.out.println("Iteration: " + iteration);
//	        	System.out.println("Centroid distances: " + distance);
     	        	
	        	if(distance == 0) {
	        		finish = true;
	        	}
	        	
	        }
	    }
	 
	 //assign the points form the given data set to its nearsest centroids.
	   private void assignCluster() {
		// TODO Auto-generated method stub
		double min=Double.MAX_VALUE;
		int cluster = 0;                 
	    double distance = 0.0; 
	    for(Point point : points) {
	    	min=Double.MAX_VALUE;
            for(int i = 0; i < NumOfCLusters; i++) {
            	Cluster c = clusters.get(i);
                distance = Point.distance(point, c.getCentroid());
                if(distance == 0||distance < min){
                    min = distance;
                    cluster = i;
                }
            }
            point.setCluster(cluster);
            
            clusters.get(cluster).addPoint(point.copyPoint());
        }
	}

//find the current centroids for each clusters
	private ArrayList<Point> getCentroids() {
		ArrayList<Point> centroids = new ArrayList<Point>();
	    	for(int i=0;i< clusters.size();i++) {
	    		Point aux = clusters.get(i).getCentroid();
	    		
	    		Point point = aux.copyPoint();
	    		centroids.add(point);
	    	}
	    	return centroids;
	    }
	 
	//convert Arraylist<arraylist<String>> into ArrayList<Arraylist<Point>>
	private void MapDataBase() {
		// TODO Auto-generated method stub
		
		int row = 0;
	
		int length = database.get(0).size();

		while(row < db.getRowCount())
		{

			Point newPoint = new Point();
			
			for(int i = 0; i < length; i++){
				
				newPoint.addP( Double.parseDouble((database.get(row).get(i))) );
			}
			
			points.add( newPoint );
			row++;
		}

	}
/*
 * 
 * print the each points
 * 
 */
	public void print(){
		
		for(int i = 0; i < points.size(); i++ ){

			System.out.println(points.get(i).toString());
		}
	}
	/*
	 * This method can clear the points in each clusters
	 * 
	 */
	 private void clearClusters() {
	    	for(Cluster cluster : clusters) {
	    		cluster.clear();
	    	}
	    }
	 
	 /*
	  * this method calcualate a new centroids points for each clusters
	  * 
	  */
	 private void calculateCentroids() {

		 for(int j = 0; j < clusters.size(); j++) {
			 
	            double [] SumOfPoints = new double [pointL];
	        	ArrayList<Point> list = clusters.get(j).getPoints();
	            int n_points = list.size();
	            
	            for(int q = 0; q < list.size(); q++) {
	            	for(int i = 0; i < pointL; i++){
	            		
	            		SumOfPoints[i] = SumOfPoints[i]+list.get(q).get(i);
	            	}
	            }

	            if(n_points != 0) {
	            	for(int i = 0; i < pointL; i++){
	            		
	            		SumOfPoints[i] = SumOfPoints[i]/n_points;
	            	}
	             
	             clusters.get(j).setNewCen(SumOfPoints); 
	            
	            }
	        }
	 }
	 
	


	@Override
	public void printIntoFile() {
		// TODO Auto-generated method stub

	}


}
