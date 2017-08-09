package interfaces;

import java.io.IOException;

import entities.Constrains;
import entities.Databases;

public interface algorithm_interface {

	public void getData(Databases db,Constrains cons);
	public void run();
	public SaveFile printTestingResult();
	public SaveFile printOutput();
}
