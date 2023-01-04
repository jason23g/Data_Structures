package main;

import java.util.Random;

import binarySearchTree.BST;
import linearHashing.LinearHashing;
import linearHashingOverflowWithChaining.LinearHashingOverflowWithChaining;
import multiwaySearchTree.BTree;
import separateChainingHashing.HashChain;
import java.text.DecimalFormat;


public class Main {
	
	final static int N = (int) Math.pow(7, 10);
    final static int numberOfsearches = 30;
	
	
	public static void main(String[] args) {
		
	int[] M = new int[6];
		M[0] = 1000;
		M[1] = 10000;
		M[2] = 30000;
		M[3] = 50000;
		M[4] = 70000;
		M[5] = 100000;
		
		
		DecimalFormat format = new DecimalFormat("##.00");
		
		for(int i = 0; i< 6;i++) {
			
			double averageComparisonsBST = 0;
			double averageComparisonsHashChain = 0;
			double averageComparisonsLinHash = 0;
			double averageComparisonsLinHashOvCh = 0;
			double averageComparisonsBTree = 0;
			
			BST bst = new BST();
			HashChain hashSeparateChaining = new HashChain(1000);
			LinearHashing linHash = new LinearHashing(5,100);
			LinearHashingOverflowWithChaining linHashOvCh = new LinearHashingOverflowWithChaining(5,100);
			BTree bTree = new BTree();
			
			
			int keys[] = new int[M[i]];
			//int keysUnsuccess[] = new int[M[i]];
			
			
		   keys = generateKeysForInsertion(M[i],0,N);
		   insertKeysInDataStructures(bst,hashSeparateChaining, linHash,linHashOvCh, bTree,  M[i],keys);
			
			
			
			//keysUnsuccess = generateKeysForUnsuccesssearch(M[i]);
			
			int key;
			int key1;
			
			for(int j = 0;j < numberOfsearches;j++) {
				key = randInt(0,N);
				key1 = randInt(0,M[i] - 1);
				
				bst.setNumberOfComparisons(0);
				//bst.search(keysUnsuccess[key1]);
				bst.search(keys[key1]);
				//bst.search(key);
				averageComparisonsBST += bst.getNumberOfComparisons();
				
				hashSeparateChaining.setNumberOfComparisons(0);
				hashSeparateChaining.find(keys[key1]);
				//hashSeparateChaining.find(keysUnsuccess[key1]);
				//hashSeparateChaining.find(key);
				averageComparisonsHashChain += hashSeparateChaining.getNumberOfComparisons();
				
				linHash.setNumberOfComparisons(0);
				linHash.searchKey(keys[key1]);
				//linHash.searchKey(keysUnsuccess[key1]);
				//linHash.searchKey(key);
				averageComparisonsLinHash += linHash.getNumberOfComparisons();
				
				linHashOvCh.setNumberOfComparisons(0);
				linHashOvCh.searchKey(keys[key1]);
				//linHashOvCh.searchKey(key);
				averageComparisonsLinHashOvCh += linHashOvCh.getNumberOfComparisons();
				
				
				bTree.setNumberOfComparisons(0);
				bTree.search(keys[key1]);
				//bTree.search(key);
				averageComparisonsBTree += bTree.getNumberOfComparisons();
			}
		
			System.out.println("===========================================================================================");
			System.out.println("For "+M[i]+" keys :");
			System.out.println("Binary Search Tree average comparisons :"+format.format(averageComparisonsBST/numberOfsearches));
			System.out.println("Separate Chaining Hashing average comparisons :"+format.format(averageComparisonsHashChain/numberOfsearches));
			System.out.println("Linear Hashing average comparisons :"+format.format(averageComparisonsLinHash/numberOfsearches));
			System.out.println("Linear Hashing with chain for overflows over 2 pages average comparisons :"+format.format(averageComparisonsLinHashOvCh/numberOfsearches));
			System.out.println("BTree average comparisons :"+format.format(averageComparisonsBTree/numberOfsearches));
			
			
		}

		
		
	}

	
	public static void insertKeysInDataStructures(BST bst,HashChain hashSeparateChaining,LinearHashing linHash,LinearHashingOverflowWithChaining linHashOvCh,BTree bTree, int numberOfKeys,int keys[]) {
		
		for(int i = 0;i <numberOfKeys;i++) {
			
			bst.insert(keys[i]);
			hashSeparateChaining.insert(keys[i]);
			linHash.insertKey(keys[i]);
			linHashOvCh.insertKey(keys[i]);
			bTree.add(keys[i], keys[i]);
		}
		
		
	}
	
	
	public static int[] generateKeysForInsertion(int generatingNumbers,int min, int max) {
		int key[] = new int[generatingNumbers];
		
		for(int i =  0;i < generatingNumbers;i++) {
			
			key[i] = randInt(min,max);
			
		}
		
		return key;
	}
	
	
	
	public static int[] generateKeysForUnsuccesssearch(int arraySize) {
		
		int keys[] = new int[arraySize];
		
		for(int i = 0;i <arraySize;i++) {
			keys[i] = randInt(10000001,Integer.MAX_VALUE);
		}
		
		return keys;
	}
	
	
	
	private static int randInt(int min,int max) {
		Random rand = new Random();
		
	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
	
	
	
	
}
