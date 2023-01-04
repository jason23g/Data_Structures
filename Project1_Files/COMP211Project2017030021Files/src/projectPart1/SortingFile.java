package projectPart1;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import sk.domes201820191.utils.Utils;

public class SortingFile {
	
	
	public static void main(String[] args) throws IOException{
		
		
		
		final int numberOfIntegersInBuffer  = 1000;
		final int numberOfFiles = 10; 
		final int numberOfIntegersInFile = 10000;

         
		int[] IntegersBuf = new int[numberOfIntegersInBuffer];
		int[]  buffer = new int[numberOfIntegersInFile];
		
		
		
		
		int countAccessesDisk = 0;//it will count the total accesses in the disk
		 int k = 0;
		while(k< numberOfFiles) {
		
		for(int i = 0;i < (numberOfIntegersInFile/numberOfIntegersInBuffer);i++) {
			IntegersBuf = Utils.readIntArrayFromDisk("jason_2017030021.dat", i*numberOfIntegersInBuffer+k*numberOfIntegersInFile, numberOfIntegersInBuffer);
			 countAccessesDisk++;
			// 2 is for the number of the integers in the IntegersBuf and 10 is for  
			//the numbers which reads from the file and writes them in different file sorted
			for(int j = 0;j<numberOfIntegersInBuffer;j++) {
			buffer[i*numberOfIntegersInBuffer+j] = IntegersBuf[j];
			}
			
			
		}
		

		quickSort(buffer,0,buffer.length-1);
		
		
		for(int i = 0;i<(numberOfIntegersInFile/numberOfIntegersInBuffer);i++) {
			
			for(int j = 0; j < numberOfIntegersInBuffer;j++) {
				IntegersBuf[j] =buffer[i*numberOfIntegersInBuffer+j];
			}
			Utils.writeIntArrayToDisk("Sorting"+k+"_jason_2017030021.dat", i*numberOfIntegersInBuffer,IntegersBuf);
			 countAccessesDisk++;
		}
		
		k++;
		
		}
		
		
		
		
		//------------------------------------------------------------------------------------------
		//Second Part
		
		int[] IntegersBuffer = new int[numberOfIntegersInBuffer];
	
        int[][] ListOfBuffers = new int[numberOfFiles+1][numberOfIntegersInBuffer];
        
        
		
		
		int b =0;
		int n1 = 0;	
		
		//with this way i initialize my buffers
		for(int i = 0;i < numberOfFiles;i++) {
			
			
			
				IntegersBuffer =  Utils.readIntArrayFromDisk("Sorting"+i+"_jason_2017030021.dat",0,numberOfIntegersInBuffer);// i store my data termporally in
				countAccessesDisk++;                                                                        //	a 1d array			
			 for(int j = 0;j<numberOfIntegersInBuffer;j++) {
			  ListOfBuffers[i][j] = IntegersBuffer[j];//i transfer my data in the 2d array(buffer)
			 }
			 
			}
		
		
		
		int minElement = Integer.MAX_VALUE;
		int fullBuffer = 0;// a helping integer to check when my array is full
		int arrayHelpBuffer[] = new int[numberOfFiles];//it will help me to check i a buffer has ended
	    int secondHelpBuffer[] = new int[numberOfFiles];
	    
		//i initialize my helpingBuffers to zero
		for(int c = 0;c < numberOfFiles;c++) {
			arrayHelpBuffer[c] = 0;
		    secondHelpBuffer[c] = 1;
		}
		
		while(n1 < numberOfFiles) {
		while(b < (numberOfIntegersInFile/numberOfIntegersInBuffer)) {
		
		for(int a = 0;a <numberOfIntegersInBuffer;a++) {
			
			minElement = searchMinElement (ListOfBuffers,arrayHelpBuffer);
			ListOfBuffers[numberOfFiles][a] = minElement;
			fullBuffer++;
			
			//minElement = 100000;
		for(int j = 0;j < numberOfFiles;j++) {
			
			
			if(minElement == ListOfBuffers[j][arrayHelpBuffer[j]]) {
				arrayHelpBuffer[j]++;// i  move forward a pointer in the buffer that i have taken the smallest element
			}
			//when a buffer has been taken all his elements then i refill proportionately to his file
			if(arrayHelpBuffer[j] == numberOfIntegersInBuffer) {
				arrayHelpBuffer[j] = 0;
				
				if(secondHelpBuffer[j] == numberOfFiles) {
					File file = new File("Sorting"+j+"_jason_2017030021.dat");
					if(file.exists()) {
					file.deleteOnExit();
					System.out.println("Sorting"+j+"_jason_2017030021.dat has been deleted "+ file.delete());
					
					
					for(int d = 0;d<numberOfIntegersInBuffer;d++) {
						 ListOfBuffers[j][d] = Integer.MAX_VALUE;
						 } 
					}
					
				}
				
				if(secondHelpBuffer[j] < numberOfFiles) {// i check if the file has other elements left
					File file = new File("Sorting"+j+"_jason_2017030021.dat");
					if(file.exists()) {
					IntegersBuffer =  Utils.readIntArrayFromDisk("Sorting"+j+"_jason_2017030021.dat",(secondHelpBuffer[j])*numberOfIntegersInBuffer, 
						numberOfIntegersInBuffer);
					
				for(int d = 0;d<numberOfIntegersInBuffer;d++) {
					  ListOfBuffers[j][d] = IntegersBuffer[d];//i transfer my data in the 2d array(buffer)
					 } 
				secondHelpBuffer[j]++;
				countAccessesDisk++;
					}
			}
				
				
				
			}
			
			
			
			
			}
		
		// when the buffer is full then i write the array in the disk
		if(fullBuffer == numberOfIntegersInBuffer) {
			 for(int j = 0;j<numberOfIntegersInBuffer;j++) {
				   IntegersBuffer[j] =ListOfBuffers[numberOfFiles][j];//i transfer my data in the 2d array(buffer)
				 }
			
			Utils.writeIntArrayToDisk("FinalSorting_Jason_2017030021.dat", b*numberOfIntegersInBuffer+n1*numberOfIntegersInFile, IntegersBuffer);
			countAccessesDisk++;// it counts the accesses in the disk
			fullBuffer = 0;// i reset my counter to start over again
			
		}
		
		
		}
		b++;
		
		}
		
		b = 0;
		
		n1++;
		}
		
		
		System.out.println("The accesses which had been totally made are "+countAccessesDisk++);
		
		
		
		
		
		
		
		//--------------------------------------------------------------------------------------------------------
	}
	  
