package separateChainingHashing;

public class HashChain {
	
	
	 private SortedList[] hashArray; 
	 private int numberOfComparisons;

	  private int arraySize;

	  public HashChain(int size) {
	    arraySize = size;
	    hashArray = new SortedList[arraySize];
	    for (int i = 0; i < arraySize; i++)
	      hashArray[i] = new SortedList(); 
	  }

	  public void displayTable() {
	    for (int j = 0; j < arraySize; j++) {
	      System.out.print(j + ". "); 
	      hashArray[j].displayList(); 
	    }
	  }

	  public int hashFunc(int key) {
	    return key % arraySize;
	  }

	  public void insert(int key) {
		  
		  this.insert(new Link(key));
	  }
	  
	  
	  private void insert(Link theLink) {
	    int key = theLink.getKey();
	    int hashVal = hashFunc(key); 
	    hashArray[hashVal].insert(theLink); 
	  }

	  public void delete(int key) {
	    int hashVal = hashFunc(key); // hash the key
	    hashArray[hashVal].delete(key); 
	  }

	  public Link find(int key) {
		  //numberOfComparisons = 0;
	    int hashVal = hashFunc(key); // hash the key
	    Link theLink = hashArray[hashVal].find(key);// get link
	    numberOfComparisons = hashArray[hashVal].getNumberOfComparisons();
	   hashArray[hashVal].setNumberOfComparisons(0);
	    return theLink;
	  }

	public int getNumberOfComparisons() {
		return numberOfComparisons;
	}

	public void setNumberOfComparisons(int numberOfComparisons) {
		this.numberOfComparisons = numberOfComparisons;
	}
	  
	  
	  

}
