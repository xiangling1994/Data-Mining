package entities;

public class Constrains {

	private String filenameTrain;	// The file name of training dataset
	private String filenameTest;	// The file name of testing dataset
	private String target;			// The target attribute
	private int targetIndex;		// The index of target attribute in od's title
	private int NumOfCluster;
	
	
	public int getNumOfCluster() {
		return NumOfCluster;
	}

	public void setNumOfCluster(int numOfCluster) {
		NumOfCluster = numOfCluster;
	}

	public Constrains() {
		
		this.filenameTrain = "";
		this.filenameTest = "";
		this.target = "";
		this.targetIndex = 0;
		NumOfCluster=0;
	}

	public String getFilenameTrain() {
		return filenameTrain;
	}

	public void setFilenameTrain(String fn) {
		this.filenameTrain = fn;
	}
	
	public String getFilenameTest() {
		return filenameTest;
	}

	public void setFilenameTest(String fn) {
		this.filenameTest = fn;
	}
	
	public String getTarget() {
		return target;
	}

	public void setTarget(String ta) {
		this.target = ta;
	}
	
	public int getTargetIndex() {
		return targetIndex;
	}
	
	public void setTargetIndex(int ti) {
		this.targetIndex = ti;
	}
	
}
