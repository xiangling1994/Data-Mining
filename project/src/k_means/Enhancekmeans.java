package k_means;


import java.io.IOException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import entities.Constrains;
import entities.Databases;


public class Enhancekmeans extends GeneralAlgorithm {

	
	private ArrayList<Point> points;
	private ArrayList<ArrayList<Point>> subsets;
	private ArrayList<ArrayList<Cluster>> subclusters;
	private ArrayList<Cluster> clusters;
	private List<List<String>> database;
	
	private final int PreNumOfCluster = 30;
	private final int N = 10;
	private int NumOfCLusters;
	
	private int pointL;
	
	
	/*
	 * constrctor
	 */
	public Enhancekmeans(){
		points  = new ArrayList<Point>();
		clusters = new ArrayList<Cluster>();
		subsets = new ArrayList<ArrayList<Point>>();
		subclusters = new ArrayList<ArrayList<Cluster>>();
		
	}
	
	
	/*
	 * get the basic informaitons
	 */
	public void getData(Databases db, Constrains cons) {
		this.db = db;
		this.cons = cons;
		database = db.getOdTrain();
		NumOfCLusters=cons.getNumOfCluster();
	}
	
	/*
	 * run the algorithm
	 */
	public void run() throws IOException{
	
		runEnhanceKmean();

	}
	
	
	/*
	 * run the enhance kmeans
	 */
	public void runEnhanceKmean() {
	
		//map the database
		MapDataBase();
		pointL = points.get(0).size();

		deleteRepeat();

		createSubsets(N);

	//run kmeans for N sudatasets
	//Major time cost.
		for(int i = 0;i < N; i++){
		
				ArrayList<Cluster> a = new ArrayList<Cluster>();
				init(subsets.get(i), PreNumOfCluster,a);
				subclusters.add(i,a);
				calculate(subsets.get(i),subclusters.get(i));
	
			}

	  ArrayList<Cluster> b = new ArrayList<Cluster>();	
	  
	  int mins = calculateMinSSE(subclusters);
	  init(b, subclusters.get(mins));

	  calculate(points,b);

	  mergeCluster(b);

	}
	
	/*
	 * implement k means
	 */
	public void calculate(ArrayList<Point> p , ArrayList<Cluster> c) {
        boolean finish = false;
        int iteration = 0;
        
        // Add in new data, one at a time, recalculating centroids with each new one. 
        while(!finish) {

        	clearClusters(c);
        	ArrayList<Point> lastCentroids = getCentroids(c);
        	assignCluster(p,c);
          
            //Calculate new centroids.
        	calculateCentroids(c);
        
        	iteration++;
        	
        	ArrayList<Point> currentCentroids = getCentroids(c);

        	//Calculates total distance between new and old Centroids
        	double distance = 0;
        	
        	for(int i = 0; i < lastCentroids.size(); i++) {

        		distance += Point.distance(lastCentroids.get(i),currentCentroids.get(i));
        	}
        	        	
        	if(distance == 0) {
        		finish = true;
        	}
   
        }
      
    }
	
	//merge k' clusters into k custers
	private ArrayList<ArrayList<Integer>> merge(int [][] m1){	
		ArrayList<Integer> ele  = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> cList  = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> List  = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> temp2 = new ArrayList<Integer>();
		int size = m1[0].length;
		
		/*
		 * find the pair which co_assco(i,j)>=7
		 */
		for(int i = 0; i < size; i++){
			temp2 = new ArrayList<Integer>();
			temp2.add(i);
			for(int j = 0; j< size; j++){
				if(m1[i][j]>110&&i !=  j){
					temp2.add(j);
				}
				cList.add(temp2);
			}
		}
		
		/*
		 * 
		 */
		for(int i = 0; i <  NumOfCLusters; i++){
			List.add(new ArrayList<Integer>());
		} 
		
		
		for(int i = 0; i<PreNumOfCluster; i++){
			ele.add(i);
		}
		
		
		for(int i = 0; i <  NumOfCLusters; i++){
			for(int j = 0; j < ele.size(); j++){
				if(connect(List.get(i), ele.get(j),cList)){
					List.get(i).add(ele.get(j));
				}
			}
			ele.removeAll(List.get(i));
		}
		List.get((NumOfCLusters-1)).addAll(ele);
		return List;
	}
	