	//this is the method to sort my array of integers
	public static void quickSort(int arr[], int begin, int end) {
	    if (begin < end) {
	        int partitionIndex = partition(arr, begin, end);
	 
	        quickSort(arr, begin, partitionIndex-1);
	        quickSort(arr, partitionIndex+1, end);
	    }
	}
	
	private static int partition(int arr[], int begin, int end) {
	    int pivot = arr[end];
	    int i = (begin-1);
	 
	    for (int j = begin; j < end; j++) {
	        if (arr[j] <= pivot) {
	            i++;
	 
	            int swapTemp = arr[i];
	            arr[i] = arr[j];
	            arr[j] = swapTemp;
	        }
	    }
	 
	    int swapTemp = arr[i+1];
	    arr[i+1] = arr[end];
	    arr[end] = swapTemp;
	 
	    return i+1;
	}
	
	
	

	public static int searchMinElement ( int[][] ListOfBuffers,int [] helpBuffer) {
		int InitialElement  = Integer.MAX_VALUE;
		
		
		for(int i = 0;i<10;i++) {
			
			if(InitialElement > ListOfBuffers[i][helpBuffer[i]]) {
				InitialElement = ListOfBuffers[i][helpBuffer[i]];
				
		}
		}
		return InitialElement;
		
	}
	

}

