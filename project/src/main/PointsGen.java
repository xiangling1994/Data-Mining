package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class PointsGen {

	public static void main(String [] args) throws IOException{
		//roundPoint();
		squrePoint();
		
		
		
}

	private static void squrePoint() throws IOException {
		// TODO Auto-generated method stub
		double x=0;
		double y=0;
		
		double a=70;
		double b=60;
		double length=100;
		double height=20;
		int pointNum=500;
		Random random=new Random();
			PrintStream console = System.out;
		
		File file = new File("test4");
		
		FileOutputStream fos = new FileOutputStream(file, true);
		
		PrintStream ps = new PrintStream(fos);
		
		System.setOut(ps);
		
		for(int i=0;i < pointNum; i++){
			x=random.nextDouble()*(length)+(a-length/2);
			y=random.nextDouble()*(height)+(b-height/2);
			System.out.println(x+","+y);
			
		}
		
		
		System.setOut(console);
		
		ps.close();
		
		fos.close();
		
	}



	public static void roundPoint() throws IOException{
		double a=200;
		double b=-400;
		double r=50;
		double x=0;
		double y=0;
		int pointNum=1000;
		Random random=new Random();
PrintStream console = System.out;
		
		File file = new File("test3");
		
		FileOutputStream fos = new FileOutputStream(file, true);
		
		PrintStream ps = new PrintStream(fos);
		
		System.setOut(ps);
		
		
		
		
		for(int i=0;i < pointNum; i++){
			x=random.nextDouble()*(2*r)+(a-r);
			y=Math.sqrt(Math.pow(r, 2)-Math.pow((x-a), 2))+b;
			if(random.nextDouble()*10>5)
				y=2*b-y;
			System.out.println(x+","+y);
			
		}
		
		
		System.setOut(console);
		
		ps.close();
		
		fos.close();
		
	}
	}
