package linearHashingOverflowWithChaining;



public class LinearHashingOverflowWithChaining {
	
	
	private HashBucketOverflowWithChaining[] hashBuckets;	// pointer to the hash buckets

	private float maxThreshold;		// max load factor threshold
	private float minThreshold;		// min load factor threshold

	private int bucketSize;		// max number of keys in each bucket
	private int keysNum;			// number of keys currently stored in the table
	private int keySpace;			// total space the hash table has for keys
	private int p;				// pointer to the next bucket to be split
	private int n;				// current number of buckets
	private int j;				// the n used for the hash function
	private int minBuckets;			// minimum number of buckets this hash table can have
    private int numberOfComparisons;
    
	private int hashFunction(int key){	// Returns a hash based on the key

		int retval;

		retval = key%this.j;
		if (retval < 0)
			retval *= -1;
		if (retval >= p){
		  //System.out.println( "Retval = " + retval);
		  return retval;
		}
		else {
			 retval = key%(2*this.j);
			 if (retval < 0)
				 retval *= -1;
			 //System.out.println( "Retval = " + retval);
		         return retval;
		}
	}

	private float loadFactor() {		// Returns the current load factor of the hash table.

		return ((float)this.keysNum)/((float)this.keySpace);
	}

	private void bucketSplit() {		// Splits the bucket pointed by p.

		int i;
		HashBucketOverflowWithChaining[] newHashBuckets;

		newHashBuckets= new HashBucketOverflowWithChaining[n+1];
		for (i = 0; i < this.n; i++){
		   newHashBuckets[i] = this.hashBuckets[i];
		}

		hashBuckets = newHashBuckets;
		hashBuckets[this.n] = new HashBucketOverflowWithChaining(this.bucketSize);
		this.keySpace += this.bucketSize;
		this.hashBuckets[this.p].splitBucket(this, 2*this.j, this.p, hashBuckets[this.n]);
		this.n++;
		if (this.n == 2*this.j) {
		  this.j = 2*this.j;
		  this.p = 0;
		}
		else {
		    this.p++;
		}
	}

	private void bucketMerge() { 		// Merges the last bucket that was split

		int i;

		HashBucketOverflowWithChaining[] newHashBuckets;
		newHashBuckets= new HashBucketOverflowWithChaining[n-1];
		for (i = 0; i < this.n-1; i++) {
		   newHashBuckets[i] = this.hashBuckets[i];
		}
		if (this.p == 0) {
		  this.j = (this.n)/2;
		  this.p = this.j-1;
		}
		else {
		  this.p--;
		}
		this.n--;
		this.keySpace -= this.bucketSize;
		this.hashBuckets[this.p].mergeBucket(this, hashBuckets[this.n]);
		hashBuckets = newHashBuckets;
	}

	public LinearHashingOverflowWithChaining(int itsBucketSize, int initPages) { 	// Constructor.

		int i;

		bucketSize = itsBucketSize;
		keysNum = 0;
		p = 0;
		n = initPages;
		j = initPages;
		minBuckets = initPages;
		keySpace = n*bucketSize;
		maxThreshold = (float)0.8;
		minThreshold = (float)0.7;

		if ((bucketSize == 0) || (n == 0)) {
		  System.out.println("error: space for the table cannot be 0");
		  System.exit(1);
		}
		hashBuckets = new HashBucketOverflowWithChaining[n];
		for (i = 0; i < n; i++) {
		   hashBuckets[i] = new HashBucketOverflowWithChaining(bucketSize);
	}
}

	public int getBucketSize() {return bucketSize;}
	public int getKeysNum() {return keysNum;}
	public int getKeySpace() {return keySpace;}
	public void setBucketSize(int size) {bucketSize = size;}
	public void setKeysNum(int num) {keysNum = num;}
	public void setKeySpace(int space) {keySpace = space;}

	public void insertKey(int key) {	// Insert a new key.

		//System.out.println( "hashBuckets[" + this.hashFunction(key) + "] =  " + key);
		this.hashBuckets[this.hashFunction(key)].insertKey(key, this);
		if (this.loadFactor() > maxThreshold || this.IsLengthChainOfOverflowBuckets()){
		  //System.out.println("loadFactor = " + this.loadFactor() );
		  this.bucketSplit();
		  
		  //System.out.println("BucketSplit++++++");
		}
	}
	
	public boolean IsLengthChainOfOverflowBuckets() {
		
		
		for(int i = 0;i < this.hashBuckets.length;i++) {
			
			if(this.hashBuckets[i].getOverflowBucket() != null && this.hashBuckets[i].getOverflowBucket().getOverflowBucket() != null && this.hashBuckets[i].getOverflowBucket().getOverflowBucket().getOverflowBucket() != null) {
				
				//if(this.hashBuckets[i].getOverflowBucket().getOverflowBucket().numKeys() == this.bucketSize) {
				return true;
				//}
			}
		}
		
		return false;
		
	}

	/*public void deleteKey(int key) {	// Delete a key.

		this.hashBuckets[this.hashFunction(key)].deleteKey(key, this);
		if (this.loadFactor() > maxThreshold){
		  this.bucketSplit();
		}
		else if ((this.loadFactor() < minThreshold) && (this.n > this.minBuckets)){
			 this.bucketMerge();
		}
	}*/
	

	public boolean searchKey(int key) {		// Search for a key.

		return this.hashBuckets[this.hashFunction(key)].searchKey(key, this);
	}

	public void printHash() {

		int i;

		for (i = 0; i < this.n; i++) {
		   System.out.println("Bucket[" + i + "]");
		   this.hashBuckets[i].printBucket(this.bucketSize);
		}
	}

	public int getNumberOfComparisons() {
		return numberOfComparisons;
	}

	public void setNumberOfComparisons(int numberOfComparisons) {
		this.numberOfComparisons = numberOfComparisons;
	}
	
	

}
