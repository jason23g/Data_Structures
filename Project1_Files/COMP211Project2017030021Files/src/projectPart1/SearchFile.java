package projectPart1;

import java.io.IOException;
import java.util.Random;

import sk.domes201820191.utils.Utils;

public class SearchFile {
	
	final static int numberOfIntegersInBuffer  = 1000;
	final static int totalNumbers = 100000;
	final static String Sortedfilename = "FinalSorting_Jason_2017030021.dat";
	final static String Unsortedfilename = "jason_2017030021.dat";
	final static int SearchesIntegersFile = 40;
	
	
	public static void main(String[] args) {
		
		int SearchesElementFile[] = new int[SearchesIntegersFile/2];
		
		int SumAccessesDiskSequentialSearch = 0;
		int SumAccessesDiskBinarySearch = 0;	
		
		// Part 3 for the sequential search  and binary search
			try {
				//This searching is for successful search
				SearchesElementFile = Utils.readIntArrayFromDisk(Unsortedfilename, 0, SearchesIntegersFile/2);
				SumAccessesDiskSequentialSearch++;
				for(int i = 0; i < SearchesElementFile.length  - 1;i++) {
					
					SumAccessesDiskSequentialSearch += sequentialSearchFile(Sortedfilename,SearchesElementFile[i]);
					SumAccessesDiskBinarySearch +=  binarySearchFile(Sortedfilename,SearchesElementFile[i]);
					
					
				}
				System.out.println("For successful sequential search needed "+(SumAccessesDiskSequentialSearch/(SearchesIntegersFile/2))+" accesses in disk in average");
				System.out.println("For successful binary search needed "+(SumAccessesDiskBinarySearch/(SearchesIntegersFile/2))+" accesses in disk in average");
				
				
				//This searching is for unsuccessful search
				SumAccessesDiskSequentialSearch = 0;
				SumAccessesDiskBinarySearch = 0;
				//With this way i will check for integers which there are not in my file because it is out 
				//of the range of my sorted file 
				for(int i = 0;i < SearchesIntegersFile/2;i++) {
					SearchesElementFile[i] = randInt(-400000,0);
				}
				
				
				
                for(int i = 0; i < SearchesElementFile.length;i++) {
					
                	SumAccessesDiskSequentialSearch += sequentialSearchFile(Sortedfilename,SearchesElementFile[i]);
                	SumAccessesDiskBinarySearch += binarySearchFile(Sortedfilename,SearchesElementFile[i]);
					
					
				}
				
                System.out.println("For unsuccessful sequential search needed "+(SumAccessesDiskSequentialSearch/(SearchesIntegersFile/2))+" accesses in disk in average");
                System.out.println("For unsuccessful binary search needed "+(SumAccessesDiskBinarySearch/(SearchesIntegersFile/2))+" accesses in disk in average");
                
                
                
                
                
                
                
                
                
				
			} catch (IOException e) {
				
				e.printStackTrace();
			}

		
		
		
		
		

	}
	
	//this is the sequential search of the file
	public static int sequentialSearchFile(String fileName,int key) {
		
		
		int integersBuffer[] = new int[numberOfIntegersInBuffer];
		int searchingElement = 0;
		int countAccessesDisk = 0;
		try {
		for(int i = 0;i < (totalNumbers/numberOfIntegersInBuffer);i++) {
			
			
				integersBuffer = Utils.readIntArrayFromDisk(fileName, i*numberOfIntegersInBuffer, numberOfIntegersInBuffer);
				countAccessesDisk++;// i count the accesses which they have been made in the disk
				searchingElement = linearSearch(integersBuffer, key);
				
				if(searchingElement == 1) {
					i = 100;
					System.out.println("The element "+key+" which exists in the file "+fileName);
					
				}
				
				
				
			}
		}
		catch (IOException e) {
				
			}
		
		if(searchingElement == 1) {
			System.out.println("The sequential searching was successful");
		}
		else {
			System.out.println("The sequential searching was unsuccessful");
		}
		
		System.out.println("The accesses which they needed in order to find the element "+key+" with sequential search were "+countAccessesDisk);
		
		return countAccessesDisk;
	}
	
	public static int binarySearchFile(String fileName,int key) {
		
		int integersBuffer[] = new int[numberOfIntegersInBuffer];
		int searchingElement = 0;
		int countAccessesDisk = 0;
		int highBoundOfFile  = totalNumbers;
		int lowBoundOfFile = 0;
		int filePointerPosition = (highBoundOfFile - lowBoundOfFile) /2 ;// I use this pointer in order to know from which position of the file to read
		
		
		try {
			
			while(filePointerPosition != 0) {
			
				if((filePointerPosition % numberOfIntegersInBuffer) == 0) {
			integersBuffer = Utils.readIntArrayFromDisk(fileName,filePointerPosition, numberOfIntegersInBuffer);
			
			countAccessesDisk++;
			searchingElement = binarySearch(integersBuffer,key);
			
			if(searchingElement == 1) {
				filePointerPosition = 0;
				System.out.println("The element "+ key +" which exists in the "+fileName);
				
			}
			else if(key < integersBuffer[0]) {
				highBoundOfFile = filePointerPosition;
				filePointerPosition =  (highBoundOfFile + lowBoundOfFile)/2;
				if((filePointerPosition%numberOfIntegersInBuffer) != 0 && filePointerPosition > (numberOfIntegersInBuffer/2)) {
					filePointerPosition += numberOfIntegersInBuffer/2;
					
					}
				
				
				 
				}
			else if(key > integersBuffer[numberOfIntegersInBuffer-1]) {
				lowBoundOfFile = filePointerPosition;
				
				filePointerPosition =  (highBoundOfFile + lowBoundOfFile)/2;
				if((filePointerPosition%numberOfIntegersInBuffer) != 0 && filePointerPosition > (numberOfIntegersInBuffer/2)) {
					filePointerPosition += numberOfIntegersInBuffer/2;
					
					}
				
				
				
			}
			
			}else {
				filePointerPosition = 0;
				
			}
			
			}
			
			
			if(searchingElement == 1) {
				System.out.println("The binary searching was successful");
			}
			else {
				System.out.println("The binary searching was unsuccessful");
			}
			
			
		}catch(IOException e ) {
			
		}
		
		System.out.println("The accesses which they needed in order to find the element "+key+" with binary search were "+countAccessesDisk);
		return countAccessesDisk;
	}
	
	
	
	
	//The code of linearSearch is from http://www.codenuclear.com/linear-or-sequential-search-algorithm/
	public static int linearSearch(int[] inputArray,int key)
	{
 
		for(int i=0;i<inputArray.length;i++)
		{
            if(inputArray[i] == key)
            {
                return 1;
            }
        }
        return -1;
	}
	
	
	//The code of binary search is from https://www.geeksforgeeks.org/binary-search/
	public static int binarySearch(int arr[], int x) 
    { 
        int l = 0, r = arr.length - 1; 
        while (l <= r) { 
            int m = l + (r - l) / 2; 
  
            // Check if x is present at mid 
            if (arr[m] == x) 
                return 1; 
  
            // If x greater, ignore left half 
            if (arr[m] < x) 
                l = m + 1; 
  
            // If x is smaller, ignore right half 
            else
                r = m - 1; 
        } 
  
        // if we reach here, then element was 
        // not present 
        return -1; 
    } 
	
	public static int randInt(int min, int max) {

	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}

}