	//
	public boolean connect(ArrayList<Integer> a, int b, ArrayList<ArrayList<Integer>> c){
		int count = 0;
		
		//if the arraylist a contains nothing then add b
		if(a.size() == 0){
			return true;
			
		}
		//if every points in a are connected with point b then true else false
		else{
		for(int i = 0; i < a.size(); i++){
			if(c.get(i).contains(b)){
				count++;
			}
		}
		if(count == a.size()){
			return true;
		}
		else{
			return false;
		}
		}
	}
	
	//
	private void mergeCluster(ArrayList<Cluster> b) {
		// TODO Auto-generated method stub
		
		ArrayList<Point> p = new ArrayList<Point>();
		ArrayList<Cluster> c2 = new ArrayList<Cluster>();
		
		
		for(int i = 0; i < b.size() ;i++){
			p.add(b.get(i).getCentroid());
		}

		//calculate the co_association matrix
		Co_association_matrix matrix = new Co_association_matrix(p.size());
		
		for(int i = 0; i < 200; i++){
			initRan(p, c2);
			assignCluster(p,c2);
			calculate(p,c2);	
			matrix.scan(c2, p);
			c2.clear();
		}
		
		int [][] m1 = matrix.getMatrix();
		ArrayList<ArrayList<Integer>> list = merge(m1);
		ArrayList<Cluster> clu = new ArrayList<Cluster>();
		
		for(int i = 0 ;i< NumOfCLusters; i++){
			clu.add(new Cluster(i));
		}
		
		//cobine subclusters into some big cluster
		for(int i = 0; i< NumOfCLusters; i++){
			for(int j = 0; j < list.get(i).size(); j++){
				 clu.get(i).getPoints().addAll(b.get(list.get(i).get(j)).getPoints());
			}	
		}
		ArrayList<Integer> a=new ArrayList<Integer>();
		for(int i = 0; i < clu.size(); i++){
			a.add(clu.get(i).getPoints().size());

	}
		System.out.print("\t\t");
	for(int i = 0; i < db.getTitleTrain().size(); i++){
		System.out.print(db.getTitleTrain().get(i)+"\t");
	}
	
	System.out.println();
	for(int i=(a.size()-1);i>=0;i--){
		System.out.print("Cluster"+i+": ");
		System.out.print(a.get(i)+"\t");
		System.out.println(Centroid(clu.get(i).getPoints()));
		System.out.println();
	}
	
	System.out.println();
		//print the size of the clusters
//		for(int i = 0; i < clu.size(); i++)
//				System.out.println("Cluster #"+i+" "+clu.get(i).getPoints().size());

	}

