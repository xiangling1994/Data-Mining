package main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import entities.Constrains;
import entities.Databases;
import k_means.GenKmeans;
import k_means.Enhancekmeans;
import pre_processes.Initialization;
import utility.Covariance;
import utility.DatabaseReader;
import utility.SeperateDatabase;

public class ProjectController {
		private String NameOfDB1;
		private List<List<String>> DB1;
		private List<List<String>> mapTrain;
		private Databases db;
		private Constrains cons;
		private int numberOfCluster;
		public ProjectController() throws IOException {
			
			NameOfDB1 = null;
			DB1 = null;
			db = new Databases();
			cons = new Constrains();
			mapTrain=new ArrayList<List<String>>();
			
			readDataCons();
			
			run_Kmeans();
		}
		
		private void run_Kmeans() throws IOException {
			// TODO Auto-generated method stub
			
			//K_means2 k=new K_means2();
			//GenKmeans k=new GenKmeans();
			Enhancekmeans k=new Enhancekmeans();
			//k k=new k();
			k.getData(db, cons);
			k.run();
			//k.print();
			
		}

		private void readDataCons() throws IOException {
			
			
			Scanner scan = new Scanner(System.in);
			DatabaseReader reader = new DatabaseReader();
			
			// Input filename of training dataset
			System.out.print("Please input filename of  dataset: ");
			NameOfDB1 = "./" + scan.nextLine();
			//NameOfDB1="./tea.txt";
			//NameOfDB1="./dataset_ellipse3";
			//NameOfDB1="./test4";
			reader.scan(NameOfDB1);
			DB1 = reader.getDB();
			System.out.print("Please input k for k means ");
			try {
				numberOfCluster =scan.nextInt();
			} catch (InputMismatchException nfe) {
				System.out.println("Incorrect interger format.");
				System.exit(1);
			}
			if(numberOfCluster<=0){
				System.out.println("cluster number has to larger then 0");
				System.exit(1);
			}
			//numberOfCluster=2;
			Initialization.initialize(NameOfDB1, DB1, mapTrain, db, cons,false);
			cons.setNumOfCluster(numberOfCluster);
			System.out.println();
			
		}
	}
