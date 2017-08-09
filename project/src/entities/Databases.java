package entities;

import java.util.ArrayList;
import java.util.List;

public class Databases {
    
	private List<String> titleTrain;		// The title of original training database
	private List<String> titleTest;			// The title of original testing database
    private List<List<String>> odTrain;		// Original training database
	private List<List<String>> odTrainMap;	// Original training database mapping
	private List<List<List<Integer>>> odTrainMapNbc;
	private List<List<Integer>> odTrainMapNbcSum;
	private List<List<String>> odTest;		// Original testing database
	private List<List<String>> odTestMap;

	private int rowCount;					// The row count of original training database

	public Databases() {
		
		this.titleTrain = new ArrayList<String>();	
		this.titleTest = new ArrayList<String>();
		this.odTrain = new ArrayList<List<String>>();	
		this.odTrainMap = new ArrayList<List<String>>();	
		this.odTrainMapNbc = new ArrayList<List<List<Integer>>>();
		this.odTest = new ArrayList<List<String>>();
		this.rowCount = 0;
	}

	public List<List<String>> getOdTestMap() {
		return odTestMap;
	}

	public void setOdTestMap(List<List<String>> odTestMap) {
		this.odTestMap = odTestMap;
	}

	public void addRowIntoOdTrain(List<String> newRow) {
		this.odTrain.add(newRow);
	}

	public void addRowIntoOdTrainMap(List<String> newRow) {
		this.odTrainMap.add(newRow);
	}
	
	public void addRowIntoOdTest(List<String> newRow) {
		this.odTest.add(newRow);
	}

	public int getRowCount() {
		return this.rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	public List<String> getTitleTrain() {
		return this.titleTrain;
	}

	public void setTitleTrain(List<String> title) {
		this.titleTrain = title;
	}
	
	public List<String> getTitleTest() {
		return this.titleTest;
	}

	public void setTitleTest(List<String> title) {
		this.titleTest = title;
	}

	public List<List<String>> getOdTrain() {
		return this.odTrain;
	}

	public void setOdTrain(List<List<String>> odTrain) {
		this.odTrain = odTrain;
	}
	
	public List<List<String>> getOdTest() {
		return odTest;
	}

	public void setOdTest(List<List<String>> odTest) {
		this.odTest = odTest;
	}

	public List<List<String>> getOdTrainMap() {
		return this.odTrainMap;
	}

	public void setOdTrainMap(List<List<String>> odMap) {
		this.odTrainMap = odMap;
	}
	
	public List<List<List<Integer>>> getOdTrainMapNbc() {
		return this.odTrainMapNbc;
	}

	public void setOdTrainMapNbc(List<List<List<Integer>>> odMapNBC) {
		this.odTrainMapNbc = odMapNBC;
	}
	
	public List<List<Integer>> getOdTrainMapNbcSum() {
		return this.odTrainMapNbcSum;
	}

	public void setOdTrainMapNbcSum(List<List<Integer>> odMapNbcS) {
		this.odTrainMapNbcSum = odMapNbcS;
	}

}
