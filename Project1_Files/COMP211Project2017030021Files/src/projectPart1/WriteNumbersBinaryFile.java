package projectPart1;
import java.io.*;
import java.util.*;

import sk.domes201820191.utils.Utils;

public class WriteNumbersBinaryFile {

	public static void main(String[] args){
		
		final int numberOfIntegersInBuffer  = 1000;
		final int totalNumbersInfile = 100000;
	
	
	
	
	
	
	int[] array = new int[numberOfIntegersInBuffer];
	
	
	int j  = 0;
	int countAccessesDisk = 0;// i initialize my counter for the accesses in the disk
	while(j < (totalNumbersInfile)/numberOfIntegersInBuffer) {
		
	
	
	for(int i = 0; i < numberOfIntegersInBuffer;i++) {
		array[i]= randInt(1, Integer.MAX_VALUE); //random.nextInt();
		}
	
	
	try {
		Utils.writeIntArrayToDisk("jason_2017030021.dat", j*numberOfIntegersInBuffer, array);
	} catch (IOException e) {
		
		e.printStackTrace();
	}
	countAccessesDisk++;//Using a counter to count the access in my disk and i increase it by one each time i write a buffer in my disk
	j++;
	}
	

	
	
	System.out.println("In the disk has been made "+countAccessesDisk+" acceses");
}
	
	public static int randInt(int min, int max) {

	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
}