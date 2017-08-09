/**
 * @author Xiang Ling, Yaocheng Chao, Yee Mei Yeung
 * Date: 2016 
 * School: Dalhousie University
 * course: CSCI4144 Data Ming
 */


package k_means;

import java.util.ArrayList;
import java.util.List;
 
public class Cluster {
	
	private ArrayList<Point> Points;
	public Point centroid;
	public int id;
	
	//Creates a new Cluster
	public Cluster(int id) {
		this.id = id;
		this.Points = new ArrayList<Point>();
		this.centroid = null;
	}
 
	public ArrayList<Point> getPoints() {
		return Points;
	}
	
	public void addPoint(Point point) {
		Points.add(point);
	}
 
	public Point getCentroid() {
		return centroid;
	}
 
	public void setCentroid(Point centroid) {
		this.centroid = centroid;
	}
 
	public int getId() {
		return id;
	}
	
	public void clear() {
		Points.clear();
	}
	public int size(){
		return Points.size();
	}
	
	//set a new centroid for this cluster
	public void setNewCen(double[] sumOfPoints) {
		// TODO Auto-generated method stub
		centroid.getPoint().clear();
		for(int i = 0;i < sumOfPoints.length;i++){
			centroid.getPoint().add(i,sumOfPoints[i]);
		}
	}
	
	//merge two clusterA and cluster B into cluster A
	public static  void conbineCluster(Cluster A, Cluster B){
		A.getPoints().addAll(B.getPoints());
		B.clear();
	}
	
	//return a new cluster by cobine cluster A and cluster B
	public static  Cluster  getConbine(Cluster A, Cluster B){
		Cluster C = new Cluster(-1);
		C.getPoints().addAll(B.getPoints());
		C.getPoints().addAll(A.getPoints());
		return C;
	}
	
 
}