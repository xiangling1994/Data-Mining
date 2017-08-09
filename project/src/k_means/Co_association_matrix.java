
package k_means;

import java.util.ArrayList;

public class Co_association_matrix {

	
	private int [][] matrix;
	private int [] count;
	private int [] max;
	
	//inital a matrix
	public Co_association_matrix(int x){
		matrix = new int [x][x];
		count = new int [x];
		for(int i = 0; i < x ; i++){
			for(int j = 0; j < x; j++){
				matrix[i][j] = 0;
			}
		}
	}
	//write 0 into matrix
	private void countClear(){
		for(int i = 0; i < count.length;i++){
			count[i] = 0;
		}
	}
	
	/*
	 * scan the new input cluster and points data set 
	 */
	public void scan( ArrayList<Cluster> c , ArrayList<Point> p){
		
		for(int i=0; i < p.size(); i++){	
				count[i] = p.get(i).getCluster();
		}
		for(int i = 0; i < matrix.length; i++){
			for(int j = i + 1; j < matrix.length; j++){
				if(count[i] == count[j]){
					matrix[i][j]++;
					matrix[j][i]++;
				}
			}
		}
	}
	
	//print the matrix
	public void printCo_matrix(){
		for(int i = 0; i < count.length;i ++)
		System.out.print(count[i] + " ");
		System.out.println();
		System.out.print("  ");
		for(int i = 0; i < matrix.length; i++){
			System.out.print(i + "\t");
		}
		System.out.println();
		for(int i = 0; i < matrix.length; i++){
			System.out.print(i + " ");
			for(int j = 0; j < matrix.length; j++){
				
				if(j <= i){
					System.out.print("\t");
				}else
				System.out.print(matrix[i][j] + "\t");
			}
			System.out.println();
		}
	}
	
	

	public int[][] getMatrix() {
		return matrix;
	}
	
}