	//random pick initial centroid
	public void  initRan(ArrayList<Point> p, ArrayList<Cluster> c) {
    	//Create Points
		//ArrayList<Cluster> c = new ArrayList<Cluster>();
    	Random r = new Random();
    	//Create Clusters
    	//Set Random Centroids
    	for (int i = 0; i < NumOfCLusters; i++) {
    		Cluster cluster = new Cluster(i); 		
    		Point np = p.get(r.nextInt(p.size()));
    		Point centroid = np.copyPoint();
    		cluster.setCentroid(centroid);
    		c.add(cluster);
    	}
  
    }
	
	
	/*
	 * 
	 * pick initial centroid by systemetic method
	 */
	public void  init(ArrayList<Point> p, int K,ArrayList<Cluster> c) {
    	//Create Points
		//ArrayList<Cluster> c = new ArrayList<Cluster>();
		
		ArrayList<Point> p2 = preCen(p,K);
		
    	//Create Clusters
    	//Set Random Centroids
    	for (int i = 0; i < p2.size(); i++) {
    		Cluster cluster = new Cluster(i); 		
    		Point np = p2.get(i);
    		Point centroid = np.copyPoint();
    		cluster.setCentroid(centroid);
    		c.add(cluster);
    	}
  
    }
	/*
	 * systemetic method for pick centroids
	 * 
	 */
	public ArrayList<Point> preCen(ArrayList<Point> p,int K){
		double min = Double.MAX_VALUE;
		ArrayList<Point> center = new ArrayList<Point>();
		ArrayList<Point> point = new ArrayList<Point>();
		point = (ArrayList<Point>) p.clone();
		
		ArrayList<Point> Am = new ArrayList<Point>();
		double distance = 0;
		int m = 0;
		int value = (int) (0.75*p.size()/K);
		Point cen = new Point();
		Point A = new Point();
		Point B = new Point();
		
		while(m < K){
		Am.clear();
		cen = new Point();
		
		for(int i = 0; i < point.size(); i++){
	
			for(int j = i + 1; j < point.size(); j++){

				distance = Point.distance(point.get(i), point.get(j));

				if(distance<min){
					min = distance;
					A = point.get(i);
					B = point.get(j);
					}
				}
			}

		Am.add(A);
		Am.add(B);
		point.remove(A);
		point.remove(B);
		for(int i = 0; i < A.getPoint().size(); i++){
			cen.addP((A.getPoint().get(i)+B.getPoint().get(i))/2);
		}
		
		while(Am.size() < value){
			min = Double.MAX_VALUE;
			
			for(int i = 0; i < point.size(); i++){


					distance = Point.distance(point.get(i), cen);

					if(distance < min){
						min = distance;
						A = point.get(i);
						}
					
				}
			Am.add(A);
			
			point.remove(A);
		}
		 
		center.add(Centroid(Am));
		m++;
		}
		
		return center;
	}
	
	
	//choose init centroid for every clusters
	public void  init(  ArrayList<Cluster> c, ArrayList<Cluster> cen) {
    
    	for (int i = 0; i < cen.size(); i++) {
    		Cluster cluster = new Cluster(i); 		
    		Point np = cen.get(i).getCentroid();
    		Point centroid = np.copyPoint();
    		cluster.setCentroid(centroid);
    		c.add(cluster);
    	}
    }
	
	//calculate the centroids for each clusters
	private ArrayList<Point> getCentroids( ArrayList<Cluster> c) {
		ArrayList<Point> centroids = new ArrayList<Point>();
	    	for(int i = 0; i < c.size(); i++) {
	    		Point aux = c.get(i).getCentroid();
	    		
	    		Point point = aux.copyPoint();
	    		centroids.add(point);
	    	}
	    	return centroids;
	    }
	
	
	//clear the clusrter's data
	 private void clearClusters(ArrayList<Cluster> c) {
	    	for(Cluster cluster : c) {
	    		cluster.clear();
	    	}
	    }
	
	//split original data set into smaller data sets
	public void createSubsets(int N){
		int i = 0;
		int count = 0;
		Random r = new Random();
		//create N empty subsets
		
		for(i = 0; i< N; i++){
		subsets.add(new ArrayList<Point>());
		}
		//the first subset is now equal the original subset.
		for(i = 0; i < points.size(); i++ ){
			subsets.get(0).add(points.get(i).copyPoint());
		}
		
		int n = points.size()/N;
		int random = 0;
		// separate data set into N of subsets.
		for(i = 1; i< N; i++){
			count = 0;
			while(count < n){
				random = r.nextInt((subsets.get(0).size()-1));
				count++;
				subsets.get(i).add(subsets.get(0).get(random));
				//subsets.get(0).remove(random);
			}	
		}
		
	}
	
	
	
