package main;

import java.util.Random;

import kdTree.KdTree;
import prQuadTree.PrQuadTree;

public class Main {
	
	
	final static int N = (int) Math.pow(2,16);
	final static int numberOfSearches = 100;
	//final static int numbersOfData = 100000;
	

	public static void main(String[] args) {
		
		int M[] = new int[6];
		M[0] = 1000;
		M[1] = 10000;
		M[2] = 30000;
		M[3] = 50000;
		M[4] = 70000;
		M[5] = 100000;
		
		for(int i = 0;i<6;i++) {
			
			
		int keyX[] = new int[M[i]];
		int keyY[] = new int[M[i]];
		int keyXUnsuccess[] = new int[M[i]];
		int keyYUnsuccess[] = new int[M[i]];
		
		
		//I generate random numbers in order to insert them in the quad tree and in a kdTree and then to use them for a successful search 
				keyX = generateNumbersForInsertion(M[i],0,N - 1);
				keyY = generateNumbersForInsertion(M[i],0,N - 1);
		//I generate numbers which do not exist in the tree in order to use them for unsuccessful search 
				keyXUnsuccess = generateNumbersForUnsuccessSearch(keyX,keyY,M[i],0,N - 1);
				keyYUnsuccess = generateNumbersForUnsuccessSearch(keyX,keyY,M[i],0,N - 1);
		//---------------------------KD TREE------------------------------------------------------------------------------------------------------
		         //I create an instance of a kdTree
		         KdTree kdTree = new KdTree();
		
		        //i insert numbers in the kdTree proportionately with the numbersOfData
				insertElementsInKdTree(M[i],kdTree,keyX,keyY);
				//I search numbers which they exist in the kdTree and i count the average number of comparisons
				searchElementInKdTree(kdTree,numberOfSearches,keyX,keyY,"successful",M[i]);
				searchElementInKdTree(kdTree,numberOfSearches,keyXUnsuccess,keyYUnsuccess,"unsuccessful",M[i]);
		
		
		
		//---------------------------PR QUADTREE--------------------------------------------------------------------------------------------------
		//I create an instance of a pr quadTree
		PrQuadTree prQuadTree = new PrQuadTree();
		
		
		//i insert numbers in the pr quadTree proportionately with the numbersOfData
		insertElementsInQuadTree(M[i],prQuadTree,keyX,keyY);
		//I search numbers which they exist in the quad tree and i count the average number of comparisons
		searchElementInQuadTree(prQuadTree,numberOfSearches,keyX,keyY,"successful",M[i]);
		searchElementInQuadTree(prQuadTree,numberOfSearches,keyXUnsuccess,keyYUnsuccess,"unsuccessful",M[i]);
		System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------");
		}	
		
	}
	
	public static int[] generateNumbersForInsertion(int generatingNumbers,int min, int max) {
		int key[] = new int[generatingNumbers];
		
		for(int i = 0;i < generatingNumbers;i++) {
			
			key[i] = randInt(min,max);
			
		}
		
		return key;
	}
	
	// A method to generate random numbers which they do not exist in the quad tree
   // for this purpose i use the two arrays which they include the elements of the quad tree in order not to include them
	public static int[] generateNumbersForUnsuccessSearch(int keyX[] ,int keyY[],int generatingNumbers,int min, int max) {
		
       int key[] = new int[generatingNumbers];
       int tmp;
       
       
		for(int i = 0;i < generatingNumbers;i++) {
			tmp = randInt(min,max);

			if(keyX[i] != tmp || keyY[i] != tmp) 
			{
			key[i] = tmp;
			
			}
		}
		
		return key;
		
		
	}
	
	// A method to search an element in a quad tree
	public static void  searchElementInQuadTree(PrQuadTree prQuadTree, int numberOfSearches,int keyX[],int keyY[],String type,int M) {
	
		double averageDepth = 0;
		int offset;
		for(int i = 0;i <numberOfSearches ;i++) {
			offset = randInt(0,M-2);
			prQuadTree.setDepth(0);
		    prQuadTree.searchElementExistance(keyX[offset], keyY[offset], prQuadTree.getRoot(), 0, N);// the offset  in the array i use it in order to have better distribution in the average comparisons in searching
		    //System.out.println("For the "+type+" search of the ("+keyX[offset]+","+ keyY[offset]+") is needed "+prQuadTree.getDepth());
		    averageDepth += prQuadTree.getDepth();
		}
		
		System.out.println("The average number for data :"+M+" of comparisons which is needed in order to search the pr quad tree is for "+type+" search "+(averageDepth/numberOfSearches));
	}
	
	// A method to insert an element in a quad tree
	public static void insertElementsInQuadTree(int M,PrQuadTree prQuadTree,int keyX[],int keyY[]) {

		for(int i = 0;i < M;i++) {
			
			prQuadTree.insertElementInTree(keyX[i], keyY[i], 0, N);
		}
		
		
	}
	// A method to insert an element in a kdTree
	public static void insertElementsInKdTree(int M,KdTree kdTree,int keyX[], int keyY[])  {
		for(int i = 0;i < M;i++) {
			kdTree.insertElementInKdTree(keyX[i], keyY[i]);
		}
	}
	
	// A method to search an element in a kdTree
	public static void searchElementInKdTree(KdTree kdTree, int numberOfSearches,int keyX[],int keyY[],String type,int M) {
		
         double averageDepth = 0;
		int offset;
		for(int i = 0;i <numberOfSearches ;i++) {
			offset = randInt(0,M-2);
			kdTree.setDepth(0);
			kdTree.searchElementExistance(keyX[offset], keyY[offset], kdTree.getRoot());// the offset  in the array i use it in order to have better distribution in the average comparisons in searching
		   // System.out.println("For the "+type+" search of the ("+keyX[offset]+","+ keyY[offset]+") is needed "+kdTree.getDepth());
		    averageDepth += kdTree.getDepth();
		    
		}
		
		System.out.println("The average number for data :"+M+" of comparisons which is needed in order to search the whole kd tree is for "+type+" search "+(averageDepth/numberOfSearches));
		
		
		
	}
	
	
	//A method to generate random number in a given range 
	private static int randInt(int min,int max) {
		Random rand = new Random();
		
	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}

}