	//delete duplicate part
	public void deleteRepeat(){
		double min = Double.MAX_VALUE;
		double distance = 0;	
		for(int i = 0; i < points.size(); i++ ){
			for( int j = i + 1; j < points.size(); j++ ){
				if( i != j ){
				distance = Point.distance( points.get(i), points.get(j) );
				if( distance == 0 ){
					points.remove(j);
					j--;
				}
	
				}
			}
		}	
	}
	
	
	/*
	 * map the data set into a Point ArrayList
	 * 
	 */
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

	//this method can assign the points into nearest cluster center;
	private void assignCluster( ArrayList<Point> p1, ArrayList<Cluster> c1 ) {
		// TODO Auto-generated method stub
		double min = Double.MAX_VALUE;
		int cluster = 0;                 
	    double distance = 0.0; 
	    for(Point point : p1) {
	    	min = Double.MAX_VALUE;
            for(int i = 0; i < c1.size(); i++) {
            	Cluster c = c1.get(i);
                distance = Point.distance(point, c.getCentroid());
                if(distance == 0  || distance < min){
                    min = distance;
                    cluster = i;
                }
            }
            point.setCluster(cluster);
            
            c1.get(cluster).addPoint(point.copyPoint());
        }
	}
	
	
	
	public void print(){
		
		for(int i = 0; i < points.size(); i++ ){

			System.out.println(points.get(i).toString());
		}
	}
	
	//calculate the cnetroid for every clusters.
	 private void calculateCentroids(ArrayList<Cluster> c1) {

		 for(int j = 0; j < c1.size(); j++) {
			 
	            double [] SumOfPoints = new double [pointL];
	        	ArrayList<Point> list = c1.get(j).getPoints();
	        	
	            int n_points = list.size();
	            
	            for(int q = 0; q < list.size(); q++) {
	            	for(int i = 0; i < pointL; i++){
	            		
	            		SumOfPoints[i] = SumOfPoints[i]+list.get(q).get(i);
	            	}
	            }

	            if(n_points != 0) {
	            	for(int i = 0; i < pointL; i++){

	            		SumOfPoints[i] =  SumOfPoints[i]/n_points;
	            	}
	             
	             c1.get(j).setNewCen(SumOfPoints); 
	            }
	        }	
	 }
	 
	 //calculate the centroid for a list of points
	 private Point Centroid(ArrayList<Point> p) {

		
			 	Point p1 = new Point();
	            double [] SumOfPoints = new double [pointL];
	        	
	        	
	            int n_points = p.size();
	            
	            for(int q = 0; q < p.size(); q++) {
	            	for(int i = 0; i < pointL; i++){
	            		
	            		SumOfPoints[i] =  SumOfPoints[i] + p.get(q).get(i);
	            	}
	            }

	            if(n_points != 0) {
	            	for(int i = 0; i < pointL; i++){

	            		SumOfPoints[i] =  SumOfPoints[i]/n_points;
	            	}
	            }
	            for(int i = 0; i < SumOfPoints.length; i++){
	            	p1.addP(SumOfPoints[i]);
	            }
	        return p1;
		
	 }
	
	 //calculate sse for each subclusters and return smallest cluster's id
	private int calculateMinSSE(ArrayList<ArrayList<Cluster>> subclusters){
		int num = -1;
		double totalDistance = 0;
		double min = Double.MAX_VALUE;
		for(int i = 0; i < subclusters.size(); i++){
			for(int j = 0; j < subclusters.get(i).size(); j++){
				for(Point point : subclusters.get(i).get(j).getPoints()){
					totalDistance = totalDistance+Point.distance(point, subclusters.get(i).get(j).getCentroid());
				}
			}
			
			if(totalDistance < min){
				min = totalDistance;
				num = i;
			}
			totalDistance = 0;
		}
		
		return num;
	}
	
	@Override
	public void printIntoFile() {
		// TODO Auto-generated method stub
		
	}
	

	

}
